buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// plugins {
//     id("com.android.library") version "7.0.1" apply false
//     id("org.jetbrains.kotlin.android") version "1.6.10" apply false
//     id("org.jetbrains.dokka") version "1.6.10"
// }
