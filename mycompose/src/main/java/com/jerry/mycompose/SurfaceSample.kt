package com.jerry.mycompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SurfaceSample() {
    Surface(
        border = BorderStroke(3.dp, Color.Red),
        shape = CutCornerShape(20.dp),
        color = Color.Yellow,
        contentColor = Color.Red,
        shadowElevation = 10.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.padding(15.dp)
        )
        Text(text = "adsf") //todo 怎么在父布局里居中？
    }
}

@Preview(showSystemUi = true)
@Composable
fun SurfaceSamplePreview() {
    SurfaceSample()
}