package com.antonio.pulido.notes.view.notes.detail

import android.net.Uri
import androidx.annotation.StringRes
import com.antonio.pulido.notes.R
import com.antonio.pulido.notes.view.core.base.viewstate.ViewState

data class DetailViewState(
    val successAddOrEdit: Boolean = false,
    @StringRes val titleScreen: Int = R.string.add_note_screen_title,
    val id: Int? = null,
    val title: String = "",
    val content: String = "",
    val imagePath: Uri? = null,
    val imagePathRecovery: String? = "",
): ViewState()
