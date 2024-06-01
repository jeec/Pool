package com.jerry.mycompose.project.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBar(modifier: Modifier = Modifier, content: @Composable (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    )
                )
            )
            .fillMaxWidth()
            .height(63.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content?.invoke()
    }
}

@Preview
@Composable
fun TopAppBarPrev() {
    TopAppBar { Text(text = "asdf") }
}