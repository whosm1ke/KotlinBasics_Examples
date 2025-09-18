package ua.kpi.practical_example_26

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import android.os.Build

class DeviceUtil {
    // Реальне отримання батареї
    fun getBatteryLevel(context: Context): Int {
        val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else 0
    }

    // Реальне отримання використання пам'яті
    fun getMemoryUsage(context: Context): Int {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
        val memoryInfo = android.app.ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        val usedMem = memoryInfo.totalMem - memoryInfo.availMem
        return ((usedMem.toDouble() / memoryInfo.totalMem) * 100).toInt()
    }

    // Реальне отримання завантаження CPU
    fun getCpuUsage(): Int {
        try {
            val reader = java.io.RandomAccessFile("/proc/stat", "r")
            val load = reader.readLine().split(" ")
            val idle1 = load[5].toLong()
            val cpu1 = load.slice(2..8).map { it.toLong() }.sum()

            Thread.sleep(360) // коротка пауза

            reader.seek(0)
            val load2 = reader.readLine().split(" ")
            val idle2 = load2[5].toLong()
            val cpu2 = load2.slice(2..8).map { it.toLong() }.sum()
            reader.close()

            return (((cpu2 - cpu1 - (idle2 - idle1)).toDouble() / (cpu2 - cpu1)) * 100).toInt()
        } catch (e: Exception) {
            return 0
        }
    }
}