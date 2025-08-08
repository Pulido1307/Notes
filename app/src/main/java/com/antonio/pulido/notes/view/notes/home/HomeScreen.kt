package com.antonio.pulido.notes.view.notes.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.antonio.pulido.notes.R
import com.antonio.pulido.notes.ui.composables.bottomsheet.CustomBottomSheetLayout
import com.antonio.pulido.notes.ui.composables.buttons.CustomFab
import com.antonio.pulido.notes.ui.composables.card.NoteCard
import com.antonio.pulido.notes.ui.composables.scaffold.CustomScaffold
import com.antonio.pulido.notes.ui.theme.Dimensions
import com.antonio.pulido.notes.ui.theme.LocalSpacing

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navToDetailNote: (Int) -> Unit,
    spacing: Dimensions = LocalSpacing.current,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.getState<HomeViewState>().collectAsState()
    CustomScaffold(
        title = R.string.home_screen_title,
        floatingActionButton = {
            CustomFab(
                onClick = { navToDetailNote(-1) },
                icon = R.drawable.ic_add,
                text = R.string.add_button,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = spacing.spaceMedium)
                .padding(horizontal = spacing.spaceMedium),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {
            items(uiState.notes) { note ->
                AnimatedVisibility(
                    visible = true, // Siempre visible una vez que se carga
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 500, delayMillis = note.id * 50)
                    ) + fadeIn(
                        initialAlpha = 0.3f,
                        animationSpec = tween(durationMillis = 500, delayMillis = note.id * 50)
                    )
                ) {
                    NoteCard(
                        title = note.title,
                        content = note.content,
                        onClick = {
                            navToDetailNote(note.id)
                        },
                        onLongClick = {
                            homeViewModel.onEvent(HomeViewEvent.ShowDeleteDialog(note.id))
                        }
                    )
                }
            }
            item {
                Spacer(modifier = modifier.height(spacing.spaceExtraLarge))
            }
        }
    }

    when {
        uiState.showDeleteDialog -> {
            CustomBottomSheetLayout(
                onDismissDialog = { homeViewModel.onEvent(HomeViewEvent.HiddenDeleteDialog) }
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceLarge),
                    verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.confirm_delete_title),
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    )


                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        TextButton(onClick = { homeViewModel.onEvent(HomeViewEvent.HiddenDeleteDialog) }) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = stringResource(id = R.string.cancel_content_desc)
                            )
                            Spacer(Modifier.width(spacing.spaceSmall))
                            Text(stringResource(id = R.string.cancel_button))
                        }

                        Button(
                            onClick = {
                                homeViewModel.onEvent(HomeViewEvent.DeleteNote)
                            }
                        ) {
                            Icon(
                                Icons.Filled.Check,
                                contentDescription = stringResource(id = R.string.delete_note_content_desc)
                            )
                            Spacer(Modifier.width(spacing.spaceSmall))
                            Text(stringResource(id = R.string.confirm_button))
                        }
                    }

                }
            }
        }
    }
}