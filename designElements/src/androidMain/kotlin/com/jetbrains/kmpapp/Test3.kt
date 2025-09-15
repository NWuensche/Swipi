package com.jetbrains.kmpapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

//TODO Remove
@Composable
fun TestText2(text: String) {
    Text(text)
}

@Preview
@Composable
private fun Preview() {
    TestText2("Test")
}