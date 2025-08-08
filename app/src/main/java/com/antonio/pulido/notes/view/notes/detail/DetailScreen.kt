package com.antonio.pulido.notes.view.notes.detail

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.antonio.pulido.notes.R
import com.antonio.pulido.notes.ui.composables.buttons.CustomFabMenu
import com.antonio.pulido.notes.ui.composables.dialog.DrawingDialog
import com.antonio.pulido.notes.ui.composables.image.NoteImage
import com.antonio.pulido.notes.ui.composables.scaffold.CustomScaffold
import com.antonio.pulido.notes.ui.composables.textfield.CustomTextField
import com.antonio.pulido.notes.ui.theme.Dimensions
import com.antonio.pulido.notes.ui.theme.LocalSpacing

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    spacing: Dimensions = LocalSpacing.current,
    onBack: () -> Unit,
    noteId: Int,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by detailViewModel.getState<DetailViewState>().collectAsState()
    val pickImageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            detailViewModel.onEvent(DetailViewEvent.OnChangeImageSelected(it))
        }
    }

    LaunchedEffect(true) {
        if (noteId != -1) {
            detailViewModel.onEvent(DetailViewEvent.GetNoteById(noteId))
        }
    }

    CustomScaffold(
        title = uiState.titleScreen,
        isHome = false,
        floatingActionButton = {
            CustomFabMenu(
                onAddDrawClick = {
                    detailViewModel.onEvent(DetailViewEvent.ShowDrawingDialog)
                },
                onAddImageClick = {
                    pickImageLauncher.launch("image/*")
                }
            )
        },
        actionClick = { detailViewModel.onEvent(DetailViewEvent.SaveNote) },
        navigationAction = onBack
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
                .verticalScroll(rememberScrollState())
        ) {
            CustomTextField(
                title = R.string.title_note_label,
                value = uiState.title,
                placeHolder = R.string.title_note_placeholder,
                onTextChange = {
                    detailViewModel.onEvent(DetailViewEvent.OnChangeTitle(it))
                }
            )

            CustomTextField(
                title = R.string.content_note_label,
                value = uiState.content,
                colorFocused = Color.Transparent,
                onTextChange = {
                    detailViewModel.onEvent(DetailViewEvent.OnChangeContent(it))
                }
            )

            if (noteId == -1 && uiState.imagePath != null) {
                NoteImage(
                    imageUri = uiState.imagePath!!
                )
            } else if (noteId != -1) {
                if (uiState.imagePathRecovery != null) {
                    NoteImage(
                        imagePath = uiState.imagePathRecovery ?: ""
                    )
                } else {
                    NoteImage(
                        imageUri = uiState.imagePath!!
                    )
                }
            }
            Spacer(modifier = modifier.height(spacing.spaceExtraLarge))
        }
    }

    when {
        uiState.successAddOrEdit -> {
            LaunchedEffect(true) {
                onBack()
            }
        }

        uiState.showDrawingDialog -> {
            DrawingDialog(
                onDrawingFinished = { detailViewModel.onEvent(DetailViewEvent.OnChangeDrawingFinished(it))},
                onDismiss = {
                    detailViewModel.onEvent(DetailViewEvent.HiddenDrawingDialog)
                }
            )
        }
    }
}