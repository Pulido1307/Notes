package com.antonio.pulido.notes.view.notes.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.antonio.pulido.notes.R
import com.antonio.pulido.notes.ui.composables.buttons.CustomFab
import com.antonio.pulido.notes.ui.composables.buttons.CustomFabMenu
import com.antonio.pulido.notes.ui.composables.scaffold.CustomScaffold
import com.antonio.pulido.notes.ui.composables.textfield.CustomTextField
import com.antonio.pulido.notes.ui.theme.Dimensions
import com.antonio.pulido.notes.ui.theme.LocalSpacing

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    spacing: Dimensions = LocalSpacing.current
) {
    CustomScaffold(
        title = R.string.add_note_screen_title,
        isHome = false,
        floatingActionButton = {
            CustomFabMenu(
                onAddDrawClick = {},
                onAddImageClick = {}
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
                .verticalScroll(rememberScrollState())
        ) {
            CustomTextField(
                title = R.string.title_note_label,
                value = "",
                placeHolder = R.string.title_note_placeholder,
                onTextChange = {

                }
            )

            CustomTextField(
                title = R.string.content_note_label,
                value = "",
                colorFocused = Color.Transparent,
                onTextChange = {

                }
            )


        }
    }
}