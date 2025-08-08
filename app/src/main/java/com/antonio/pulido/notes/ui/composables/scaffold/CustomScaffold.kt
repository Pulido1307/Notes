package com.antonio.pulido.notes.ui.composables.scaffold

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonio.pulido.notes.ui.composables.topbar.CustomTopBar

@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    isHome: Boolean = true,
    floatingActionButton: @Composable () -> Unit,
    actionClick: () -> Unit = {},
    navigationAction: () -> Unit = {},
    contentBody: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                title = title,
                isHome = isHome,
                navigationAction = navigationAction,
                actionClick = actionClick
            )
        },
        floatingActionButton = {
            floatingActionButton()
        }
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                contentBody()
            }
        }
    }
}