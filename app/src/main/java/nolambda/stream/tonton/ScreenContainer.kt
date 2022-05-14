package nolambda.stream.tonton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import nolambda.stream.tonton.peoplebrowser.PeopleBrowserScreen

class ScreenContainer : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigator(PeopleBrowserScreen())
        }
    }
}