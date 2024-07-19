package com.selfproject.cordsapp.presentation.addPoint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selfproject.cordsapp.presentation.common.CustomDropdownSpinner
import com.selfproject.cordsapp.presentation.common.CustomOutlinedTextField
import com.selfproject.cordsapp.ui.theme.CordsAppTheme

@Composable
fun AddPointScreen() {
    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)
        .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp))
        {
        CustomDropdownSpinner(title = "Coordinate System",items = listOf("WGS-84","UTM")){}
        UTMAddPoint()
        CustomDropdownSpinner(title = "Elevation Type",items = listOf("Ellipsoidal","UTM")){}
        CustomOutlinedTextField(value = "", onValueChanged = {}, placeholder = "Altitude")
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "ADD POINT", style = TextStyle(fontWeight = FontWeight.Bold))
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LightModeAddPointScreenPreview() {
    CordsAppTheme(darkTheme = false) {
        AddPointScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModeAddPointScreenPreview() {
    CordsAppTheme(darkTheme = true) {
        AddPointScreen()
    }
}