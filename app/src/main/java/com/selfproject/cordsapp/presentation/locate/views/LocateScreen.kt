package com.selfproject.cordsapp.presentation.locate.views

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.selfproject.cordsapp.R
import com.selfproject.cordsapp.presentation.locate.LocateScreenEvents
import com.selfproject.cordsapp.presentation.locate.LocateViewModel
import com.selfproject.cordsapp.presentation.navigation.Route

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun LocateScreen(navController: NavController, viewModel: LocateViewModel) {
    val localDensity = LocalDensity.current
    var sheetState by remember { mutableStateOf(BottomSheetState.COLLAPSED) }
    val onSheetStateChanged: (BottomSheetState) -> Unit = { newState ->
        sheetState = newState
    }
    val topHeight = 60.dp
    val halfExpandedHeight = 135.dp

    var bottomCardHeight by remember { mutableStateOf(0.dp) }
    var isBottomCardMeasured by remember { mutableStateOf(false) }

    viewModel.apply {

        if (state.toastMessage != null) {
            Toast.makeText(LocalContext.current, state.toastMessage, Toast.LENGTH_SHORT).show()
            onEvent(LocateScreenEvents.ToastShowed)
        }
        if (state.clickedPoint == null) {
            onSheetStateChanged(BottomSheetState.HIDDEN)
        } else {
            if (sheetState == BottomSheetState.HIDDEN) {
                onSheetStateChanged(BottomSheetState.COLLAPSED)
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            MapBoxMap(viewModel = viewModel)
            if (state.isProgress) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                ExpandedSearchView(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(end = 8.dp, top = 8.dp)
                        .align(Alignment.TopEnd)
                        .alpha(0.9F),
                    searchDisplay = "",
                    onSearchDisplayChanged = {},
                    onSearchDisplayClosed = {
                        onEvent(LocateScreenEvents.OnPointClick(it))
                    }
                )
                if (!isBottomCardMeasured) {
                    BottomCard(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                bottomCardHeight =
                                    with(localDensity) { coordinates.size.height.toDp() }
                                isBottomCardMeasured = true
                            }
                            .alpha(0.0F)

                    )
                }
                if (isBottomCardMeasured) {
                    CustomBottomSheet(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        sheetState = sheetState,
                        fullyExpandedHeight = bottomCardHeight + halfExpandedHeight,
                        halfExpandedHeight = halfExpandedHeight,
                        minHeight = topHeight,
                        enableDrag = state.clickedPoint != null,
                        onSheetStateChanged = onSheetStateChanged
                    ) {
                        Column {
                            TopSheet(modifier = Modifier.height(topHeight), navController)
                            TopHeaderCard(
                                modifier = Modifier.height(halfExpandedHeight - topHeight),
                                viewModel = this@apply
                            )
                            BottomCard(viewModel = this@apply)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TopSheet(modifier: Modifier = Modifier, navController: NavController) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .align(Alignment.Bottom)
        ) {
            IconButton(modifier = Modifier
                .background(
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    color = MaterialTheme.colorScheme.surface
                )
                .align(Alignment.BottomCenter)
                .padding(0.dp)
                .size(35.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_gps_fixed_24),
                    contentDescription = "Locate",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(0.dp)
                        .size(18.dp)
                )
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate(Route.AddPointScreen.route) },
            modifier = Modifier.padding(2.dp),
            shape = RoundedCornerShape(18.dp),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            backgroundColor = MaterialTheme.colorScheme.surface,
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,

                )
        }
    }
}

@Composable
fun TopHeaderCard(modifier: Modifier = Modifier, viewModel: LocateViewModel) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(
            topEnd = 8.dp,
            topStart = 8.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(modifier = Modifier
                .width(30.dp)
                .padding(0.dp)
                .background(shape = RectangleShape, color = Color.Transparent)
                .align(AbsoluteAlignment.CenterLeft),
                onClick = { viewModel.onEvent(LocateScreenEvents.OnLeftClick) }) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Locate", tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(modifier = Modifier
                .height(30.dp)
                .width(30.dp)
                .padding(0.dp)
                .background(shape = RectangleShape, color = Color.Transparent)
                .align(Alignment.TopCenter),
                onClick = { viewModel.onEvent(LocateScreenEvents.OnUpClick) }) {
                Icon(
                    Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Locate", tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(modifier = Modifier
                .height(30.dp)
                .width(30.dp)
                .padding(0.dp)
                .background(shape = RectangleShape, color = Color.Transparent)
                .align(AbsoluteAlignment.CenterRight),
                onClick = { viewModel.onEvent(LocateScreenEvents.OnRightClick) }) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Locate", tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "Point no : ${viewModel.state.clickedPoint?.pointNumber}",
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }

}

