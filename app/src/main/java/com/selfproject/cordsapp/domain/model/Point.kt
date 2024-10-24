package com.selfproject.cordsapp.domain.model

data class Point(
    val pointId: Int,
    val cordsType: CoordinateSystemType,
    val wgs84Coords: WGS84Coordinate? = null,
    val utmCoordinate: UTMCoordinate? = null,
    val elevationType: ElevationType
)

enum class CoordinateSystemType(val code: String) {
    WGS84("WGS-84"),
    UTM("UTM"),
    ALL("ALL");

    companion object {
        fun fromCode(code: String): CoordinateSystemType? {
            return entries.find { it.code == code }
        }
    }
}