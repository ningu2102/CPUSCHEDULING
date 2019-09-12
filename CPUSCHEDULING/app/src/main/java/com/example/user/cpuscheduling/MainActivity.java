package com.example.user.cpuscheduling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private TextView fcfs, sjf, rr, pri;
    public String algo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fcfs = (TextView) findViewById(R.id.fcfs);
        sjf = (TextView) findViewById(R.id.sjf);
        rr = (TextView) findViewById(R.id.rr);
        pri = (TextView) findViewById(R.id.pri);

        fcfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algo="";
                //String string = view.getResources().getResourceName(view.getId());
                //Toast.makeText(getApplicationContext(),string, Toast.LENGTH_SHORT).show();
                algo= String.valueOf(fcfs.getText());
                redirect(view.getId());

            }

        });

        sjf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algo="";
                algo= String.valueOf(sjf.getText());
                redirect(view.getId());

            }

        });

        rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algo="";
                algo= String.valueOf(rr.getText());
                redirect(view.getId());

            }

        });

        pri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algo="";
                algo= String.valueOf(pri.getText());
                redirect(view.getId());

            }

        });
    }

    public void redirect(int id) {

        Intent newIntent = new Intent(MainActivity.this, InputActivity.class);
        newIntent.putExtra("EXTRA_VIEW_ID", id);
        newIntent.putExtra("EXTRA_ALGO_NAME",algo);
        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
        startActivity(newIntent);
    }
}