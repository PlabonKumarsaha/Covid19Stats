package com.example.covid19tracker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterStat extends RecyclerView.Adapter<AdapterStat.HoldStat> {

    private Context context;
    private ArrayList<ModelStat>startArrayList;

    public AdapterStat(Context context, ArrayList<ModelStat> startArrayList) {
        this.context = context;
        this.startArrayList = startArrayList;
    }

    @NonNull
    @Override
    public HoldStat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HoldStat holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    //view hold class
    class HoldStat extends RecyclerView.ViewHolder{

        //UI views
        TextView countryTV,caseTV,deathTV,totalrecoveredTV,todayDeathTV,recoveredTV;


        public HoldStat(@NonNull View itemView) {
            super(itemView);
            countryTV = itemView.findViewById(R.id.countryTV);
            caseTV = itemView.findViewById(R.id.caseTV);
            deathTV = itemView.findViewById(R.id.deathTV);
            todayDeathTV = itemView.findViewById(R.id.todayDeathTV);
            recoveredTV = itemView.findViewById(R.id.recoveredTV);
            totalrecoveredTV = itemView.findViewById(R.id.totalrecoveredTV);


        }

    }
    
}
