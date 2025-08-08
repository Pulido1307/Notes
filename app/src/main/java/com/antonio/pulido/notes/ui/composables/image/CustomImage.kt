package com.antonio.pulido.notes.ui.composables.image

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.io.File

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

// Para usarla en el caso de que la nota ya tenga una imagen guardada como String
@Composable
fun NoteImage(imagePath: String?, modifier: Modifier = Modifier) {
    val imageUri = Uri.fromFile(File(imagePath))
    NoteImage(imageUri = imageUri, modifier = modifier)
}