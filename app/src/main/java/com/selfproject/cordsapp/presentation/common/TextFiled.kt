package com.selfproject.cordsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.selfproject.cordsapp.domain.model.InputForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: InputForm,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = 20,
    onValueChange: (InputForm) -> Unit
) {
    OutlinedTextField(
        value = value.input,
        onValueChange = { newInput ->
            if (newInput.length <= maxLength) {
                onValueChange(value.copy(input = newInput, error = null))
            }
        },
        label = { Text(text = placeholder, color = MaterialTheme.colorScheme.onSurfaceVariant) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        singleLine = true,
        maxLines = 1,
        isError = value.error != null,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant

        ),
    )
}


//@Preview(showBackground = true)
//@Composable
//fun LightModeOutLinedScreenPreview() {
//    CordsAppTheme(darkTheme = false) {
//        CustomOutlinedTextField(value = "dfsd", placeholder = "Longitude")
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DarkModeOutLinedScreenPreview() {
//    CordsAppTheme(darkTheme = true) {
//        CustomOutlinedTextField(value = "sdf", placeholder = "Longitude")
//    }
//}