package nolambda.stream.tonton.build

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.provideDelegate

@Suppress("UnstableApiUsage")
class TonTonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.apply(plugin = "com.android.library")
        target.apply(plugin = "org.jetbrains.kotlin.android")

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
    }
}
