package nolambda.stream.tonton.peoplebrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nolambda.stream.tonton.peoplebrowser.ui.components.CardStack
import nolambda.stream.tonton.peoplebrowser.ui.components.Item
import nolambda.stream.tonton.peoplebrowser.ui.theme.TonTonTheme

@OptIn(ExperimentalMaterialApi::class)
class PeopleBrowser : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeopleBrowserContent()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun PeopleBrowserContent() {

    val items = remember {
        val item1 = Item(
            "https://f4.bcbits.com/img/0020592180_10.jpg",
            "Jane",
            "16 miles near you"
        )
        val item2 = Item(
            "https://images.pexels.com/photos/91224/pexels-photo-91224.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
            "Robert",
            "7 miles near you"
        )
        val item3 = Item(
            "https://images.pexels.com/photos/789812/pexels-photo-789812.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "Daria",
            "3 miles from you"
        )
        val item4 = Item(
            "https://images.pexels.com/photos/872756/pexels-photo-872756.jpeg?cs=srgb&dl=pexels-dishan-lathiya-872756.jpg&fm=jpg",
            "Violet",
            "43 miles from you"
        )
        mutableListOf(item1, item2, item3, item4)
    }

    TonTonTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            CardStack(
                items = items,
                enableButtons = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PeopleBrowserContent()
}