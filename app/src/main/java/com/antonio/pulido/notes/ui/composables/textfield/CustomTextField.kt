package com.antonio.pulido.notes.ui.composables.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    @StringRes title: Int,
    @StringRes placeHolder: Int? = null,
    @StringRes supportingText: Int? = null,
    colorFocused: Color = MaterialTheme.colorScheme.onSecondary
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onTextChange,
        isError = supportingText != null,
        label = {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        placeholder = {
            if (placeHolder != null){
                Text(
                    text = stringResource(id = placeHolder),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.tertiary
                    )
                )
            }
        },
        textStyle = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSecondary
        ),
        supportingText = {
            if (supportingText != null) {
                Text(
                    text = stringResource(id = supportingText),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.tertiary
                    )
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = colorFocused,
            unfocusedIndicatorColor = colorFocused
        )
    )
}