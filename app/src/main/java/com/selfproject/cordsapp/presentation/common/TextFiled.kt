package com.selfproject.cordsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.selfproject.cordsapp.ui.theme.CordsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    onValueChanged: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(value) }


    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        label = { Text(text = placeholder, color = MaterialTheme.colorScheme.onSurfaceVariant) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier.background(color =  MaterialTheme.colorScheme.background),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedLabelColor =MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor =  MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTextColor =  MaterialTheme.colorScheme.onSurfaceVariant

        ),
    )
}


@Preview(showBackground = true)
@Composable
fun LightModeOutLinedScreenPreview() {
    CordsAppTheme(darkTheme = false) {
        CustomOutlinedTextField(placeholder = "Longitude",value = "dfsd")
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModeOutLinedScreenPreview() {
    CordsAppTheme(darkTheme = true) {
        CustomOutlinedTextField(placeholder = "Longitude",value="sdf")
    }
}