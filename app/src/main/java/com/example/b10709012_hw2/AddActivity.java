package com.example.b10709012_hw2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.b10709012_hw2.data.WaitListContract;
import com.example.b10709012_hw2.data.WaitListDbHelper;


public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "AddActivity";

    private SQLiteDatabase mDb;
    private EditText mNewGuestNameEditText;
    private EditText mNewPartySizeEditText;

    Button button1;
    Button button2;
    Bundle args;

    int checkedAmount;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Log.d(TAG, "onCreate: activity started.");

        WaitListDbHelper dbHelper = new WaitListDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        // COMPLETED (2) Set the Edit texts to the corresponding views using findViewById
        mNewGuestNameEditText = (EditText) this.findViewById(R.id.person_name_edit_text);
        mNewPartySizeEditText = (EditText) this.findViewById(R.id.party_count_edit_text);

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
        }
    }

    @Override
    public void onClick(View v) {

        //onClick for button1, easy way
        if (v.getId() == R.id.button1) {
            Log.d(TAG, "onClick: button 1 clicked");
            addToWaitlist(v);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        //onClick for button2
        if (v.getId() == R.id.button2) {
            Log.d(TAG, "onClick: button 2 clicked");
            Intent intent = new Intent(this, MainActivity.class);
            setResult(RESULT_CANCELED, intent);
            finish();
        }


    }

    public void addToWaitlist(View view) {
        //First thing, check if any of the EditTexts are empty, return if so
        if (mNewGuestNameEditText.getText().length() == 0 ||
                mNewPartySizeEditText.getText().length() == 0) {
            return;
        }
        //Create an integer to store the party size and initialize to 1
        //default party size to 1
        int partySize = 1;
        // Use Integer.parseInt to parse mNewPartySizeEditText.getText to an integer
        try {
            //mNewPartyCountEditText inputType="number", so this should always work
            partySize = Integer.parseInt(mNewPartySizeEditText.getText().toString());
        } catch (NumberFormatException ex) {
            //Make sure you surround the Integer.parseInt with a try catch and log any exception
            Log.e(TAG, "Failed to parse party size text to number: " + ex.getMessage());
        }

        // Call addNewGuest with the guest name and party size
        // Add guest info to mDb
        addNewGuest(mNewGuestNameEditText.getText().toString(), partySize);

        //To make the UI look nice, call .getText().clear() on both EditTexts, also call clearFocus() on mNewPartySizeEditText
        //clear UI text fields
        mNewPartySizeEditText.clearFocus();
        mNewGuestNameEditText.getText().clear();
        mNewPartySizeEditText.getText().clear();
    }

    // Adds a new guest to the mDb including the party count and the current timestamp
    private long addNewGuest(String name, int partySize) {
        // Create a ContentValues instance to pass the values onto the insert query
        ContentValues cv = new ContentValues();

        // Call put to insert the name value with the key COLUMN_GUEST_NAME
        cv.put(WaitListContract.WaitListEntry.COLUMN_GUEST_NAME, name);

        // Call put to insert the party size value with the key COLUMN_PARTY_SIZE
        cv.put(WaitListContract.WaitListEntry.COLUMN_PARTY_SIZE, partySize);
        
        //Call insert to run an insert query on TABLE_NAME with the ContentValues created
        return mDb.insert(WaitListContract.WaitListEntry.TABLE_NAME, null, cv);
    }

}
