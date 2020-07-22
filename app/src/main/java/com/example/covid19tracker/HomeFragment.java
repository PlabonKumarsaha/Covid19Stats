package com.example.covid19tracker;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HomeFragment extends Fragment {

    private static final String STATS_URL = "https://api.covid19api.com/summary";

    Context context;
    //UI views
    ProgressBar progressBar;
    TextView totalcasesTV,newcasesTV,totalDeathTV,newDeathTV,totalRecoveredTV;


    public HomeFragment() {
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

        View view= inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        totalcasesTV = view.findViewById(R.id.totalcasesTV);
        newcasesTV = view.findViewById(R.id.newcasesTV);
        totalDeathTV = view.findViewById(R.id.totalDeathTV);
        newDeathTV = view.findViewById(R.id.newDeathTV);
        totalRecoveredTV = view.findViewById(R.id.totalRecoveredTV);
        // Inflate the layout for this fragment
        return view;
    }
    private void loadHomeData(){
        //show progress
        progressBar.setVisibility(View.VISIBLE);
        //JSON String request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, STATS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //response recieverd ,handle response
                handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //some error has occures ,so stop the progessbar
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context," "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        //add reqires to add
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void handleResponse(String response) {
        try{

            //we know that our data is in JSON format ,so we change it to object
        } catch (Exception e){
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}