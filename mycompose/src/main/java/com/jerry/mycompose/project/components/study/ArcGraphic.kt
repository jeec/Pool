package com.jerry.mycompose.project.components.study

import android.util.Log
import androidx.annotation.IntRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.fontscaling.MathUtils
import androidx.compose.ui.unit.sp
import com.jerry.mycompose.project.vm.StudyViewModel
import kotlinx.coroutines.delay
import org.jetbrains.annotations.Range
import kotlin.concurrent.fixedRateTimer

@Composable
fun ArcGraphic(vm: StudyViewModel) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.padding(top = 14.dp)
    ) {
        //Arc
        Canvas(modifier = Modifier.size(screenWidthDp.dp.div(2)), onDraw = {
            drawArc(
                Brush.linearGradient(
                    colors = mutableListOf(
                        Color.White.copy(0.5f),
                        Color.White.copy(0.5f)
                    )
                ),
                startAngle = 150f,
                sweepAngle = 240f,
                useCenter = false,
                style = Stroke(25f, cap = StrokeCap.Round)
            )

            drawArc(
                Brush.linearGradient(colors = mutableListOf(Color.White, Color.White)),
                startAngle = 150f,
                sweepAngle = MathUtils.lerp(0f, 240f, (vm.points/1000f).coerceIn(0f, 1f)),
                useCenter = false,
                style = Stroke(25f, cap = StrokeCap.Round)
            )
        })
        //text
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val annotationStr = buildAnnotatedString {
                pushStyle(
                    SpanStyle(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Black,
                        fontSize = 30.sp,
                    )
                )
                append(vm.points.toString())
                pop()
                append("points")
            }
            Text(text = annotationStr, color = MaterialTheme.colorScheme.inversePrimary)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "annual grade", color = MaterialTheme.colorScheme.inversePrimary)

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ArcGraphicPrev() {
//    ArcGraphic(56)
}