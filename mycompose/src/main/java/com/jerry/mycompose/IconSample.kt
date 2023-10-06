package com.jerry.mycompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IconSample() {
//    Icon(imageVector = Icons.Filled.AccountBox, contentDescription = null)
    Icon(painter = painterResource(id = R.drawable.baseline_support_agent_24), contentDescription = null)
}

@Preview
@Composable
fun IconSamplePreview() {
    IconSample()
}