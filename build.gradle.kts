buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.31")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}