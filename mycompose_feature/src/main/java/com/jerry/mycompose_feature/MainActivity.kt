package com.jerry.mycompose_feature

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.mycompose_feature.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

//                    LauncedEffectTest()
//                    ColorScreen()
//                    LauchedEffectTest()
//                    OneButton()
//                    Greeting("Android")
//                    SideEffectM()
//                    Log.i(">>>", data.value.toString())
//                    fixedRateTimer(period = 5000, action = {
//                        key1 = Random.nextInt().toString()
//                    })
//                    Column {
//                        HelloContent()
//                        HelloContent()
//                    }
//                    HoistStateButtonCounter()
//                    WrapDerivedStateTest()
//                    T1()
//                    SnapshotTest()
                    LauchedEffect()
                }
            }
        }
    }
}

var myNum = 0

@Composable
fun LauchedEffect() {
    var mState by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = mState, key2 = myNum, block = {
//        delay(3000)
        Log.d(">>>", "LauchedEffect: $myNum")
    })
//    Log.d(">>>", "LauchedEffect: $mState")
    Column {
        TextButton(onClick = {
            mState++
            myNum++
        }) {
            Text(text = "text button")
        }
    }
}

@Composable
fun SnapshotTest() {
    var myState by remember {
        mutableStateOf(0)
    }

    val myFlow = snapshotFlow {

    }

    LaunchedEffect(key1 = Unit, block = {
        Log.d(">>>", "SnapshotTest: ")
        snapshotFlow {
            myState
        }.map {
            it
        }.collect {
            Log.e(">>>", "$it")
        }
    })

    LaunchedEffect(key1 = Unit, block = {
        repeat(10) {
//            delay(1)
            myState++
            Log.d(">>>", "$myState")
        }
    })

    Column {
        ClickableText(text = buildAnnotatedString {
            pushStyle(
                style = SpanStyle(
                    fontSize = 25.sp,
                    color = Color.DarkGray
                )
            )
            append("abc")
            pop()
        }, onClick = {
            myState++
        })
    }
}

@Composable
fun T1() {
    var mState by remember {
        mutableStateOf(0)
    }
    var mDeriveState = remember {
        Log.i(">>>", "111 recompose: derive")
        derivedStateOf {
            Log.i(">>>", "222 recompose: derive")
            if (mState > 4) 4 else 0
        }
    }
    Column {
        OutlinedButton(onClick = {
            mState++
        }) {
            Text(text = "$mState")
        }
        InT1(mDeriveState.value)
    }
}

@Composable
fun InT1(data: Int) {
    LaunchedEffect(key1 = Unit, block = {
        Log.i(">>>", "$data")
    })
    Text(text = "222:$data")
}

var mKey: Int = 0

@Composable
fun WrapDerivedStateTest() {
    var wState by remember {
        mutableStateOf(0)
    }
    Log.i(">>>", "recompose: 111wrap")
    Column {
        OutlinedButton(onClick = {
            wState++
            mKey++
        }) {
            Text(text = "wrap: $wState")
        }
//        Log.i(">>>", "uppper Derived: $wState")
        DerivedStateTest(myKey = mKey)
    }
}

@Composable
fun DerivedStateTest(myKey: Int) {
    Log.i(">>>", "recompose: 222derivedState")
    var state_1 by remember {
        mutableStateOf(0)
    }
    val state_2 by remember(myKey) {
        derivedStateOf {
            state_1 + Random.nextInt()
        }
    }
    Column {
        OutlinedButton(onClick = {
            state_1++
        }) {
            Text(text = state_2.toString())
        }
    }
}


var myKey = 0

