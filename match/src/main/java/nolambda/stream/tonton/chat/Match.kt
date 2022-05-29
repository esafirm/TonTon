package nolambda.stream.tonton.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import nolambda.stream.core.element.BackHeader
import nolambda.stream.core.element.MainHeader
import nolambda.stream.tonton.chat.model.ChatModel
import nolambda.stream.tonton.chat.model.Profile

class MatchScreen : Screen {

    @Composable
    override fun Content() {
        MatchContent()
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
fun MatchContent() {
    Column {
        BackHeader(headerTitle = "Match")
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(chatItems) { chat ->
                MatchUiItem(chat)
            }
        }
    }
}

@Composable
fun MatchUiItem(
    chatItem: ChatModel
) {
    val profile = chatItem.profile

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        AsyncImage(
            model = profile.avatar,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(36.dp)
                .background(color = Color.LightGray, shape = CircleShape)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.size(8.dp))


        val captionString =
            buildAnnotatedString {
                if (chatItem.matched) {
                    append("Draw me ")
                    withStyle(SpanStyle(fontWeight = FontWeight.W700)) {
                        append(profile.drawMe)
                    }
                } else {
                    withStyle(
                        SpanStyle(
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    ) {
                        append("You've matched. Say hi!")
                    }
                }
            }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = profile.name)
            Text(text = captionString)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MatchContent()
}
