package com.newsviews.rokomari.HomeView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;
import com.newsviews.rokomari.Features.About.AboutFragment;
import com.newsviews.rokomari.Features.Home.HomeFragment;
import com.newsviews.rokomari.Features.Search.SearchActivity;
import com.newsviews.rokomari.R;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                dl.closeDrawer(Gravity.LEFT);

                switch (id) {
                    case R.id.home:
//                        Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        gotoMyFragment();
                        break;
                    case R.id.about:
//                        Toast.makeText(HomeActivity.this, "About", Toast.LENGTH_SHORT).show();
                        gotoAboutFragment();
                        break;

                    case R.id.exit:
//                        Toast.makeText(HomeActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                        finish();
                        break;

                    default:
                        return true;
                }

                return true;
            }
        });

        gotoMyFragment();


    }

    public void gotoAboutFragment() {
        AboutFragment myf = new AboutFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.flContent, myf);
        transaction.commit();
    }

    public void gotoMyFragment() {
        HomeFragment myf = new HomeFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.flContent, myf);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;


        }


        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        // return true so that the menu pop up is opened
        return true;
    }

}



