package com.jerry.routeapp

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.jerry.routeapp.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}

val ziRoomRouter = listOf(
    "E4:F3:E8:E9:59:AC",
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
    "56:31:F7:68:4C:AE",//my phone
    "36:02:57:40:D6:2A",//my phone
)

val room1 = listOf(
    "B8:31:B5:99:58:F1",//笔记电脑
    "B6:79:FC:76:34:0F",//手机
    "3C:7D:0A:F2:62:36", //ipadAir"
)

val room3 = listOf(
    "0A:2C:4D:CD:28:84",
    "28:39:26:94:CB:B7",
)

val room4 = listOf(
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
    var devices by remember {
        mutableStateOf(emptyList<DeviceMacData>())
    }
    var rxState by remember {
        mutableDoubleStateOf(0.0)
    }

    val stateEmptyRoom1 by remember {
        derivedStateOf {
            devices.any { room1.contains(it.mac) }.not()
        }
    }
    val stateEmptyRoom2 by remember {
        derivedStateOf {
            devices.any { myDevices.contains(it.mac) }.not()
        }
    }
    val stateEmptyRoom3 by remember {
        derivedStateOf {
            devices.any { room3.contains(it.mac) }.not()
        }
    }
    val stateEmptyRoom4 by remember {
        derivedStateOf {
            devices.any { room4.contains(it.mac) }.not()
        }
    }
    val stateZiRoomRouterInUse by remember {
        derivedStateOf {
            rxState > 20 //determine if people connected by *KB
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
    var timeState5 by remember {
        mutableStateOf("")
    }

    LaunchedEffect(stateEmptyRoom1) {
        timeState1 = getCurrentFormatTime()
    }
    LaunchedEffect(stateEmptyRoom2) {
        timeState2 = getCurrentFormatTime()
    }
    LaunchedEffect(stateEmptyRoom3) {
        timeState3 = getCurrentFormatTime()
    }
    LaunchedEffect(stateEmptyRoom4) {
        timeState4 = getCurrentFormatTime()
    }
    LaunchedEffect(stateZiRoomRouterInUse) {
        timeState5 = getCurrentFormatTime()
    }

    LaunchedEffect(devices.size) {
        if (isAtDayTime()) {
            playAlarmWhenComingBack(context, devices)
        }
    }

    LaunchedEffect(stateZiRoomRouterInUse) {
        if (isAtDayTime()) {
            playAlarmRx(context)
        }
    }

    Column(modifier = modifier.background(Color.Black)) {
        WebViewScreen { data ->
            devices = data
            rxState = data.find { ziRoomRouter.contains(it.mac) }?.rx ?: 0.0
        }
        val buildStr = buildAnnotatedString {
//            append("当前连接")
            val devicesCountWithoutMine = devices.count { ziRoomRouter.contains(it.mac).not() }
            val colorOfDeviceNum = if (devicesCountWithoutMine > 0) Color.Red else Color.DarkGray
            withStyle(
                style = SpanStyle(
                    color = colorOfDeviceNum, fontSize = 208.sp, fontWeight = FontWeight.Bold
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
            modifier = modifier.fillMaxSize()
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                RoomText(text = "1\n${timeState1}", stateEmptyRoom1)
                Text(text = timeState5, color = Color.DarkGray)
            }

            Spacer(modifier = modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                RoomText(text = "2\n${timeState2}", stateEmptyRoom = stateEmptyRoom2)
                Text(
                    text = "3\n${timeState3}", modifier = modifier
                        .background(
                            if (stateEmptyRoom3) Color.DarkGray else Color.Red
                        )
                        .size(100.dp, 140.dp)
                )
                Text(
                    text = "4\n${timeState4}", modifier = modifier
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

val scope = CoroutineScope(Dispatchers.Main)

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(onChange: (List<DeviceMacData>) -> Unit) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()

            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)
            visibility = View.GONE
        }
    }, update = { webView ->

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

                    val job = scope.launch {
                        val allData = evaluateData(dataMac, dataFlowing) //suspend
                        val dataWithMacAndRx = eliminateUnwantedDate(allData)
                        Log.d(">>>", "onPageFinished: $dataWithMacAndRx")
                        onChange(dataWithMacAndRx)
                    }
                }
            }
        }
    })
}

private suspend fun evaluateData(
    dataMac: JSONArray,
    dataFlowing: JSONArray
): MutableList<DeviceMacData> {
    return coroutineScope {
        val dataList = mutableListOf<DeviceMacData>()
        //step_1: find target IP
        for (i in 0 until dataMac.length()) {
            val dataWithMacArray = dataMac.getString(i).split(";")

            val targetIp = dataWithMacArray[0] //first:ip
            val mac = dataWithMacArray[1] //second:mac

            //step_2: find realtime uploading data via target IP
            for (j in 0 until dataFlowing.length()) {
                val ip = dataFlowing.getJSONArray(j).getString(0)
                if (ip == targetIp) {
                    val itemFlowData = dataFlowing.getJSONArray(j)
//                    val tx = itemFlowData.getDouble(6) / 8
                    val rx = itemFlowData.getDouble(8) / 8
                    dataList.add(DeviceMacData(mac, rx))
                }
            }
        }
        dataList
    }
}

private suspend fun eliminateUnwantedDate(dataMacList: MutableList<DeviceMacData>): List<DeviceMacData> {
    return withContext(Dispatchers.Default) {
        dataMacList.filterNot { data ->
            myDevices.any { it == data.mac }
                    || unknownDevices.any { it == data.mac }
                    || staticDevices.any { it == data.mac }
                    || myIphone.any { it == data.mac }
//                    || ziRoomRouter.any { it == data.mac}
        }
    }
}

data class DeviceMacData(val mac: String, val rx: Double)

fun getCurrentFormatTime(): String {
    val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
    return dateFormat.format(Date())
}

var mediaPlayer1: MediaPlayer? = null
var mediaPlayer2: MediaPlayer? = null

fun playAlarmWhenComingBack(
    context: Context,
    devices: List<DeviceMacData>,
    myRoom: Boolean = false
) {
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