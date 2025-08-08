package com.antonio.pulido.notes.ui.composables.dialog

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DrawingDialog(
    onDrawingFinished: (Bitmap) -> Unit,
    onDismiss: () -> Unit
) {
    var currentColor by remember { mutableStateOf(Color.Black) }
    var currentStrokeWidth by remember { mutableFloatStateOf(5f) }

    // Estado del lienzo: almacena todos los trazos
    val paths = remember { mutableStateListOf<Pair<Path, Color>>() }
    var currentPath by remember { mutableStateOf(Path()) }
    var lastOffset by remember { mutableStateOf(Offset.Unspecified) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                .border(1.dp, Color.LightGray, MaterialTheme.shapes.medium)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Dibujar", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            // Controles de color y grosor (igual que antes)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val colors = listOf(Color.Black, Color.Red, Color.Blue, Color.Green, Color.Yellow)
                items(colors) { color ->
                    val isSelected = currentColor == color
                    Button(
                        onClick = { currentColor = color },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = color),
                        modifier = Modifier
                            .size(30.dp)
                            .border(
                                width = if (isSelected) 2.dp else 0.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                shape = CircleShape
                            )
                    ) {}
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Grosor:", style = MaterialTheme.typography.bodySmall)
                Slider(
                    value = currentStrokeWidth,
                    onValueChange = { currentStrokeWidth = it },
                    valueRange = 1f..50f,
                    steps = 49,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Canvas de dibujo
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                currentPath.moveTo(offset.x, offset.y)
                                lastOffset = offset
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                val newOffset = lastOffset + dragAmount
                                currentPath.quadraticTo(
                                    lastOffset.x,
                                    lastOffset.y,
                                    (newOffset.x + lastOffset.x) / 2,
                                    (newOffset.y + lastOffset.y) / 2
                                )
                                lastOffset = newOffset
                            },
                            onDragEnd = {
                                paths.add(currentPath to currentColor)
                                currentPath = Path()
                                lastOffset = Offset.Unspecified
                            }
                        )
                    }
            ) {
                // Dibujamos todos los trazos guardados
                paths.forEach { (path, color) ->
                    drawPath(
                        path = path,
                        color = color,
                        style = Stroke(width = currentStrokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
                    )
                }
                // Dibujamos el trazo actual
                drawPath(
                    path = currentPath,
                    color = currentColor,
                    style = Stroke(width = currentStrokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Botones de acción
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                TextButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "Cancelar")
                    Spacer(Modifier.width(8.dp))
                    Text("Cancelar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    val bitmap = captureDrawing(paths)
                    onDrawingFinished(bitmap)
                }) {
                    Icon(Icons.Filled.Check, contentDescription = "Guardar dibujo")
                    Spacer(Modifier.width(8.dp))
                    Text("Guardar")
                }
            }
        }
    }
}

fun captureDrawing(paths: List<Pair<Path, Color>>): Bitmap {
    val bitmap = Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)

    val paint = android.graphics.Paint().apply {
        style = android.graphics.Paint.Style.STROKE
        strokeCap = android.graphics.Paint.Cap.ROUND
        strokeJoin = android.graphics.Paint.Join.ROUND
        isAntiAlias = true
    }

    // Dibuja cada path en el bitmap
    paths.forEach { (path, color) ->
        paint.color = color.toArgb()
        canvas.drawPath(path.asAndroidPath(), paint)
    }

    return bitmap
}
