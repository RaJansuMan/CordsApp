package com.selfproject.cordsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selfproject.cordsapp.ui.theme.CordsAppTheme


@Composable
fun CustomDropdownSpinner(
    modifier: Modifier=Modifier,
    title: String? = null,
    items: List<String>,
    selectedItem: String? = null,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedItem ?: items[0]) }

    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        title?.let {
            Text(text = it, modifier = Modifier.padding(bottom = 4.dp), color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.SemiBold)
        }

        Box(
            modifier = Modifier
                .clickable { expanded = true }
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.onSurfaceVariant)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier .fillMaxWidth()
                    .padding(8.dp)  .background(color = MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = selectedText, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                    , tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            selectedText = item
                            onItemSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LightModeDropDownScreenPreview() {
    CordsAppTheme(darkTheme = false) {
        CustomDropdownSpinner(
            title = "ZoneNo",
            items = listOf("A", "B", "C"),
            onItemSelected = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModeDropDownScreenPreview() {
    CordsAppTheme(darkTheme = true) {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {


            CustomDropdownSpinner(
                title = "ZoneNo",
                items = listOf("A", "B", "C"),
                onItemSelected = {})
        }
    }
}