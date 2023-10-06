package com.jerry.mycompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSample() {
    Box(contentAlignment = Alignment.Center) {
        var inputData by remember {
            mutableStateOf("")
        }
        TextField(
            value = inputData,
            onValueChange = {
                inputData = it
            },
            label = {
                Text(text = "姓名")
            },
            placeholder = {
                Text(text = "请输入您的姓名")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Go
            ),
            isError = true

        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TextFieldSamplePreview() {
    TextFieldSample()
}