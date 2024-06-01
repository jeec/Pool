package com.jerry.mycompose.project.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jerry.mycompose.R
import com.jerry.mycompose.project.components.home.TopAppBar
import com.jerry.mycompose.project.vm.MineViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MinePage(
    vm: MineViewModel = viewModel()
) {
    Column {
        TopAppBar {
            Text(text = "我的", color = Color.White, fontSize = 18.sp)
        }
        LazyColumn() {
            //头像
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bird),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .size(62.dp)
                            .clip(
                                CircleShape
                            )
                            .clickable {
                                Log.i(">>>", "click")
                            }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier
                            .height(62.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "188****9123", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(text = "已坚持学习12天", fontSize = 15.sp)
                    }
                }
            }
            //菜单
            itemsIndexed(vm.data) { index, item ->
                Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 14.dp)) {
                    Icon(imageVector = item.icon, contentDescription = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = item.title)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
                }
                if (index == 3) {
                    Divider(thickness = 14.dp, color = DividerDefaults.color.copy(0.3f))
                } else {
                    Divider(color = DividerDefaults.color.copy(0.3f))
                }

            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MinePagePreview() {
    MinePage()
}