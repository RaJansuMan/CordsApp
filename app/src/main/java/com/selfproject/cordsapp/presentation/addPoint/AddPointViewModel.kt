package com.selfproject.cordsapp.presentation.addPoint

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.model.coordinateModel.CoordinateSystemType
import com.selfproject.cordsapp.domain.model.coordinateModel.Elevation
import com.selfproject.cordsapp.domain.model.coordinateModel.ElevationType
import com.selfproject.cordsapp.domain.model.coordinateModel.Point
import com.selfproject.cordsapp.domain.model.coordinateModel.UTMCoordinate
import com.selfproject.cordsapp.domain.model.coordinateModel.WGS84Coordinate
import com.selfproject.cordsapp.domain.repository.FileRepository
import com.selfproject.cordsapp.domain.repository.PointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.sql.Date
import javax.inject.Inject


@HiltViewModel
class AddPointViewModel @Inject constructor(
    val pointRepository: PointRepository,
    val fileRepository: FileRepository
) :
    ViewModel() {

    var state by mutableStateOf(AddPointScreenState(layerList = emptyList(), selectedLayer = ""))

    init {
        viewModelScope.launch {
            fileRepository.getFolderDetail(null).flowOn(Dispatchers.Unconfined)
                .collectLatest { result ->
                    when (result) {
                        is Result.Error -> {

                        }

                        is Result.Loading -> {
                            state = state.copy(folderFetchProgress = result.isLoading)
                        }

                        is Result.Success -> {
                            result.data?.let { folder ->
                                val layerList = folder.layers.map { "${it.name} (${it.layerId})" }
                                state = state.copy(
                                    folder = folder, layerList = layerList,
                                    selectedLayer = layerList.firstOrNull() ?: ""
                                )
                            }
                        }
                    }
                }
        }
    }


    fun onEvent(event: AddPointScreenEvents) {
        when (event) {
            AddPointScreenEvents.AddPoint -> {
                var wgS84Coordinate: WGS84Coordinate? = null
                var utmCoordinate: UTMCoordinate? = null
                state.apply {
                    when (coordinateSystemType) {
                        CoordinateSystemType.ALL -> {
                            return
                        }

                        CoordinateSystemType.WGS84 -> {

                            val latitude: Double = latitude.input.toDoubleOrNull() ?: run {
                                state = state.copy(latitude = latitude.copy(error = "Required"))
                                return
                            }
                            val longitude: Double = longitude.input.toDoubleOrNull() ?: run {
                                state = state.copy(longitude = longitude.copy(error = "Required"))
                                return
                            }
                            wgS84Coordinate = WGS84Coordinate(latitude, longitude)
                        }

                        CoordinateSystemType.UTM -> {
                            val easting: Double = easting.input.toDoubleOrNull() ?: run {
                                state = state.copy(easting = easting.copy(error = "Required"))
                                return
                            }
                            val northing: Double = northing.input.toDoubleOrNull() ?: run {
                                state = state.copy(northing = northing.copy(error = "Required"))
                                return
                            }
                            val zoneNumber: Int = zoneNumber.input.toIntOrNull() ?: run {
                                state = state.copy(zoneNumber = zoneNumber.copy(error = "Required"))
                                return
                            }
                            val zoneLetterValue = if (zoneLetter.input.isNotBlank()) {
                                zoneLetter.input[0]
                            } else {
                                state = state.copy(zoneLetter = zoneLetter.copy(error = "Required"))
                                return
                            }
                            utmCoordinate =
                                UTMCoordinate(easting, northing, zoneNumber, zoneLetterValue)

                        }
                    }

                    val elevation: Double = elevation.input.toDoubleOrNull() ?: run {
                        state = state.copy(elevation = elevation.copy(error = "Required"))
                        return
                    }
                    var ellipsoidal: Double? = null
                    var egm08: Double? = null
                    var egm96: Double? = null
                    when (elevationType) {
                        ElevationType.ALL -> {}
                        ElevationType.ELLIPSOIDAL -> ellipsoidal = elevation
                        ElevationType.EGM96 -> egm96 = elevation
                        ElevationType.EGM08 -> egm08 = elevation
                    }
                    val layer =
                        folder!!.layers[0]
                    val pointNumber: Int = 0
//                        when (val it = pointRepository.getPointLastId(layer.layerId)) {
//                            is Result.Error -> return
//                            is Result.Loading -> return
//                            is Result.Success -> it.data ?: 0
//                        }
                    viewModelScope.launch {
                        pointRepository.addPoint(
                            Point(
                                wgs84Coords = wgS84Coordinate,
                                elevation = Elevation(
                                    elevationType, ellipsoidal, egm96, egm08
                                ),
                                utmCoordinate = utmCoordinate,
                                layer = layer,
                                folderId = folder.folderId!!,
                                cordsType = coordinateSystemType,
                                pointNumber = pointNumber,
                                description = description.input,
                                createdOn = Date(System.currentTimeMillis()),
                                pointId = null
                            )
                        ).flowOn(Dispatchers.IO).collectLatest {
                            when (it) {
                                is Result.Error -> {}
                                is Result.Loading -> {}
                                is Result.Success -> {}
                            }
                        }
                    }
                }
            }
        }
    }

    private fun extractLayerId(input: String): String? {
        val regex = """\((\d+)\)$""".toRegex()
        return regex.find(input)?.groupValues?.get(1)
    }


}