package com.antonio.pulido.notes.view.notes.detail

import android.app.Application
import com.antonio.pulido.notes.view.core.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {
}