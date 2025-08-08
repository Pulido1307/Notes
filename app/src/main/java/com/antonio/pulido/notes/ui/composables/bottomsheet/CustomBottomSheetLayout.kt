package com.antonio.pulido.notes.ui.composables.bottomsheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheetLayout(
    modifier: Modifier = Modifier,
    onDismissDialog: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier
            .fillMaxWidth(),
        onDismissRequest = onDismissDialog,
        containerColor = MaterialTheme.colorScheme.onPrimary,
        sheetState = rememberModalBottomSheetState(),
        shape = RoundedCornerShape(8)
    ) {
        content()
    }
}