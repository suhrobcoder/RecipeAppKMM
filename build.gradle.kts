buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.android.tools.build:gradle:7.0.0-alpha14")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.31")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.4")

        classpath("com.google.dagger:hilt-android-gradle-plugin:2.33-beta")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}