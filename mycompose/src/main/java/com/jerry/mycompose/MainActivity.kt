package com.jerry.mycompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.mycompose.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android121")
                    Log.i(">>>", "asdf")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    SelectionContainer {
//        Text(
//            text = name,
//            modifier = modifier,
//            color = Color.Red,
//            fontSize = 16.sp,
//            textDecoration = TextDecoration.combine(
//                listOf(
//                    TextDecoration.LineThrough,
//                    TextDecoration.Underline,
//                )
//            ),
//            textAlign = TextAlign.Start,
//            overflow = TextOverflow.Ellipsis,
//            maxLines = 2,
//            lineHeight = 40.sp,
//            letterSpacing = 4.sp,
//            softWrap = true//该属性会影响maxLines
//        )
//    }

    val annotatedString = buildAnnotatedString {
        append("Let's start learning it: ")

        pushStringAnnotation("firstUrl", "https://www.baidu.com")
        withStyle(
            style = SpanStyle(
                Color.Red,
                textDecoration = TextDecoration.Underline,
                fontSize = 17.sp
            )
        ) {
            append("Android Jetpack")
        }
        pop()

        append("  and  ")

        pushStringAnnotation("secondUrl", "https://www.google.com")
        withStyle(
            style = SpanStyle(
                Color.Red,
                textDecoration = TextDecoration.Underline,
                fontSize = 17.sp
            )
        ) {
            append("Framework")
        }
        pop()
    }

    ClickableText(text = annotatedString, onClick = { clickPositionIndex ->
//        Log.i(">>>", "click: $clickPositionIndex")
        annotatedString.getStringAnnotations("firstUrl", clickPositionIndex, clickPositionIndex)
            .firstOrNull()?.let {
                Log.i(">>>", "${it.item}")
            }
        annotatedString.getStringAnnotations("secondUrl", clickPositionIndex, clickPositionIndex)
            .firstOrNull()?.let {
                Log.i(">>>", "${it.item}")
            }
    })
}

@Preview(showBackground = true, widthDp = 200, device = Devices.PIXEL_4)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting(stringResource(id = R.string.text_str))
    }
}
