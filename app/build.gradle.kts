plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltDagger)
    kotlin("kapt")
    alias(libs.plugins.androidksp)
//    alias(libs.plugins.gmsService)
}

android {
    namespace = "com.selfproject.cordsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.selfproject.cordsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.google.hilt)
    kapt(libs.google.hilt.compiler)
//    implementation(libs.androidx.hilt.viewmodel)
    implementation(libs.androidx.hilt.compose)
    kapt(libs.androidx.hilt.compiler)


    implementation(libs.google.accompanist.swiperefresh)
    implementation(libs.google.accompanist.insets)
    implementation(libs.google.accompanist.navigation.animation)

    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.moshiConverter)
    implementation(libs.squareup.http3)
    implementation(libs.squareup.http3.interceptor)

    implementation(libs.mapbox.android)
//    implementation (libs.mapbox.android) {
//        exclude(group = "group_name", module =  "module_name")
//    }
    implementation(libs.mapbox.compose)
//    implementation(libs.google.android.map.service)
//    implementation (com.google.android.gms:play-services-maps:18.1.0)

//    implementation(libs.coil)
//    implementation(libs.raamcosta.compose.destination)
//    ksp(libs.raamcosta.compose.destination.ksp)
//    implementation(libs.opencsv)
}