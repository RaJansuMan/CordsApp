package com.selfproject.cordsapp.data.remote

data class PointQuery(
    val x: Double,
    val y: Double,
    val z: Double,
    val zType: Int,
    val xyType: Int,
    val zoneNo: Int = -1,
    val zoneLetter: Char = '1'
)