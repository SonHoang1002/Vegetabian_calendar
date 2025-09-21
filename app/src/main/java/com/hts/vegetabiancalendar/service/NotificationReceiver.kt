package com.hts.vegetabiancalendar.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.hts.vegetabiancalendar.MainActivity

@RequiresApi(Build.VERSION_CODES.O)
class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            MyNotificationService().showStatusNotification(context, MainActivity())
        }
    }
}
