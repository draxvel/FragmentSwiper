package com.tkachuk.testapp.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.tkachuk.testapp.R;

import java.util.Random;

public class NotificationManager {
    public static void create(final Context context, final int pageNumber, final int pageCount){
        final Intent intent = new Intent(context, context.getClass());
        intent.putExtra(context.getResources().getString(R.string.page_number), pageNumber);
        intent.putExtra(context.getResources().getString(R.string.page_count), pageCount);

        final PendingIntent contentIntent = PendingIntent
                .getActivity(context.getApplicationContext(), Math.abs(new Random().nextInt()), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context,"1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true)
                        .setContentTitle("You create a notification")
                        .setContentText("Notification "+(pageNumber+1));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(pageNumber, mBuilder.build());
    }

    public static void delete(final Context context, final int id){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(id);
    }
}
