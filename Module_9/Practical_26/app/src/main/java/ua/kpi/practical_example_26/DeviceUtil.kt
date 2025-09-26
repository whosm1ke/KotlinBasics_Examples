package ua.kpi.practical_example_26

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import android.os.Build

class DeviceUtil {
    // Метод отримує рівень заряду батареї пристрою
    // Використовує BatteryManager для отримання даних про стан батареї
    // Повертає ціле число від 0 до 100, що відповідає відсотку заряду
    fun getBatteryLevel(context: Context): Int {
        val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Використовуємо метод getIntProperty для отримання поточного рівня заряду батареї
            bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else 0
        // Для старих версій Android повертаємо 0, оскільки метод недоступний
    }

    // Метод отримує використання оперативної пам'яті пристрою
    // Використовує ActivityManager для отримання інформації про пам'ять
    // Повертає відсоток використаної пам'яті у вигляді цілого числа
    fun getMemoryUsage(context: Context): Int {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
        val memoryInfo = android.app.ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        // Обчислюємо кількість використаної пам'яті
        val usedMem = memoryInfo.totalMem - memoryInfo.availMem
        // Розраховуємо відсоток використання пам'яті
        return ((usedMem.toDouble() / memoryInfo.totalMem) * 100).toInt()
    }

    // Метод отримує завантаження процесора (CPU)
    // Використовує файл /proc/stat для аналізу статистики CPU
    // Повертає відсоток завантаження CPU у вигляді цілого числа
    fun getCpuUsage(): Int {
        try {
            // Відкриваємо файл /proc/stat для читання статистики CPU
            val reader = java.io.RandomAccessFile("/proc/stat", "r")
            val load = reader.readLine().split(" ")
            // Зчитуємо значення idle (неактивного часу) і загальний час CPU
            val idle1 = load[5].toLong()
            val cpu1 = load.slice(2..8).map { it.toLong() }.sum()

            Thread.sleep(360) // Затримка для отримання змін у статистиці CPU

            reader.seek(0) // Повертаємося на початок файлу
            val load2 = reader.readLine().split(" ")
            val idle2 = load2[5].toLong()
            val cpu2 = load2.slice(2..8).map { it.toLong() }.sum()
            reader.close() // Закриваємо файл

            // Обчислюємо завантаження CPU на основі різниці між двома вимірами
            return (((cpu2 - cpu1 - (idle2 - idle1)).toDouble() / (cpu2 - cpu1)) * 100).toInt()
        } catch (e: Exception) {
            // У разі помилки повертаємо 0 як значення завантаження CPU
            return 0
        }
    }
}