package com.selfproject.cordsapp.presentation.addPoint

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selfproject.cordsapp.presentation.common.CustomDropdownSpinner
import com.selfproject.cordsapp.presentation.common.CustomOutlinedTextField
import com.selfproject.cordsapp.ui.theme.CordsAppTheme

@Composable
fun UTMAddPoint(modifier: Modifier = Modifier) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentSize(unbounded = false),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CustomDropdownSpinner(modifier.weight(1.0f),title = "Zone No", items = listOf("A", "B", "C")) {}
            CustomDropdownSpinner(modifier.weight(1.0f),title = "Zone Letter", items = listOf("1", "2", "3")) {}
        }
        CustomOutlinedTextField(placeholder = "Easting"){}
        CustomOutlinedTextField(placeholder = "Northing"){}

    }
}

@Preview(showBackground = true)
@Composable
fun LightModeUTMAddPointPreview() {
    CordsAppTheme(darkTheme = false) {
        UTMAddPoint()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModeUTMAddPointPreview() {
    CordsAppTheme(darkTheme = true) {
        UTMAddPoint()
    }
}