package com.selfproject.cordsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.cordsapp.domain.model.InputForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: InputForm,
    isEnabled: Boolean = true,
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
        enabled = isEnabled,
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

@Composable
fun SectionHeader(modifier: Modifier = Modifier, value: String, spacer: Boolean = true) {
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
fun TextRow(
    modifier: Modifier = Modifier,
    field: String,
    value: String,
    filedLength: Int = 20,
    horizontalSpace: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(20.dp)
) {
    Row(
        modifier = modifier.padding(start = 20.dp, top = 8.dp),
        horizontalArrangement = horizontalSpace
    ) {
        Text(
            text = createPaddedString(field, filedLength - field.length),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(text = value, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

fun createPaddedString(input: String, totalLength: Int): String {
    return input.padEnd(totalLength, ' ')
}