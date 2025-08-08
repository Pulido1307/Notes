package com.antonio.pulido.notes.ui.composables.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.antonio.pulido.notes.R
import com.antonio.pulido.notes.ui.theme.Dimensions
import com.antonio.pulido.notes.ui.theme.LocalSpacing

@Composable
fun CustomFabMenu(
    modifier: Modifier = Modifier,
    spacing: Dimensions = LocalSpacing.current,
    onAddImageClick: () -> Unit,
    onAddDrawClick: () -> Unit
) {
    var isFabMenuExpanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
        modifier = modifier.padding(spacing.spaceMedium)
    ) {
        AnimatedVisibility(
            visible = isFabMenuExpanded,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)) + expandVertically(
                expandFrom = Alignment.Top
            ),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)) + shrinkVertically(
                shrinkTowards = Alignment.Top
            )
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
            ) {
                CustomFab(
                    onClick = {
                        onAddImageClick()
                        isFabMenuExpanded = false
                    },
                    icon = R.drawable.ic_image,
                    text = R.string.image_fab,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
                CustomFab(
                    onClick = {
                        onAddDrawClick()
                        isFabMenuExpanded = false
                    },
                    icon = R.drawable.ic_draw,
                    text = R.string.draw_fab,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            }


        }

        FloatingActionButton(
            onClick = { isFabMenuExpanded = !isFabMenuExpanded },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            val icon = if (isFabMenuExpanded) R.drawable.ic_close else R.drawable.ic_add_image
            Icon(
                painter = painterResource(id = icon),
                contentDescription = stringResource(id = R.string.fab_menu_icon_content_desc)
            )
        }
    }
}

@Composable
fun CustomFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    @StringRes text: Int,
    containerColor: Color,
    contentColor: Color
) {
    ExtendedFloatingActionButton(
        containerColor = containerColor,
        contentColor = contentColor,
        onClick = onClick,
        text = {
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.labelMedium
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = stringResource(id = R.string.fab_icon_content_desc),
            )
        }
    )
}


