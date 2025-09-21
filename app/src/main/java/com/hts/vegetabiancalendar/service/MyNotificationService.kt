package com.hts.vegetabiancalendar.service

import MyCalendarService
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.hts.vegetabiancalendar.MainActivity
import com.hts.vegetabiancalendar.R
import com.hts.vegetabiancalendar.util.checkVegetabianDayWithSolarLocalDateTime
import java.time.LocalDateTime
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
class MyNotificationService {
    val TAG: String = "MyNotificationService"

    fun requestPermission(activity: MainActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
    }

    fun showStatusNotification(context: Context, mainActivity: MainActivity) {
        try {
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channelId =
                "my_channel_id"    // Create Notification Channel for Android 8+    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val channel = NotificationChannel(
                channelId,
                "Channel Name",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Status Notification"
                enableLights(true)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(context, mainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                val localDateTime = LocalDateTime.now().plusDays(1)
                val lunar = MyCalendarService(localDateTime).myLunarDate
                val vegeStatus = localDateTime
                    .checkVegetabianDayWithSolarLocalDateTime()
                val suffixText = vegeStatus.second.reason

                val bigText = "Ngày mai • ${lunar.day}/${lunar.month} • $suffixText"
                val notificationBuilder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Vegetabian Calendar")
                    .setWhen(System.currentTimeMillis())
                    .setContentText(bigText)
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(bigText)
                    )
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                with(NotificationManagerCompat.from(context)) {
                    notificationManager.notify(Random.nextInt(), notificationBuilder.build())
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "showNotification: ${e.toString()}")
        }
    }

    fun showHeadUpNotification(context: Context) {
        val channelId = "CHANNEL_ID"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Channel Name",
            NotificationManager.IMPORTANCE_HIGH // bắt buộc HIGH thì mới hiện heads-up
        ).apply {
            description = "Channel description"
            enableLights(true)
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(channel)
        val localDateTime = LocalDateTime.now().plusDays(1)
        val lunar = MyCalendarService(localDateTime).myLunarDate
        val vegeStatus = localDateTime
            .checkVegetabianDayWithSolarLocalDateTime()
        val suffixText = vegeStatus.second.reason

        val bigText = "Ngày mai • ${lunar.day}/${lunar.month} • $suffixText"

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // icon phải hợp lệ, nền trong suốt
            .setContentTitle("Vegetabian Calendar")
            .setContentText(bigText)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // cho Android < 8
            .setCategory(NotificationCompat.CATEGORY_MESSAGE) // nên để MESSAGE hoặc CALL
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL) // bật sound + rung

        notificationManager.notify(666, notificationBuilder.build())
    }

    fun scheduleTimerToShowNotification(context: Context, mainActivity: MainActivity) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("target", "status")
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Mỗi 1 tiếng = 3600000 ms
        val intervalMillis = 60 * 60 * 1000L
        val triggerAtMillis = System.currentTimeMillis() + intervalMillis

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            intervalMillis,
            pendingIntent
        )
        x
    }

}