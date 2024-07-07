package com.jerry.mycompose_feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.jerry.mycompose_feature.Ids.id_card_num_time
import com.jerry.mycompose_feature.Ids.id_cate
import com.jerry.mycompose_feature.Ids.id_company
import com.jerry.mycompose_feature.Ids.id_money_amount
import com.jerry.mycompose_feature.Ids.id_money_balance
import com.jerry.mycompose_feature.ui.theme.MyApplicationTheme

class BankActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.White
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        //head
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(id = R.drawable.backwards), contentDescription = null, modifier = Modifier.height(23.dp))
            Row(
                modifier = Modifier
                    .width(160.dp)
                    .background(
                        color = colorResource(id = R.color.search_title_bg),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(vertical = 8.dp)
                    .padding(start = 10.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.search_icon), contentDescription = null, modifier = Modifier
                    .height(20.dp)
                    .padding(top = 3.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "搜索关键词", color = colorResource(id = R.color.gray_93))
            }
//            Spacer(modifier = Modifier.width(2.dp))
            Image(painter = painterResource(id = R.drawable.consultant), contentDescription = null, modifier = Modifier.height(23.dp))
            Image(painter = painterResource(id = R.drawable.more_icon), contentDescription = null, modifier = Modifier.width(25.dp))
        }

        Divider(modifier.padding(top = 10.dp, bottom = 20.dp), color = colorResource(id = R.color.gray_93).copy(alpha = 0.2f), thickness = 0.5.dp)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Row {
                Text(text = "选择时间", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
            Surface(
                contentColor = colorResource(
                    id = R.color.red_color
                )
            ) {
                Row {
                    Text(text = "尾号5439", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
            Surface(
                contentColor = colorResource(
                    id = R.color.red_color
                )
            ) {
                Row {
                    Text(text = "筛选①", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(30))
                .background(
                    color = colorResource(
                        id = R.color.red_color_bg
                    )
                )
                .padding(horizontal = 10.dp, vertical = 10.dp)

        ) {
            Text(text = "全部汇款总笔数:14", color = Color.Gray, fontSize = 15.sp)
            Icon(imageVector = Icons.Default.Info, contentDescription = null)
            Text(text = "收")
            Text(text = "¥114.78", fontFamily = FontFamily.Serif, color = colorResource(id = R.color.red_color))
            Text(text = "支")
            Text(text = "¥0,00")
        }

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn() {
            item {
                MoneyItem()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun GreetingPreview2() {
    MyApplicationTheme {
        Greeting2("Android")
    }
}

@Composable
fun MoneyItem() {
    val constraintSet = ConstraintSet {
        val id_top_date = createRefFor(Ids.id_top_date)
        val id_left_date = createRefFor(Ids.id_left_date)
        val id_leading_icon = createRefFor(Ids.id_leading_icon)
        val id_cate = createRefFor(Ids.id_cate)
        val id_company = createRefFor(Ids.id_company)
        val id_card_num_time = createRefFor(Ids.id_card_num_time)
        val id_money_amount = createRefFor(Ids.id_money_amount)
        val id_money_balance = createRefFor(Ids.id_money_balance)

        constrain(id_top_date) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
        constrain(id_left_date) {
            start.linkTo(parent.start)
            top.linkTo(id_top_date.bottom)
            bottom.linkTo(parent.bottom)
            height = Dimension.fillToConstraints
        }
        constrain(id_leading_icon) {
            start.linkTo(id_left_date.end, margin = 10.dp)
            top.linkTo(id_top_date.bottom, margin = 10.dp)
        }
        constrain(id_cate) {
            start.linkTo(id_leading_icon.end, margin = 10.dp)
            top.linkTo(id_leading_icon.top)
        }
        constrain(id_company) {
            start.linkTo(id_cate.start)
            top.linkTo(id_cate.bottom)
        }
        constrain(id_card_num_time) {
            start.linkTo(id_cate.start)
            top.linkTo(id_company.bottom, margin = 15.dp)
        }
        constrain(id_money_amount) {
            baseline.linkTo(id_cate.baseline)
            end.linkTo(parent.end, margin = 15.dp)
        }
        constrain(id_money_balance) {
            baseline.linkTo(id_card_num_time.baseline)
            end.linkTo(parent.end, margin = 15.dp)
        }
    }

    ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "2023年5月", modifier = Modifier
                .layoutId(Ids.id_top_date)
                .background(
                    color = colorResource(
                        id = R.color.item_title_bg
                    )
                )
                .padding(start = 15.dp)
                .padding(vertical = 10.dp)
        )

        val annotationStr = buildAnnotatedString {
            pushStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            append("10")
            pop()
            append("\n周三")
        }

        Text(
            text = annotationStr, modifier = Modifier
                .layoutId(Ids.id_left_date)
                .background(
                    color = colorResource(
                        id = R.color.item_date_bg
                    )
                )
                .padding(start = 14.dp, end = 10.dp)
                .padding(top = 10.dp),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.item_date_text)
        )
        Image(
            painterResource(id = R.drawable.icon_bank),
            contentDescription = null,
            modifier = Modifier
                .layoutId(Ids.id_leading_icon)
                .size(35.dp)
        )
        Text(
            text = "工资",
            modifier = Modifier.layoutId(id_cate),
            color = colorResource(id = R.color.item_title),
            fontWeight = FontWeight.Bold
        )
        Text(text = "asfasdf", modifier = Modifier.layoutId(id_company))
        Text(
            text = "工行借记卡 5439 21:23:13",
            modifier = Modifier.layoutId(id_card_num_time),
            color = colorResource(
                id = R.color.item_bank_text
            ),
            fontSize = 14.sp
        )
        Text(
            text = "+6,928.57",
            modifier = Modifier.layoutId(id_money_amount),
            color = colorResource(
                id = R.color.red_color
            ), fontSize = 23.sp,
            fontWeight = FontWeight.SemiBold,
        )
        val annotation = buildAnnotatedString {
            append("余额:")
            pushStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            append("35,456.23")
            pop()
        }
        Text(
            text = annotation,
            modifier = Modifier.layoutId(id_money_balance),
            color = colorResource(
                id = R.color.item_balance_text
            ),
            fontSize = 14.sp
        )
    }
}

object Ids {
    const val id_top_date = 1
    const val id_left_date = 2
    const val id_leading_icon = 3
    const val id_cate = 4
    const val id_company = 5
    const val id_card_num_time = 6
    const val id_money_amount = 7
    const val id_money_balance = 8
}
















