package com.example.user.cpuscheduling;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.PersistableBundle;
import android.support.annotation.StringDef;
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
import com.example.user.cpuscheduling.finalfcfs;
import com.example.user.cpuscheduling.finalrr;
import com.example.user.cpuscheduling.finalsjf;
import com.example.user.cpuscheduling.finalpri;
import org.w3c.dom.Text;


import java.util.ArrayList;
import java.util.List;

public class ParticularActivity extends AppCompatActivity {

    private finalrr rr;
    private finalfcfs fcfs;
    private finalsjf sjf;
    private finalpri pri;
    private int no_pro,timeSlice;
    private int time;
    private TextView TAT,WT;
    private TextView arivalTimeText,BurstTimeText,process,algoname,priorityText;
    private EditText arivalTime,BurstTime,priority;
    TableRow.LayoutParams p,q,r,s;
    ScrollView scrollView;
    String value;
    List<String> ATs = new ArrayList<String>();
    List<String> BTs = new ArrayList<String>();
    List<String> PTs = new ArrayList<String>();
    int id;
    int TATA[] = new int[no_pro];
    int WTA[] = new int[no_pro];
    int gnattchart[] = new int[no_pro];
    Button getAnswer;
    String algorithmname;
    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";
    int mCurrentScore,mCurrentLevel;
    EditText currentEditText;
    //LinearLayout table;
    TableRow row,currentRow;
    int viewId;

    TableLayout mTableLayout;

    public void preparepridata()
    {
        for (int i = 0; i < mTableLayout.getChildCount(); i++) {

            if(i==0)
            {
                continue;
            }
            else {


                currentRow = (TableRow) mTableLayout.getChildAt(i);

                for (int j = 0; j < currentRow.getChildCount(); j++) {
                    if (j == 0) {
                        continue;
                    } else if (j == 1) {


                        EditText currentEditText = (EditText) currentRow.getChildAt(j);
                        ATs.add(currentEditText.getText().toString());


                    } else  if(j==2){
                        EditText currentEditText = (EditText) currentRow.getChildAt(j);
                        BTs.add(currentEditText.getText().toString());
                    }

                    else
                    {
                        EditText currentEditText = (EditText) currentRow.getChildAt(j);
                        PTs.add(currentEditText.getText().toString());
                    }
                }

            }
        }
    }

    public void prepareData()
    {


        for (int i = 0; i < mTableLayout.getChildCount(); i++) {

            if(i==0)
            {
                continue;
            }
            else {


                currentRow = (TableRow) mTableLayout.getChildAt(i);

                for (int j = 0; j < currentRow.getChildCount(); j++) {
                    if (j == 0) {
                        continue;
                    } else if (j == 1) {


                        EditText currentEditText = (EditText) currentRow.getChildAt(j);
                        ATs.add(currentEditText.getText().toString());


                    } else {
                        EditText currentEditText = (EditText) currentRow.getChildAt(j);
                        BTs.add(currentEditText.getText().toString());
                    }
                }

            }
        }
    }

