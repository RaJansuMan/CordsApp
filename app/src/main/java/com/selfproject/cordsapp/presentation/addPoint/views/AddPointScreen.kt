package com.selfproject.cordsapp.presentation.addPoint.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.selfproject.cordsapp.domain.model.CoordinateSystemType
import com.selfproject.cordsapp.domain.model.ElevationType
import com.selfproject.cordsapp.presentation.addPoint.AddPointScreenEvents
import com.selfproject.cordsapp.presentation.addPoint.AddPointViewModel
import com.selfproject.cordsapp.presentation.common.ButtonProgressBar
import com.selfproject.cordsapp.presentation.common.CustomDropdownSpinner
import com.selfproject.cordsapp.presentation.common.CustomOutlinedTextField
import com.selfproject.cordsapp.presentation.common.CustomTitleBar
import com.selfproject.cordsapp.presentation.navigation.Route
import com.selfproject.cordsapp.ui.theme.CordsAppTheme

@Composable
fun AddPointScreen(
    navController: NavHostController? = null,
    viewModel: AddPointViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        CustomTitleBar(title = "Add Point") {
            navController?.navigate(Route.HomeScreen .route)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            CustomDropdownSpinner(title = "Coordinate System", items = listOf("WGS-84", "UTM")) {
                viewModel.state = viewModel.state.copy(
                    coordinateSystemType = CoordinateSystemType.fromCode(it)
                        ?: CoordinateSystemType.WGS84
                )
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
                viewModel.state.elevationType =
                    ElevationType.fromCode(it) ?: ElevationType.ELLIPSOIDAL
            }
            CustomOutlinedTextField(
                value = viewModel.state.elevation,
                placeholder = "Altitude",
                keyboardType = KeyboardType.Number
            ) {
                viewModel.state.elevation = it
            }
            ButtonProgressBar(modifier = Modifier.align(Alignment.CenterHorizontally),text = "ADD POINT", isProgress = viewModel.state.isProgress) {
                viewModel.state.isProgress = true
                viewModel.onEvent(AddPointScreenEvents.AddPoint)
            }
//            Button(
//                modifier = Modifier  .align(Alignment.CenterHorizontally),
//                onClick = { viewModel.onEvent(AddPointScreenEvents.AddPoint) },
//                shape = RoundedCornerShape(8.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
//            ) {
//                Text(text = "ADD POINT", style = TextStyle(fontWeight = FontWeight.Bold))
//            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun LightModeAddPointScreenPreview() {
    CordsAppTheme(darkTheme = false) {
        AddPointScreen(viewModel = AddPointViewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModeAddPointScreenPreview() {
    CordsAppTheme(darkTheme = true) {
        AddPointScreen(viewModel = AddPointViewModel())
    }
}