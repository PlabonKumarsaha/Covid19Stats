package com.example.covid19tracker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
        View view = LayoutInflater.from(context).inflate(R.layout.row_stat,parent,false);
        return new HoldStat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldStat holder, int position) {

        //get data
        ModelStat modelStat = startArrayList.get(position);
        String country = modelStat.getCountry();
        String totalConfirmed = modelStat.getTotalConfirmed();
        String totalDeath = modelStat.getTotalDeaths();
        String totalRecovered = modelStat.getTotalRecovered();
        String newConfirmed = modelStat.getNewConfirmed();
        String newDeath = modelStat.getNewDeaths();
        String newRecover = modelStat.getNewRecovered();

        //set Data
        holder.countryTV.setText(country);
        holder.todayDeathTV.setText(totalDeath);
        holder.todayCaseTV.setText(totalConfirmed);
        holder.totalrecoveredTV.setText(totalRecovered);
        holder.recoveredTV.setText(newRecover);
        holder.deathTV.setText(newDeath);
        holder.caseTV.setText(newConfirmed);
    }

    @Override
    public int getItemCount() {
        return startArrayList.size();
    }


    //view hold class
    class HoldStat extends RecyclerView.ViewHolder{

        //UI views
        TextView countryTV,todayCaseTV,caseTV,deathTV,totalrecoveredTV,todayDeathTV,recoveredTV;


        public HoldStat(@NonNull View itemView) {
            super(itemView);
            countryTV = itemView.findViewById(R.id.countryTV);
            caseTV = itemView.findViewById(R.id.caseTV);
            todayCaseTV = itemView.findViewById(R.id.todayCaseTV);
            deathTV = itemView.findViewById(R.id.deathTV);
            todayDeathTV = itemView.findViewById(R.id.todayDeathTV);
            recoveredTV = itemView.findViewById(R.id.recoveredTV);
            totalrecoveredTV = itemView.findViewById(R.id.totalrecoveredTV);


        }

    }
    
}
