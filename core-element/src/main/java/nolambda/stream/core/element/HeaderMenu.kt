package nolambda.stream.core.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage

@Composable
fun MainHeader(
    onProfilePressed: () -> Unit,
    onMatchPressed: () -> Unit
) {
    HeaderLayout {
        IconButton(onProfilePressed) {
            AsyncImage(
                model = "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50",
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(32.dp)
                    .background(color = Color.LightGray, shape = CircleShape)
                    .clip(CircleShape)
            )
        }
        IconButton(onMatchPressed) {
            Icon(
                Icons.Filled.ChatBubble,
                contentDescription = "Matched",
                tint = Color.LightGray,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun BackHeader(
    headerTitle: String,
    showBack: Boolean = true
) {
    val navigator = LocalNavigator.currentOrThrow
    val arrangement = if (showBack) Arrangement.Start else Arrangement.Center

    HeaderLayout(horizontalArrangement = arrangement) {
        if (showBack) {
            IconButton({
                navigator.pop()
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back button",
                    tint = Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }

        if (headerTitle.isNotEmpty()) {
            Text(headerTitle, fontSize = 20.sp)
        }
    }
}

@Composable
private fun HeaderLayout(
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    children: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        children()
    }
}