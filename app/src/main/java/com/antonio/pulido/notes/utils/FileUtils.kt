package com.antonio.pulido.notes.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

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



suspend fun saveBitmapAndGetUri(context: Context, bitmap: Bitmap): Uri? {
    return withContext(Dispatchers.IO) {
        val filename = "drawing_${UUID.randomUUID()}.png"
        val file = File(context.filesDir, filename)

        try {
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            }
            // Obtener la Uri usando FileProvider
            return@withContext FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: IOException) {
            e.printStackTrace()
            return@withContext null
        }
    }
}