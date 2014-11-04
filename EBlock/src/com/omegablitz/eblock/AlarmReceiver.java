package com.omegablitz.eblock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * User: bobacadodl
 * Date: 11/3/14
 * Time: 10:04 PM
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
    }
}
