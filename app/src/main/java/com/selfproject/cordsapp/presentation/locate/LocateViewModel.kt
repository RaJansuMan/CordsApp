package com.selfproject.cordsapp.presentation.locate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfproject.cordsapp.domain.model.Folder
import com.selfproject.cordsapp.domain.model.FolderWithPoint
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.repository.FileRepository
import com.selfproject.cordsapp.domain.repository.PointRepository
import com.selfproject.cordsapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.sql.Date
import javax.inject.Inject

@HiltViewModel
class LocateViewModel @Inject constructor(
    private val pointRepository: PointRepository,
    private val fileRepository: FileRepository
) :
    ViewModel() {
    var state by mutableStateOf(LocateScreenState())

    init {
        viewModelScope.launch {
            fileRepository.getFolderDetail(Constants().defaultFolderId)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    when (result) {
                        is Result.Error -> {}
                        is Result.Loading -> {}
                        is Result.Success -> result.data?.let {
                            fetchPointsForDynamicLayer()
                        } ?: createDefaultFolder()
                    }
                }

        }
    }

    fun onEvent(event: LocateScreenEvents) {
        when (event) {
            is LocateScreenEvents.OnPointClick -> {
                if (event.pointId == null) {
                    state = state.copy(clickedPoint = null)
                } else if (event.layerId == null) {
                    state.folderWithPoint?.pointsWithLayer?.values?.forEach {
                        if (it.keys.contains(event.pointId)) {
                            state = state.copy(clickedPoint = it[event.pointId])
                        }
                    }
                } else {
                    if (state.folderWithPoint?.pointsWithLayer?.get(event.layerId)
                            ?.containsKey(event.pointId) == true
                    ) {
                        state = state.copy(
                            clickedPoint = state.folderWithPoint?.pointsWithLayer?.get(event.layerId)!![event.pointId]
                        )
                    }
                }
            }

            LocateScreenEvents.OnDeletePoint -> TODO()
            LocateScreenEvents.OnLeftClick -> TODO()
            LocateScreenEvents.OnPointDetails -> TODO()
            LocateScreenEvents.OnRightClick -> TODO()
            LocateScreenEvents.OnUpClick -> TODO()
        }
    }

    private suspend fun createDefaultFolder() {
        fileRepository.addFolder(
            Folder(
                Constants().defaultFolderId,
                "Default",
                "Default folder",
                Date(System.currentTimeMillis()),
                listOf(Constants().defaultLayer)
            )
        ).collect { addFolderResult ->
            when (addFolderResult) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> fetchPointsForDynamicLayer()
            }
        }
    }

    private suspend fun fetchPointsForDynamicLayer() {
        pointRepository.getAllPoint(0)
            .flowOn(Dispatchers.IO)
            .collect { pointResult ->
                when (pointResult) {
                    is Result.Error -> {}
                    is Result.Loading -> {}
                    is Result.Success -> {
                        pointResult.data?.let {
                            state = state.copy(
                                dynamicLayers = it.convertFolderWithPointToLayerDataMap(),
                                folderWithPoint = it,
                                clickedPoint = it.pointsWithLayer.values.lastOrNull()?.values?.lastOrNull()
                            )
                        }
                    }
                }
            }


    }

    private fun FolderWithPoint.convertFolderWithPointToLayerDataMap(): MutableMap<String, LayerData> {
        val layersById = folder.layers.associateBy { it.layerId }

        return pointsWithLayer.mapValues { (layerId, pointsMap) ->
            val layer = layersById[layerId]
                ?: throw IllegalArgumentException("Layer with ID $layerId not found in folder")

            LayerData(
                layer = layer,
                points = pointsMap.values.toMutableList()
            )
        }.toMutableMap()
    }

}