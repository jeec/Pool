package com.jerry.mycompose.project.components.study

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.mycompose.project.entity.DailyStudyEntity
import com.jerry.mycompose.project.vm.StudyViewModel

@Composable
fun DailyTask(vm: StudyViewModel) {
    vm.studyData.forEach {
        DailyTaskItem(it)
        Divider(modifier = Modifier.padding(vertical = 20.dp))
    }
}

@Composable
fun DailyTaskItem(data: DailyStudyEntity) {
    val iconId = "icon_id"
    val secondAnnotationText = buildAnnotatedString {
        append(data.subTitle)
        appendInlineContent(iconId, alternateText = "[icon]")
    }
    val inlineContent = mapOf(
        iconId to InlineTextContent(
            Placeholder(
                16.sp, 16.sp, placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(imageVector = Icons.Default.HelpOutline, contentDescription = null)
        }

    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(7f)) {
            Text(text = data.title)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = secondAnnotationText, inlineContent = inlineContent)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                LinearProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = Color.LightGray,
                    progress = data.progress.div(100f),
                    modifier = Modifier.weight(3f)
                )
                val annotatedStr = buildAnnotatedString {
                    append(data.detailInfoFormer)
                    pushStyle(
                        style = SpanStyle(
                            color = Color.Gray
                        )
                    )
                    append(data.detailInfoLatter)
                    pop()
                }
                Text(
                    text = annotatedStr,
                    maxLines = 1,
                    modifier = Modifier
                        .weight(7f, false)
                        .padding(start = 5.dp),
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        OutlinedButton(
            onClick = {
                // TODO:  
            },
            modifier = Modifier.weight(3f, false),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "去学习", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DailyTaskPrev() {
//    DailyTask()
}