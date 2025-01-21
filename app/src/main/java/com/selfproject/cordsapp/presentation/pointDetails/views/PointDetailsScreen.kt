package com.selfproject.cordsapp.presentation.pointDetails.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.selfproject.cordsapp.presentation.common.CustomTitleBar
import com.selfproject.cordsapp.presentation.common.SectionHeader
import com.selfproject.cordsapp.presentation.common.TextRow
import com.selfproject.cordsapp.presentation.navigation.Route
import com.selfproject.cordsapp.presentation.pointDetails.PointDetailViewModel

@Composable
fun PointDetails(navController: NavController, viewModel: PointDetailViewModel) {
    viewModel.apply {
        if (state.toast != null) {
            Toast.makeText(LocalContext.current, state.toast, Toast.LENGTH_SHORT).show()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            CustomTitleBar(title = "Point Details") {
                navController.navigate(Route.HomeScreen.route)
            }
            state.point?.apply {
                if (state.point != null) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                    SectionHeader(
                        modifier = Modifier.padding(top = 5.dp),
                        value = "Info",
                        spacer = false
                    )
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Point Id :",
                        value = pointId.toString()
                    )
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Point Number :",
                        value = pointId.toString()
                    )
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Description :",
                        value = description
                    )
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Created on :",
                        value = createdOn.toLocaleString()
                    )

                    SectionHeader(modifier = Modifier.padding(top = 5.dp), value = "WGS Coordinate")
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Latitude :",
                        value = wgs84Coords?.lat?.toString() ?: "0.0"
                    )
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Longitude :",
                        value = wgs84Coords?.lng?.toString() ?: "0.0"
                    )

                    SectionHeader(modifier = Modifier.padding(top = 5.dp), value = "UTM Coordinate")
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Zone :",
                        value = if (utmCoordinate != null) {
                            "${utmCoordinate.zoneNumber}${utmCoordinate.zoneLetter}"
                        } else "_"
                    )
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Northing :",
                        value = utmCoordinate?.northing?.toString() ?: "0.0"
                    )
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Easting :",
                        value = utmCoordinate?.easting?.toString() ?: "0.0"
                    )

                    SectionHeader(modifier = Modifier.padding(top = 5.dp), value = "Elevation")
                    TextRow(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Ellipsoidal :",
                        value = elevation.ellipsoidal.toString()
                    )
//                    TextRow(
//                        modifier = Modifier.fillMaxWidth(),
//                        field = "EGM08 :",
//                        value = elevation.egm08.toString()
//                    )
//                    TextRow(
//                        modifier = Modifier.fillMaxWidth(),
//                        field = "EGM96 :",
//                        value = elevation.egm96.toString()
//                    )
                }
            }
        }
    }
}