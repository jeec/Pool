package com.jerry.mycompose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonSample() {
//    Button(
//        onClick = {
//            Log.i(">>>", "button click")
//        },
//        colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Blue),
//        shape = RoundedCornerShape(50, 50, 50, 50),
//        contentPadding = PaddingValues(20.dp)
//    ) {
//        Text(text = "It's a button")
//    }

//    TextButton(onClick = { /*TODO*/ }) {
//        Text(text = "text button")
//    }

//    OutlinedButton(
//        onClick = { /*TODO*/ },
//        border = BorderStroke(2.dp, Color.Red)
//    ) {
//        Text(text = "outlined button")
//    }
}

@Preview
@Composable
fun ButtonSamplePreview() {
    ButtonSample()
}