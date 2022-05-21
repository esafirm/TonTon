package nolambda.stream.tonton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import nolambda.stream.tonton.chat.ChatScreen
import nolambda.stream.tonton.peoplebrowser.PeopleBrowserScreen
import nolambda.stream.toton.navigation.TonTonScreen

class ScreenContainer : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ScreenRegistry {
            register<TonTonScreen.PeopleBrowser> {
                PeopleBrowserScreen()
            }
            register<TonTonScreen.Chat> {
                ChatScreen()
            }
        }

        setContent {
            Navigator(PeopleBrowserScreen())
        }
    }
}