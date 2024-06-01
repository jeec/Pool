package com.jerry.mycompose_feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderView(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.mipmap.icon_back_arrow),
                contentDescription = null,
                modifier = modifier
                    .height(50.dp)
                    .width(20.dp)
            )
            Text(
                text = "查询明细",
                color = colorResource(id = R.color.gray_5b),
                fontSize = 20.sp,
                modifier = modifier.padding(start = 47.dp)
            )
            Text(text = "我的账户", color = colorResource(id = R.color.gray_5b), fontSize = 17.sp)
        }
        Divider(thickness = 1.dp, color = DividerDefaults.color.copy(alpha = 0.5f))
    }
}

@Composable
fun TitleItem(sum: Lazy<Double>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(Color.White)) {
        Row(modifier = modifier.padding(start = 10.dp, top = 10.dp, bottom = 13.dp)) {
            Image(
                painter = painterResource(id = R.mipmap.deposit_card),
                contentDescription = null,
                modifier = modifier.height(38.dp),
                contentScale = ContentScale.FillHeight
            )
            val annotated = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = TextUnit(0.0f, type = TextUnitType.Sp)
                ), block = {
                    append("借记卡(1类）")
                })

                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.gray_5b),
                        fontWeight = FontWeight.W400,
                        letterSpacing = TextUnit(0.0f, type = TextUnitType.Sp)
                    )
                ) {
                    append("尾号5439")
                }

            }
            Text(
                text = annotated,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(start = 2.dp, top = 2.dp)
            )
        }
        Divider(thickness = 1.dp, color = DividerDefaults.color.copy(alpha = 0.5f))
        val annotationStr = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.gray_5b),
                    fontWeight = FontWeight.W400,
                    letterSpacing = TextUnit(0f, TextUnitType.Sp),
                    fontSize = 13.sp
                )
            ) {
                append("当页人民币汇总")
            }

            append("   ")

            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.red_color),
                    fontWeight = FontWeight.W400,
                    letterSpacing = TextUnit(0f, TextUnitType.Sp),
                    fontSize = 13.sp,
                )
            ) {
                append(String.format("+%,.2f", sum.value))
            }

            append("   ")

            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.green_4a),
                    fontWeight = FontWeight.W400,
                    letterSpacing = TextUnit(0f, TextUnitType.Sp),
                    fontSize = 13.sp,
                )
            ) {
                append("支:-0.00")
            }
        }
        Text(text = annotationStr, modifier = modifier.padding(start = 10.dp))
    }
}

@Preview
@Composable
fun TitleItemPrev() {
    TitleItem(sum = lazyOf(23332.3))
}