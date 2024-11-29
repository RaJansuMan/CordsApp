package com.selfproject.cordsapp.domain.repository

import com.selfproject.cordsapp.domain.coordinateModel.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CachePointRepository {
    private val _stockMap = MutableStateFlow<Map<Int, Point>>(emptyMap())
    val stockMap: StateFlow<Map<Int, Point>> = _stockMap

    suspend fun addOrUpdateStock(point: Point) {
        _stockMap.value = _stockMap.value.toMutableMap().apply {
            this[point.pointId] = point
        }
    }

    suspend fun removeStock(id: Int) {
        _stockMap.value = _stockMap.value.toMutableMap().apply {
            this.remove(id)
        }
    }
}