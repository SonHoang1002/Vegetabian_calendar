package com.hts.vegetabiancalendar.service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hts.vegetabiancalendar.MainActivity

class NotificationAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val type = intent?.getStringExtra("type")
            MyNotificationService().showStatusNotification(context, MainActivity())
        }
    }
}
