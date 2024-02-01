plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.garif.teststore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.garif.teststore"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    val navigation = "2.7.6"
    implementation("androidx.navigation:navigation-fragment-ktx:${navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${navigation}")

    val dagger = "2.46"
    implementation("com.google.dagger:dagger:${dagger}")
    kapt("com.google.dagger:dagger-compiler:${dagger}")

    val room = "2.6.1"
    implementation("androidx.room:room-runtime:${room}")
    implementation("androidx.room:room-ktx:${room}")
    kapt("androidx.room:room-compiler:${room}")

    // region Network
    val retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:${retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofit}")

    val okhttp = "4.9.3"
    implementation("com.squareup.okhttp3:okhttp:${okhttp}")
    debugImplementation("com.squareup.okhttp3:logging-interceptor:${okhttp}")
    // endregion

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(project(":core"))
    implementation(project(":registration-feature"))
    implementation(project(":database"))
    implementation(project(":main-feature"))
    implementation(project(":catalog-feature"))
    implementation(project(":network"))
    implementation(project(":item-feature"))
}