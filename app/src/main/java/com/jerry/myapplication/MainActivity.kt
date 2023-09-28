package com.jerry.myapplication

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jerry.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent {
            MyApplicationTheme {
                Surface {
//                    MessageCard()
                    Conversation(messages = SampleData.conversationSample)
                }
            }
        }
    }
}

data class Message(val title: String, val body: String)

@Composable
fun Conversation(modifier: Modifier = Modifier, messages: List<Message>): Unit {
    Surface (modifier = modifier){
        LazyColumn {
            items(messages) {message ->
                MessageCard(message)
            }
        }
    }
}

@Preview(name = "conversation", showBackground = true, widthDp = 480)
@Composable
fun previewConversation(): Unit {
    MyApplicationTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}

@Composable
fun MessageCard(msg: Message?= null) {
    Row (modifier = Modifier.padding(all = 10.dp)){
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "jiyongchunDesc",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "",
        )

        Column (modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = msg?.title?:"",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(5.dp))

            Surface(shape = MaterialTheme.shapes.extraSmall,
                shadowElevation = 1.dp, tonalElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)) {
                Text(text = msg?.body?:"",
                    modifier = Modifier.padding(all = 2.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1)
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode")
@Composable
fun PreviewMessageCard() {
    MyApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            MessageCard(Message("xiaoming", "watch TV!"))
        }
    }
}
