package com.jerry.mycompose.project.components.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.mycompose.R
import kotlinx.coroutines.launch
import kotlin.concurrent.fixedRateTimer
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeContent() {
    val virtualCount = Int.MAX_VALUE
    val actualCount = 3
    val initialPageIndex = virtualCount.div(2)

    val pagerState = rememberPagerState(
        initialPage = initialPageIndex,
        pageCount = {
            virtualCount
        })

    val fling = PagerDefaults.flingBehavior(
        state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(actualCount)
    )

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(key1 = Unit, effect = {
        val timer = fixedRateTimer(daemon = true, period = 1500, action = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        })
        onDispose {
            timer.cancel()
        }
    })


    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 40.dp),
        flingBehavior = fling,
        beyondBoundsPageCount = 10//预加载item项，不能太大，否则爆内存，out of memory!
    ) { page ->
        // Our page content
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .graphicsLayer {
                val pageOffset =
                    ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                // We animate the alpha, between 50% and 100%
                val lerp = 0f
//                = MathUtils.lerp(
//                    start = 0.95f, stop = 1f, amount = 1f - pageOffset.coerceIn(0f, 1f)
//                )
                alpha = lerp
                scaleX = lerp
                scaleY = lerp
            }) {
            Image(
                painter = painterResource(id = R.mipmap.banner_1),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(actualCount) { iteration ->
            val color =
                if (pagerState.currentPage % actualCount == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun SwipeContentPrev() {
    SwipeContent()
}