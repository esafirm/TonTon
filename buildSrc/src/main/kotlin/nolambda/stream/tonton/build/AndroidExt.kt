package nolambda.stream.tonton.build

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.getByType

/**
 * This extension files contains extension functions for [Project]
 * to get the extension object in the build system
 */


/**
 * Get android app extension from the [Project]
 * In build.gradle this extension is called "android"
 */
internal fun Project.androidApp(action: Action<AppExtension>) {
    runOnExtensionType(AppExtension::class, action)
}


/**
 * Get android library extension from the [Project]
 * In build.gradle this extension is called "android"
 */
internal fun Project.androidLib(action: Action<LibraryExtension>) {
    runOnExtensionType(LibraryExtension::class, action)
}

/**
 * Get generic extension for Android Gradle Plugin related components. [Project]
 * In build.gradle this extension is called "androidComponents"
 */
internal fun Project.androidComponents(action: Action<AndroidComponentsExtension<*, *, *>>) {
    runOnExtensionType(AndroidComponentsExtension::class, action)
}

/**
 * Get the java extension from [Project]
 * In build.gradle this extension is called "java"
 */
internal fun Project.java(action: Action<JavaPluginExtension>) {
    runOnExtensionType(JavaPluginExtension::class, action)
}


/**
 * Run action on extension with assigned [type]
 */
private fun <T : Any> Project.runOnExtensionType(
    type: kotlin.reflect.KClass<T>,
    action: Action<T>
) {
    val ext = extensions.getByType(type)
    action.execute(ext)
}