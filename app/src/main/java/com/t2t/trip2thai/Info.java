package com.t2t.trip2thai;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Info extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FAB//
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FAB Action goes here
                //Toast.makeText(MainActivity)
                Intent navigation = new Intent(Info.this, MapsActivity.class);
                startActivity(navigation);
                //finish();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent_map = new Intent(Info.this, NavigationActivity.class);
            startActivity(intent_map);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            Intent intent_map = new Intent(Info.this, MapsActivity.class);
            startActivity(intent_map);
            finish();

        //}else if (id == R.id.nav_info){
       //     Intent intent_info = new Intent(Info.this, Info.class);
       //     startActivity(intent_info);
       //     finish();

             }else if (id == R.id.nav_navg) {
             Intent intent_navg = new Intent(this, NavigationActivity.class);
              startActivity(intent_navg);
              finish();

        }else if (id == R.id.nav_about_us) {
            Intent intent_aboutus = new Intent(Info.this, Aboutus.class);
            startActivity(intent_aboutus);
            finish();
        }

       // else if (id == R.id.nav_share) {
            // Create the text message with a string
          //  Intent sendIntent = new Intent();
          //  sendIntent.setAction(Intent.ACTION_SEND);
            //sendIntent.putExtra(Intent.EXTRA_SUBJECT, "I am in Trouble");
           // sendIntent.setType("text/plain");

        //}
        else if (id == R.id.nav_send) {
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
            sendIntent.setData(Uri.parse("smsto:"));

            sendIntent.putExtra("sms_body", "Here I am");

            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(sendIntent);
            }
            else{
                Toast.makeText(this, "Cannot open SMS.", Toast.LENGTH_SHORT).show();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
