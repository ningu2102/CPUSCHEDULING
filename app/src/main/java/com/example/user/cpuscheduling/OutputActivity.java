package com.example.user.cpuscheduling;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class OutputActivity extends AppCompatActivity {

    private int no_pro;
    private int time;
    private TextView arivalTimeText, BurstTimeText, process,arivalTime, BurstTime,gnattChart;
    private Button moreButton;
    TableRow.LayoutParams p, q, r,s,t;
    TextView TAT,WT;
    ScrollView scrollView;
    TableLayout mTableLayout;
    TableRow row;

    private List<String> ATs = new ArrayList<String>();
    private List<String> BTs = new ArrayList<String>();
    private int[] gnattchart;
    private int[] tat;
    private int[] wt;
    private int tbt;
    private float atat;
    private float awt;
    public void retrieveData(){


        ATs = (ArrayList<String>) getIntent().getStringArrayListExtra("EXTRA_ATs");
        BTs = (ArrayList<String>) getIntent().getStringArrayListExtra("EXTRA_BTs");


        tbt = getIntent().getIntExtra("EXTRA_TBT",0);
        atat = getIntent().getIntExtra("EXTRA_ATAT",0);
        awt = getIntent().getIntExtra("EXTRA_AWT",0);
        tat = getIntent().getIntArrayExtra("EXTRA_TATs");
        wt = getIntent().getIntArrayExtra("EXTRA_WTs");
        gnattchart = getIntent().getIntArrayExtra("EXTRA_GANT");
        no_pro = getIntent().getIntExtra("EXTRA_NO_PRO",0);
        time = getIntent().getIntExtra("EXTRA_TIME",0);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        retrieveData();
        gnattChart = (TextView) findViewById(R.id.ganttchart);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        displayAnswer();

        moreButton=(Button) findViewById(R.id.wantMore);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revertMethod();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        retrieveData();
        scrollView.removeViewAt(0);
        displayAnswer();
    }

    public void revertMethod() {
        //Toast.makeText(getApplicationContext(), "Revert method", Toast.LENGTH_LONG).show();
        Intent newIntent = new Intent(OutputActivity.this, MainActivity.class);
        startActivity(newIntent);
    }


    public void displayAnswer() {
        mTableLayout = new TableLayout(this);
        LinearLayout.LayoutParams tableLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tableLayoutParams.setMargins(0, 50, 0, 0);
        mTableLayout.setLayoutParams(tableLayoutParams);

        for (int count = 0; count <= no_pro; count++) {

            row = new TableRow(this);
            row.setLayoutParams((new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT)));
            if (count == 0) {

                process = new TextView(this);
                arivalTimeText = new TextView(this);
                BurstTimeText = new TextView(this);
                TAT = new TextView(this);
                WT = new TextView(this);

                Resources res = getResources();
                float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, res.getDisplayMetrics());

                process.setHeight((int)px);
                arivalTimeText.setHeight((int)px);
                BurstTimeText.setHeight((int)px);
                TAT.setHeight((int)px);
                WT.setHeight((int)px);

                process.setText("PID");
                process.setGravity(View.TEXT_ALIGNMENT_GRAVITY);

                arivalTimeText.setText("AT");
                arivalTimeText.setGravity(View.TEXT_ALIGNMENT_GRAVITY);

                BurstTimeText.setText("BT");
                BurstTimeText.setGravity(View.TEXT_ALIGNMENT_GRAVITY);

                TAT.setText("TAT");
                TAT.setGravity(View.TEXT_ALIGNMENT_GRAVITY);

                WT.setText("WT");
                WT.setGravity(View.TEXT_ALIGNMENT_GRAVITY);

                process.setGravity(Gravity.CENTER);
                arivalTimeText.setGravity(Gravity.CENTER);
                BurstTimeText.setGravity(Gravity.CENTER);
                TAT.setGravity(Gravity.CENTER);
                WT.setGravity(Gravity.CENTER);


                p = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                p.weight = 1;
                process.setLayoutParams(p);
                process.setBackgroundResource(R.drawable.row_border);

                q = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                q.weight = 1;
                arivalTimeText.setLayoutParams(p);
                arivalTimeText.setBackgroundResource(R.drawable.row_border);

                r = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                r.weight = 1;
                BurstTimeText.setLayoutParams(p);
                BurstTimeText.setBackgroundResource(R.drawable.row_border);

                s = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                s.weight = 1;
                TAT.setLayoutParams(p);
                TAT.setBackgroundResource(R.drawable.row_border);

                t = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                t.weight = 1;
                WT.setLayoutParams(p);
                WT.setBackgroundResource(R.drawable.row_border);

                row.addView(process);
                row.addView(arivalTimeText);
                row.addView(BurstTimeText);
                row.addView(TAT);
                row.addView(WT);
            } else {

                process = new TextView(this);
                arivalTime = new TextView(this);
                BurstTime = new TextView(this);
                TAT = new TextView(this);
                WT =  new TextView(this);


                Resources res = getResources();
                float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, res.getDisplayMetrics());

                process.setHeight((int)px);
                arivalTime.setHeight((int)px);
                BurstTime.setHeight((int)px);
                TAT.setHeight((int)px);
                WT.setHeight((int)px);

                arivalTime.setId(new Integer(((count - 1) * 2) + 1));
                BurstTime.setId(new Integer(((count - 1) * 2) + 2));

                process.setGravity(Gravity.CENTER);
                arivalTime.setGravity(Gravity.CENTER);
                BurstTime.setGravity(Gravity.CENTER);
                TAT.setGravity(Gravity.CENTER);
                WT.setGravity(Gravity.CENTER);

                arivalTime.setText(ATs.get(count - 1));
                BurstTime.setText(BTs.get(count - 1));
                TAT.setText(String.valueOf(tat[count-1]));

                WT.setText(String.valueOf(wt[count-1]));
                process.setText(String.valueOf(count));


                p = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                p.weight = 1;
                process.setLayoutParams(p);
                process.setBackgroundResource(R.drawable.row_border);

                q = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                q.weight = 1;
                arivalTime.setLayoutParams(p);
                arivalTime.setBackgroundResource(R.drawable.row_border);

                r = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                r.weight = 1;
                BurstTime.setLayoutParams(p);
                BurstTime.setBackgroundResource(R.drawable.row_border);


                s = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                s.weight = 1;
                TAT.setLayoutParams(p);
                TAT.setBackgroundResource(R.drawable.row_border);

                t = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                t.weight = 1;
                WT.setLayoutParams(p);
                WT.setBackgroundResource(R.drawable.row_border);

                row.addView(process);
                row.addView(arivalTime);
                row.addView(BurstTime);
                row.addView(TAT);
                row.addView(WT);

            }
            mTableLayout.addView(row);
        }
        scrollView.addView(mTableLayout);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0; i < tbt;i++)
        {
            stringBuffer.append(String.valueOf(gnattchart[i]));
            stringBuffer.append(" ");
        }
        gnattChart.setText(stringBuffer.toString());
        gnattChart.setTextSize(20);
    }

}