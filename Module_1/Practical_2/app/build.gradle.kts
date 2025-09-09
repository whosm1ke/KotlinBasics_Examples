//plugins — блок для підключення плагінів Gradle. Плагіни додають специфічні можливості проекту, наприклад:
//com.android.application — дозволяє компілювати Android-додаток;
//org.jetbrains.kotlin.android — підключає Kotlin для Android;
//org.jetbrains.kotlin.plugin.compose — дозволяє використовувати Jetpack Compose у Kotlin.
plugins {
    // Плагін для Android застосунку
    id("com.android.application")
    // Плагін для Kotlin
    id("org.jetbrains.kotlin.android")
    // Плагін для використання Jetpack Compose
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "ua.kpi.practical_example_2" // унікальне ім’я пакету для ресурсу R, класів та Manifest;
    compileSdk = 36 //версія Android SDK, на якій компілюється проект;

    defaultConfig { // базові налаштування додатку: id, мінімальна та цільова версія, версія додатку, тестовий раннер;
        applicationId = "ua.kpi.practical_example_2" // Ідентифікатор додатку
        minSdk = 24 // Мінімальна підтримувана версія Android
        targetSdk = 36 // Версія Android, на яку орієнтований додаток
        versionCode = 1 // Цілочисельний номер версії
        versionName = "1.0" // Версія у форматі рядка

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Раннер для інструментальних тестів
    }

    buildTypes { // типи збірки (debug, release) з різними налаштуваннями;
        release {
            isMinifyEnabled = false // Вимкнення мінімізації коду
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // Стандартні правила Proguard
                "proguard-rules.pro" // Додаткові правила
            )
        }
    }

    compileOptions { // версії Java для компіляції;
        sourceCompatibility = JavaVersion.VERSION_17 // Версія Java для компіляції
        targetCompatibility = JavaVersion.VERSION_17 // Цільова версія JVM
    }

    kotlinOptions { //версія JVM для Kotlin;
        jvmTarget = "17" // JVM target для Kotlin
    }

    buildFeatures { //увімкнення специфічних функцій, наприклад compose = true.
        compose = true // Увімкнення Jetpack Compose
    }
}

dependencies {

    // ===================
    // Базові залежності
    // ===================
    //implementation — це конфігурація залежності, яка додає бібліотеку до компіляції і виконання додатку.
    //Код із цієї бібліотеки доступний для твого проекту, але не впливає на проекти, які підключають твій модуль.
    //Це найчастіше використовувана конфігурація для основного коду додатку.
    implementation("androidx.core:core-ktx:1.17.0") // Розширення Kotlin для Android API
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.3") // Життєвий цикл Activity/Fragment
    implementation("androidx.activity:activity-compose:1.10.1") // Інтеграція Compose з Activity


    //platform — використовується для керування версіями групи залежностей.
    //Наприклад, compose-bom забезпечує сумісність усіх модулів Compose, щоб не виникало конфліктів версій.

    implementation(platform("androidx.compose:compose-bom:2025.08.01")) // BOM для Compose (керування версіями)
    implementation("androidx.compose.ui:ui") // Основний модуль Compose UI
    implementation("androidx.compose.ui:ui-graphics") // Графіка та кольори
    implementation("androidx.compose.ui:ui-tooling-preview") // Прев’ю в Android Studio
    implementation("androidx.compose.material3:material3") // Material3 компоненти UI

    // ===================
    // Демонстраційні залежності
    // ===================
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")
    // Accompanist для контролю статусбару, навбару

    implementation("com.google.accompanist:accompanist-permissions:0.37.3")
    // Accompanist для керування runtime permissions

    implementation("io.coil-kt:coil-compose:2.7.0")
    // Coil для завантаження зображень у Compose

    implementation("com.patrykandpatrick.vico:compose:2.1.3")
    // Демонстраційна бібліотека для графіків у Compose

    // ===================
    // Тестові залежності
    // ===================
    //testImplementation — додає бібліотеки лише для юнiт-тестів, які виконуються на JVM (без Android-пристрою).
    //Код цих бібліотек не потрапляє у фінальний APK.
    testImplementation("junit:junit:4.13.2") // Юніт-тести

    //androidTestImplementation — додає бібліотеки для інструментальних тестів, які виконуються на реальному пристрої
    //або емуляторі Android. Наприклад, Espresso або Compose UI тести.

    androidTestImplementation("androidx.test.ext:junit:1.3.0") // Android JUnit
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0") // Espresso для UI тестів
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.08.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4") // Compose UI тести

    //debugImplementation — додає залежності, які потрібні тільки в debug-збірці, наприклад, інструменти для прев’ю,
    //дебагу, логування. В релізній збірці ці бібліотеки не включаються.

    debugImplementation("androidx.compose.ui:ui-tooling") // Інструменти для дебагу Compose
    debugImplementation("androidx.compose.ui:ui-test-manifest") // Додаткові ресурси для тестів у дебаг режимі
}