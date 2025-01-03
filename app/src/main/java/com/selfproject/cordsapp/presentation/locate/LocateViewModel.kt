package com.selfproject.cordsapp.presentation.locate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfproject.cordsapp.domain.model.Folder
import com.selfproject.cordsapp.domain.model.FolderWithPoint
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.model.coordinateModel.CoordinateSystemType
import com.selfproject.cordsapp.domain.model.coordinateModel.Elevation
import com.selfproject.cordsapp.domain.model.coordinateModel.ElevationType
import com.selfproject.cordsapp.domain.model.coordinateModel.Point
import com.selfproject.cordsapp.domain.model.coordinateModel.WGS84Coordinate
import com.selfproject.cordsapp.domain.repository.FileRepository
import com.selfproject.cordsapp.domain.repository.PointRepository
import com.selfproject.cordsapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _dynamicLayers = MutableStateFlow<MutableMap<String, LayerData>>(mutableMapOf())
    val dynamicLayers: StateFlow<Map<String, LayerData>> = _dynamicLayers

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
            is LocateScreenEvents.OnPointClick -> TODO()
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
                            _dynamicLayers.value = it.convertFolderWithPointToLayerDataMap()
                            _dynamicLayers.value = _dynamicLayers.value.apply {
                                put(
                                    Constants().defaultLayer.layerId,
                                    LayerData(
                                        Constants().defaultLayer, mutableListOf(
                                            Point(
                                                pointId = 0,
                                                CoordinateSystemType.ALL,
                                                wgs84Coords = WGS84Coordinate(12.971599, 77.594566),
                                                elevation = Elevation(
                                                    elevationType = ElevationType.ALL,
                                                    ellipsoidal = 0.0
                                                ),
                                                description = "",
                                                layer = Constants().defaultLayer,
                                                pointNumber = 0,
                                                createdOn = Date(System.currentTimeMillis()),
                                                folderId = 0
                                            )
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
            }


    }
}