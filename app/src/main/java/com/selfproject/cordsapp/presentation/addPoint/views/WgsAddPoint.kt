package com.selfproject.cordsapp.presentation.addPoint.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.selfproject.cordsapp.presentation.addPoint.AddPointViewModel
import com.selfproject.cordsapp.presentation.common.CustomOutlinedTextField

@Composable
fun WGSAddPoint(modifier: Modifier = Modifier, viewModel: AddPointViewModel) {
    viewModel.apply {
        Column(modifier = modifier) {
            CustomOutlinedTextField(
                value = state.latitude,
                placeholder = "Latitude",
                keyboardType = KeyboardType.Decimal
            ) {
                state = state.copy(latitude = it)
            }
            CustomOutlinedTextField(
                value = state.longitude,
                placeholder = "Longitude",
                keyboardType = KeyboardType.Decimal
            ) {
                state = state.copy(longitude = it)
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun LightModeWGSAddPointPreview() {
//    CordsAppTheme(darkTheme = false) {
//        WGSAddPoint()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DarkModeWGSAddPointPreview() {
//    CordsAppTheme(darkTheme = true) {
//        WGSAddPoint()
//    }
//}