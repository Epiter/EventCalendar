package com.example.runescroll.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.runescroll.service.QuestReminderService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("BootReceiver", "Device restarted, starting QuestReminderService")

            val serviceIntent = Intent(context, QuestReminderService::class.java)
            context.startService(serviceIntent)
        }
    }
}
