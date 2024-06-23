package com.jerry.routeapp

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.MediaPlayer.OnErrorListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.runtime.mutableDoubleStateOf
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
import org.json.JSONObject
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
    //branch_1 111
}

fun master(): Unit {
    //m11
}

val ziRoomRouter = listOf(
    "E4:F3:E8:E9:59:AC",
//    "32:1F:6B:37:09:54"
)

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
    "2A:42:BC:E2:88:51",//iphone
)

val roomAcrossMine = listOf(
    "B8:31:B5:99:58:F1",//笔记电脑
    "B6:79:FC:76:34:0F",//手机
    "3C:7D:0A:F2:62:36", //ipadAir"
)

val roomNextToMine = listOf(
    "D4:90:9C:D3:4E:6C",//morenfangjian 隔壁音箱
    "72:A4:7F:32:9B:BF",//华为手机
    "32:C4:63:8E:3E:C6",//未知
    "8E:9E:0A:46:EA:BD",//maybe his gf
)

//未知设备
val unknownDevices = listOf(
    "56:3A:64:E1:B1:0B",
)

val newDevices = listOf(
    "72:72:0E:60:D5:6B", //new HuaWei phone
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val devices = remember {
        mutableStateOf(emptyList<String>())
    }
    var rxState by remember {
        mutableDoubleStateOf(0.0)
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
    val stateZiRoomRouterInUse by remember {
        derivedStateOf {
            rxState > 5 //determine if people connected by 5KB
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
        Log.d(">>>", "launchedEffect: devices.value changed: ${devices.value}")
        if (isAtDayTime()) {
            playAlarmWhenComingBack(context, devices.value)
        }
    }

    LaunchedEffect(stateZiRoomRouterInUse) {
        if (isAtDayTime()) {
            playAlarmRx(context)
        }
    }

    Column(modifier = modifier.background(Color.Black)) {
        WebViewScreen { newDevicesList, newRx ->
            devices.value = newDevicesList
            rxState = newRx
        }
        val buildStr = buildAnnotatedString {
//            append("当前连接")
            val devicesCountWithoutMine =
                devices.value.subtract(myIphone).subtract(ziRoomRouter).size
            val colorOfDeviceNum = if (devicesCountWithoutMine > 0) Color.Red else Color.DarkGray
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = buildStr, color = Color.Gray)
            if (stateZiRoomRouterInUse) {
                Text(text = "AC", fontSize = 180.sp, color = Color.Red)
            }
        }

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
fun WebViewScreen(onChange: (List<String>, Double) -> Unit) {
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
                    val jsCode =
                        "javascript:var result = { var1: net_mac_list, var2: ip_stat}; JSON.stringify(result);"
                    view?.evaluateJavascript(jsCode) { wholeItem ->
                        val noEscapeCharacter = wholeItem.replace("\\", "").drop(1).dropLast(1)
                        val json = JSONObject(noEscapeCharacter)
                        val dataMac = json.getJSONArray("var1")
                        val dataFlowing = json.getJSONArray("var2")
                        var targetIp = ""
                        var rx: Double = 0.0
                        //step_1: find target IP
                        for (i in 0 until dataMac.length()) {
                            val dataWithMacArray = dataMac.getString(i).split(";")
                            val targetMac = dataWithMacArray[1] //second:mac
                            if (targetMac == ziRoomRouter.first()) {
                                targetIp = dataWithMacArray.first() //first:ip
                                break
                            }
                        }
                        //step_2: find realtime uploading data via target IP
                        for (i in 0 until dataFlowing.length()) {
                            val ip = dataFlowing.getJSONArray(i).getString(0)
                            if (ip == targetIp) {
                                val itemFlowData = dataFlowing.getJSONArray(i)
                                val tx = itemFlowData.getDouble(6) / 8
                                rx = itemFlowData.getDouble(8) / 8
                                val itemStr = itemFlowData.getString(0) + " tx: $tx /// rx: $rx"
                                Log.d(">>>", "onPageFinished: $itemStr")
                            }
                        }

                        val deviceOnlyWithMacAddress =
                            dataMac.toString().drop(1).dropLast(1)
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
                        onChange(deviceOnlyWithMacAddress, rx)
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

var mediaPlayer1: MediaPlayer? = null
var mediaPlayer2: MediaPlayer? = null

fun playAlarmWhenComingBack(context: Context, devices: List<String>, myRoom: Boolean = false) {
    val resourceAudio = if (myRoom) {
        if (devices.isEmpty()) R.raw.room_empty else R.raw.meeting_the_stars
    } else {
        if (devices.isEmpty()) R.raw.room_empty else R.raw.meeting_the_stars
    }
    mediaPlayer1?.release()
    mediaPlayer1 = MediaPlayer.create(context, resourceAudio)
    mediaPlayer1?.setOnPreparedListener {
        mediaPlayer1?.start() // 播放音频
    }
    mediaPlayer1?.setOnCompletionListener {
        it.release()
        mediaPlayer1 = null
    }
    mediaPlayer1?.setOnErrorListener { mp, what, extra ->
        Log.e(">>>", "what:$what, extra: $extra ")
        true
    }
    mediaPlayer1?.setVolume(0.1f, 0.1f)
}

fun playAlarmRx(context: Context) {
    val resourceAudio = R.raw.didi
    mediaPlayer2?.release()
    mediaPlayer2 = MediaPlayer.create(context, resourceAudio)
    mediaPlayer2?.setOnPreparedListener {
        mediaPlayer2?.start() // 播放音频
    }
    mediaPlayer2?.setOnCompletionListener {
        it.release()
        mediaPlayer2 = null
    }
    mediaPlayer2?.setVolume(1f, 1f)
}

fun isAtDayTime(): Boolean {
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