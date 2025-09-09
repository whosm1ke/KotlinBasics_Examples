# 🚀 Kotlin Android Practical Examples

Цей репозиторій містить **навчально-демонстраційні проєкти на Kotlin для Android**, які демонструють різні аспекти розробки Android-застосунків із використанням **Jetpack Compose** та сучасних бібліотек.

Кожен практичний проєкт незалежний і призначений для самостійного вивчення.

---

## 📁 Структура репозиторію

Репозиторій організований за **модулями** та **практичними прикладами**:

    Module_1/
    ── Practical_1/
    ── Practical_2/
    └── Practical_3/

    Module_2/
    ├── Practical_4/
    ├── Practical_5/
    └── Practical_6/


- **Модулі** – логічні групи практичних завдань.
- **Практичні приклади** – окремі Android-проєкти, повністю незалежні.

Кожен проєкт має стандартну Android-структуру:

    Practical_X/
    ├── app/
    │ ├── src/
    │ │ ├── main/
    │ │ │ ├── java/ua/kpi/practical_example_X/
    │ │ │ │ └── ui/theme/ # Теми Compose
    │ │ │ └── res/ # Ресурси (layout, values, xml)
    │ │ ├── test/ # Unit-тести
    │ │ └── androidTest/ # Інструментальні тести
    ├── build.gradle.kts # Конфігурація модуля
    └── proguard-rules.pro # Оптимізація та правила Proguard


---

## ⚙️ Вимоги до середовища

- **Android Studio** – остання стабільна версія
- **Gradle 9.0.0** (налаштований через `gradle-wrapper.properties`)
- **Kotlin 1.9+**
- **Android SDK**:
    - Compile SDK: 36
    - Minimum SDK: 24
    - Target SDK: 36

---

## 🏃 Інструкції з запуску

1. Клонувати репозиторій:

```bash

git clone https://github.com/<username>/KotlinBasics_Examples.git
cd KotlinBasics_Examples
Відкрити репозиторій у Android Studio.

Для запуску будь-якого практичного прикладу:

Відкрити папку Module_X/Practical_Y як модуль у Android Studio.

Дочекатися завершення Gradle Sync.

Створити емулятор або підключити фізичний пристрій.

Натиснути Run.

💡 Порада: для емулятора рекомендовано API 33+ та мінімум 4 GB RAM.

📦 Залежності
Всі приклади використовують сучасні бібліотеки Android:

Jetpack Compose

AndroidX Core, Lifecycle, Activity

Material3

JUnit (unit-тести)

Espresso (UI-тести)

Для встановлення достатньо виконати Gradle Sync у Android Studio.

🧪 Тестування
Unit-тести – src/test/java

Instrumented UI-тести – src/androidTest/java

Команди для запуску через термінал:

bash
./gradlew test                   # Unit-тести
./gradlew connectedAndroidTest   # UI-тести
🔧 Робота з Git
Клонування репозиторію:

bash
git clone https://github.com/<username>/KotlinBasics_Examples.git
Кожен модуль можна відкривати окремо як модуль Android Studio.

Рекомендовано додавати .gitignore для ігнорування локальних налаштувань Android Studio та Gradle кешу (вже включено у репозиторій).