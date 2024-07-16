package com.selfproject.cordsapp.presentation.locate

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selfproject.cordsapp.ui.theme.CordsAppTheme


@Composable
fun ExpandableSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    tint: Color = Color.White,
    backgroundColor:Color = Color.Gray,
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }


    Crossfade(targetState = expanded, label = "", modifier =modifier) { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpandedSearchView(
                searchDisplay = searchDisplay,
                onSearchDisplayChanged = onSearchDisplayChanged,
                onSearchDisplayClosed = onSearchDisplayClosed,
                onExpandedChanged = onExpandedChanged,
                modifier = modifier.background(color = Color.Gray),
                tint = tint,
                backgroundColor = backgroundColor
            )

            false -> CollapsedSearchView(
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint,
                backgroundColor = backgroundColor
            )
        }
    }
}

@Composable
fun SearchIcon(iconTint:Color= MaterialTheme.colorScheme.onSurfaceVariant) {
    Icon(
        Icons.Filled.Search,
        contentDescription = "search icon",
        tint = iconTint
    )
}

@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color,
    backgroundColor:Color
) {

    Row(
        modifier = modifier
            .background(color =backgroundColor)
    ) {
        IconButton(onClick = { onExpandedChanged(true) }) {
            SearchIcon(iconTint = tint)
        }
    }
}

@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color,
    backgroundColor:Color
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        modifier = modifier.background(color = backgroundColor),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onSearchDisplayChanged(it.text)
            },
            leadingIcon = {
                SearchIcon(iconTint = tint)
            },
            modifier = Modifier
                .focusRequester(textFieldFocusRequester)
                .background(color = Color.Gray),
            colors = TextFieldDefaults.colors(focusedContainerColor = backgroundColor, unfocusedContainerColor = backgroundColor),
            label = {
                Text(text = "Search", color = tint)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )


        IconButton(onClick = {
            onExpandedChanged(false)
            onSearchDisplayClosed()
        }) {
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "Locate", tint = tint
            )
        }
    }
}

@Preview
@Composable
fun CollapsedSearchViewPreview() {
    CordsAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            ExpandableSearchView(
                modifier = Modifier.height(48.dp),
                searchDisplay = "",
                onSearchDisplayChanged = {},
                onSearchDisplayClosed = {}
            )
        }
    }
}

@Preview
@Composable
fun ExpandedSearchViewPreview() {
    CordsAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            ExpandableSearchView(
                modifier = Modifier.height(48.dp),

                searchDisplay = "",
                onSearchDisplayChanged = {},
                expandedInitially = true,
                onSearchDisplayClosed = {}
            )
        }
    }
}