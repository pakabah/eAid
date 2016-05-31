package com.overcoretech.studenteaid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.overcoretech.studenteaid.services.DataService;
import com.overcoretech.studenteaid.views.Login;
import com.overcoretech.studenteaid.views.Message;
import com.overcoretech.studenteaid.views.Notes;
import com.overcoretech.studenteaid.views.Pasco;
import com.overcoretech.studenteaid.views.Profile;
import com.overcoretech.studenteaid.views.Score;

public class MainActivity extends AppCompatActivity implements Pasco.OnFragmentInteractionListener,
Score.OnFragmentInteractionListener, Profile.OnFragmentInteractionListener, Notes.OnFragmentInteractionListener,
        Message.OnFragmentInteractionListener
{

    public static final String DEFAULT = "N/A";
    public static FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String loginPref = sharedPreferences.getString("login", DEFAULT);
        if(!loginPref.equals("1"))
        {
            startService(new Intent(MainActivity.this, DataService.class));

            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Pasco pasco = new Pasco();
            android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.container, pasco);
            fragmentTransaction1.commit();
            setupDrawerLayout();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.getMenu().getItem(0).setChecked(true);


        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
                    case R.id.drawer_pasco:
                        Pasco pasco = new Pasco();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.container, pasco);
                        fragmentTransaction1.commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.drawer_notes:
                        Notes notes = new Notes();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, notes);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.drawer_scores:
                        Score score = new Score();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.container, score);
                        fragmentTransaction2.commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.drawer_message:
                        Message message = new Message();
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.container,message);
                        fragmentTransaction3.commit();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.drawer_logout:
                        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("login", "0");
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
