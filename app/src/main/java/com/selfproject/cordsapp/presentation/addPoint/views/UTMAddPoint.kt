package com.selfproject.cordsapp.presentation.addPoint.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.selfproject.cordsapp.presentation.addPoint.AddPointViewModel
import com.selfproject.cordsapp.presentation.common.CustomOutlinedTextField

@Composable
fun UTMAddPoint(modifier: Modifier = Modifier, viewModel: AddPointViewModel) {
    viewModel.apply {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentSize(unbounded = false),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomOutlinedTextField(
                    modifier = modifier.weight(1.0f),
                    placeholder = "Zone Number",
                    value = state.zoneNumber,
                    maxLength = 2,
                    keyboardType = KeyboardType.Number
                ) {
                    state = state.copy(zoneNumber = it)
                }
                CustomOutlinedTextField(
                    modifier = modifier.weight(1.0f),
                    placeholder = "Zone Letter",
                    maxLength = 1,
                    value = state.zoneLetter
                ) {
                    state = state.copy(zoneLetter = it)
                }
            }
            CustomOutlinedTextField(
                placeholder = "Easting",
                value = state.easting,
                keyboardType = KeyboardType.Decimal
            ) {
                state = state.copy(easting = it)
            }
            CustomOutlinedTextField(
                placeholder = "Northing",
                value = state.northing,
                keyboardType = KeyboardType.Decimal
            ) {
                state = state.copy(northing = it)
            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LightModeUTMAddPointPreview() {
//    CordsAppTheme(darkTheme = false) {
//        UTMAddPoint()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DarkModeUTMAddPointPreview() {
//    CordsAppTheme(darkTheme = true) {
//        UTMAddPoint()
//    }
//}