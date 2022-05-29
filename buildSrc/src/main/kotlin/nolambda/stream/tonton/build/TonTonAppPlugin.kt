package nolambda.stream.tonton.build

import org.gradle.api.Plugin
import org.gradle.api.Project

class TonTonAppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        DependantModulesTask.attach(target)
    }
}