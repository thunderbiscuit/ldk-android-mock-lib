plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 24
        targetSdk = 30
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(file("proguard-android-optimize.txt"), file("proguard-rules.pro"))
        }
    }

    // publishing {
    //     singleVariant("release") {
    //         // withSourcesJar() // requires AGP 7.1+
    //         // withJavadocJar() // requires AGP 7.1+
    //     }
    // }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    // ldk
    implementation("com.google.code.findbugs:jsr305:3.0.2")

    // implementation("net.java.dev.jna:jna:5.8.0@aar")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "org.lightningdevkit"
                artifactId = "ldk-android"
                version = "0.0.1-SNAPSHOT"
                from(components["release"])
                pom {
                    name.set("ldk-android")
                    description.set("LDK Android Java Bindings and Release Binaries")
                    url.set("https://lightningdevkit.org/")
                    licenses {
                        license {
                            name.set("APACHE 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                        license {
                            name.set("MIT")
                            url.set("http://www.opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("person1")
                            name.set("Person 1")
                            email.set("a@b.com")
                        }
                        developer {
                            id.set("person2")
                            name.set("Person 2")
                            email.set("a@b.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/lightningdevkit/ldk-garbagecollected.git")
                        url.set("https://github.com/lightningdevkit/ldk-garbagecollected")
                    }
                }
            }
        }
    }
}
