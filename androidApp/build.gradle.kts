plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

val composeVersion = "1.0.0-beta04"

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation("androidx.activity:activity-compose:1.3.0-alpha06")

    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha03")

    val navComposeVersion = "1.0.0-alpha10"
    implementation("androidx.navigation:navigation-compose:$navComposeVersion")

    val lifecycleVersion = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")

    val hiltVersion = "2.33-beta"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha01")

    val accompanist = "0.7.1"
    implementation("com.google.accompanist:accompanist-insets:$accompanist")
    implementation("com.google.accompanist:accompanist-coil:$accompanist")
    implementation("com.google.accompanist:accompanist-flowlayout:$accompanist")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "uz.suhrob.recipeappkmm"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

kapt {
    correctErrorTypes = true
}