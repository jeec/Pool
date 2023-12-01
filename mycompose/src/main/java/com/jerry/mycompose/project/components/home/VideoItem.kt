package com.jerry.mycompose.project.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.jerry.mycompose.R
import com.jerry.mycompose.project.components.home.Ids.id_desc
import com.jerry.mycompose.project.components.home.Ids.id_divider
import com.jerry.mycompose.project.components.home.Ids.id_duration
import com.jerry.mycompose.project.components.home.Ids.id_pic
import com.jerry.mycompose.project.components.home.Ids.id_title
import com.jerry.mycompose.project.entity.VideoEntity

object Ids {
    const val id_pic = 1
    const val id_title = 2
    const val id_desc = 3
    const val id_duration = 4
    const val id_divider = 5
}

@Composable
fun VideoItem(data: VideoEntity) {
    val constraintSet = ConstraintSet {
        val pic = createRefFor(id_pic)
        val title = createRefFor(id_title)
        val desc = createRefFor(id_desc)
        val duration = createRefFor(id_duration)
        val divider = createRefFor(id_divider)

        constrain(pic) {
            start.linkTo(parent.start, margin = 15.dp)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        constrain(title) {
            start.linkTo(pic.end, margin = 10.dp)
            top.linkTo(pic.top, margin = 4.dp)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
        constrain(desc) {
            start.linkTo(pic.end, margin = 10.dp)
            bottom.linkTo(pic.bottom, margin = 4.dp)
        }
        constrain(duration) {
            start.linkTo(desc.end, margin = 20.dp)
            bottom.linkTo(desc.bottom)
        }
        constrain(divider) {
            start.linkTo(pic.start)
            top.linkTo(pic.bottom, margin = 15.dp)
            end.linkTo(parent.end)
        }
    }
    ConstraintLayout(constraintSet, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.bird),
            contentDescription = null,
            modifier = Modifier.layoutId(id_pic).clip(RoundedCornerShape(10.dp))
        )
        Text(
            text = data.title,
            modifier = Modifier.layoutId(id_title),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = data.desc,
            modifier = Modifier.layoutId(id_desc),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = data.duration,
            modifier = Modifier.layoutId(id_duration),
            style = MaterialTheme.typography.labelMedium
        )
        Divider(modifier = Modifier.layoutId(id_divider))
    }
}

@Preview
@Composable
fun VideoItemPre() {
    VideoItem(
        data = VideoEntity(
            "picUrl",
            "How to demonstrate romantic interest in your country?",
            "video course",
            "10:12:48"
        )
    )
}