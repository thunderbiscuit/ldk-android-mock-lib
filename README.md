# Readme
This is a proof-of-concept for building the lightningdevkit Android library into a Maven artifact. The source files were taken from [ldk-java 0.0.105.1](https://repo1.maven.org/maven2/org/lightningdevkit/ldk-java/0.0.105.1/) and the native binaries from [LDK-release.aar 0.0.105.1](https://github.com/lightningdevkit/ldk-garbagecollected/releases/tag/v0.0.105.1).

Build this library and publish it to your local maven repository by running the `publishToMavenLocal` task:
```shell
./gradlew :lib:publishToMavenLocal
```

By adding `mavenLocal()` to your list of repositories used for dependency resolution, you'll be able to import the library into any Android project using
```gradle
// Kotlin DSL
dependencies {
    implementation("org.lightningdevkit:ldk-android:0.0.1-SNAPSHOT")
}

// Groovy DSL
dependencies {
    implementation "org.lightningdevkit:ldk-android:0.0.1-SNAPSHOT"
}
```
