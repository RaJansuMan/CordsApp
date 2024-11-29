package com.selfproject.cordsapp.data.mapper

import com.selfproject.cordsapp.data.local.entity.PointEntity
import com.selfproject.cordsapp.data.remote.PointQuery
import com.selfproject.cordsapp.domain.coordinateModel.CoordinateSystemType
import com.selfproject.cordsapp.domain.coordinateModel.ElevationType
import com.selfproject.cordsapp.domain.coordinateModel.Point

fun Point.toPointQuery(): PointQuery {
    val z = when (elevation.elevationType) {
        ElevationType.ALL -> elevation.ellipsoidal
        ElevationType.ELLIPSOIDAL -> elevation.ellipsoidal
        ElevationType.EGM96 -> elevation.egm96
        ElevationType.EGM08 -> elevation.egm08
    }
    if (z == null) {
        throw IllegalArgumentException("Elevation cannot be null")
    }
    when (cordsType) {
        CoordinateSystemType.WGS84 -> {
            return PointQuery(
                x = wgs84Coords?.lat ?: throw IllegalArgumentException("wgs84 cannot be null"),
                y = wgs84Coords.lng,
                z = z,
                zType = elevation.elevationType.codeInt,
                xyType = cordsType.codeInt
            )
        }

        CoordinateSystemType.UTM -> {
            return PointQuery(
                x = utmCoordinate?.easting
                    ?: throw IllegalArgumentException("utm cords cannot be null"),
                y = utmCoordinate.northing,
                z = z,
                zType = elevation.elevationType.codeInt,
                xyType = cordsType.codeInt,
                zoneLetter = utmCoordinate.zoneLetter,
                zoneNo = utmCoordinate.zoneNumber
            )
        }

        CoordinateSystemType.ALL -> {
            return PointQuery(
                x = wgs84Coords?.lat ?: throw IllegalArgumentException("wgs84 cannot be null"),
                y = wgs84Coords.lng,
                z = z,
                zType = elevation.elevationType.codeInt,
                xyType = cordsType.codeInt
            )
        }
    }
}


fun Point.pointToPointQuery(folderId: Int): PointEntity {
    if (cordsType != CoordinateSystemType.ALL) {
        throw IllegalArgumentException("Coords type should be all")
    }
    if (elevation.elevationType != ElevationType.ALL) {
        throw IllegalArgumentException("Elevation type should be all")
    }
    return PointEntity(
        folderId = folderId,
        lat = wgs84Coords!!.lat,
        lon = wgs84Coords.lng,
        east = utmCoordinate!!.easting,
        north = utmCoordinate.northing,
        zoneLetter = utmCoordinate.zoneLetter,
        zoneNumber = utmCoordinate.zoneNumber,
        eleEllip = elevation.ellipsoidal!!,
        eleEgm08 = elevation.egm08!!,
        eleEgm96 = elevation.egm96!!,
        description = description,
        pointLabel = pointLabel,
        createdOn = createdOn!!
    )

}