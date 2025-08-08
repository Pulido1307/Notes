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
    imagePath: String,
    modifier: Modifier = Modifier
) {
    if (imagePath.isNotEmpty()) {
        val imageFile = File(imagePath)
        AsyncImage(
            model = imageFile, // Coil carga el archivo de forma asíncrona
            contentDescription = "Imagen de la nota",
            modifier = modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun NoteImage(imageUri: Uri, modifier: Modifier = Modifier) {
    // Coil recibe directamente la URI
    AsyncImage(
        model = imageUri,
        contentDescription = "Imagen de la nota",
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}