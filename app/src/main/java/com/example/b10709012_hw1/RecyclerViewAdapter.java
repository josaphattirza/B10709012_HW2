package com.example.b10709012_hw1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class    RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mCheckb = new ArrayList<String>();
    private ArrayList<Boolean> checked = new ArrayList<Boolean>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mCheckb , ArrayList<Boolean> checked) {
        this.mCheckb = mCheckb;
        this.mContext = mContext;
        this.checked = checked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called.");

        holder.checkBox.setText(mCheckb.get(position));

        if (checked.get(position)==true) {
            holder.checkBox.setChecked(true);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked on" + mCheckb.get(position));

                String currentItem = mCheckb.get(position);

                if(holder.checkBox.isChecked()){
                    checked.set(position,true);
                }

                else if(!holder.checkBox.isChecked()){
                    checked.set(position,false);
                }

                Log.d(TAG, "clicked");
//                Toast.makeText(mContext, new String(Integer.toString(checked.size())), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCheckb.size();
    }

    public ArrayList<Boolean> getChecked() {
        return checked;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkb_view);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


}