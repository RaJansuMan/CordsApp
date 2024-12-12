package com.selfproject.cordsapp.presentation.addPoint

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.selfproject.cordsapp.domain.repository.PointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddPointViewModel @Inject constructor(pointRepository: PointRepository) :
    ViewModel() {

    var state by mutableStateOf(AddPointScreenState(layerList = emptyList(), selectedLayer = ""))

    
    fun onEvent(event: AddPointScreenEvents) {
        when (event) {
            AddPointScreenEvents.AddPoint -> {

            }
        }
    }

}