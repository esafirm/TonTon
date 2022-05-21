package nolambda.stream.tonton.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import nolambda.stream.tonton.chat.model.ChatModel
import nolambda.stream.tonton.chat.model.Profile

class ChatScreen : Screen {

    @Composable
    override fun Content() {
        ChatContent()
    }
}

private val chatItems = listOf(
    ChatModel(
        profile = Profile(
            avatar = "https://f4.bcbits.com/img/0020592180_10.jpg",
            name = "Jane",
            drawMe = "Ship"
        ),
        matched = false
    ),
    ChatModel(
        profile = Profile(
            avatar = "https://images.pexels.com/photos/91224/pexels-photo-91224.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
            name = "Robert",
            drawMe = "A cute dog ❤️"
        ),
        matched = true
    ),
    ChatModel(
        profile = Profile(
            avatar = "https://images.pexels.com/photos/789812/pexels-photo-789812.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            name = "Daria",
            drawMe = "2 cat hugging"
        ),
        matched = true
    ),
    ChatModel(
        profile = Profile(
            avatar = "https://images.pexels.com/photos/872756/pexels-photo-872756.jpeg?cs=srgb&dl=pexels-dishan-lathiya-872756.jpg&fm=jpg",
            name = "Violet",
            drawMe = "Your face 😅"
        ),
        matched = false
    )
)

@Composable
fun ChatContent() {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(chatItems) { chat ->
            ChatItem(chat)
        }
    }
}

@Composable
fun ChatItem(
    chatItem: ChatModel
) {
    val profile = chatItem.profile

    Row {
        AsyncImage(
            model = profile.avatar,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(32.dp)
                .background(color = Color.LightGray, shape = CircleShape)
                .clip(CircleShape)
        )

        if (chatItem.matched) {
            Text(text = profile.name)
        } else {

            val drawMeString = buildAnnotatedString {
                append("Draw me ")
                withStyle(SpanStyle(fontWeight = FontWeight.W700)) {
                    append(profile.drawMe)
                }
            }

            Column {
                Text(text = profile.name)
                Text(text = drawMeString)
            }
        }
    }
}

