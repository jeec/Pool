package com.jerry.mycompose.project.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jerry.mycompose.project.components.home.ArticleItem
import com.jerry.mycompose.project.components.home.NotificationContent
import com.jerry.mycompose.project.components.home.SwipeContent
import com.jerry.mycompose.project.components.home.TopAppBar
import com.jerry.mycompose.project.components.home.VideoItem
import com.jerry.mycompose.project.vm.ArticleViewModel
import com.jerry.mycompose.project.vm.MainViewModel
import com.jerry.mycompose.project.vm.VideoViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(
    vm: MainViewModel = viewModel(),
    articleVm: ArticleViewModel = viewModel(),
    videoVm: VideoViewModel = viewModel()
) {

    Column {
        //title
        TopAppBar(modifier = Modifier.padding(horizontal = 10.dp)) {
            //search bar
            Surface(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .weight(1f),
                color = Color.Transparent.copy(alpha = 0.16f),
                contentColor = Color.White,
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(23.dp)
                    )
                    Text(
                        text = "search what you like",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            //progress
            Surface(color = Color.Transparent, contentColor = Color.White) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "studying\nprogress", fontSize = 16.sp)
                    Text(text = "36%", fontSize = 16.sp)
                    Spacer(modifier = Modifier.size(10.dp))
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                }
            }
        }
        //4 tabs
        TabRow(
            selectedTabIndex = vm.selectedTab,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            vm.tabsData.forEachIndexed { index, tabTitle ->
                Tab(selected = (index == vm.selectedTab),
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                    onClick = {
                        vm.updateTabIndex(index)
                    }) {
                    Text(
                        text = tabTitle,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
        //2 tabs
        TabRow(
            selectedTabIndex = vm.cateTab,
            indicator = {},
        ) {
            vm.cateData.forEachIndexed { index, cate ->
                LeadingIconTab(
                    selected = (index == vm.cateTab),
                    onClick = {
                        vm.updateCateIndex(index)
                    },
                    text = { Text(cate.title, fontSize = 24.sp) },
                    icon = {
                        Icon(
                            imageVector = cate.icon, contentDescription = null
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        LazyColumn() {
            //3 banner
            item {
                SwipeContent()
            }
            //4 Notification
            item {
                NotificationContent(vm)
            }
            //5 列表
            if (vm.bShowAffairs) {
                items(articleVm.articleData) {
                    ArticleItem(article = it)
                }
            } else {
                items(videoVm.videoData) {
                    VideoItem(data = it)
                }
            }
        }


    }
}

@Preview
@Composable
fun HomePagePreview() {
    HomePage()
}