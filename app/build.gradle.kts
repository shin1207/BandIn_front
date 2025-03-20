plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.bandin"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bandin"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.fragment:fragment-ktx:1.6.2") // fragment 설정
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1") // LiveData
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit (API 통신)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // JSON 변환
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0") // RetrofitClient설정
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout) // 코루틴
}
