package com.jerry.routeapp

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.jerry.routeapp.ui.theme.MyApplicationTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}

val staticDevices = listOf(
    "D4:90:9C:D3:4E:6C"
)

val myDevices = listOf(
    "D2:7D:1B:36:E6:83",//ipad
//    "32:1F:6B:37:09:54",//iphone
    "5C:E9:1E:89:39:B6",//my_mac
    "6C:7E:67:CD:B9:93",//work_mac
    "A8:E5:44:83:E8:69",//hw
    "52:35:95:9A:6D:28",//qp iphone
    "48:BF:6B:EB:06:4C",//qp mac
)

val myIphone = listOf(
    "32:1F:6B:37:09:54",//iphone
)

val roomAcrossMine = listOf(
    "B8:31:B5:99:58:F1",//笔记电脑
)

val roomNextToMine = listOf(
    "D4:90:9C:D3:4E:6C",//morenfangjian 隔壁音箱
    "72:A4:7F:32:9B:BF",//华为手机
    "32:C4:63:8E:3E:C6",//未知
    "B6:79:FC:76:34:0F",//手机
)

//未知设备
val unknownDevices = listOf(
    "56:3A:64:E1:B1:0B"
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val devices = remember {
        mutableStateOf(emptyList<String>())
    }

    val stateEmptyRoom1 by remember {
        derivedStateOf {
            devices.value.intersect(roomAcrossMine).isEmpty()
        }
    }
    val stateEmptyRoom2 by remember {
        derivedStateOf {
            devices.value.intersect(myDevices).isEmpty()
        }
    }
    val stateEmptyRoom3 by remember {
        derivedStateOf {
            devices.value.intersect(myDevices).isEmpty()
        }
    }
    val stateEmptyRoom4 by remember {
        derivedStateOf {
            devices.value.intersect(roomNextToMine).isEmpty()
        }
    }

    var timeState1 by remember {
        mutableStateOf("")
    }
    var timeState2 by remember {
        mutableStateOf("")
    }
    var timeState3 by remember {
        mutableStateOf("")
    }
    var timeState4 by remember {
        mutableStateOf("")
    }

    LaunchedEffect(stateEmptyRoom1) {
        timeState1 = getCurrentFormatTime()
    }
    LaunchedEffect(stateEmptyRoom2) {
        timeState2 = getCurrentFormatTime()
//        if (stateEmptyRoom2) {
//            playAlarmWhenComingBack(context, devices.value, true)
//        }
    }
    LaunchedEffect(stateEmptyRoom3) {
        timeState3 = getCurrentFormatTime()
    }
    LaunchedEffect(stateEmptyRoom4) {
        timeState4 = getCurrentFormatTime()
    }

    LaunchedEffect(devices.value) {
        if (isAtDay()) {
            playAlarmWhenComingBack(context, devices.value)
        }
    }

    Column(modifier = modifier.background(Color.Black)) {
        WebViewScreen { newDevicesList ->
            devices.value = newDevicesList
        }
        val buildStr = buildAnnotatedString {
//            append("当前连接")
            val devicesCountWithoutMine = devices.value.subtract(myIphone).size
            val colorOfDeviceNum = if (devicesCountWithoutMine>0) Color.Red else Color.DarkGray
            withStyle(
                style = SpanStyle(
                    color = colorOfDeviceNum,
                    fontSize = 208.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(devicesCountWithoutMine.toString())
            }
//            append("台")
        }
        Text(text = buildStr, color = Color.Gray)

        //房间布局
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            RoomText(text = "1\n${timeState1}", stateEmptyRoom1)

            Spacer(modifier = modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                RoomText(text = "2\n${timeState2}", stateEmptyRoom = stateEmptyRoom2)
                Text(
                    text = "3\n${timeState3}",
                    modifier = modifier
                        .background(
                            if (stateEmptyRoom3) Color.DarkGray else Color.Red
                        )
                        .size(100.dp, 140.dp)
                )
                Text(
                    text = "4\n${timeState4}",
                    modifier = modifier
                        .background(
                            if (stateEmptyRoom4) Color.DarkGray else Color.Red
                        )
                        .size(140.dp)
                )
            }
        }
    }
}

@Composable
fun RoomText(text: String, stateEmptyRoom: Boolean) {
    Text(
        text = text,
        modifier = Modifier
            .background(if (stateEmptyRoom) Color.DarkGray else Color.Red)
            .size(140.dp)
    )
}

@Composable
fun WebViewScreen(onChange: (List<String>) -> Unit) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
                visibility = View.GONE
            }
        },
        update = { webView ->
            webView.loadUrl("http://192.168.1.2/ip_statistics.asp?sort_turn=0&sort_item=8&max_row=9")
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    //val jsCode = "javascript:var result = { var1: var1, var2: var2, var3: var3 }; JSON.stringify(result);";

                    view?.evaluateJavascript(
                        "(function() { return net_mac_list; })();"
                    ) { wholeItem ->
                        val deviceOnlyWithMacAddress =
                            wholeItem.drop(1).dropLast(1)
                                .split(",")
                                .toMutableList()
                                .map {
                                    it.split(";")[1]
                                }
                                .subtract(myDevices)
                                .subtract(unknownDevices)
                                .subtract(staticDevices)
                                .toList()

                        Log.d(">>>", "onPageFinished: $deviceOnlyWithMacAddress")
                        onChange(deviceOnlyWithMacAddress)
                    }
                }
            }
        }
    )
}

fun getCurrentFormatTime(): String {
    val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
    return dateFormat.format(Date())
}

fun playAlarmWhenComingBack(context: Context, devices: List<String>, myRoom: Boolean = false) {
    val resourceAudio = if (myRoom) {
        if (devices.isEmpty()) R.raw.room_empty else R.raw.meeting_the_stars
    } else {
        if (devices.isEmpty()) R.raw.room_empty else R.raw.meeting_the_stars
    }
    val mediaPlayer = MediaPlayer.create(context, resourceAudio)
    mediaPlayer?.start() // 播放音频
}

fun isAtDay(): Boolean {
    val hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return hourOfDay in 8..24
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}