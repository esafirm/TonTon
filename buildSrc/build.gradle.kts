plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
}

gradlePlugin {
    plugins {
        register("tonton-library") {
            id = "nolambda.stream.tonton.build.library"
            implementationClass = "nolambda.stream.tonton.build.TonTonPlugin"
        }
    }
}