package com.selfproject.cordsapp.presentation.pointDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfproject.cordsapp.domain.model.Result
import com.selfproject.cordsapp.domain.repository.PointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val pointRepository: PointRepository
) :
    ViewModel() {
    val pointId: Int by lazy {
        savedStateHandle.get<Int>("param") ?: -1
    }
    var state by mutableStateOf(PointScreenState())

    init {
        viewModelScope.launch {
            when (val result = pointRepository.getPointDetail(pointId, null)) {
                is Result.Error -> state = state.copy(toast = result.message)
                is Result.Loading -> {}
                is Result.Success -> {
                    state = if (result.data == null) {
                        state.copy(toast = "Point not found")
                    } else
                        state.copy(point = result.data)
                }
            }
        }
    }


}