package com.example.b10709012_hw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ResultActivity";

    TextView resultText;
    Button button1;
    Button button2;
    Bundle args;

    int checkedAmount;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d(TAG, "onCreate: activity started.");

        resultText = findViewById(R.id.resulttextv_view);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: get incoming intent");

        if(getIntent().hasExtra("BUNDLE")){
            args = getIntent().getBundleExtra("BUNDLE");
            ArrayList<Boolean> result = (ArrayList<Boolean>) args.getSerializable("ARRAYLIST");

            Iterator<Boolean> iter = result.iterator();
            while (iter.hasNext()) {
                if(iter.next()==true){
                    checkedAmount++;
                }
            }
            resultText.setText("Choosen Item Amount: "+Integer.toString(checkedAmount));
        }
    }

    @Override
    public void onClick(View v) {

//        //onClick for button1, easy way
//        if (v.getId() == R.id.button1) {
//            Log.d(TAG, "onClick: button 1 clicked");
//            Intent intent = new Intent(this, MainActivity.class);
//
//            setResult(RESULT_CANCELED, intent);
//            finish();
//        }

        //onClick for button1, hard way, using getIncomingIntent() on MainActivity
        if (v.getId() == R.id.button1) {
            Log.d(TAG, "onClick: button 2 clicked");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("BUNDLE", args);
            startActivity(intent);
        }

        //onClick for button2
        if (v.getId() == R.id.button2) {
            Log.d(TAG, "onClick: button 2 clicked");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }

}
