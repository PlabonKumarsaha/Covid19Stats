package com.example.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView titleTV;
    private ImageButton refreshBtn;
    private BottomNavigationView navigationView;
    private Fragment homeFragment,statFragment;
    private Fragment activeFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTV = findViewById(R.id.titleTV);
        refreshBtn = findViewById(R.id.refreshBtn);
        navigationView = findViewById(R.id.navigationView);
        
        initFragmentManager();

        //refreshButtonclick
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(this);


    }

//this will be initialized when the app starts. When the app starts , home fragment is the first fragment that will start the whole app
    private void initFragmentManager() {
    HomeFragment homeFragment = new HomeFragment();
    StatasticsFragment statasticsFragment = new StatasticsFragment();

    fragmentManager = getSupportFragmentManager();
    activeFragment = homeFragment;

    //initilaizing with this fragment
     fragmentManager.beginTransaction().
            add(R.id.frame,homeFragment,"Home_Fragment")
             .commit();

    //keeping another fragment hidden!
    fragmentManager.beginTransaction()
            .add(R.id.frame,statasticsFragment,"Statastics_fragment").hide(statasticsFragment)
            .commit();
    //commit the transaction
       // fragmentTransaction.commit();
    }

    private void loadHomeFragment(){
        titleTV.setText("Home");
        fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
        activeFragment = homeFragment;
    }

    private void loadStatasticsFragment(){
        titleTV.setText("Covid 19 statastics");
        fragmentManager.beginTransaction().hide(activeFragment).show(statFragment).commit();
        activeFragment = statFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //handles bottom navigation item clicks
        switch(item.getItemId()){

            case R.id.navigation_home :
                loadHomeFragment();
                 return true;
            case R.id.navigation_stats :
                loadStatasticsFragment();
                return true;

        }
        return false;
    }
}