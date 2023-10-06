package com.jerry.mycompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImageSample() {
    Image(
        painter = painterResource(id = R.drawable.baseline_support_agent_24),
        contentDescription = null,
        modifier = Modifier.size(70.dp).border(1.dp, Color.Blue).background(Color.Yellow).padding(10.dp),
        colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.SrcIn)
    )
}

@Preview
@Composable
fun ImageSamplePreview() {
    ImageSample()
}