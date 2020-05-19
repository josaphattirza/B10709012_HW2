package com.example.b10709012_hw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mText = new ArrayList<>();
    private ArrayList<Boolean> checkedMain = new ArrayList<>();
    Button button1;
    private ArrayList<Boolean> result;
    RecyclerViewAdapter adapter;

    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        initCheckBoxArray();

        button1 = findViewById(R.id.confirmButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                result = adapter.getChecked();
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)result);
                intent.putExtra("BUNDLE", args);
//                //easy way
//                startActivityForResult(intent,1);

                //hard way
                startActivity(intent);
            }
        });
    }

    //hard way
    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: get incoming intent");

        if(getIntent().hasExtra("BUNDLE")){
            args = getIntent().getBundleExtra("BUNDLE");
            ArrayList<Boolean> result = (ArrayList<Boolean>) args.getSerializable("ARRAYLIST");
            checkedMain = result;
        }
    }

    private void initCheckBoxArray() {
        mText.add("toys1");
        checkedMain.add(false);
        mText.add("toys2");
        checkedMain.add(false);
        mText.add("toys3");
        checkedMain.add(false);
        mText.add("toys4");
        checkedMain.add(false);
        mText.add("toys5");
        checkedMain.add(false);
        mText.add("toys6");
        checkedMain.add(false);
        mText.add("toys7");
        checkedMain.add(false);
        mText.add("toys8");
        checkedMain.add(false);
        mText.add("toys9");
        checkedMain.add(false);
        mText.add("toys10");
        checkedMain.add(false);
        mText.add("toys11");
        checkedMain.add(false);
        mText.add("toys12");
        checkedMain.add(false);
        mText.add("toys13");
        checkedMain.add(false);
        mText.add("toys14");
        checkedMain.add(false);
        mText.add("toys15");
        checkedMain.add(false);
        mText.add("toys16");
        checkedMain.add(false);
        mText.add("toys17");
        checkedMain.add(false);
        mText.add("toys18");
        checkedMain.add(false);
        mText.add("toys19");
        checkedMain.add(false);
        mText.add("toys20");
        checkedMain.add(false);
        mText.add("toys21");
        checkedMain.add(false);
        mText.add("toys22");
        checkedMain.add(false);
        mText.add("toys23");
        checkedMain.add(false);
        mText.add("toys24");
        checkedMain.add(false);
        mText.add("toys25");
        checkedMain.add(false);


        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        //for the har dway
        getIncomingIntent();

        adapter = new RecyclerViewAdapter(this, mText, checkedMain);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if(menuItemThatWasSelected == R.id.add) {
            Toast.makeText(this, "add",Toast.LENGTH_LONG).show();
        }
        else if (menuItemThatWasSelected == R.id.setting) {
            Toast.makeText(this,"setting",Toast.LENGTH_LONG).show();

        }
        return true;
    }
}
