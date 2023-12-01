package com.jerry.mycompose.project.components.study

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun ChartView() {
    val lines = 5
    val heightPerLine = 25
    val radius = 2.5
    val points = 7

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height((heightPerLine * (lines - 1)).dp)
        .background(Color.White), onDraw = {
        //画背景横线
        for (i in 0 until lines) {
            val y = (i * heightPerLine).dp.toPx()
            drawLine(
                Color.LightGray,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 2.5f
            )
        }

        val dotData = mutableListOf<Pair<Float, Float>>()

        //画圆圈
        for (i in 0 until points) {
            val averageWidth = size.width / points
            val x = averageWidth / 2 + i * averageWidth - radius.dp.toPx()
            val y = Random.nextInt(IntRange(0, 100)).dp.toPx()
            drawCircle(
                color = Color.Blue,
                radius = radius.dp.toPx(),
                center = Offset(x, y),
                style = Stroke(width = 2f)
            )
            dotData.add(Pair(x, y))
        }

        //连线
        for (i in 1..dotData.size - 1) {
            val startOffset = Offset(dotData.get(i - 1).first, dotData.get(i - 1).second)
            val endOffset = Offset(dotData.get(i).first, dotData.get(i).second)
            drawLine(Color.Blue, start = startOffset, endOffset)
        }
    })
}

@Preview(showSystemUi = true)
@Composable
fun ChartViewPrev() {
    ChartView()
}