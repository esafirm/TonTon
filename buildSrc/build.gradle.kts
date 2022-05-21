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
}

gradlePlugin {
    plugins {
        register("tonton-library") {
            id = "nolambda.stream.tonton.build.library"
            implementationClass = "nolambda.stream.tonton.build.TonTonPlugin"
        }
    }
}