package com.antonio.pulido.notes.view.notes.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.antonio.pulido.notes.R
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
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
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
                NoteCard(
                    title = note.title,
                    content = note.content
                ) {
                    navToDetailNote(note.id)
                }
            }
            item {
                Spacer(modifier = modifier.height(spacing.spaceExtraLarge))
            }
        }
    }
}