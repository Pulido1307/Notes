package com.antonio.pulido.notes.view.core.navigation

sealed class Screens(val route: String) {
    data object HomeScreen : Screens("homeScreen")
    data object DetailScreen : Screens("detailScreen/{noteId}") {
        fun createRoute(noteId: Int?): String {
            return "detailScreen" + if (noteId != null) "/$noteId" else ""
        }
    }
}