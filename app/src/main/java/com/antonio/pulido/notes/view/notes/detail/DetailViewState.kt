package com.antonio.pulido.notes.view.notes.detail

import android.net.Uri
import androidx.annotation.StringRes
import com.antonio.pulido.notes.view.core.base.viewstate.ViewState

data class DetailViewState(
    val successAddOrEdit: Boolean = false,

    val title: String = "",

    val content: String = "",

    val imagePath: Uri? = null,

    val imagePathRecovery: String? = "",
): ViewState()
