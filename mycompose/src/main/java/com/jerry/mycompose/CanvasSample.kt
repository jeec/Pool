package com.jerry.mycompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CanvasSample() {
    val bitmap = ImageBitmap.imageResource(id = R.drawable.bird)
    Canvas(modifier = Modifier.size(800.dp), onDraw = {
        drawImage(bitmap)
    })
}

@Preview
@Composable
fun CanvasSamplePreview() {
    CanvasSample()
}