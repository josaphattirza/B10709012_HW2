package com.example.b10709012_hw2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b10709012_hw2.data.WaitListContract;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.GuestViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private Context mContext;
    private Cursor mCursor;

    public RecyclerViewAdapter(Context mContext,Cursor cursor) {
        this.mContext = mContext;
        this.mCursor = cursor;
//        this.mCheckb = mCheckb;
//        this.mChecked = checked;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_listitem, parent, false);
        GuestViewHolder holder = new GuestViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GuestViewHolder holder, final int position) {

        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // COMPLETED (6) Call getString on the cursor to get the guest's name
        String name = mCursor.getString(mCursor.getColumnIndex(WaitListContract.WaitListEntry.COLUMN_GUEST_NAME));

        // COMPLETED (7) Call getInt on the cursor to get the party size
        int partySize = mCursor.getInt(mCursor.getColumnIndex(WaitListContract.WaitListEntry.COLUMN_PARTY_SIZE));
        long id = mCursor.getLong(mCursor.getColumnIndex(WaitListContract.WaitListEntry._ID));

        // Display the guest name
        holder.nameTextView.setText(name);
        // Display the party count
        holder.partySizeTextView.setText(String.valueOf(partySize));
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        //getItemCount  return the getCount of the cursor
        return mCursor.getCount();
    }

    /**
     * Swaps the Cursor currently held in the adapter with a new one
     * and triggers a UI refresh
     *
     * @param newCursor the new cursor that will replace the existing one
     */
    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder implements SharedPreferences.OnSharedPreferenceChangeListener{

        // Will display the guest name
        TextView nameTextView;
        // Will display the party size number
        TextView partySizeTextView;
        LinearLayout parentLayout;


        public GuestViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            partySizeTextView = (TextView) itemView.findViewById(R.id.party_size_text_view);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            setupSharedPreferences();


            //deprecated CONTEXT.getResource().getColor()
//            partySizeTextView.getBackground().setTint(mContext.getResources().getColor(R.color.shapeRed));
        }

        public void setupSharedPreferences() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            loadColorFromPreferences(sharedPreferences);

            sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        }

        private void loadColorFromPreferences(SharedPreferences sharedPreferences) {
            String background_chooser = sharedPreferences.getString(mContext.getString(R.string.pref_color_key),
                    mContext.getString(R.string.pref_color_red_value));

            if(background_chooser.equals("red")){
                partySizeTextView.getBackground().setTint(ContextCompat.getColor(mContext, R.color.shapeRed));
            }

            else if(background_chooser.equals("blue")){
                partySizeTextView.getBackground().setTint(ContextCompat.getColor(mContext, R.color.shapeBlue));
            }

            else if(background_chooser.equals("green")){
                partySizeTextView.getBackground().setTint(ContextCompat.getColor(mContext, R.color.shapeGreen));
            }
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals(mContext.getString(R.string.pref_color_key))) {
                loadColorFromPreferences(sharedPreferences);
            }
        }
    }

}