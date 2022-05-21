package nolambda.stream.toton.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class TonTonScreen : ScreenProvider {
    object PeopleBrowser : TonTonScreen()
    object Chat : TonTonScreen()
}