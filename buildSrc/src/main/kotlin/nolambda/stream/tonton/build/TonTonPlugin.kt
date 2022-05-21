package nolambda.stream.tonton.build

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("UnstableApiUsage")
class TonTonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.apply(plugin = "com.android.library")
        target.apply(plugin = "org.jetbrains.kotlin.android")

        val extension = target.extensions.create<TonTonLibraryExtension>("tonton")

        val globalExt = target.rootProject.extra

        val compileSdk: Int by globalExt
        val minSdk: Int by globalExt
        val targetSdk: Int by globalExt

        val composeVersion: String by globalExt

        target.androidLib {
            this.compileSdk = compileSdk

            defaultConfig {
                this.minSdk = minSdk
                this.targetSdk = targetSdk
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = composeVersion
            }

            packagingOptions {
                resources {
                    excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                }
            }
        }

        target.tasks.withType(KotlinCompile::class) {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        target.androidComponents {
            finalizeDsl {
                val isComposeEnabled = extension.features.isComposeEnabled
                if (isComposeEnabled) {
                    target.addComposeDependencies(composeVersion)
                }

                it.buildFeatures.compose = isComposeEnabled

            }
        }
    }

    private fun Project.addComposeDependencies(composeVersion: String) {
        dependencies.add("implementation", "androidx.compose.foundation:foundation:$composeVersion")
    }
}
