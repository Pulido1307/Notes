package com.antonio.pulido.notes.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

// En tu ViewModel o en una clase de utilidad
fun saveImageFromUri(context: Context, uri: Uri): String? {
    // Genera un nombre de archivo único
    val filename = "image_${System.currentTimeMillis()}.jpg"
    val directory = context.filesDir // Almacenamiento interno
    val file = File(directory, filename)

    return try {
        // Abre un InputStream desde la URI
        val inputStream = context.contentResolver.openInputStream(uri)

        // Abre un OutputStream hacia tu nuevo archivo
        val outputStream = FileOutputStream(file)

        // Copia los datos del InputStream al OutputStream
        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        // Retorna la ruta del archivo guardado
        file.absolutePath
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}