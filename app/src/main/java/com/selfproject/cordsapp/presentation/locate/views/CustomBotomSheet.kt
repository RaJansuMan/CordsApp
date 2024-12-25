package com.selfproject.cordsapp.presentation.locate.views

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.selfproject.cordsapp.ui.theme.CordsAppTheme
import kotlinx.coroutines.launch

@Composable
fun CustomBottomSheet(
    sheetState: BottomSheetState,
    modifier: Modifier = Modifier,
    fullyExpandedHeight: Dp,
    halfExpandedHeight: Dp,
    minHeight: Dp,
    onSheetStateChanged: (BottomSheetState) -> Unit,
    sheetContent: @Composable () -> Unit
) {
    var _sheetState by remember { mutableStateOf(BottomSheetState.COLLAPSED) }
    val scope = rememberCoroutineScope()
    val sheetHeight = remember { Animatable(halfExpandedHeight.value) }
    val fullHeightPx = with(LocalDensity.current) { fullyExpandedHeight.toPx() }
    val halfHeightPx = with(LocalDensity.current) { halfExpandedHeight.toPx() }
    val minHeightPx = with(LocalDensity.current) { minHeight.toPx() }
    LaunchedEffect(sheetState) {
        when (sheetState) {
            BottomSheetState.HIDDEN -> {
                sheetHeight.animateTo(minHeightPx)
            }

            BottomSheetState.COLLAPSED -> {
                sheetHeight.animateTo(halfHeightPx)
            }

            BottomSheetState.EXPANDED -> {
                sheetHeight.animateTo(fullHeightPx)
            }
        }
        _sheetState = sheetState
    }
    Card(
        modifier = modifier
            .height(with(LocalDensity.current) { sheetHeight.value.toDp() })
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {},
                    onDragEnd = {
                        scope.launch {
                            if (sheetHeight.value > halfHeightPx && sheetHeight.value < fullHeightPx) {
                                if (_sheetState == BottomSheetState.COLLAPSED)
                                    onSheetStateChanged(BottomSheetState.EXPANDED)
                                else
                                    onSheetStateChanged(BottomSheetState.COLLAPSED)
                            }
                            if (sheetHeight.value < halfHeightPx) {
                                if (_sheetState == BottomSheetState.COLLAPSED)
                                    onSheetStateChanged(BottomSheetState.HIDDEN)
                                else
                                    onSheetStateChanged(BottomSheetState.COLLAPSED)
                            }
                        }
                    },
                    onDragCancel = {}
                ) { change, dragAmount ->
                    change.consume()
                    scope.launch {
                        val targetValue = sheetHeight.value - dragAmount.y
                        if (targetValue > minHeightPx && targetValue < fullHeightPx) {
                            sheetHeight.snapTo(targetValue)
                        }

                    }
                }
            }
            .background(color = Color.Transparent),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        sheetContent()
    }
}

@Composable
fun DemoScreen() {
    var sheetState by remember { mutableStateOf(BottomSheetState.COLLAPSED) }
    val onSheetStateChanged: (BottomSheetState) -> Unit = { newState ->
        sheetState = newState
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Main Content",
            modifier = Modifier.align(Alignment.Center)
        )
        FloatingActionButton(
            onClick = { sheetState = BottomSheetState.COLLAPSED },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = if (sheetState != BottomSheetState.HIDDEN) "Hide" else "Show")
        }

        CustomBottomSheet(
            sheetState = sheetState,
            halfExpandedHeight = 200.dp,
            fullyExpandedHeight = 400.dp,
            minHeight = 100.dp,
            onSheetStateChanged = onSheetStateChanged
        ) {
            Text(text = "Bottom Sheet Content")
        }
    }
}

enum class BottomSheetState {
    HIDDEN,
    COLLAPSED,
    EXPANDED
}


@Preview(showBackground = true)
@Composable
fun CustomLightPreView() {
    CordsAppTheme(darkTheme = false) {
        DemoScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun CustomDarkModePreview() {
    CordsAppTheme(darkTheme = true) {
        DemoScreen()
    }
}