package com.example.notifications_classactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

//        Button btn = findViewById(R.id.button);


        TextView textView = findViewById(R.id.text2);
        Bundle remoteReply = RemoteInput.getResultsFromIntent(getIntent());
        if (remoteReply != null){
            String message = remoteReply.getCharSequence(MainActivity.KEY_TEXT_REPLY).toString();
            textView.setText(message);
        }
        NotificationManager notificationManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.NOTIFICATION_ID);
    }

}