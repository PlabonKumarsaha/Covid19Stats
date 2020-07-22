package com.example.covid19tracker;

import android.widget.Adapter;
import android.widget.Filter;

import java.util.ArrayList;

public class FilterStat extends Filter {
    private AdapterStat adapterStat;
    private ArrayList<ModelStat>filerList;

    public FilterStat(AdapterStat adapterStat, ArrayList<ModelStat> filerList) {
        this.adapterStat = adapterStat;
        this.filerList = filerList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {


        FilterResults filterResults =new FilterResults();
        //check constratint validity
        if(charSequence!=null && charSequence.length()>0){
            //change to upper case
            charSequence =charSequence.toString().toUpperCase();

            ArrayList<ModelStat>filterModels = new ArrayList<>();
            //store filer records
            for(int i=0;i<filerList.size();i++){

                if(filerList.get(i).getCountry().toUpperCase().contains(charSequence)){
                    filterModels.add(filerList.get(i));
                }
            }
            filterResults.count = filterModels.size();
            filterResults.values= filterModels;
        }else{
            filterResults.count = filerList.size();
            filterResults.values= filerList;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        adapterStat.startArrayList = (ArrayList<ModelStat>) filterResults.values;
        adapterStat.notifyDataSetChanged();
    }
}
