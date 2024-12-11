package com.selfproject.cordsapp.domain.model.coordinateModel

import com.selfproject.cordsapp.domain.model.Layer
import java.sql.Date

data class Point(
    val pointId: Int?,
    val cordsType: CoordinateSystemType,
    val wgs84Coords: WGS84Coordinate? = null,
    val utmCoordinate: UTMCoordinate? = null,
    val elevation: Elevation,
    val description: String,
    val layer: Layer,
    val pointNumber: Int,
    val createdOn: Date,
    val folderId: Int
)

enum class CoordinateSystemType(val code: String, val codeInt: Int) {
    ALL("ALL", 1),
    WGS84("WGS-84", 1),
    UTM("UTM", 2);


    companion object {
        fun fromCode(code: String): CoordinateSystemType? {
            return entries.find { it.code == code }
        }
    }
}