@Composable
fun LauncedEffectTest() {
    Log.i(">>>", "recompose")
    var myState by remember {
        mutableStateOf(0)
    }
    OutlinedButton(onClick = {
        myState++
//        myKey++
    }) {
        LaunchedEffect(key1 = myKey, block = {
            Log.i(">>>", "launchedEffect $myState")
        })
        Text(text = "click Me$myState, $myKey")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    SideEffect {
        Log.i(">>>", "SideEffect")
    }

    var btnColor by remember {
        mutableStateOf("unknown")
    }
    timer(btnColor = btnColor)
    LaunchedEffect(key1 = Unit, block = {
        delay(8000)
        Log.i(">>>", "btnColor: $btnColor")
    })
    Column(Modifier.background(Color.DarkGray)) {
        OutlinedButton(onClick = {
            btnColor = "black"
        }) {
            Text(text = "black btn")
        }
        OutlinedButton(onClick = {
            btnColor = "white"
        }) {
            Text(text = "white btn")
        }
    }
}

@Composable
fun timer(btnColor: String) {
    Log.i(">>>", "compose: $btnColor")
    var rememberBtnColor = rememberUpdatedState(newValue = btnColor)
    LaunchedEffect(key1 = Unit, block = {
        delay(5000)
        Log.e(">>>", "btnColor: $btnColor")
        Log.e(">>>", "btnColor: ${rememberBtnColor.value}")
    })
}

suspend fun delayPrintBtnColor(btnColor: String) {
    delay(5000)
    Log.e(">>>", "btnColor: $btnColor")
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFCEFAD,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    device = "spec:width=1080px,height=2340px,dpi=480"
)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

@Composable
fun OutlinedBtn() {
    //compose作用区域

    //协程scope创建方式一：
    LaunchedEffect(key1 = Unit, block = {
        delay(1000)
    })

    //协程scope创建方式二：
    val scope = rememberCoroutineScope()

    OutlinedButton(onClick = {
        //非compose区域
        scope.launch {
            delay(2000)
        }
    }) {
        Text(text = "button")
    }


    DisposableEffect(key1 = Unit, effect = {
        //业务代码
        onDispose {
            /**
             * 以下场景onDispose会被执行
             * 1.当重组时(key发生变化）
             * 2.可组合项退出组合后
             */
        }
    })
}

@Composable
fun OneButton() {
    var pKey1 = "a"
    var otherState by remember {
        mutableStateOf(0)
    }
    var pState = produceState(initialValue = 0, key1 = otherState, producer = {
        delay(2000)
        value = Random.nextInt()
    })
    OutlinedButton(onClick = {
        otherState = Random.nextInt()
    }) {
        Text(text = "produceState value:${pState.value}")
    }
}

@Composable
fun SideEffectM() {


    SideEffect {
        Log.e(">>>", "out: SideEffect")
    }

    var btnColor by remember {
        mutableStateOf("unknown")
    }
//    timer(btnColor = btnColor)
//    LaunchedEffect(key1 = Unit, block = {
//        delay(8000)
//        Log.i(">>>", "btnColor: $btnColor")
//    })
//    Log.i(">>>", "out read: $btnColor")
    Column(Modifier.background(Color.DarkGray)) {
        Log.i(">>>", "column_recompose")
//        Log.i(">>>", "out read: $btnColor")
        OutlinedButton(onClick = {
            Log.i(">>>", "click_black")
            btnColor = "black"
        }) {
            Log.w(">>>", "recompose black")
            Text(text = "black btn")
        }
        OutlinedButton(onClick = {
            Log.i(">>>", "click_white")
            btnColor = "white"
        }) {
            Log.w(">>>", "recompose white")
            Text(text = "white btn: $btnColor")
        }
        ListWithBug(
            listOf(
                "a", "b", "c", "d", "a", "b", "c", "d", "a", "b", "c", "d", "a", "b", "c", "d"
            )
        )
    }
}

@Composable
fun ListWithBug(myList: List<String>) {
    var items = 0

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("Item: $item")
                items++ // Avoid! Side-effect of the column recomposing.
            }
        }
        Text("Count: $items")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun TextFieldM() {
    TextField(value = "asdf", onValueChange = {

    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
    }
}


@Composable
fun HoistStateButtonCounter() {
    var hoistNum by remember {
        mutableStateOf(0)
    }
    ButtonCounter(hoistNum) {
        hoistNum += 10
    }
}

@Composable
fun ButtonCounter(num: Int, onClickBtn: () -> Unit) {
    Column {
        Text(text = "num: $num")
        OutlinedButton(onClick = {
            onClickBtn()
        }) {
            Text(text = "num increasing!_$num")
        }
    }
}

@Composable
fun ButtonCounterWithoutHoisting() {
    var num by remember {
        mutableStateOf(0)
    }
    Column {
        Text(text = "num: $num")
        OutlinedButton(onClick = {
            num++
        }) {
            Text(text = "the times you tapped on the button: _$num")
        }
    }
}

@Composable
fun getDataOnline(key1: String, key2: String): State<Int> {
    val result =
        produceState<Int>(initialValue = 0, key1 = key1, key2 = key2) {
            delay(1000)
            Log.e(">>>", "recomposing")
            value = Random.nextInt()
        }

    Log.i(">>>", "result: ${result.value}")

    return result
}

@Composable
fun LauchedEffectTest() {
    var aState by remember {
        mutableStateOf(0)
    }
    var p1 by remember {
        mutableStateOf(0)
    }
    var p2 = 1
    Log.i(">>>", "recompose")
    LaunchedEffect(key1 = p1, key2 = p2) {
        delay(1000)
        Log.i(">>>", "sideEffect LaunchedEffect")
    }

    OutlinedButton(onClick = {
        Log.i(">>>", "onClick$p1")
        p1++
//        aState++
    }) {
        Text(text = "test launchedEffect!$aState")
    }
}

val numberFlow = flow {
    repeat(100) {
        delay(1000)
        emit(it)
    }
}


@Composable
fun ColorScreen() {
    var selectColor by remember {
        mutableStateOf(0)
    }
    var backgroundColor = produceState(initialValue = 1f, key1 = numberFlow, producer = {
        Log.i(">>>", "recomposing")
//        numberFlow.collect(){
//            value = it.toFloat()
//        }
        value = Random.nextFloat()
    })

    Column {
        OutlinedButton(onClick = {
//            pKey = Random.nextInt()
        }) {
            Text(text = "btn: ${backgroundColor.value}")
        }

        OutlinedButton(onClick = {

        }) {
            Text(text = "btn: $backgroundColor")
        }
    }

}
















