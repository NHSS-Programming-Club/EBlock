package com.omegablitz.eblock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * User: bobacadodl
 * Date: 11/3/14
 * Time: 10:35 PM
 */
public class BootReceiver extends BroadcastReceiver {

    //Setup alarm on restart device
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            MainActivity.setupAlarm(context);
        }
    }
}
