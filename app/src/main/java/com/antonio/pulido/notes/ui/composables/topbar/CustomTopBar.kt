package com.antonio.pulido.notes.ui.composables.topbar

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.antonio.pulido.notes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    @StringRes title: Int,
    isHome: Boolean,
    navigationAction: () -> Unit = {},
    actionClick: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary,
            titleContentColor = MaterialTheme.colorScheme.onSecondary,
        ),
        navigationIcon = {
            IconButton(onClick = navigationAction) {
                Icon(
                    painter = painterResource(id = if (isHome) R.drawable.ic_home else R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.navigation_icon_content_desc),

                    )
            }
        },
        title = {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            if (!isHome) {
                IconButton(onClick = actionClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = stringResource(id = R.string.navigation_icon_content_desc),
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    )
}