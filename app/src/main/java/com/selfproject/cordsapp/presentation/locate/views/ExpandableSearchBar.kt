package com.selfproject.cordsapp.presentation.locate.views

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selfproject.cordsapp.ui.theme.CordsAppTheme


@Composable
fun SearchIcon(iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant) {
    Icon(
        Icons.Filled.Search,
        contentDescription = "search icon",
        tint = iconTint
    )
}



@Composable
fun ExpandedSearchView(
    modifier: Modifier = Modifier,
    searchDisplay: String,
    onSearchDisplayChanged: (Int) -> Unit,
    onSearchDisplayClosed: (Int) -> Unit,
    initialState: BarState = BarState.COLLAPSED,
    tint: Color = Color.White,
    backgroundColor: Color = Color.Gray
) {
    val collapsedWidth = with(LocalDensity.current) { 48.dp.toPx() }
    val expandedWidth = with(LocalDensity.current) { 300.dp.toPx() }

    var barState by remember {
        mutableStateOf(initialState)
    }
    val barWidth by remember {
        mutableStateOf(Animatable(collapsedWidth))
    }
    val textFieldFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(barState) {
        when (barState) {
            BarState.EXPANDED -> {
                textFieldFocusRequester.requestFocus()
                barWidth.animateTo(expandedWidth)

            }

            BarState.COLLAPSED -> {
                focusManager.clearFocus(true)
                barWidth.animateTo(collapsedWidth)

            }
        }
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }
    Row(
        modifier = modifier
            .background(color = backgroundColor)
            .width(with(LocalDensity.current) { barWidth.value.toDp() }),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            if (barState != BarState.EXPANDED) {
                barState = BarState.EXPANDED
            }
        }) {
            SearchIcon(iconTint = tint)
        }
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onSearchDisplayChanged(it.text.toInt())
            },
            modifier = Modifier
                .focusRequester(textFieldFocusRequester)
                .background(color = backgroundColor)
                .padding(4.dp)
                .width(210.dp),
            textStyle = TextStyle(color = tint),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Number
            ),

            keyboardActions = KeyboardActions(
                onDone = {
                    barState = BarState.COLLAPSED
                }
            ),
            decorationBox = { innerTextField ->
                if (textFieldValue.text.isEmpty()) {
                    Text(
                        text = "Search",
                        color = tint.copy(alpha = 0.5f)
                    )
                }
                innerTextField()
            }
        )
        IconButton(onClick = {
            barState = BarState.COLLAPSED
            onSearchDisplayClosed(textFieldValue.text.toInt())
        }) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Locate", tint = tint,

            )
        }
    }
}

enum class BarState {
    EXPANDED,
    COLLAPSED
}

@Preview
@Composable
fun CollapsedSearchViewPreview() {
    CordsAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            ExpandedSearchView(
                modifier = Modifier.height(48.dp),
                searchDisplay = "",
                onSearchDisplayChanged = {},
                onSearchDisplayClosed = {},
                initialState= BarState.EXPANDED
            )
        }
    }
}