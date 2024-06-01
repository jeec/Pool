package com.jerry.mycompose

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

const val REF_TEXT = "text"
const val REF_ICON = "icon"
const val REF_IMAGE = "image"

@Composable
fun ConstraintLayoutSample() {
    val constraintsSet = ConstraintSet() {
        val textRef = createRefFor(REF_TEXT)
        val iconRef = createRefFor(REF_ICON)
        val imageRef = createRefFor(REF_IMAGE)

        constrain(textRef) {
            start.linkTo(parent.start, margin = 10.dp)
            top.linkTo(parent.top, margin = 5.dp)
        }

        constrain(iconRef) {
            start.linkTo(textRef.end, goneMargin = 5.dp)
            top.linkTo(textRef.bottom, goneMargin = 5.dp)
        }

        constrain(imageRef) {
            start.linkTo(iconRef.end, goneMargin = 5.dp)
            top.linkTo(iconRef.bottom, goneMargin = 5.dp)
        }
    }
    ConstraintLayout(constraintSet = constraintsSet) {
        Text(
            text = "constraintsLayout",
            modifier = Modifier.layoutId(REF_TEXT)
        )
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
            modifier = Modifier.layoutId(
                REF_ICON
            )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.layoutId(
                REF_IMAGE
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ConstraintsLayoutSamplePreview() {
    ConstraintLayoutSample()
}