plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.garif.cataog_feature"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    // region Network
    val retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:${retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofit}")

    val okhttp = "4.9.3"
    implementation("com.squareup.okhttp3:okhttp:${okhttp}")
    debugImplementation("com.squareup.okhttp3:logging-interceptor:${okhttp}")
    // endregion

    val room = "2.6.1"
    implementation("androidx.room:room-runtime:${room}")
    implementation("androidx.room:room-ktx:${room}")
    kapt("androidx.room:room-compiler:${room}")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":database"))
}