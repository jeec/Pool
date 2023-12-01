package com.jerry.mycompose.project.vm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jerry.mycompose.project.entity.CateData

class MainViewModel : ViewModel() {
    val tabsData = listOf<String>("politics", "law rule", "career", "loyalty")
    val cateData = listOf<CateData>(
        CateData("affairs", Icons.Default.Cloud),
        CateData("course", Icons.Default.SmartDisplay)
    )
    val notiData = listOf<String>(
        "BBC news: Premier of China is going to visit USA",
        "CNN: Human rights in China"
    )
    var selectedTab by mutableIntStateOf(0)
        private set

    var cateTab by mutableIntStateOf(0)
        private set

    var bShowAffairs: Boolean = false
        private set
        get() {
            return cateTab == 0
        }


    fun updateTabIndex(index: Int) {
        selectedTab = index
    }

    fun updateCateIndex(index: Int) {
        cateTab = index
    }


}