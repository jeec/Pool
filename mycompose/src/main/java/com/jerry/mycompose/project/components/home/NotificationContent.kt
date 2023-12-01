package com.jerry.mycompose.project.components.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jerry.mycompose.project.vm.MainViewModel
import kotlinx.coroutines.launch
import kotlin.concurrent.fixedRateTimer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationContent(vm: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        pageCount = {
            2
        }
    )

    DisposableEffect(key1 = Unit, effect = {
        val timer = fixedRateTimer(period = 2000, action = {
            coroutineScope.launch {
                val futureIndex = if (pagerState.currentPage == pagerState.pageCount - 1) {
                    pagerState.currentPage - 1
                } else {
                    pagerState.currentPage + 1
                }
                pagerState.animateScrollToPage(futureIndex)
            }
        })
        onDispose {
            timer.cancel()
        }
    })

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = 15.dp, vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "happening: ",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Cursive
        )
        VerticalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .height(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = vm.notiData[it],
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "more",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun NotificationContentPrev() {
    NotificationContent(viewModel())
}