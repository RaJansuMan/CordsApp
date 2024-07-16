package com.selfproject.cordsapp.presentation.locate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selfproject.cordsapp.R
import com.selfproject.cordsapp.ui.theme.CordsAppTheme

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun LocateScreen() {
    var sheetState by remember { mutableStateOf(BottomSheetState.COLLAPSED) }
    val onSheetStateChanged: (BottomSheetState) -> Unit = { newState ->
        sheetState = newState
    }
    val topHeight = 60.dp
    val halfExpandedHeight = 150.dp

    Box(modifier = Modifier.fillMaxSize()) {
        MapBoxMap(point = null)
      ExpandedSearchView(
                modifier = Modifier.height(48.dp)
                    .padding(end=8.dp, top = 8.dp).align(Alignment.TopEnd).alpha(0.9F),
                searchDisplay = "",
                onSearchDisplayChanged = {},
                onSearchDisplayClosed = {}
            )
        CustomBottomSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            sheetState = sheetState,
            fullyExpandedHeight = 400.dp,
            halfExpandedHeight = halfExpandedHeight,
            minHeight = topHeight,
            onSheetStateChanged = onSheetStateChanged
        ) {
            Column {
                TopSheet(modifier = Modifier.height(topHeight))
                TopHeaderCard(modifier = Modifier.height(halfExpandedHeight-topHeight))
            }

        }
    }
}






@Composable
fun TopSheet(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(modifier = Modifier
            .height(30.dp)
            .width(30.dp)
            .padding(0.dp)
            .background(shape = RectangleShape, color = MaterialTheme.colorScheme.surface)
            .align(Alignment.Bottom),
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_gps_fixed_24),
                contentDescription = "Locate", tint =  MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        FloatingActionButton(
            onClick = { /* TODO: Handle FAB click */ },
            modifier = Modifier.padding(2.dp),
            shape = RoundedCornerShape(18.dp),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            backgroundColor = MaterialTheme.colorScheme.surface,
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun TopHeaderCard(modifier: Modifier=Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(modifier = Modifier
                .width(30.dp)
                .padding(0.dp)
                .background(shape = RectangleShape, color = Color.Transparent)
                .align(AbsoluteAlignment.CenterLeft),
                onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Locate", tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(modifier = Modifier
                .height(30.dp)
                .width(30.dp)
                .padding(0.dp)
                .background(shape = RectangleShape, color = Color.Transparent)
                .align(Alignment.TopCenter),
                onClick = { /*TODO*/ }) {
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
                onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Locate", tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(text = "Point no : 0", modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun LightModePreview() {
    CordsAppTheme(darkTheme = false) {
        LocateScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModePreview() {
    CordsAppTheme(darkTheme = true) {
        LocateScreen()
    }
}