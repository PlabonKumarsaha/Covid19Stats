package com.example.covid19tracker;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StatasticsFragment extends Fragment {

    Context context;
    //UI views
    ProgressBar progressbarFragmentStat;
    ImageButton sortBtn;
    EditText searchET;
    RecyclerView startsRV;
    ArrayList<ModelStat>statArrayList;
    AdapterStat adapterStat;

    private static final String STATS_URL = "https://api.covid19api.com/summary";

    public StatasticsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_statastics, container, false);

        progressbarFragmentStat = view.findViewById(R.id.progressbarFragmentStat);
        searchET = view.findViewById(R.id.searchET);
        sortBtn = view.findViewById(R.id.sortBtn);
        startsRV = view.findViewById(R.id.startsRV);
        progressbarFragmentStat.setVisibility(View.GONE);

        loadStatData();
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //called when user typer or removes letter
                try{

                    adapterStat.getFilter().filter(charSequence);
                } catch (Exception e){

                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //pop up menu for sorting action
        final PopupMenu popupMenu = new PopupMenu(context,sortBtn);
        popupMenu.getMenu().add(Menu.NONE,0,0,"Accending");// first patram :id of item,second para meter :position inlist of items.
        //3rd paramer :title
        popupMenu.getMenu().add(Menu.NONE,1,1,"Decending");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                //handle items clicks
                int i = menuItem.getItemId();
                if(i == 0){
                    Collections.sort(statArrayList,new SortStatCountyAS());
                    adapterStat.notifyDataSetChanged();
                } else if(i ==1 ){

                    Collections.sort(statArrayList,new SortStatByDencding());
                    adapterStat.notifyDataSetChanged();
                }
                return false;
            }
        });

        //sorting
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //show menu
                popupMenu.show();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        loadStatData();
        super.onResume();
    }

    private void loadStatData(){
        progressbarFragmentStat.setVisibility(View.VISIBLE);

        //api call
        StringRequest stringRequest = new StringRequest(Request.Method.GET, STATS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //get response
                handleResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //failed getting respond
                progressbarFragmentStat.setVisibility(View.GONE);
                Toast.makeText(context," "+error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        //add request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    private void handleResponse(String response) {
        statArrayList = new ArrayList<>();
        statArrayList.clear();
        try {
            //crate JSON object to show the data
            //we know that our data is in JSON format ,so we change it to object

            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray =  jsonObject.getJSONArray("Countries");

            //we are using GSON bulder bcz there is arecyler view invoved
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd/MM/yyyy hh:mm a");
            Gson gson = gsonBuilder.create();

            //start getting data
            for(int i = 0;i<jsonArray.length();i++){
                ModelStat modelStat = gson.fromJson(jsonArray.getJSONObject(i).toString(),ModelStat.class);
                statArrayList.add(modelStat);
            }
            //set up adapter
            adapterStat = new AdapterStat(context,statArrayList);
            startsRV.setAdapter(adapterStat); //set adapter to recyler view
            progressbarFragmentStat.setVisibility(View.GONE);



        } catch (Exception e){

            progressbarFragmentStat.setVisibility(View.GONE);
            Toast.makeText(context," "+e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }

    //sort country in accending order
    public class SortStatCountyAS implements Comparator<ModelStat>{

        @Override
        public int compare(ModelStat left, ModelStat right) {
            return left.getCountry().compareTo(right.getCountry());
        }
    }

    public class SortStatByDencding implements Comparator<ModelStat>{

        @Override
        public int compare(ModelStat left, ModelStat right) {
            return right.getCountry().compareTo(left.getCountry());
        }
    }
}