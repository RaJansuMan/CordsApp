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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.selfproject.cordsapp.domain.model.coordinateModel.CoordinateSystemType
import com.selfproject.cordsapp.domain.model.coordinateModel.ElevationType
import com.selfproject.cordsapp.presentation.addPoint.AddPointScreenEvents
import com.selfproject.cordsapp.presentation.addPoint.AddPointViewModel
import com.selfproject.cordsapp.presentation.common.ButtonProgressBar
import com.selfproject.cordsapp.presentation.common.CustomDropdownSpinner
import com.selfproject.cordsapp.presentation.common.CustomOutlinedTextField
import com.selfproject.cordsapp.presentation.common.CustomTitleBar
import com.selfproject.cordsapp.presentation.navigation.Route

@Composable
fun AddPointScreen(
    navController: NavHostController? = null,
    viewModel: AddPointViewModel
) {
    viewModel.apply {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CustomTitleBar(title = "Add Point") {
                navController?.navigate(Route.HomeScreen.route)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                CustomOutlinedTextField(
                    value = state.pointNo,
                    placeholder = "Point No",
                    keyboardType = KeyboardType.Number
                ) {
                    state = state.copy(pointNo = it)
                }
                CustomOutlinedTextField(
                    value = state.description,
                    placeholder = "Point Description",
                    keyboardType = KeyboardType.Number
                ) {
                    state = state.copy(description = it)
                }
                CustomDropdownSpinner(
                    title = "Layer",
                    items = state.layerList,
                    selectedItem = state.selectedLayer
                ) {
                    state = state.copy(
                        selectedLayer = it
                    )
                }
                CustomDropdownSpinner(
                    title = "Coordinate System",
                    items = listOf("WGS-84", "UTM")
                ) {
                    state = state.copy(
                        coordinateSystemType = CoordinateSystemType.fromCode(it)
                            ?: CoordinateSystemType.WGS84
                    )
                }
                if (state.coordinateSystemType == CoordinateSystemType.UTM) {
                    UTMAddPoint(viewModel = this@apply)
                } else {
                    WGSAddPoint(viewModel = viewModel)
                }
                CustomDropdownSpinner(
                    title = "Elevation Type",
                    items = listOf("Ellipsoidal", "EGM-96", "EGM-08")
                ) {
                    state = state.copy(
                        elevationType =
                        ElevationType.fromCode(it) ?: ElevationType.ELLIPSOIDAL
                    )
                }
                CustomOutlinedTextField(
                    value = state.elevation,
                    placeholder = "Altitude",
                    keyboardType = KeyboardType.Number
                ) {
                    state = state.copy(elevation = it)
                }
                ButtonProgressBar(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "ADD POINT",
                    isProgress = viewModel.state.isProgress
                ) {
                    state = state.copy(isProgress = true)
                    viewModel.onEvent(AddPointScreenEvents.AddPoint)
                }
            }

        }
    }
}

//class MockRepository : PointRepository {
//    override suspend fun addPoint(point: Point): Flow<Result<Point>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deletePoint(point: Point): Flow<Result<Void>> {
//        TODO("Not yet implemented")
//    }
//
//
//    override suspend fun getAllPoint(folderId: Int): Flow<Result<FolderWithPoint>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getPointDetailFromDatabase(pointId: Int): Flow<Result<Point>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getPointDetail(pointId: Int, layerId: String?): Result<Point> {
//        TODO("Not yet implemented")
//    }
//
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LightModeAddPointScreenPreview() {
//    val mockViewModel = AddPointViewModel(
//        pointRepository = MockRepository()
//    )
//    CordsAppTheme(darkTheme = false) {
//        AddPointScreen(viewModel = mockViewModel)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DarkModeAddPointScreenPreview() {
//    val mockViewModel = AddPointViewModel(
//        pointRepository = MockRepository()
//    )
//    CordsAppTheme(darkTheme = true) {
//        AddPointScreen(viewModel = mockViewModel)
//    }
//}