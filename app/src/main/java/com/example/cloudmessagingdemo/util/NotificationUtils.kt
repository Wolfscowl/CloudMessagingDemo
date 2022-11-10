/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cloudmessaging.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

import android.graphics.BitmapFactory
import android.os.Build
import com.example.cloudmessagingdemo.MainActivity
import com.example.cloudmessagingdemo.R

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0


/** ================================== sendNotification ========================================= */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {


    /** ------------------------------ Create the content intent  -------------------------------- */
    // Create the content intent for the notification, which launches this activity
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // create PendingIntent
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                    PendingIntent.FLAG_IMMUTABLE else 0
    )

    /** ------------------------------------- Create style -------------------------------------- */
    val smileyImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.smiley
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(smileyImage)
        .bigLargeIcon(null)


    /** --------------------------------- Build the notification --------------------------------- */
    // get an instance of NotificationCompat.Builder with Channel
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.smile_notification_channel_id)
    )
        // set title, text and icon to builder
        .setSmallIcon(R.drawable.smiley)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        // set content intent
        .setContentIntent(contentPendingIntent)
        // add style to builder
        .setStyle(bigPicStyle)
        .setLargeIcon(smileyImage)
        // add action
        // ...
        // set priority
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    /** ------------------------------------ call notify  --------------------------------------- */
    // Deliver the notification
    notify(NOTIFICATION_ID, builder.build())
}


/** ================================== cancelNotifications ======================================= */
// Cancel all notifications
fun NotificationManager.cancelNotifications() {
    cancelAll()
}
