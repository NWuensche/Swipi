package com.jetbrains.kmpapp.iconButtons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InfoIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Info, contentDescription = "About")
    }
}

@Preview
@Composable
fun Preview() {
    InfoIconButton {  }
}