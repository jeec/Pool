package com.jerry.mycompose.project.vm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.DesktopAccessDisabled
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PhoneCallback
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerticalSplit
import androidx.lifecycle.ViewModel
import com.jerry.mycompose.project.entity.MenuEntity

class MineViewModel: ViewModel() {
    val data = listOf<MenuEntity>(
        MenuEntity(Icons.Default.DesktopAccessDisabled, "学习积分"),
        MenuEntity(Icons.Default.RecordVoiceOver, "浏览记录"),
        MenuEntity(Icons.Default.Menu, "学习档案"),
        MenuEntity(Icons.Default.AddComment, "常见问题"),
        MenuEntity(Icons.Default.VerticalSplit, "版本信息"),
        MenuEntity(Icons.Default.Settings, "个人设置"),
        MenuEntity(Icons.Default.PhoneCallback, "联系我们"),
    )
}