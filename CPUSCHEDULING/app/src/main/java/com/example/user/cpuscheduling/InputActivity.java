package com.example.user.cpuscheduling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import com.example.user.cpuscheduling.finalfcfs;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class InputActivity extends AppCompatActivity {
    int contentId;
    TextView text1,text2;
    TextView timeQuantum,noProcess,algorithmName;
    EditText timeQuantumText,noProcessText;
    Button submitButton;
    RelativeLayout relativeLayout;
    int no_pro, time;
    private static int isRr = 0;

    private static final String EXTRA_NO_PRO = "EXTRA_NO_PRO";
    private static final String EXTRA_VIEW_ID = "EXTRA_VIEW_ID";
    private static final String EXTRA_TIME = "EXTRA_TIME";
    private static String algoname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        contentId = getIntent().getIntExtra("EXTRA_VIEW_ID",0);
        algoname=getIntent().getStringExtra("EXTRA_ALGO_NAME");

        noProcessText = (EditText) findViewById(R.id.pno);
        noProcess = (TextView) findViewById(R.id.processTextView);
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
                    String tempString = noProcessText.getText().toString();
                    no_pro = Integer.parseInt(tempString);

                    Intent newIntent = new Intent(InputActivity.this,ParticularActivity.class);
                    newIntent.putExtra(EXTRA_NO_PRO,no_pro);
                    newIntent.putExtra("EXTRA_ALGO_NAME",algoname);
                    newIntent.putExtra(EXTRA_VIEW_ID,contentId);


                    if(isRr == 1)
                    {

                        if(text2.getText().toString().trim().equals(""))
                        {
                            text2.setError("Enter Tme Quantumn");
                        }
                        else
                        {
                            tempString = text2.getText().toString();
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


// yeh nai chahiye isse back aaya toh humko phrse dalan apdega time ,woh pehle ka data as a
    //hint chala jaata hai
   /* @Override
    protected void onPostResume() {
        super.onPostResume();

        contentId = getIntent().getIntExtra("EXTRA_VIEW_ID",1);
        noProcessText = (EditText) findViewById(R.id.pno);
        noProcess = (TextView) findViewById(R.id.processTextView);
        relativeLayout = (RelativeLayout) findViewById(R.id.inputLayout);
        submitButton = (Button) findViewById(R.id.submit);
        findActivity(contentId,relativeLayout);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempString = noProcessText.getText().toString();
                no_pro = Integer.parseInt(tempString);

                Intent newIntent = new Intent(InputActivity.this,ParticularActivity.class);
                newIntent.putExtra(EXTRA_NO_PRO,no_pro);
                newIntent.putExtra("EXTRA_VIEW_ID",contentId);

                if(isRr == 1)
                {
                    tempString = text2.getText().toString();
                    time = Integer.parseInt(tempString);
                    newIntent.putExtra(EXTRA_TIME,time);
                }



                startActivity(newIntent);

            }
        });

    }*/

    public void findActivity(int contentId, RelativeLayout relativeLayout)
    {
        if(contentId == R.id.rr)
        {

            isRr = 1;
            text1 = new TextView(this);
            text1.setText("Enter time Quantum");
            text1.setId(R.id.processEditId);
            text1.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
            text1.setTextSize(20);

            text2 = new EditText(this);
            text2.setId(R.id.processTextId);
            text2.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
            text2.setTextSize(20);
            text2.setHint("Enter an Integer");
            text2.setInputType(InputType.TYPE_CLASS_NUMBER);

            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams q = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            p.addRule(RelativeLayout.BELOW, noProcessText.getId());
            text1.setLayoutParams(p);
            relativeLayout.addView(text1);
            p.setMargins(0,20,0,20);


            q.addRule(RelativeLayout.BELOW,text1.getId());
            text2.setLayoutParams(q);
            relativeLayout.addView(text2);
            q.setMargins(0,20,0,20);


        }
        else
        {
            isRr = 0;
        }
    }

}