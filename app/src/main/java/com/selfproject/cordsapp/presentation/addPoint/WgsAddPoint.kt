package com.selfproject.cordsapp.presentation.addPoint

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.selfproject.cordsapp.presentation.common.CustomOutlinedTextField
import com.selfproject.cordsapp.ui.theme.CordsAppTheme

@Composable
fun WGSAddPoint(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CustomOutlinedTextField(value = "", onValueChanged = {}, placeholder = "Latitude")
        CustomOutlinedTextField(value = "", onValueChanged = {}, placeholder = "Longitude")
    }
}


@Preview(showBackground = true)
@Composable
fun LightModeWGSAddPointPreview() {
    CordsAppTheme(darkTheme = false) {
        WGSAddPoint()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModeWGSAddPointPreview() {
    CordsAppTheme(darkTheme = true) {
        WGSAddPoint()
    }
}