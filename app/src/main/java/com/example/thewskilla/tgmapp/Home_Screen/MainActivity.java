package com.example.thewskilla.tgmapp.Home_Screen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.example.thewskilla.tgmapp.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton programButton, skillsButton, dailyButton;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        programButton = (ImageButton) findViewById(R.id.nav_explore);
        skillsButton = (ImageButton) findViewById(R.id.nav_profile);
        dailyButton = (ImageButton) findViewById(R.id.nav_logout);

        programButton.setOnClickListener(this);
        skillsButton.setOnClickListener(this);
        dailyButton.setOnClickListener(this);
*/
        /** Full Screen **/
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent_programs,intent_skills, intent_daily;
        intent_skills = new Intent(MainActivity.this, Skills.class);
        intent_daily = new Intent(MainActivity.this, Daily.class);
        intent_programs = new Intent(MainActivity.this, Programs.class);
        switch (menuItem.getItemId()){
            case R.id.nav_programs:
                startActivity(intent_programs);
                break;
            case R.id.nav_skills:
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessageFragment()).commit();
                startActivity(intent_skills);
                break;
            case R.id.nav_daily_chalange:
                startActivity(intent_daily);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }



    /*
    @Override
    public void onClick(View v) {
        Intent intent_programs, intent_skills, intent_daily;
        intent_programs = new Intent(MainActivity.this, Programs.class);
        intent_skills = new Intent(MainActivity.this, Skills.class);
        intent_daily = new Intent(MainActivity.this, Daily.class);
        switch (v.getId()){
            case R.id.nav_explore:
                startActivity(intent_programs);
                break;
            case R.id.nav_profile:
                startActivity(intent_skills);
                break;
            case R.id.nav_logout:
                startActivity(intent_daily);
                break;
        }
    }
    */
}

