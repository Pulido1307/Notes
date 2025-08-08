package com.antonio.pulido.notes.view.core.navigation

sealed class Screens(val route: String) {
    data object HomeScreen : Screens("homeScreen")
    data object DetailScreen : Screens("detailScreen/{noteId}") {
        fun createRoute(noteId: Int?): String {
            // Usa el prefijo de la ruta y añade el ID si no es nulo
            return "detailScreen" + if (noteId != null) "/$noteId" else ""
        }
    }
}