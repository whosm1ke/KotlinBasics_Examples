plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "ua.kpi.practical_example_7"
    compileSdk = 36

    defaultConfig {
        applicationId = "ua.kpi.practical_example_7"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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

    // Основні бібліотеки Android
    implementation("androidx.core:core-ktx:1.17.0")

    // Lifecycle runtime для коректної роботи ViewModel і LiveData
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.3")

    // Activity для Compose
    implementation("androidx.activity:activity-compose:1.10.1")

    // BOM для керування версіями Compose
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))

    // Основні UI бібліотеки Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Додатково для інтеграції ViewModel з Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // Дозволяє використовувати viewModel() у Composable

    // Корутини для асинхронної обробки у ViewModel
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Для viewModelScope

    // Тестові залежності
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
