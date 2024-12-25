package com.selfproject.cordsapp.presentation.locate.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.gson.JsonObject
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.selfproject.cordsapp.R
import com.selfproject.cordsapp.domain.model.coordinateModel.Point
import com.selfproject.cordsapp.presentation.locate.LocateScreenEvents
import com.selfproject.cordsapp.presentation.locate.LocateViewModel

@Composable
fun MapBoxMap(
    modifier: Modifier = Modifier,
    viewModel: LocateViewModel
) {
    val context = LocalContext.current

    val dynamicLayers by viewModel.dynamicLayers.collectAsState()
    val activeAnnotationManagers = mutableMapOf<String, PointAnnotationManager>()

    AndroidView(
        factory = {
            MapView(it).apply {
                mapboxMap.loadStyleUri(Style.TRAFFIC_DAY)
            }
        },
        update = { mapView ->
            val annotationApi = mapView.annotations

            val layerIds = dynamicLayers.map { it.key }
            dynamicLayers.forEach { layerData ->

                val layerId = layerData.key ?: return@forEach

                if (layerData.value.annotationManager == null) {
                    layerData.value.annotationManager = annotationApi.createPointAnnotationManager()

                    layerData.value.annotationManager?.addClickListener { annotation ->
                        val clickedPoint = annotation.getData()?.asJsonObject?.get("id")?.asInt
                        clickedPoint?.let { LocateScreenEvents.OnPointClick(it) }
                            ?.let { viewModel.onEvent(it) }
                        true
                    }
                }

                layerData.value.annotationManager?.let { manager ->

                    val existingPoints =
                        manager.annotations.map { it.getData()?.asJsonObject?.get("id")?.asInt }
                            .toSet()
                    val newPoints = layerData.value.points.filter { it.pointId !in existingPoints }
                    val pointIds = layerData.value.points.map { it.pointId }
                    val removedPoints = existingPoints.filter { it !in pointIds }

                    // Remove old annotations
                    manager.annotations
                        .filter { it.getData()?.asJsonObject?.get("id")?.asInt in removedPoints }
                        .forEach { manager.delete(it) }

                    // Add new annotations
                    val newOptions = newPoints.map { point ->
                        PointAnnotationOptions()
                            .withPoint(point.toPoint())
                            .withData(JsonObject().apply { addProperty("id", point.pointId) })
                            .withTextField("${layerData.value.layer.layerId} ${point.pointNumber}")
                            .withIconImage(
                                context.getDrawable(R.drawable.map_pin_black)!!.toBitmap()
                            )
                            .withIconOffset(listOf(0.0, -0.9))
                            .withTextOffset(listOf(0.0, 0.9))
                            .withTextSize(12.0)
                            .withIconSize(0.8)
                            .withTextColor(ContextCompat.getColor(context, R.color.black))
                    }
                    manager.create(newOptions)
                }
            }

            // Handle deletion of layers
            val managersToRemove = activeAnnotationManagers.keys.filter { it !in layerIds }
            managersToRemove.forEach { layerId ->
                val manager = activeAnnotationManagers[layerId]
                manager?.deleteAll()
                activeAnnotationManagers.remove(layerId) // Remove from tracking
            }
        },
        modifier = modifier
    )
}

fun Point.toPoint(): com.mapbox.geojson.Point {
    return com.mapbox.geojson.Point.fromLngLat(
        wgs84Coords!!.lng, wgs84Coords.lat, elevation.ellipsoidal!!
    )
}