package com.jerry.mycompose.project.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jerry.mycompose.project.entity.DailyStudyEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class StudyViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            delay(1000)
            repeat(638) {
                delay(1)
                points++
            }
        }
    }

    var points by mutableIntStateOf(0)
        private set

    var calendar = listOf<String>("07.08", "07.09", "07.10", "07.11", "07.12", "07.13", "今日")
        private set

    var studyData = listOf<DailyStudyEntity>(
        DailyStudyEntity(
            "文章学习",
            "10积分/有效阅读一篇文章",
            Random.nextInt(0, 100),
            "已获得5积分",
            "/每日上限100积分"
        ),
        DailyStudyEntity(
            "登录",
            "15积分/每日首次登录",
            Random.nextInt(0, 100),
            "已获得50积分",
            "/每日上限100积分"
        ),
        DailyStudyEntity(
            "视听学习",
            "40积分/有效观看视频或收听音频",
            Random.nextInt(0, 100),
            "已获得45积分",
            "/每日上限100积分"
        )
    )
        private set

    fun updatePoints() {
        points = 80
    }
}