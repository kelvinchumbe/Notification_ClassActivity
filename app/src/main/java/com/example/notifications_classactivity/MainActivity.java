package com.example.notifications_classactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TEXT_REPLY = "key_text_reply";

    private final String CHANNEL_ID = "Personal Notification";
    public static final int NOTIFICATION_ID = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    public void showNotification(){
        createNotificationChannel();

        Intent landingintent = new Intent(this, Activity2.class);
        landingintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, landingintent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_chat_black_24dp);
        builder.setContentTitle("Overwatch");
        builder.setContentText("You have been robbed!!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY).setLabel("Reply").build();
        Intent replyIntent = new Intent(this, Activity3.class);
        replyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent replyPendingIntent = PendingIntent.getActivity(this, 0, replyIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_message_black_24dp, "Reply",
                replyPendingIntent).addRemoteInput(remoteInput).build();
        builder.addAction(action);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            CharSequence name = "Personal Notification";
            String description = "To include all Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel NC = new NotificationChannel(CHANNEL_ID, name, importance);
            NC.setDescription(description);

            NotificationManager NM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NM.createNotificationChannel(NC);
        }
    }

    public void displayNotification(View view){
        Intent landingintent = new Intent(this, Activity3.class);
        landingintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent landingPendingIntent =
                PendingIntent.getActivity(this, 0, landingintent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentIntent(landingPendingIntent);
        builder.setAutoCancel(true);
    }

}
