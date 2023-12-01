package com.jerry.mycompose.project.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jerry.mycompose.project.components.study.ArcGraphic
import com.jerry.mycompose.project.components.study.ChartView
import com.jerry.mycompose.project.components.study.DailyTask
import com.jerry.mycompose.project.vm.StudyViewModel

@Composable
fun StudyPage(vm: StudyViewModel = viewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(MaterialTheme.colorScheme.primary, Color.White))
            )
    ) {
        Text(
            text = "Daily Task",
            color = MaterialTheme.colorScheme.inversePrimary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "study period: 2022.12.23-2023.04.12",
            color = MaterialTheme.colorScheme.inversePrimary
        )
        //arc graphic
        LazyColumn(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item {
                    ArcGraphic(vm)
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-20).dp)
                    ) {
                        Column {
                            Text(text = "13500", color = MaterialTheme.colorScheme.inversePrimary)
                            Text(
                                text = "学年规定积分",
                                color = MaterialTheme.colorScheme.inversePrimary
                            )
                        }
                        Column {
                            Text(text = "13500", color = MaterialTheme.colorScheme.inversePrimary)
                            Text(text = "还差", color = MaterialTheme.colorScheme.inversePrimary)
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )
                            .background(Color.White)
                            .fillParentMaxSize()
                            .padding(horizontal = 10.dp, vertical = 15.dp)
                    ) {
                        Text(text = "学习明细", fontWeight = FontWeight.W800, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "最近一周获得积分情况", color = Color.Gray, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        //折线图
                        ChartView()
                        Spacer(modifier = Modifier.height(5.dp))
                        //日期
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            vm.calendar.forEach {
                                Text(
                                    text = it,
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        //今日提醒
                        Text(
                            text = "今日获得5积分，快去完成下面的任务吧！",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(5.dp)
                                )
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(8.dp)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        //每日任务
                        DailyTask(vm)
                    }
                }
            })
    }
}

@Preview(showSystemUi = true)
@Composable
fun StudyPagePrev() {
//    StudyPage()
}