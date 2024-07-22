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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.selfproject.cordsapp.domain.model.CoordinateSystemType
import com.selfproject.cordsapp.domain.model.ElevationType
import com.selfproject.cordsapp.presentation.common.CustomDropdownSpinner
import com.selfproject.cordsapp.presentation.common.CustomOutlinedTextField

@Composable
fun AddPointScreen(
    navController: NavHostController? = null,
    viewModel: AddPointViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        CustomDropdownSpinner(title = "Coordinate System", items = listOf("WGS-84", "UTM")) {
            viewModel.state.coordinateSystemType = CoordinateSystemType.valueOf(it)
        }
        if (viewModel.state.coordinateSystemType == CoordinateSystemType.UTM) {
            UTMAddPoint(state = viewModel.state)
        } else {
            WGSAddPoint(state = viewModel.state)
        }
        CustomDropdownSpinner(
            title = "Elevation Type",
            items = listOf("Ellipsoidal", "EGM-96", "EGM-08")
        ) {
            viewModel.state.elevationType = ElevationType.valueOf(it)
        }
        CustomOutlinedTextField(
            value = viewModel.state.elevation,
            placeholder = "Altitude",
            keyboardType = KeyboardType.Number
        ) {
            viewModel.state.elevation = it
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { viewModel.onEvent(AddPointScreenEvents.AddPoint) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "ADD POINT", style = TextStyle(fontWeight = FontWeight.Bold))
        }
    }

}


//@Preview(showBackground = true)
//@Composable
//fun LightModeAddPointScreenPreview() {
//    CordsAppTheme(darkTheme = false) {
//        AddPointScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DarkModeAddPointScreenPreview() {
//    CordsAppTheme(darkTheme = true) {
//        AddPointScreen()
//    }
//}