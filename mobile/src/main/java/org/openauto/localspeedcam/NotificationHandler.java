package org.openauto.localspeedcam;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.apps.auto.sdk.CarToast;
import com.google.android.apps.auto.sdk.notification.CarNotificationExtender;

public class NotificationHandler {

    private static final int TEST_NOTIFICATION_ID = 1;
    private Handler mHandler = new Handler();

    private void showTestNotification(Context context) {
        CarToast.makeText(context, "Will show notification in 5 seconds", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(() -> {
            Notification notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Test notification")
                    .setContentText("This is a test notification")
                    .setAutoCancel(true)
                    .extend(new CarNotificationExtender.Builder()
                            .setTitle("Test")
                            .setSubtitle("This is a test notification")
                            .setActionIconResId(R.mipmap.ic_launcher)
                            .setThumbnail(CarUtils.getCarBitmap(context,
                                    R.mipmap.ic_launcher, R.color.car_primary, 128))
                            .setShouldShowAsHeadsUp(true)
                            .build())
                    .build();

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(MainCarActivity.class.getSimpleName(), TEST_NOTIFICATION_ID, notification);

            CarNotificationSoundPlayer soundPlayer = new CarNotificationSoundPlayer(
                    context, R.raw.bubble);
            soundPlayer.play();
        }, 5000);
    }
}
