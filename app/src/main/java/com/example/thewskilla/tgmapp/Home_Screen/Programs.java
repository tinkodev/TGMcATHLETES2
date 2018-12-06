package com.example.thewskilla.tgmapp.Home_Screen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.thewskilla.tgmapp.Advance_workout;
import com.example.thewskilla.tgmapp.Intermediate_workout;
import com.example.thewskilla.tgmapp.Programs_Screen.Beginner_workout;
import com.example.thewskilla.tgmapp.R;
import com.example.thewskilla.tgmapp.Weighted_workout;

public class Programs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    ImageButton cBeginner_button,cIntermediate_button,cAdvance_button,cweighted_button;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cBeginner_button = (ImageButton) findViewById(R.id.beginner_id);
        cIntermediate_button = (ImageButton) findViewById(R.id.intermediate_id);
        cAdvance_button = (ImageButton) findViewById(R.id.advance_id);
        cweighted_button = (ImageButton) findViewById(R.id.weighted_id);

        cBeginner_button.setOnClickListener(this);
        cIntermediate_button.setOnClickListener(this);
        cAdvance_button.setOnClickListener(this);
        cweighted_button.setOnClickListener(this);

        /** Full Screen **/
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


    public void onClick(View v) {

        Intent cbeginner_intent = new Intent(Programs.this, Beginner_workout.class);
        Intent cintermediate_intent = new Intent(Programs.this, Intermediate_workout.class);
        Intent cadvance_intent = new Intent(Programs.this, Advance_workout.class);
        Intent cweighted_intent = new Intent(this,Weighted_workout.class);
        switch (v.getId()){
            case R.id.beginner_id:
                startActivity(cbeginner_intent);
                break;
            case R.id.intermediate_id:
                startActivity(cintermediate_intent);
                break;
            case R.id.advance_id:
                startActivity(cadvance_intent);
                break;
            case R.id.weighted_id:
                startActivity(cweighted_intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent_programs,intent_skills, intent_daily;
        intent_skills = new Intent(Programs.this, Skills.class);
        intent_daily = new Intent(Programs.this, Daily.class);
        intent_programs = new Intent(Programs.this, Programs.class);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
