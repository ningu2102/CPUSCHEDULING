package com.example.user.cpuscheduling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ningu.cpuscheduling.R;

public class InputActivity extends AppCompatActivity {

    /* View references */
    private static TextView timeQuantumText,timeQuantumEdit,algorithmName;
    private static EditText noProcessText;
    private static Button submitButton;
    private static RelativeLayout relativeLayout;

    /* Variables for communicating with other componenrs of the app */
    private static final String EXTRA_NO_PRO = "EXTRA_NO_PRO";
    private static final String EXTRA_VIEW_ID = "EXTRA_VIEW_ID";
    private static final String EXTRA_TIME = "EXTRA_TIME";

    /* Normal variables */
    private static int contentId,  no_pro, time,isRr = 0;
    private static String algoname,tempString;

    /* Lifecycle methods */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input);

        contentId = getIntent().getIntExtra("EXTRA_VIEW_ID",0);
        algoname=getIntent().getStringExtra("EXTRA_ALGO_NAME");

        noProcessText = (EditText) findViewById(R.id.pno);
        algorithmName=(TextView) findViewById(R.id.algoname);
        relativeLayout = (RelativeLayout) findViewById(R.id.inputLayout);
        submitButton = (Button) findViewById(R.id.submit);

        algorithmName.setText(algoname);
        algorithmName.setTextSize(20);

        findActivity(contentId,relativeLayout);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noProcessText.getText().toString().trim().equals(""))
                {
                    noProcessText.setError("Enter number of processes");
                }
                else
                {
                    tempString = noProcessText.getText().toString();
                    no_pro = Integer.parseInt(tempString);

                    Intent newIntent = new Intent(InputActivity.this,ParticularActivity.class);
                    newIntent.putExtra(EXTRA_NO_PRO,no_pro);
                    newIntent.putExtra("EXTRA_ALGO_NAME",algoname);
                    newIntent.putExtra(EXTRA_VIEW_ID,contentId);

                    if(isRr == 1)
                    {
                        if(timeQuantumEdit.getText().toString().trim().equals(""))
                        {
                            timeQuantumEdit.setError("Enter Tme Quantumn");
                        }
                        else
                        {
                            tempString = timeQuantumEdit.getText().toString();
                            time = Integer.parseInt(tempString);
                            newIntent.putExtra(EXTRA_TIME,time);
                            newIntent.putExtra("EXTRA_ALGO_NAME",algoname);
                            startActivity(newIntent);
                        }
                    }
                    else
                    {
                        startActivity(newIntent);
                    }
                }
            }
        });
    }

    /* Normal method */
    public void findActivity(int contentId, RelativeLayout relativeLayout)
    {
        if(contentId == R.id.rr)
        {
            isRr = 1;
            timeQuantumText = new TextView(this);
            timeQuantumText.setTextSize(20);
            timeQuantumText.setId(R.id.processEditId);
            timeQuantumText.setText("Enter time Quantum");
            timeQuantumText.setGravity(View.TEXT_ALIGNMENT_GRAVITY);

            timeQuantumEdit = new EditText(this);
            timeQuantumEdit.setTextSize(20);
            timeQuantumEdit.setId(R.id.processTextId);
            timeQuantumEdit.setHint("Enter an Integer");
            timeQuantumEdit.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
            timeQuantumEdit.setInputType(InputType.TYPE_CLASS_NUMBER);

            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams q = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            p.addRule(RelativeLayout.BELOW, noProcessText.getId());
            timeQuantumText.setLayoutParams(p);
            relativeLayout.addView(timeQuantumText);
            p.setMargins(0,20,0,20);


            q.addRule(RelativeLayout.BELOW,timeQuantumText.getId());
            timeQuantumEdit.setLayoutParams(q);
            relativeLayout.addView(timeQuantumEdit);
            q.setMargins(0,20,0,20);
        }
        else
        {
            isRr = 0;
        }
    }
}