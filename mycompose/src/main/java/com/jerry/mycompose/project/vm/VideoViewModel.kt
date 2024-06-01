package com.jerry.mycompose.project.vm

import androidx.lifecycle.ViewModel
import com.jerry.mycompose.project.entity.VideoEntity

class VideoViewModel: ViewModel() {
    var videoData = listOf<VideoEntity>(
        VideoEntity("picUrl", "How to demonstrate romantic interest in your country?", "video course", "10:12:48"),
        VideoEntity("picUrl", "How to demonstrate romantic interest in your country?", "video course", "10:12:48"),
        VideoEntity("picUrl", "How to demonstrate romantic interest in your country?", "video course", "10:12:48"),
        VideoEntity("picUrl", "How to demonstrate romantic interest in your country?", "video course", "10:12:48"),
        VideoEntity("picUrl", "How to demonstrate romantic interest in your country?", "video course", "10:12:48"),
    )
        private set
}