@Composable
fun BottomCard(modifier: Modifier = Modifier, viewModel: LocateViewModel? = null) {
    viewModel?.state?.clickedPoint.let { point ->
        Card(
            modifier = modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(
                0.dp
            ),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column {
                SectionHeader(modifier = Modifier.padding(top = 5.dp), value = "Description")
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                    text = point?.description ?: "",
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                SectionHeader(modifier = Modifier.padding(top = 5.dp), value = "WGS Coordinates")
                TextRow(
                    modifier = Modifier.fillMaxWidth(),
                    field = "Latitude   :",
                    value = point?.wgs84Coords?.lat?.toString() ?: "0.0"
                )
                TextRow(
                    modifier = Modifier.fillMaxWidth(),
                    field = "Longitude :",
                    value = point?.wgs84Coords?.lng?.toString() ?: "0.0"
                )
//            SectionHeader(modifier= Modifier.padding(top = 5.dp),value = "UTM Coordinates")
//            TextRow(modifier=Modifier.fillMaxWidth(), field = "Easting    :", value = "1.4588537")
//            TextRow(modifier=Modifier.fillMaxWidth(), field = "Northing   :", value = "1.4588537")
//            TextRow(modifier=Modifier.fillMaxWidth(), field = "UTM Zone   :", value = "1.4588537")
//            TextRow(modifier=Modifier.fillMaxWidth(), field = "UTM Letter   :", value = "1.4588537")
                SectionHeader(modifier = Modifier.padding(top = 5.dp), value = "Elevation")
                TextRow(
                    modifier = Modifier.fillMaxWidth(),
                    field = "Ellipsoidal   :",
                    value = point?.elevation?.ellipsoidal?.toString() ?: "0.0"
                )
//            TextRow(modifier=Modifier.fillMaxWidth(), field = "   :", value = "1.4588537")
//            TextRow(modifier=Modifier.fillMaxWidth(), field = "UTM Letter   :", value = "1.4588537")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = { viewModel?.onEvent(LocateScreenEvents.OnDeletePoint) },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "DELETE POINT", style = TextStyle(fontWeight = FontWeight.Bold))
                    }
                    Button(
                        onClick = { viewModel?.onEvent(LocateScreenEvents.OnPointDetails) },
                        shape = RoundedCornerShape(8.dp) // Rounded corners
                    ) {
                        Text(
                            text = "POINT DETAILS",
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                    }
                }

            }
        }
    }
}


@Composable
private fun SectionHeader(modifier: Modifier = Modifier, value: String, spacer: Boolean = true) {
    Column(modifier = modifier) {
        if (spacer) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant)
            )
        }
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 5.dp, bottom = 2.dp),
            text = value,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp
            )
        )
    }
}

@Composable
fun TextRow(modifier: Modifier = Modifier, field: String, value: String) {
    Row(
        modifier = modifier.padding(start = 20.dp, top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(text = "$field ", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LightModePreview() {
//    CordsAppTheme(darkTheme = false) {
//        LocateScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DarkModePreview() {
//    CordsAppTheme(darkTheme = true) {
//        LocateScreen()
//    }
//}