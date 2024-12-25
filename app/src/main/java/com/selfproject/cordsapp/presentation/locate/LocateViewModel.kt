package com.selfproject.cordsapp.presentation.locate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfproject.cordsapp.domain.model.FolderWithPoint
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.repository.FileRepository
import com.selfproject.cordsapp.domain.repository.PointRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocateViewModel @Inject constructor(
    pointRepository: PointRepository,
    fileRepository: FileRepository
) :
    ViewModel() {

    private val _dynamicLayers = MutableStateFlow<Map<String, LayerData>>(emptyMap())
    val dynamicLayers: StateFlow<Map<String, LayerData>> = _dynamicLayers

    init {
        viewModelScope.launch {
            pointRepository.getAllPoint(0).flowOn(Dispatchers.IO).collect{
                when(it){
                    is Result.Error -> {
                    }
                    is Result.Loading -> {
                    }
                    is Result.Success -> {
                        if(it.data==null){

                        }else{
                            _dynamicLayers.value = it.data.convertFolderWithPointToLayerDataMap()
                        }
                    }
                }
            }
        }
    }
    fun onEvent(event: LocateScreenEvents) {
        when (event) {
            is LocateScreenEvents.OnPointClick -> TODO()
        }
    }


    private fun FolderWithPoint.convertFolderWithPointToLayerDataMap(): Map<String, LayerData> {
        val layersById = folder.layers.associateBy { it.layerId }

        return pointsWithLayer.mapValues { (layerId, pointsMap) ->
            val layer = layersById[layerId]
                ?: throw IllegalArgumentException("Layer with ID $layerId not found in folder")

            LayerData(
                layer = layer,
                points = pointsMap.values.toMutableList()
            )
        }
    }


}