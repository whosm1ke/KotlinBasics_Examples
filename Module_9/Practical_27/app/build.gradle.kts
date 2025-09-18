
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    kotlin("kapt")
}

android {
    namespace = "ua.kpi.practical_example_27"
    compileSdk = 36
    flavorDimensions += "version"

    productFlavors {
        create("demo") {
            dimension = "version"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
        }
        create("full") {
            dimension = "version"
        }
    }

    signingConfigs {
        create("release") {
            // keytool -genkeypair -v -alias key -keyalg RSA -keysize
            // 2048 -validity 10000 -keystore key.jks -storetype JKS
            // Заміни на свій шлях і паролі keystore
            storeFile = file("C:\\strmanip\\key.jks")
            storePassword = "String123!"
            keyAlias = "key"
            keyPassword = "String123!"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true      // увімкнення R8/ProGuard
            // Видаляє непотрібні ресурси
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    defaultConfig {
        applicationId = "ua.kpi.practical_example_27"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2025.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.9.3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // Retrofit2 + Gson + Coroutines
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.retrofit2:converter-scalars:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")
    implementation("androidx.datastore:datastore-preferences:1.1.7")

    // MPAndroidChart – для графіків та діаграм
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    testImplementation("androidx.room:room-testing:2.6.1")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    // JSON (Gson)
    implementation("com.google.code.gson:gson:2.13.2")

    // CSV (читання/запис)
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.10.0")

    // kotlinx.serialization (опціонально)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    // kotlinx-datetime (для роботи з датами у CSV/JSON)
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")

    // Coil для Jetpack Compose
    implementation("io.coil-kt:coil-compose:2.7.0")
}