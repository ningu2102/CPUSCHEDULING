package com.example.user.cpuscheduling;

import android.content.Intent;
import android.content.res.Resources;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.example.ningu.cpuscheduling.finalfcfs;
import com.example.user.cpuscheduling.AlgorithmInterface;

public class ParticularActivity extends AppCompatActivity {

    /* object reference variables */
    private static AlgorithmInterface algorithmObj;

    /* Normal variables */
    private static int no_pro,tbt;
    private static int[] wt = new int[no_pro];
    private static int[] tat = new int[no_pro];
    private static int[] gnattchart = new int[tbt];
    private static float awt,atat;
    private static String algorithmname;

    /* Data for communicating with other app components */
    private static int time,viewId;;
    private static List<String> ATs = new ArrayList<String>();
    private static List<String> BTs = new ArrayList<String>();
    private static List<String> PTs = new ArrayList<String>();

    /* view and object references */
    private static TextView arivalTimeText,BurstTimeText,process,algoname,priorityText;
    private static EditText arivalTime,BurstTime,priority,currentEditText;
    private static TableRow.LayoutParams p;
    private static ScrollView scrollView;
    private static Button getAnswer;
    private static TableRow row,currentRow;
    private static TableLayout mTableLayout;

    /* Lifecycle methods */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        viewId = savedInstanceState.getInt("VIEW");
    }

    @Override
    protected void onResume() {
        super.onResume();
        buildView();
    }

    @Override
    protected void
    onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular);

        algorithmname=getIntent().getStringExtra("EXTRA_ALGO_NAME");
        viewId = getIntent().getIntExtra("EXTRA_VIEW_ID",0);
        no_pro = getIntent().getIntExtra("EXTRA_NO_PRO",0);
        time = getIntent().getIntExtra("EXTRA_TIME",0);

        algoname=(TextView)findViewById(R.id.algoname);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        algoname.setText(algorithmname);

        if (savedInstanceState != null) {
            viewId =  savedInstanceState.getInt("VIEW_ID");
        }

        mTableLayout = new TableLayout(this);
        LinearLayout.LayoutParams tableLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tableLayoutParams.setMargins(0,50,0,0);
        mTableLayout.setLayoutParams(tableLayoutParams);

        for (int count = 0; count <= no_pro; count++) {

            row = new TableRow(this);
            row.setLayoutParams((new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT)));

            if(count == 0)
            {
                process = new TextView(this);
                arivalTimeText = new TextView(this);
                BurstTimeText = new TextView(this);

                Resources res = getResources();
                float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, res.getDisplayMetrics());

                process.setHeight((int)px);
                arivalTimeText.setHeight((int)px);
                BurstTimeText.setHeight((int)px);

                process.setText("PID");
                process.setGravity(Gravity.CENTER);
                arivalTimeText.setText("Arrival Time");
                arivalTimeText.setGravity(Gravity.CENTER);
                BurstTimeText.setText("Burst Time");
                BurstTimeText.setGravity(Gravity.CENTER);

                p = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                p.weight = 1;
                process.setLayoutParams(p);
                process.setBackgroundResource(R.drawable.row_border);
                arivalTimeText.setLayoutParams(p);
                arivalTimeText.setBackgroundResource(R.drawable.row_border);
                BurstTimeText.setLayoutParams(p);
                BurstTimeText.setBackgroundResource(R.drawable.row_border);

                row.addView(process);
                row.addView(arivalTimeText);
                row.addView(BurstTimeText);

                if(viewId==R.id.pri)
                {
                    priorityText=new TextView(this);
                    priorityText.setHeight((int)px);
                    priorityText.setText("Priority");
                    priorityText.setGravity(Gravity.CENTER);
                    priorityText.setLayoutParams(p);
                    priorityText.setBackgroundResource(R.drawable.row_border);
                    row.addView(priorityText);
                }
            }
            else
            {
                process = new TextView(this);
                arivalTime = new EditText(this);
                BurstTime = new EditText(this);

                arivalTime.setHint("   ");
                BurstTime.setHint("   ");
                process.setText(String.valueOf(count));

                process.setGravity(Gravity.CENTER);
                arivalTime.setGravity(Gravity.CENTER);
                BurstTime.setGravity(Gravity.CENTER);

                arivalTime.setInputType(InputType.TYPE_CLASS_NUMBER);
                BurstTime.setInputType(InputType.TYPE_CLASS_NUMBER);

                p = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                p.weight = 1;

                process.setLayoutParams(p);
                process.setBackgroundResource(R.drawable.row_border);
                arivalTime.setLayoutParams(p);
                arivalTime.setBackgroundResource(R.drawable.row_border);
                BurstTime.setLayoutParams(p);
                BurstTime.setBackgroundResource(R.drawable.row_border);

                row.addView(process);
                row.addView(arivalTime);
                row.addView(BurstTime);

                if(viewId==R.id.pri)
                {
                    priority=new EditText(this);
                    priority.setGravity(Gravity.CENTER);
                    priority.setInputType(InputType.TYPE_CLASS_NUMBER);
                    priority.setLayoutParams(p);
                    priority.setBackgroundResource(R.drawable.row_border);
                    row.addView(priority);
                }
            }
            mTableLayout.addView(row);
        }
        scrollView.addView(mTableLayout);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("VIEW", viewId);
        super.onSaveInstanceState(savedInstanceState);
    }

    /* Normal methods */
    public void prepareData()
    {
        if(viewId == R.id.pri)
        {
            PTs.clear();
        }
        ATs.clear();
        BTs.clear();

        for (int i = 1; i < mTableLayout.getChildCount(); i++) {

            currentRow = (TableRow) mTableLayout.getChildAt(i);

            currentEditText = (EditText) currentRow.getChildAt(1);
            ATs.add(currentEditText.getText().toString());

            currentEditText = (EditText) currentRow.getChildAt(2);
            BTs.add(currentEditText.getText().toString());

            if(viewId == R.id.pri)
            {
                currentEditText = (EditText) currentRow.getChildAt(3);
                PTs.add(currentEditText.getText().toString());
            }
        }
    }


    public void buildView(){

        getAnswer = (Button) findViewById(R.id.getAnswer);
        getAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prepareData();

                switch(viewId)
                {
                    case R.id.fcfs:
                        algorithmObj = new finalfcfs((ArrayList<String>) ATs, (ArrayList<String>) BTs, no_pro);
                        break;
                    case R.id.sjf:
                        algorithmObj = new finalsjf((ArrayList<String>) ATs,(ArrayList<String>) BTs,no_pro);
                        break;
                    case R.id.rr:
                        algorithmObj = new finalrr((ArrayList<String>) ATs,(ArrayList<String>) BTs,no_pro,time);
                        break;
                    case R.id.pri:
                        algorithmObj = new finalpri((ArrayList<String>) ATs,(ArrayList<String>) BTs,no_pro,(ArrayList<String>) PTs);
                        break;

                }

                algorithmObj.exec();
                wt = algorithmObj.getWt();
                tat = algorithmObj.getTat();
                gnattchart = algorithmObj.getGnattchart();
                atat = algorithmObj.getAtat();
                awt = algorithmObj.getAwt();
                tbt = algorithmObj.getTbt();

                Intent newIntent = new Intent(ParticularActivity.this, OutputActivity.class);
                newIntent.putStringArrayListExtra("EXTRA_ATs", (ArrayList<String>) ATs);
                newIntent.putStringArrayListExtra("EXTRA_BTs", (ArrayList<String>) BTs);
                newIntent.putExtra("EXTRA_TATs", tat);
                newIntent.putExtra("EXTRA_WTs", wt);
                newIntent.putExtra("EXTRA_GANT", gnattchart);
                newIntent.putExtra("EXTRA_ATAT", atat);
                newIntent.putExtra("EXTRA_AWT", awt);
                newIntent.putExtra("EXTRA_TBT", tbt);
                newIntent.putExtra("EXTRA_NO_PRO", no_pro);
                newIntent.putExtra("EXTRA_TIME", time);
                startActivity(newIntent);
            }
        });
    }



}