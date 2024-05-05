package com.jerry.mycompose_feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MonthlyPay(
    vm: Bank2ViewModel = viewModel()
) {
    Column {
        HeaderView()
        LazyColumn {
            item {
                TitleItem(vm.sum)
            }
            items(vm.data.size) {
                PayItem(vm.data[it])
            }
        }
    }
}

@Composable
fun PayItem(data: SalaryData) {
    Column() {
        Text(
            text = data.date,
            fontSize = 18.sp,
            color = colorResource(id = R.color.gray_5b),
            modifier = Modifier
                .background(color = colorResource(id = R.color.gray_ee))
                .fillMaxWidth()
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
        )
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
            Column {
                Text(text = String.format("%02d", data.monthDay), color = colorResource(id = R.color.gray_5b), fontSize = 20.sp)
                Text(text = data.weekDay, color = colorResource(id = R.color.gray_5b), fontSize = 13.sp)
            }
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = data.cate, fontSize = 15.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = String.format("%s%,.2f", "+", data.money),
                        color = colorResource(id = R.color.red_color),
                        fontSize = 16.sp,
                        letterSpacing = 0.sp
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Row {
                    Text(
                        text = data.corporate,
                        color = colorResource(id = R.color.gray_69),
                        fontSize = 12.sp,
                        letterSpacing = 0.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = String.format("%s%,.2f", "余额:", data.balance),
                        color = colorResource(id = R.color.gray_69),
                        fontSize = 12.sp,
                        letterSpacing = 0.sp
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 360, showBackground = true)
@Composable
fun MonthlyPayPrev() {
    MonthlyPay()
}