    @Override
    protected void
    onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular);

        algorithmname=getIntent().getStringExtra("EXTRA_ALGO_NAME");

        algoname=(TextView)findViewById(R.id.algoname);

        algoname.setText(algorithmname);
        if (savedInstanceState != null) {
            id  = savedInstanceState.getInt(STATE_SCORE);
            //Toast.makeText(getApplicationContext(), String.valueOf(id),Toast.LENGTH_LONG).show();

        }

        viewId = getIntent().getIntExtra("EXTRA_VIEW_ID",0);

        getAnswer = (Button) findViewById(R.id.getAnswer);
        getAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if( viewId == R.id.fcfs ) {
                    prepareData();
                    //Toast.makeText(getApplicationContext(),"weudsd",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"defaa",Toast.LENGTH_LONG).show();

                    fcfs = new finalfcfs((ArrayList<String>) ATs, (ArrayList<String>) BTs, no_pro);
                    fcfs.excuteFCFS();
                    int[] wt = fcfs.getWT();
                    int[] tat = fcfs.getTAT();
                    int[] gnattchart = fcfs.getGantchart();
                    float atat = fcfs.getAtat();
                    float awt = fcfs.getAwt();
                    int tbt = fcfs.getTbt();

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
                else if(viewId == R.id.rr)
                {
                    prepareData();
                    timeSlice = getIntent().getIntExtra("EXTRA_TIME", 0);
                    rr = new finalrr((ArrayList<String>) ATs,(ArrayList<String>) BTs,no_pro,timeSlice);
                    rr.execrr();

                    int[] wt = rr.getWT();
                    int[] tat = rr.getTAT();
                    int[] gnattchart = rr.getGanttchart();
                    float atat = rr.getAvgtt();
                    float awt = rr.getAvgwt();
                    int tbt = rr.getTbt();

                    Intent newIntent = new Intent(ParticularActivity.this,OutputActivity.class);
                    newIntent.putStringArrayListExtra("EXTRA_ATs", (ArrayList<String>) ATs);
                    newIntent.putStringArrayListExtra("EXTRA_BTs", (ArrayList<String>) BTs);
                    newIntent.putExtra("EXTRA_TATs", tat);
                    newIntent.putExtra("EXTRA_WTs", wt);
                    newIntent.putExtra("EXTRA_GANT",gnattchart);
                    newIntent.putExtra("EXTRA_ATAT", atat);
                    newIntent.putExtra("EXTRA_AWT",awt);
                    newIntent.putExtra("EXTRA_TBT",tbt);
                    newIntent.putExtra("EXTRA_NO_PRO",no_pro);
                  newIntent.putExtra("EXTRA_TIME",time);

                    startActivity(newIntent);

                }
                else if(viewId == R.id.sjf)
                {
                    prepareData();
                    sjf = new finalsjf((ArrayList<String>) ATs,(ArrayList<String>) BTs,no_pro);
                    sjf.excuteSJf();

                    int[] wt = sjf.getWT();
                    int[] tat = sjf.getTAT();
                    int[] gnattchart = sjf.getGantchart();
                    float atat = sjf.getAtat();
                    float awt = sjf.getAwt();
                    int tbt = sjf.getTbt();

                    Intent newIntent = new Intent(ParticularActivity.this,OutputActivity.class);
                    newIntent.putStringArrayListExtra("EXTRA_ATs", (ArrayList<String>) ATs);
                    newIntent.putStringArrayListExtra("EXTRA_BTs", (ArrayList<String>) BTs);
                    newIntent.putExtra("EXTRA_TATs", tat);
                    newIntent.putExtra("EXTRA_WTs", wt);
                    newIntent.putExtra("EXTRA_GANT",gnattchart);
                    newIntent.putExtra("EXTRA_ATAT", atat);
                    newIntent.putExtra("EXTRA_AWT",awt);
                    newIntent.putExtra("EXTRA_TBT",tbt);
                    newIntent.putExtra("EXTRA_NO_PRO",no_pro);
                    newIntent.putExtra("EXTRA_TIME",time);

                    startActivity(newIntent);
                }
                else if(viewId == R.id.pri)
                {
                    Toast.makeText(getApplicationContext(),"wede",Toast.LENGTH_SHORT).show();
                    preparepridata();



                    pri = new finalpri((ArrayList<String>) ATs,(ArrayList<String>) BTs,no_pro,(ArrayList<String>) PTs);
                    pri.execpri();

                    int[] wt = pri.getWT();
                    int[] tat = pri.getTAT();
                    int[] gnattchart = pri.getGantt();
                    float atat = pri.getAvgtt();
                    float awt = pri.getAvgwt();
                    int tbt = pri.getGp();

                    Intent newIntent = new Intent(ParticularActivity.this,OutputActivity.class);
                    newIntent.putStringArrayListExtra("EXTRA_ATs", (ArrayList<String>) ATs);
                    newIntent.putStringArrayListExtra("EXTRA_BTs", (ArrayList<String>) BTs);
                    newIntent.putExtra("EXTRA_TATs", tat);
                    newIntent.putExtra("EXTRA_WTs", wt);
                    newIntent.putExtra("EXTRA_GANT",gnattchart);
                    newIntent.putExtra("EXTRA_ATAT", atat);
                    newIntent.putExtra("EXTRA_AWT",awt);
                    newIntent.putExtra("EXTRA_TBT",tbt);
                    newIntent.putExtra("EXTRA_NO_PRO",no_pro);
                    newIntent.putExtra("EXTRA_TIME",time);


                    startActivity(newIntent);
                }
            }
        });
        //text = (TextView) findViewById(R.id.textView2);
        no_pro = getIntent().getIntExtra("EXTRA_NO_PRO",0);
        time = getIntent().getIntExtra("EXTRA_TIME",0);
        //table =  (LinearLayout) findViewById(R.id.table);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

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

                row.addView(process);
                row.addView(arivalTimeText);
                row.addView(BurstTimeText);
                if(viewId==R.id.pri)
                {
                    priorityText=new TextView(this);
                    priorityText.setHeight((int)px);
                    priorityText.setText("Priority");
                    priorityText.setGravity(Gravity.CENTER);
                    s = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                    s.weight = 1;
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

                arivalTime.setId(new Integer(((count-1)*2) + 1));
                BurstTime.setId(new Integer(((count-1)*2) + 2));


                arivalTime.setHint("   ");
                BurstTime.setHint("   ");
                process.setText(String.valueOf(count));
                process.setGravity(Gravity.CENTER);

                arivalTime.setGravity(Gravity.CENTER);
                arivalTime.setInputType(InputType.TYPE_CLASS_NUMBER);
                BurstTime.setGravity(Gravity.CENTER);
                BurstTime.setInputType(InputType.TYPE_CLASS_NUMBER);
                p = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                p.weight = 1;
                process.setLayoutParams(p);
                process.setBackgroundResource(R.drawable.row_border);

                q = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                q.weight = 1;
                arivalTime.setLayoutParams(p);
                arivalTime.setBackgroundResource(R.drawable.row_border);

                r = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                r.weight = 1;
                BurstTime.setLayoutParams(p);
                BurstTime.setBackgroundResource(R.drawable.row_border);

                row.addView(process);
                row.addView(arivalTime);
                row.addView(BurstTime);

                if(viewId==R.id.pri)
                {
                    priority=new EditText(this);
                    priority.setId(new Integer(((count-1)*2) + 3));
                    priority.setGravity(Gravity.CENTER);
                    priority.setInputType(InputType.TYPE_CLASS_NUMBER);
                    s = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                    s.weight = 1;
                    priority.setLayoutParams(p);
                    priority.setBackgroundResource(R.drawable.row_border);
                    row.addView(priority);


                }

            }
            id = 100;
            mTableLayout.addView(row);
        }
        scrollView.addView(mTableLayout);

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state


        mCurrentLevel = id;

        savedInstanceState.putInt(STATE_SCORE, mCurrentLevel);


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

    }
}