package com.hts.vegetabiancalendar.service

import MyCalendarService
import android.Manifest
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
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.hts.vegetabiancalendar.MainActivity
import com.hts.vegetabiancalendar.R
import com.hts.vegetabiancalendar.util.checkVegetabianDayWithSolarLocalDateTime
import java.time.LocalDateTime
import java.util.Calendar
import kotlin.random.Random


class MyNotificationService {
    val TAG: String = "MyNotificationService"
    val hours = listOf(7,8,9,10,11,12,13,14,15,16,17)

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

    @SuppressLint("ScheduleExactAlarm")
    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    fun scheduleTimerToShowNotification(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        for (hour in hours) {
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                hour,
                Intent(context, NotificationAlarmReceiver::class.java).apply {
                    putExtra("type", "notification_morning_at_$hour")
                },
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                if (before(Calendar.getInstance())) {
                    add(Calendar.DATE, 1)
                }
            }
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }

    }


}