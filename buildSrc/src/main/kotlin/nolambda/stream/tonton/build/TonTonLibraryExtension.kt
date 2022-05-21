package nolambda.stream.tonton.build

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.newInstance
import javax.inject.Inject

abstract class TonTonLibraryExtension(
    factory: ObjectFactory
) {
    internal val features = factory.newInstance<Features>()

    fun features(action: Action<Features>) {
        action.execute(features)
    }

    abstract class Features @Inject constructor(
        factory: ObjectFactory
    ) {
        internal var isComposeEnabled: Boolean = false

        fun compose() {
            isComposeEnabled = true
        }
    }
}