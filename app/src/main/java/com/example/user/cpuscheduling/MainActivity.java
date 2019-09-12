package com.example.user.cpuscheduling;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* Variables for view reference */
    private static TextView fcfs, sjf, rr, pri;

    /* Normal variables */
    public static String algo;

//    FragmentManager fragmentManager;

    /* Lifecycle methods */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //fragmentManager=(FragmentManager)

        addNotification();

        fcfs = (TextView) findViewById(R.id.fcfs);
        sjf = (TextView) findViewById(R.id.sjf);
        rr = (TextView) findViewById(R.id.rr);
        pri = (TextView) findViewById(R.id.pri);

        fcfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algo= String.valueOf(fcfs.getText());
                redirect(view.getId());
            }
        });

        sjf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algo= String.valueOf(sjf.getText());
                redirect(view.getId());
            }
        });

        rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algo= String.valueOf(rr.getText());
                redirect(view.getId());
            }
        });

        pri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algo= String.valueOf(pri.getText());
                redirect(view.getId());
            }
        });



}
    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic)
                        .setContentTitle("CPU SCHEDULING")
                        .setContentText("Your app is turned ON");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    /* Normal methods */
    public void redirect(int id) {
        Intent newIntent = new Intent(MainActivity.this, InputActivity.class);
        newIntent.putExtra("EXTRA_VIEW_ID", id);
        newIntent.putExtra("EXTRA_ALGO_NAME",algo);
        startActivity(newIntent);
    }

}
