package com.antonio.pulido.notes.ui.composables.image

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun NoteImage(
    imageUri: Uri?,
    modifier: Modifier = Modifier
) {
    if (imageUri != null) {
        AsyncImage(
            model = imageUri,
            contentDescription = "Imagen de la nota",
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}
