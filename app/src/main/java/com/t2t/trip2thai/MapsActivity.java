package com.t2t.trip2thai;

import android.Manifest;

import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements

        NavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener,
        LocationListener,
        View.OnClickListener        {




    private GoogleMap mMap;

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    public static final String TAG = MapsActivity.class.getSimpleName();

    MarkerOptions markerOptions;
    LatLng latLng;

    //Google ApiClient

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    Location location;

    double[] lat = new double[88];
    double[] lon = new double[88];
    String[] nname = new String[88];


    LatLng Don;
    LatLng Suvarnabhumi;
    LatLng Amari;
    LatLng Ariyasomvilla;
    LatLng Mandarin;
    LatLng Siam;
    LatLng Phranakorn;
    LatLng Okura;
    LatLng Adelphi;
    LatLng Sheraton;
    LatLng Hua_Hin;
    LatLng Ko_Samet;
    LatLng Bang_Saen;
    LatLng Charms;
    LatLng Pran_Bur;
    LatLng Jomtien;
    LatLng Koh_Chang;
    LatLng SO_Sofite;
    LatLng Central_World;
    LatLng Siam_Paragon;
    LatLng Siam_Center;
    LatLng MBK;
    LatLng U_Tapao;
    LatLng Sunshine;
    LatLng Basaya;
    LatLng I_Rovers;
    LatLng Walee;
    LatLng Areca;
    LatLng A_One;
    LatLng Pattaya_Loft;
    LatLng AA;
    LatLng Chon_Buri;
    LatLng Sea_Sand_Sun;
    LatLng Centara_Grand;
    LatLng Cape_Dara;
    LatLng InterContinental;
    LatLng CentralFestivalpat;
    LatLng Avenue;
    LatLng Central_Center;
    LatLng HomePro;
    LatLng Phuket_Airport;
    LatLng Dara_Boutique;
    LatLng Baan_Suwantawe;
    LatLng Saruda;
    LatLng Sino_Imperial;
    LatLng Baan_Sutra;
    LatLng Cool_Residence;
    LatLng Memory_at_On;
    LatLng Freedom;
    LatLng Banana;
    LatLng Kata_Noi;
    LatLng Kata;
    LatLng Haad_Sai_Kaew;
    LatLng Karon;
    LatLng Khao_Yai;
    LatLng Khao_Sam_Roi_Yot;
    LatLng Jungceylon;
    LatLng The_Plaza_Surin;
    LatLng CentralFestivalphu;
    LatLng Wat_Pho;
    LatLng Wat_Arun;
    LatLng Wat_Traimit;
    LatLng Wat_Suthat;
    LatLng Pattaya_Floating_Market;
    LatLng Pattaya_Park;
    LatLng Underwater_World;
    LatLng Naklua_Beach;
    LatLng MiNi_Siam;
    LatLng Nong_Nooch;
    LatLng Jomtien_Beach;
    LatLng Sanctuary_of_Truth;
    LatLng Wat_Yansangwararam;
    LatLng Wat_Chalong;
    LatLng Laem;
    LatLng Town_Night_Market ;
    LatLng Muay_Thai;
    LatLng Big_Buddha;
    LatLng Simon;
    LatLng FantaSea;
    LatLng Damnoen_Saduak;
    LatLng Khaosan;
    LatLng Lumphini;
    LatLng Street_Food_Stalls;
    LatLng Chatuchak;
    LatLng Giant_Swing;
    LatLng National_Museum;
    LatLng Grand_Palace;


    ///////////////////////          DATABASE ACCESS          /////////////////////////////////


    public void accessDatabase() {
        DatabaseHelper databaseHelper = DatabaseHelper.getObject(this);
        databaseHelper.open();

        for (int i = 1; i <= 87; i++)
        {
            lat[i] = databaseHelper.dataTodouble(i, 3);
            lon[i] = databaseHelper.dataTodouble(i, 4);
            nname[i] = databaseHelper.databaseTostring(i, 1);


        }

        Don = new LatLng(lat[1], lon[1]);
        Suvarnabhumi = new LatLng(lat[2], lon[2]);
        Amari = new LatLng(lat[3], lon[3]);
        Ariyasomvilla = new LatLng(lat[4], lon[4]);
        Mandarin = new LatLng(lat[5], lon[5]);
        Siam = new LatLng(lat[6], lon[6]);
        Phranakorn = new LatLng(lat[7], lon[7]);
        Okura = new LatLng(lat[8], lon[8]);
        Adelphi = new LatLng(lat[9], lon[9]);
        Sheraton = new LatLng(lat[10], lon[10]);
        Hua_Hin = new LatLng(lat[11], lon[11]);
        Ko_Samet = new LatLng(lat[12], lon[12]);
        Bang_Saen = new LatLng(lat[13], lon[13]);
        Charms = new LatLng(lat[14], lon[14]);
        Pran_Bur = new LatLng(lat[15], lon[15]);
        Jomtien = new LatLng(lat[16], lon[16]);
        Koh_Chang = new LatLng(lat[17], lon[17]);
        SO_Sofite = new LatLng(lat[18], lon[18]);
        Central_World = new LatLng(lat[19], lon[19]);
        Siam_Paragon = new LatLng(lat[20], lon[20]);
        Siam_Center = new LatLng(lat[21], lon[21]);
        MBK = new LatLng(lat[22], lon[22]);
        U_Tapao = new LatLng(lat[23], lon[23]);
        Sunshine = new LatLng(lat[24], lon[24]);
        Basaya = new LatLng(lat[25], lon[25]);
        I_Rovers = new LatLng(lat[26], lon[26]);
        Walee = new LatLng(lat[27], lon[27]);
        Areca = new LatLng(lat[28], lon[28]);
        A_One = new LatLng(lat[29], lon[29]);
        Pattaya_Loft = new LatLng(lat[30], lon[30]);
        AA = new LatLng(lat[31], lon[31]);
        Chon_Buri = new LatLng(lat[32], lon[32]);
        Sea_Sand_Sun = new LatLng(lat[33], lon[33]);
        Centara_Grand = new LatLng(lat[34], lon[34]);
        Cape_Dara = new LatLng(lat[35], lon[35]);
        InterContinental = new LatLng(lat[36], lon[36]);
        CentralFestivalpat = new LatLng(lat[37], lon[37]);
        Avenue = new LatLng(lat[38], lon[38]);
        Central_Center = new LatLng(lat[39], lon[39]);
        HomePro = new LatLng(lat[40], lon[40]);
        Phuket_Airport = new LatLng(lat[41], lon[41]);
        Dara_Boutique = new LatLng(lat[42], lon[42]);
        Baan_Suwantawe = new LatLng(lat[43], lon[43]);
        Saruda = new LatLng(lat[44], lon[44]);
        Sino_Imperial = new LatLng(lat[45], lon[45]);
        Baan_Sutra = new LatLng(lat[46], lon[46]);
        Cool_Residence = new LatLng(lat[47], lon[47]);
        Memory_at_On = new LatLng(lat[48], lon[48]);
        Freedom = new LatLng(lat[49], lon[49]);
        Banana = new LatLng(lat[50], lon[50]);
        Kata_Noi = new LatLng(lat[51], lon[51]);
        Kata = new LatLng(lat[52], lon[52]);
        Haad_Sai_Kaew = new LatLng(lat[53], lon[53]);
        Karon = new LatLng(lat[54], lon[54]);
        Khao_Yai = new LatLng(lat[55], lon[55]);
        Khao_Sam_Roi_Yot = new LatLng(lat[56], lon[56]);
        Jungceylon = new LatLng(lat[57], lon[57]);
        The_Plaza_Surin = new LatLng(lat[58], lon[58]);
        CentralFestivalphu = new LatLng(lat[59], lon[59]);
        Wat_Pho = new LatLng(lat[60], lon[60]);
        Wat_Arun = new LatLng(lat[61], lon[61]);
        Wat_Traimit = new LatLng(lat[62], lon[62]);
        Wat_Suthat = new LatLng(lat[63], lon[63]);
        Pattaya_Floating_Market = new LatLng(lat[64], lon[64]);
        Pattaya_Park = new LatLng(lat[65], lon[65]);
        Underwater_World = new LatLng(lat[66], lon[66]);
        Naklua_Beach = new LatLng(lat[67], lon[67]);
        MiNi_Siam = new LatLng(lat[68], lon[68]);
        Nong_Nooch = new LatLng(lat[69], lon[69]);
        Jomtien_Beach = new LatLng(lat[70], lon[70]);
        Sanctuary_of_Truth = new LatLng(lat[71], lon[71]);
        Wat_Yansangwararam = new LatLng(lat[72], lon[72]);
        Wat_Chalong = new LatLng(lat[73], lon[73]);
        Laem = new LatLng(lat[74], lon[74]);
        Town_Night_Market = new LatLng(lat[75], lon[75]);
        Muay_Thai = new LatLng(lat[76], lon[76]);
        Big_Buddha = new LatLng(lat[77], lon[77]);
        Simon = new LatLng(lat[78], lon[78]);
        FantaSea = new LatLng(lat[79], lon[79]);
        Damnoen_Saduak = new LatLng(lat[80], lon[80]);
        Khaosan = new LatLng(lat[81], lon[81]);
        Lumphini = new LatLng(lat[82], lon[82]);
        Street_Food_Stalls = new LatLng(lat[83], lon[83]);
        Chatuchak = new LatLng(lat[84], lon[84]);
        Giant_Swing = new LatLng(lat[85], lon[85]);
        National_Museum = new LatLng(lat[86], lon[86]);
        Grand_Palace = new LatLng(lat[87], lon[87]);

    }


    /////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_drawer_maps);

        //  Toolbar needs AppCompatActivity
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        ///////////////////       FOR NAVIGATION DRAWER       ///////////////////

     //   LinearLayout DrawerLinear = (LinearLayout) findViewById(R.id.drawerLinear);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Access Database in onCreate

        accessDatabase();
        DatabaseHelper databaseHelper = DatabaseHelper.getObject(this);
        databaseHelper.open();

        setMapIfNeeded();


        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        // LocationRequest object is created to request a quality of service for location updates from the FusedLocationProviderApi.

        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
               // .setInterval(10 * 1000)      //set the interval in which you want to get locations
              //  .setFastestInterval(5 * 1000);       // if a location is available sooner you can get it (i.e. another app is using the location services)


        //////////////////          FAB          ///////////////

        FloatingActionButton info = (FloatingActionButton) findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FAB Action goes here


                        Intent navigation = new Intent(MapsActivity.this, NavigationActivity.class);
                        startActivity(navigation);
                    }
                });



             /*   try {

                    Intent appintent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
                    appintent.setType("vnd.android.cursor.item/event");
                    startActivity(appintent);

                    if (appintent.resolveActivity(getPackageManager()) != null) {
                        startActivity(appintent);
                    } else {
                        Toast.makeText(MapsActivity.this, "Cannot open this app.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/



       /* FloatingActionButton email = (FloatingActionButton) findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FAB Action goes here
                try {

                    Intent appintent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
                    appintent.setType("vnd.android.cursor.item/event");
                    startActivity(appintent);

                    if (appintent.resolveActivity(getPackageManager()) != null) {
                        startActivity(appintent);
                    } else {
                        Toast.makeText(MapsActivity.this, "Cannot open this app.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/

    }


    public void onArrow(View v)
    {
        Toast.makeText(MapsActivity.this, "Slide for drawer", Toast.LENGTH_SHORT).show();
    }






    @Override
    protected void onResume()
    {
        super.onResume();
        location = null;
        setMapIfNeeded();
        googleApiClient.connect();  //be connected with GoogleApiClient
    }

    // If the connection is successful it will call the onConnected() method
    // or if the connection is failed, it will call onConnectionFailed()
    // or else it will call onConnectionSuspended() method.

    @Override
    protected void onPause()
    {
        super.onPause();
        // only stop if it's connected, otherwise it'll crash
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            //when activity is on pause, need to stop the location update since we
            //do not need that any more
            location = null;
        }
    }


    @Override
    protected void onStop()
    {
        googleApiClient.disconnect();
        super.onStop();
    }


    // We can't be guaranteed that the map is available because Google Play services might
    // not be available. So we do a null check to confirm if map is available

    private void setMapIfNeeded()
    {
        if (mMap == null) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            if (mMap != null) {
                getNewLocation(location);
            }
        }
    }


    ////////////////////////////    to edit ///////////////////////////



    ////////////////////////////          FOR NEW LOCATIONS          /////////////////////////////


    Marker marker;
    private int counter = 0;
    private int c = 0;

    private void getNewLocation(Location location)
    {
        // For Current location in mMap.moveCamera
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latlng = new LatLng(13.6, 100.7);

        mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 16.0), 2000, null);
        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(13.6, 100.7), (float) 16.0));
               //   .newLatLngZoom(new LatLng(currentLatitude,currentLongitude), (float) 16.0));
        MarkerOptions options = new MarkerOptions()
                .position(latlng)
               // .title("You are here.")
                .icon(BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.HUE_BLUE));





        if (counter == 0) {
            mMap.clear();
            marker = mMap.addMarker(options);
            counter++;

        }

        //String to display current latitude and longitude
        String toaster = currentLatitude + ", " + currentLongitude;

        //Displaying current coordinates in toast
        Toast.makeText(this, toaster, Toast.LENGTH_LONG).show();


        /////////////////      TO CHECK IF THE POINTS ARE IN THE RADIUS OR NOT     /////////////////

        Location current_point = new Location("A");
       // current_point.setLatitude(currentLatitude);
       // current_point.setLongitude(currentLongitude);
        current_point.setLatitude(13.6899991);
        current_point.setLongitude(100.7501124);

        accessDatabase();
        DatabaseHelper databaseHelper = DatabaseHelper.getObject(this);
        databaseHelper.open();

        //For Don
        Location Donm = new Location("B");
        Donm.setLatitude(lat[1]);
        Donm.setLongitude(lon[1]);

        if (Donm.distanceTo(current_point) <= 60000) {
            Marker don = mMap.addMarker(new MarkerOptions()
                    .position(Don)
                    .title("Don Mueang Airport")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (Donm.distanceTo(current_point) > 60000) {
            counter = 0;
        }

        //For Suvarnabhumi
        Location suva = new Location("B");
        suva.setLatitude(lat[2]);
        suva.setLongitude(lon[2]);

        if (suva.distanceTo(current_point) <= 2000) {
            Marker suv = mMap.addMarker(new MarkerOptions()
                    .position(Suvarnabhumi)
                    .title("Suvarnabhumi Airport (BKK)")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (suva.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Amari
        Location amari= new Location("B");
        amari.setLatitude(lat[3]);
        amari.setLongitude(lon[3]);

        if (amari.distanceTo(current_point) <= 2000) {
            Marker ama = mMap.addMarker(new MarkerOptions()
                    .position(Amari)
                    .title("Amari Watergate Bangkok")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (amari.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Ariyasomvilla
        Location ariya = new Location("B");
        ariya.setLatitude(lat[4]);
        ariya.setLongitude(lon[4]);

        if (ariya.distanceTo(current_point) <= 2000) {
            Marker ari = mMap.addMarker(new MarkerOptions()
                    .position(Ariyasomvilla)
                    .title("AriyasomVilla Luxury Boutique Hotel Bangkok")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (ariya.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Mandarin
        Location manda = new Location("B");
        manda.setLatitude(lat[5]);
        manda.setLongitude(lon[5]);

        if (manda.distanceTo(current_point) <= 100000) {
            Marker man = mMap.addMarker(new MarkerOptions()
                    .position(Mandarin)
                    .title("Mandarin hotel Bangkok")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (manda.distanceTo(current_point) > 100000) {
            counter = 0;
        }

        //For Siam
        Location siam = new Location("B");
        siam.setLatitude(lat[6]);
        siam.setLongitude(lon[6]);

        if (siam.distanceTo(current_point) <= 2000) {
            Marker sia = mMap.addMarker(new MarkerOptions()
                    .position(Siam)
                    .title("The Siam Hotel, Bangkok")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (siam.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Phranakorn
        Location phran = new Location("B");
        phran.setLatitude(lat[7]);
        phran.setLongitude(lon[7]);

        if (phran.distanceTo(current_point) <= 2000) {
            Marker phr = mMap.addMarker(new MarkerOptions()
                    .position(Phranakorn)
                    .title("Phranakorn Nornlen Hotel, Bangkok")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (phran.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Okura
        Location okura = new Location("B");
        okura.setLatitude(lat[8]);
        okura.setLongitude(lon[8]);

        if (okura.distanceTo(current_point) <= 2000) {
            Marker oku = mMap.addMarker(new MarkerOptions()
                    .position(Okura)
                    .title("Okura Prestige Bangkok Hotel")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (okura.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Adelphi
        Location adelp = new Location("B");
        adelp.setLatitude(lat[9]);
        adelp.setLongitude(lon[9]);

        if (adelp.distanceTo(current_point) <= 2000) {
            Marker ade = mMap.addMarker(new MarkerOptions()
                    .position(Adelphi)
                    .title("Sukhumvit Bangkok Residence")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (adelp.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Sheraton
        Location shera = new Location("B");
        shera.setLatitude(lat[10]);
        shera.setLongitude(lon[10]);

        if (shera.distanceTo(current_point) <= 2000) {
            Marker she = mMap.addMarker(new MarkerOptions()
                    .position(Sheraton)
                    .title("Sheraton Hotels & Resorts ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (shera.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Hua_Hin
        Location hua_h = new Location("B");
        hua_h.setLatitude(lat[11]);
        hua_h.setLongitude(lon[11]);

        if (hua_h.distanceTo(current_point) <= 2000) {
            Marker hua = mMap.addMarker(new MarkerOptions()
                    .position(Hua_Hin)
                    .title("Hua Hin Hotels, Resorts")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (hua_h.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Ko_Samet
        Location ko_sam = new Location("B");
        ko_sam.setLatitude(lat[12]);
        ko_sam.setLongitude(lon[12]);

        if (ko_sam.distanceTo(current_point) <= 2000) {
            Marker ko_s = mMap.addMarker(new MarkerOptions()
                    .position(Ko_Samet)
                    .title("Ko Samet Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (ko_sam.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Bang_Saen
        Location bang = new Location("B");
        bang.setLatitude(lat[13]);
        bang.setLongitude(lon[13]);

        if (bang.distanceTo(current_point) <= 2000) {
            Marker ban = mMap.addMarker(new MarkerOptions()
                    .position(Bang_Saen)
                    .title("Bang Saen Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (bang.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Charms
        Location charms = new Location("B");
        charms.setLatitude(lat[14]);
        charms.setLongitude(lon[14]);

        if (charms.distanceTo(current_point) <= 2000) {
            Marker charm = mMap.addMarker(new MarkerOptions()
                    .position(Charms)
                    .title("The Charm Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (charms.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Pran_Bur
        Location pran = new Location("B");
        pran.setLatitude(lat[15]);
        pran.setLongitude(lon[15]);

        if (pran.distanceTo(current_point) <= 2000) {
            Marker pra = mMap.addMarker(new MarkerOptions()
                    .position(Pran_Bur)
                    .title("The Pranburi beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (pran.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Jomtien
        Location jomt = new Location("B");
        jomt.setLatitude(lat[16]);
        jomt.setLongitude(lon[16]);

        if (jomt.distanceTo(current_point) <= 2000) {
            Marker jom = mMap.addMarker(new MarkerOptions()
                    .position(Jomtien)
                    .title("The Jomtien beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (jomt.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Koh_Chang
        Location koh_c = new Location("B");
        koh_c.setLatitude(lat[17]);
        koh_c.setLongitude(lon[17]);

        if (koh_c.distanceTo(current_point) <= 2000) {
            Marker koh = mMap.addMarker(new MarkerOptions()
                    .position(Koh_Chang)
                    .title("The Koh Chang beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (koh_c.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For SO_Sofite
        Location so_s = new Location("B");
        so_s.setLatitude(lat[18]);
        so_s.setLongitude(lon[18]);

        if (so_s.distanceTo(current_point) <= 2000) {
            Marker so = mMap.addMarker(new MarkerOptions()
                    .position(SO_Sofite)
                    .title("The SO_Sofite Hotel, Bangkok")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (so_s.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Central_World
        Location centr = new Location("B");
        centr.setLatitude(lat[19]);
        centr.setLongitude(lon[19]);

        if (centr.distanceTo(current_point) <= 2000) {
            Marker cent = mMap.addMarker(new MarkerOptions()
                    .position(Central_World)
                    .title(" CentralWorld: The Largest Lifestyle Shopping Destination in Bangkok")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (centr.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Siam_Paragon
        Location siam_p = new Location("B");
        siam_p.setLatitude(lat[20]);
        siam_p.setLongitude(lon[20]);

        if (siam_p.distanceTo(current_point) <= 2000) {
            Marker siam_ = mMap.addMarker(new MarkerOptions()
                    .position(Siam_Paragon)
                    .title(" Siam Paragon - Bangkok Shopping Centre ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (siam_p.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Siam_Center
        Location siam_c = new Location("B");
        siam_c.setLatitude(lat[21]);
        siam_c.setLongitude(lon[21]);

        if (siam_c.distanceTo(current_point) <= 2000) {
            Marker siam_m = mMap.addMarker(new MarkerOptions()
                    .position(Siam_Center)
                    .title("Siam Center Bangkok - Bangkok Shopping Centre")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (siam_c.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For MBK
        Location mbk = new Location("B");
        mbk.setLatitude(lat[22]);
        mbk.setLongitude(lon[22]);

        if (mbk.distanceTo(current_point) <= 2000) {
            Marker mb = mMap.addMarker(new MarkerOptions()
                    .position(MBK)
                    .title("MBK Shopping Center Bangkok - Bangkok Shopping Malls")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (mbk.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For U-Tapao
        Location u_tap = new Location("B");
        u_tap.setLatitude(lat[23]);
        u_tap.setLongitude(lon[23]);

        if (u_tap.distanceTo(current_point) <= 2000) {
            Marker u_t = mMap.addMarker(new MarkerOptions()
                    .position(U_Tapao)
                    .title("U-Tapao International Airport. Pattaya")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (u_tap.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Sunshine
        Location suns = new Location("B");
        suns.setLatitude(lat[24]);
        suns.setLongitude(lon[24]);

        if (suns.distanceTo(current_point) <= 2000) {
            Marker sun = mMap.addMarker(new MarkerOptions()
                    .position(Sunshine)
                    .title("Sunshine Pattaya Hotel ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (suns.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Basaya
        Location basa = new Location("B");
        basa.setLatitude(lat[25]);
        basa.setLongitude(lon[25]);

        if (basa.distanceTo(current_point) <= 2000) {
            Marker bas = mMap.addMarker(new MarkerOptions()
                    .position(Basaya)
                    .title("Basaya Beach Resort, Pattaya ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (basa.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For I_Rovers
        Location i_rov = new Location("B");
        i_rov.setLatitude(lat[26]);
        i_rov.setLongitude(lon[26]);

        if (i_rov.distanceTo(current_point) <= 2000) {
            Marker i_ro = mMap.addMarker(new MarkerOptions()
                    .position(I_Rovers)
                    .title("I-Rovers Sportsbar Pattaya")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (i_rov.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Walee
        Location walee = new Location("B");
        walee.setLatitude(lat[27]);
        walee.setLongitude(lon[27]);

        if (walee.distanceTo(current_point) <= 2000) {
            Marker wal = mMap.addMarker(new MarkerOptions()
                    .position(Walee)
                    .title("Guest house Walee's Place, Pattaya ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (walee.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Areca
        Location areca = new Location("B");
        areca.setLatitude(lat[28]);
        areca.setLongitude(lon[28]);

        if (areca.distanceTo(current_point) <= 2000) {
            Marker are = mMap.addMarker(new MarkerOptions()
                    .position(Areca)
                    .title("Areca Lodge, Pattaya ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (areca.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For A_One
        Location a_one = new Location("B");
        a_one.setLatitude(lat[29]);
        a_one.setLongitude(lon[29]);

        if (a_one.distanceTo(current_point) <= 2000) {
            Marker a_on = mMap.addMarker(new MarkerOptions()
                    .position(A_One)
                    .title("A-one Pattaya Resort ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (a_one.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Pattaya_Loft
        Location pattaya_l = new Location("B");
        pattaya_l.setLatitude(lat[30]);
        pattaya_l.setLongitude(lon[30]);

        if (pattaya_l.distanceTo(current_point) <= 2000) {
            Marker patt = mMap.addMarker(new MarkerOptions()
                    .position(Pattaya_Loft)
                    .title("Hotel Pattaya Loft, Pattaya")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (pattaya_l.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For AA
        Location aa = new Location("B");
        aa.setLatitude(lat[31]);
        aa.setLongitude(lon[31]);

        if (aa.distanceTo(current_point) <= 2000) {
            Marker a = mMap.addMarker(new MarkerOptions()
                    .position(AA)
                    .title("AA Hotel Pattaya")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (aa.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Chon_Buri
        Location chon_b = new Location("B");
        chon_b.setLatitude(lat[32]);
        chon_b.setLongitude(lon[32]);

        if (chon_b.distanceTo(current_point) <= 2000) {
            Marker chon = mMap.addMarker(new MarkerOptions()
                    .position(Chon_Buri)
                    .title("Chonburi Hotels, Thailand")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (chon_b.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Sea_Sand_Sun
        Location sea_s = new Location("B");
        sea_s.setLatitude(lat[33]);
        sea_s.setLongitude(lon[33]);

        if (sea_s.distanceTo(current_point) <= 2000) {
            Marker sea = mMap.addMarker(new MarkerOptions()
                    .position(Sea_Sand_Sun)
                    .title("Book Sea Sand Sun Resort & Spa Pattaya")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (sea_s.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Centara_Grand
        Location centara_g = new Location("B");
        centara_g.setLatitude(lat[34]);
        centara_g.setLongitude(lon[34]);

        if (centara_g.distanceTo(current_point) <= 2000) {
            Marker centara = mMap.addMarker(new MarkerOptions()
                    .position(Centara_Grand)
                    .title("Centara Grand Mirage family resort & Pattaya water park")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (centara_g.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Cape_Dara
        Location cape_d = new Location("B");
        cape_d.setLatitude(lat[35]);
        cape_d.setLongitude(lon[35]);

        if (cape_d.distanceTo(current_point) <= 2000) {
            Marker cap = mMap.addMarker(new MarkerOptions()
                    .position(Cape_Dara)
                    .title("Cape Dara Resort, Pattaya")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (cape_d.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For InterContinental
        Location interc = new Location("B");
        interc.setLatitude(lat[36]);
        interc.setLongitude(lon[36]);

        if (interc.distanceTo(current_point) <= 2000) {
            Marker inte = mMap.addMarker(new MarkerOptions()
                    .position(InterContinental)
                    .title("InterContinental Pattaya Resort")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (interc.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For CentralFestivalpat
        Location centralf = new Location("B");
        centralf.setLatitude(lat[37]);
        centralf.setLongitude(lon[37]);

        if (centralf.distanceTo(current_point) <= 2000) {
            Marker center = mMap.addMarker(new MarkerOptions()
                    .position(CentralFestivalpat)
                    .title("CentralFestival Pattaya Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (centralf.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Avenue
        Location avenu = new Location("B");
        avenu.setLatitude(lat[38]);
        avenu.setLongitude(lon[38]);

        if (avenu.distanceTo(current_point) <= 2000) {
            Marker aven = mMap.addMarker(new MarkerOptions()
                    .position(Avenue)
                    .title("Pattaya Avenue Shopping Mall ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (avenu.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Central_Center
        Location central_c = new Location("B");
        central_c.setLatitude(lat[39]);
        central_c.setLongitude(lon[39]);

        if (central_c.distanceTo(current_point) <= 2000) {
            Marker central = mMap.addMarker(new MarkerOptions()
                    .position(Central_Center)
                    .title("CentralFestival Shopping mall, Pattaya, Thailand ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (central_c.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For HomePro
        Location homep = new Location("B");
        homep.setLatitude(lat[40]);
        homep.setLongitude(lon[40]);

        if (homep.distanceTo(current_point) <= 2000) {
            Marker home = mMap.addMarker(new MarkerOptions()
                    .position(HomePro)
                    .title("Sb Furniture Homepro Pattaya")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (homep.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Phuket_Airport
        Location phuket_a = new Location("B");
        phuket_a.setLatitude(lat[41]);
        phuket_a.setLongitude(lon[41]);

        if (phuket_a.distanceTo(current_point) <= 2000) {
            Marker phuk = mMap.addMarker(new MarkerOptions()
                    .position(Phuket_Airport)
                    .title("Phuket International Airport")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (phuket_a.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Dara_Boutique
        Location dara_b= new Location("B");
        dara_b.setLatitude(lat[42]);
        dara_b.setLongitude(lon[42]);

        if (dara_b.distanceTo(current_point) <= 2000) {
            Marker dara = mMap.addMarker(new MarkerOptions()
                    .position(Dara_Boutique)
                    .title("New Dara Boutique Hotel & Residence")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (dara_b.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Baan_Suwantawe
        Location baan_s = new Location("B");
        baan_s.setLatitude(lat[43]);
        baan_s.setLongitude(lon[43]);

        if (baan_s.distanceTo(current_point) <= 2000) {
            Marker baan = mMap.addMarker(new MarkerOptions()
                    .position(Baan_Suwantawe)
                    .title("Baan Suwantawe Hotel Apartment in Phuket")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (baan_s.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Saruda
        Location saru = new Location("B");
        saru.setLatitude(lat[44]);
        saru.setLongitude(lon[44]);

        if (saru.distanceTo(current_point) <= 2000) {
            Marker sar = mMap.addMarker(new MarkerOptions()
                    .position(Saruda)
                    .title("Saruda Phuket Hotel ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (saru.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Sino_Imperial
        Location sino_i = new Location("B");
        sino_i.setLatitude(lat[45]);
        sino_i.setLongitude(lon[45]);

        if (sino_i.distanceTo(current_point) <= 2000) {
            Marker sino = mMap.addMarker(new MarkerOptions()
                    .position(Sino_Imperial)
                    .title("Sino Imperial Phuket Hotel in Phuket")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (sino_i.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Baan_Sutra
        Location baan_su = new Location("B");
        baan_su.setLatitude(lat[46]);
        baan_su.setLongitude(lon[46]);

        if (baan_su.distanceTo(current_point) <= 2000) {
            Marker baa = mMap.addMarker(new MarkerOptions()
                    .position(Baan_Sutra)
                    .title("Baan Sutra Guesthouse")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (baan_su.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Cool_Residence
        Location cool_r = new Location("B");
        cool_r.setLatitude(lat[47]);
        cool_r.setLongitude(lon[47]);

        if (cool_r.distanceTo(current_point) <= 2000) {
            Marker cool = mMap.addMarker(new MarkerOptions()
                    .position(Cool_Residence)
                    .title("Cool Residence")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (cool_r.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Memory_at_On
        Location memory = new Location("B");
        memory.setLatitude(lat[48]);
        memory.setLongitude(lon[48]);

        if (memory.distanceTo(current_point) <= 2000) {
            Marker memo = mMap.addMarker(new MarkerOptions()
                    .position(Memory_at_On)
                    .title("The Memory at On On Hotel")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (memory.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Freedom
        Location freed = new Location("B");
        freed.setLatitude(lat[49]);
        freed.setLongitude(lon[49]);

        if (freed.distanceTo(current_point) <= 2000) {
            Marker free = mMap.addMarker(new MarkerOptions()
                    .position(Freedom)
                    .title("Freedom Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (freed.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Banana
        Location banan = new Location("B");
        banan.setLatitude(lat[50]);
        banan.setLongitude(lon[50]);

        if (banan.distanceTo(current_point) <= 2000) {
            Marker bana = mMap.addMarker(new MarkerOptions()
                    .position(Banana)
                    .title("Banana Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (banan.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Kata_Noi
        Location kata_n = new Location("B");
        kata_n.setLatitude(lat[51]);
        kata_n.setLongitude(lon[51]);

        if (kata_n.distanceTo(current_point) <= 2000) {
            Marker kata = mMap.addMarker(new MarkerOptions()
                    .position(Kata_Noi)
                    .title("Kata Noi Beach Phuket ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (kata_n.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Kata
        Location kata = new Location("B");
        kata.setLatitude(lat[52]);
        kata.setLongitude(lon[52]);

        if (kata.distanceTo(current_point) <= 2000) {
            Marker kat = mMap.addMarker(new MarkerOptions()
                    .position(Kata)
                    .title("Kata Beach Resort and Spa")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (kata.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Haad_Sai_Kaew
        Location haad_s = new Location("B");
        haad_s.setLatitude(lat[53]);
        haad_s.setLongitude(lon[53]);

        if (haad_s.distanceTo(current_point) <= 2000) {
            Marker haad = mMap.addMarker(new MarkerOptions()
                    .position(Haad_Sai_Kaew)
                    .title("Haad Sai Kaew Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (haad_s.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Karon
        Location karo = new Location("B");
        karo.setLatitude(lat[54]);
        karo.setLongitude(lon[54]);

        if (karo.distanceTo(current_point) <= 2000) {
            Marker kar = mMap.addMarker(new MarkerOptions()
                    .position(Karon)
                    .title("Karon Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (karo.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Khao_Yai
        Location khao_y = new Location("B");
        khao_y.setLatitude(lat[55]);
        khao_y.setLongitude(lon[55]);

        if (khao_y.distanceTo(current_point) <= 2000) {
            Marker khao = mMap.addMarker(new MarkerOptions()
                    .position(Khao_Yai)
                    .title("Khao Yai National Park ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (khao_y.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For Khao_Sam_Roi_Yot
        Location khao_s = new Location("B");
        khao_s.setLatitude(lat[56]);
        khao_s.setLongitude(lon[56]);

        if (khao_s.distanceTo(current_point) <= 2000) {
            Marker khao_ = mMap.addMarker(new MarkerOptions()
                    .position(Khao_Sam_Roi_Yot)
                    .title("The first marine park")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (khao_s.distanceTo(current_point) > 2000) {
            counter = 0;
        }


        //For Jungceylon
        Location jung = new Location("B");
        jung.setLatitude(lat[57]);
        jung.setLongitude(lon[57]);

        if (jung.distanceTo(current_point) <= 2000) {
            Marker jun = mMap.addMarker(new MarkerOptions()
                    .position(Jungceylon)
                    .title("Jungceylon Phuket Shopping Mall ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (jung.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For The_Plaza_Surin
        Location the_p = new Location("B");
        the_p.setLatitude(lat[58]);
        the_p.setLongitude(lon[58]);

        if (the_p.distanceTo(current_point) <= 2000) {
            Marker the = mMap.addMarker(new MarkerOptions()
                    .position(The_Plaza_Surin)
                    .title("The Surin Phuket Resort ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (the_p.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //For CentralFestivalphu
        Location centralfe = new Location("B");
        centralfe.setLatitude(lat[59]);
        centralfe.setLongitude(lon[59]);

        if (centralfe.distanceTo(current_point) <= 2000) {
            Marker centra = mMap.addMarker(new MarkerOptions()
                    .position(CentralFestivalphu)
                    .title("Central Festival Phuket - Phuket Shopping Mall")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (centralfe.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Wat Pho
        Location wat_ph = new Location("B");
        wat_ph.setLatitude(lat[60]);
        wat_ph.setLongitude(lon[60]);

        if (wat_ph.distanceTo(current_point) <= 2000) {
            Marker wat_p = mMap.addMarker(new MarkerOptions()
                    .position(Wat_Pho)
                    .title("Wat Pho Temple")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (wat_ph.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Wat Arun
        Location wat_ar = new Location("B");
        wat_ar.setLatitude(lat[61]);
        wat_ar.setLongitude(lon[61]);

        if (wat_ar.distanceTo(current_point) <= 2000) {
            Marker wat_a = mMap.addMarker(new MarkerOptions()
                    .position(Wat_Arun)
                    .title("Wat Arun Temple")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (wat_ar.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Wat Traimit
        Location wat_tr = new Location("B");
        wat_tr.setLatitude(lat[62]);
        wat_tr.setLongitude(lon[62]);

        if (wat_tr.distanceTo(current_point) <= 2000) {
            Marker wat_t = mMap.addMarker(new MarkerOptions()
                    .position(Wat_Traimit)
                    .title("Wat Traimit Temple")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (wat_tr.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Wat Suthat
        Location wat_su = new Location("B");
        wat_su.setLatitude(lat[63]);
        wat_su.setLongitude(lon[63]);

        if (wat_su.distanceTo(current_point) <= 2000) {
            Marker wat_s = mMap.addMarker(new MarkerOptions()
                    .position(Wat_Suthat)
                    .title("Wat Suthat Temple")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (wat_su.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Pattaya_Floating_Market
        Location pattaya_f = new Location("B");
        pattaya_f.setLatitude(lat[64]);
        pattaya_f.setLongitude(lon[64]);

        if (pattaya_f.distanceTo(current_point) <= 2000) {
            Marker patta = mMap.addMarker(new MarkerOptions()
                    .position(Pattaya_Floating_Market)
                    .title("Pattaya Floating Market")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (pattaya_f.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Pattaya_Park
        Location pattaya_p = new Location("B");
        pattaya_p.setLatitude(lat[65]);
        pattaya_p.setLongitude(lon[65]);

        if (pattaya_p.distanceTo(current_point) <= 2000) {
            Marker pattay = mMap.addMarker(new MarkerOptions()
                    .position(Pattaya_Park)
                    .title("Pattaya Park")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (pattaya_p.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Underwater_World
        Location under = new Location("B");
        under.setLatitude(lat[66]);
        under.setLongitude(lon[66]);

        if (under.distanceTo(current_point) <= 2000) {
            Marker unde = mMap.addMarker(new MarkerOptions()
                    .position(Underwater_World)
                    .title("Underwater World")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (under.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Naklua_Beach
        Location nakl = new Location("B");
        nakl.setLatitude(lat[67]);
        nakl.setLongitude(lon[67]);

        if (nakl.distanceTo(current_point) <= 2000) {
            Marker nak = mMap.addMarker(new MarkerOptions()
                    .position(Naklua_Beach)
                    .title("Naklua Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (nakl.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //MiNi_Siam
        Location mini_s = new Location("B");
        mini_s.setLatitude(lat[68]);
        mini_s.setLongitude(lon[68]);

        if (mini_s.distanceTo(current_point) <= 2000) {
            Marker mini = mMap.addMarker(new MarkerOptions()
                    .position(MiNi_Siam)
                    .title("MiNi Siam, Miniature Park")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (mini_s.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Nong_Nooch
        Location nong_n = new Location("B");
        nong_n.setLatitude(lat[69]);
        nong_n.setLongitude(lon[69]);

        if (nong_n.distanceTo(current_point) <= 2000) {
            Marker nong = mMap.addMarker(new MarkerOptions()
                    .position(Nong_Nooch)
                    .title("Nong Nooch Botancial Garden")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (nong_n.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Jomtien_Beach
        Location jomti = new Location("B");
        jomti.setLatitude(lat[70]);
        jomti.setLongitude(lon[70]);

        if (jomti.distanceTo(current_point) <= 2000) {
            Marker jom = mMap.addMarker(new MarkerOptions()
                    .position(Jomtien_Beach)
                    .title("Jomtien Beach")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (jomti.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Sanctuary_of_Truth
        Location sanct = new Location("B");
        sanct.setLatitude(lat[71]);
        sanct.setLongitude(lon[71]);

        if (sanct.distanceTo(current_point) <= 2000) {
            Marker sanc = mMap.addMarker(new MarkerOptions()
                    .position(Sanctuary_of_Truth)
                    .title("Sanctuary of Truth Temple")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (sanct.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Wat_Yansangwararam
        Location wat_ya = new Location("B");
        wat_ya.setLatitude(lat[72]);
        wat_ya.setLongitude(lon[72]);

        if (wat_ya.distanceTo(current_point) <= 2000) {
            Marker wat_y = mMap.addMarker(new MarkerOptions()
                    .position(Wat_Yansangwararam)
                    .title("Wat Yansangwararam Temple")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (wat_ya.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Wat_Chalong
        Location wat_ch = new Location("B");
        wat_ch.setLatitude(lat[73]);
        wat_ch.setLongitude(lon[73]);

        if (wat_ch.distanceTo(current_point) <= 2000) {
            Marker wat_c = mMap.addMarker(new MarkerOptions()
                    .position(Wat_Chalong)
                    .title("Wat Chalong Temple")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (wat_ch.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Laem
        Location laem = new Location("B");
        laem.setLatitude(lat[74]);
        laem.setLongitude(lon[74]);

        if (laem.distanceTo(current_point) <= 2000) {
            Marker lae = mMap.addMarker(new MarkerOptions()
                    .position(Laem)
                    .title("Laem Chabang, Phromthep Cape")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (laem.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Town_Night_Market
        Location town_n = new Location("B");
        town_n.setLatitude(lat[75]);
        town_n.setLongitude(lon[75]);

        if (town_n.distanceTo(current_point) <= 2000) {
            Marker town = mMap.addMarker(new MarkerOptions()
                    .position(Town_Night_Market)
                    .title("Town Night Market")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (town_n.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Muay_Thai
        Location muay_t = new Location("B");
        muay_t.setLatitude(lat[76]);
        muay_t.setLongitude(lon[76]);

        if (muay_t.distanceTo(current_point) <= 2000) {
            Marker muay = mMap.addMarker(new MarkerOptions()
                    .position(Muay_Thai)
                    .title("Muay Thai Boxing")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (muay_t.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Big_Buddha
        Location big_b = new Location("B");
        big_b.setLatitude(lat[77]);
        big_b.setLongitude(lon[77]);

        if (big_b.distanceTo(current_point) <= 2000) {
            Marker big = mMap.addMarker(new MarkerOptions()
                    .position(Big_Buddha)
                    .title("Big Buddha Statue")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (big_b.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Simon
        Location simo = new Location("B");
        simo.setLatitude(lat[78]);
        simo.setLongitude(lon[78]);

        if (simo.distanceTo(current_point) <= 2000) {
            Marker sim = mMap.addMarker(new MarkerOptions()
                    .position(Simon)
                    .title("Simon Cabaret Phuket ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (simo.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //FantaSea
        Location fanta = new Location("B");
        fanta.setLatitude(lat[79]);
        fanta.setLongitude(lon[79]);

        if (fanta.distanceTo(current_point) <= 2000) {
            Marker fant = mMap.addMarker(new MarkerOptions()
                    .position(FantaSea)
                    .title("Phuket Fantasea")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (fanta.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Damnoen_Saduak
        Location damnoen = new Location("B");
        damnoen.setLatitude(lat[80]);
        damnoen.setLongitude(lon[80]);

        if (damnoen.distanceTo(current_point) <= 2000) {
            Marker damn = mMap.addMarker(new MarkerOptions()
                    .position(Damnoen_Saduak)
                    .title("Damnoen Saduak Floating Market")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (damnoen.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Khaosan
        Location khaos = new Location("B");
        khaos.setLatitude(lat[82]);
        khaos.setLongitude(lon[82]);

        if (khaos.distanceTo(current_point) <= 2000) {
            Marker khao = mMap.addMarker(new MarkerOptions()
                    .position(Khaosan)
                    .title("Khosan, Bangkok rice market")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (khaos.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Lumphini
        Location lumph = new Location("B");
        lumph.setLatitude(lat[82]);
        lumph.setLongitude(lon[82]);

        if (lumph.distanceTo(current_point) <= 2000) {
            Marker lump = mMap.addMarker(new MarkerOptions()
                    .position(Lumphini)
                    .title("Lumphini Park")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (lumph.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Street_Food_Stalls
        Location street = new Location("B");
        street.setLatitude(lat[83]);
        street.setLongitude(lon[83]);

        if (street.distanceTo(current_point) <= 2000) {
            Marker stree = mMap.addMarker(new MarkerOptions()
                    .position(Street_Food_Stalls)
                    .title("Street Food Stalls")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (street.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Chatuchak
        Location chatu = new Location("B");
        chatu.setLatitude(lat[84]);
        chatu.setLongitude(lon[84]);

        if (chatu.distanceTo(current_point) <= 2000) {
            Marker chat = mMap.addMarker(new MarkerOptions()
                    .position(Chatuchak)
                    .title("Chatuchak Weekend Market")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (chatu.distanceTo(current_point) > 2000) {
            counter = 0;
        }


        //Giant_Swing
        Location giant_s = new Location("B");
        giant_s.setLatitude(lat[85]);
        giant_s.setLongitude(lon[85]);

        if (giant_s.distanceTo(current_point) <= 2000) {
            Marker giant = mMap.addMarker(new MarkerOptions()
                    .position(Giant_Swing)
                    .title("Giant Swing, Religious Place")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (giant_s.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //National_Museum
        Location national_m = new Location("B");
        national_m.setLatitude(lat[86]);
        national_m.setLongitude(lon[86]);

        if (national_m.distanceTo(current_point) <= 2000) {
            Marker national_ = mMap.addMarker(new MarkerOptions()
                    .position(National_Museum)
                    .title("Bangkok National Museum")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (national_m.distanceTo(current_point) > 2000) {
            counter = 0;
        }

        //Grand_Palace
        Location grand_p = new Location("B");
        grand_p.setLatitude(lat[87]);
        grand_p.setLongitude(lon[87]);

        if (grand_p.distanceTo(current_point) <= 2000) {
            Marker grand = mMap.addMarker(new MarkerOptions()
                    .position(Grand_Palace)
                    .title("Grand Palace")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            counter = 1;

        }
        if (grand_p.distanceTo(current_point) > 2000) {
            counter = 0;
        }


//////////////////////////////////////////////////////////////////////////////////////////////
        mMap.setOnInfoWindowClickListener(this);




    }

    /*Circle circle = mMap.addCircle(new CircleOptions()
            .center(new LatLng(location.getLatitude(), location.getLongitude()))
            .radius(1000)
            .strokeColor(Color.RED)
            .fillColor(Color.BLUE));*/


    ///////////////////////////       SEARCH FUNCTION     /////////////////////////////////

    public void onClick(View view) {
        EditText location_tf = (EditText) findViewById(R.id.editText); // Getting reference to EditText to get the user input location
        String location = location_tf.getText().toString(); // getting user input location

        if (location != null || !location.equals("")) {
            new GeocoderTask().execute(location);
        }
        else{
            Toast.makeText(MapsActivity.this, "Error: Can't find address!", Toast.LENGTH_LONG).show();
        }

    }

    // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if(addresses==null || addresses.size()==0){
              //  Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
                Toast.makeText(MapsActivity.this, "ERROR: Can't find address", Toast.LENGTH_SHORT).show();
            }
            else {
                // Clears all the existing markers on the map
                mMap.clear();

                // Adding Markers on Google Map for each matching address
                for (int i = 0; i < addresses.size(); i++) {

                    Address address = (Address) addresses.get(i);

                    // Creating an instance of GeoPoint, to display in Google Map
                    latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    String addressText = String.format("%s, %s",
                            address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                            address.getCountryName());

                    markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    markerOptions.title(addressText);

                    mMap.addMarker(markerOptions);

                    // Locate the first location
                    if (i == 0)
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                }
            }
        }
    }


    public void onConnected(Bundle Bundle) {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(3000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (location == null) {
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(googleApiClient, locationRequest, this);

        } else {
            getNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location Service Suspended ! Please Reconnect.");
        googleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {

                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        getNewLocation(location);
    }






    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setMapIfNeeded();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)

        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    boolean twice;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (twice == true) {
            Intent back = new Intent(Intent.ACTION_MAIN);
            back.addCategory(Intent.CATEGORY_HOME);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(back);
            finish();
            System.exit(0);


        }


        twice = true;
        Log.d(TAG, "click");
        Toast.makeText(MapsActivity.this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
                Log.d(TAG, "twice: " + twice);
            }
        }, 3000);


    }

    @Override
    public void onInfoWindowClick(Marker marker) {





            String[] key;
            key = new String[2];
            Intent intent = new Intent(MapsActivity.this, ScrollingActivity.class);
            Bundle mBundle = new Bundle();
            key[0] = "MapsActivity";
            switch (marker.getTitle()) {


                case "Don Mueang Airport":
                    key[1] = "1";
                    break;

                case "Suvarnabhumi Airport (BKK)":
                    key[1] = "2";
                    break;

                case "Amari Watergate Bangkok":
                    key[1] = "3";
                    break;

                case "AriyasomVilla Luxury Boutique Hotel Bangkok":
                    key[1] = "4";
                    break;

                case "Mandarin hotel Bangkok":
                    key[1] = "5";
                    break;

                case "The Siam Hotel, Bangkok":
                    key[1] = "6";
                    break;

                case "Phranakorn Nornlen Hotel, Bangkok":
                    key[1] = "7";
                    break;

                case "Okura Prestige Bangkok Hotel":
                    key[1] = "8";
                    break;

                case "Sukhumvit Bangkok Residence":
                    key[1] = "9";
                    break;

                case "Sheraton Hotels & Resorts":
                    key[1] = "10";
                    break;

                case "Hua Hin Hotels, Resorts":
                    key[1] = "11";
                    break;

                case "Ko Samet Beach":
                    key[1] = "12";
                    break;

                case "Bang Saen Beach":
                    key[1] = "13";
                    break;

                case "The Charm Beach":
                    key[1] = "14";
                    break;

                case "The Pranburi beach":
                    key[1] = "15";
                    break;

                case "The Jomtien beach":
                    key[1] = "16";
                    break;

                case "The Koh Chang beach":
                    key[1] = "17";
                    break;

                case "The SO_Sofite Hotel, Bangkok":
                    key[1] = "18";
                    break;

                case "CentralWorld: The Largest Lifestyle Shopping Destination in Bangkok":
                    key[1] = "19";
                    break;

                case "Siam Paragon - Bangkok Shopping Centre ":
                    key[1] = "20";
                    break;

                case "Siam Center Bangkok - Bangkok Shopping Centre":
                    key[1] = "21";
                    break;

                case "MBK Shopping Center Bangkok - Bangkok Shopping Malls":
                    key[1] = "22";
                    break;

                case "U-Tapao International Airport. Pattaya":
                    key[1] = "23";
                    break;

                case "Sunshine Pattaya Hotel":
                    key[1] = "24";
                    break;

                case "Basaya Beach Resort, Pattaya":
                    key[1] = "25";
                    break;

                case "I-Rovers Sportsbar Pattaya":
                    key[1] = "26";
                    break;

                case "Guest house Walee's Place, Pattayaa":
                    key[1] = "27";
                    break;

                case "Areca Lodge, Pattaya":
                    key[1] = "28";
                    break;

                case "A-one Pattaya Resort":
                    key[1] = "29";
                    break;

                case "Hotel Pattaya Loft, Pattaya":
                    key[1] = "30";
                    break;

                case "AA Hotel Pattaya":
                    key[1] = "31";
                    break;

                case "Chonburi Hotels, Thailand":
                    key[1] = "32";
                    break;

                case "Book Sea Sand Sun Resort & Spa Pattayaa":
                    key[1] = "33";
                    break;

                case "Centara Grand Mirage family resort & Pattaya water park":
                    key[1] = "34";
                    break;

                case "Cape Dara Resort, Pattaya":
                    key[1] = "35";
                    break;

                case "InterContinental Pattaya Resort":
                    key[1] = "36";
                    break;

                case "CentralFestival Pattaya Beach":
                    key[1] = "37";
                    break;

                case "Pattaya Avenue Shopping Malla":
                    key[1] = "38";
                    break;

                case "CentralFestival Shopping mall, Pattaya, Thailand":
                    key[1] = "39";
                    break;

                case "Sb Furniture Homepro Pattaya":
                    key[1] = "40";
                    break;

                case "Phuket International Airport":
                    key[1] = "41";
                    break;

                case "New Dara Boutique Hotel & Residence":
                    key[1] = "42";
                    break;

                case "Baan Suwantawe Hotel Apartment in Phuket":
                    key[1] = "43";
                    break;

                case "Saruda Phuket Hotel":
                    key[1] = "44";
                    break;

                case "Sino Imperial Phuket Hotel in Phuket":
                    key[1] = "45";
                    break;

                case "Baan Sutra Guesthouse":
                    key[1] = "46";
                    break;

                case "Cool Residence":
                    key[1] = "47";
                    break;

                case "The Memory at On On Hotel":
                    key[1] = "48";
                    break;

                case "Freedom Beach":
                    key[1] = "49";
                    break;

                case "Banana Beach":
                    key[1] = "50";
                    break;

                case "Kata Noi Beach Phuket":
                    key[1] = "51";
                    break;

                case "Kata Beach Resort and Spa":
                    key[1] = "52";
                    break;

                case "Haad Sai Kaew Beach":
                    key[1] = "53";
                    break;

                case "Karon Beach":
                    key[1] = "54";
                    break;

                case "Khao Yai National Park":
                    key[1] = "55";
                    break;

                case "The first marine park":
                    key[1] = "56";
                    break;

                case "Jungceylon Phuket Shopping Mall":
                    key[1] = "57";
                    break;

                case "The Surin Phuket Resort":
                    key[1] = "58";
                    break;

                case "Central Festival Phuket - Phuket Shopping Mall":
                    key[1] = "59";
                    break;

                case "Wat Pho Temple":
                    key[1] = "60";
                    break;

                case "Wat Arun Temple":
                    key[1] = "61";
                    break;

                case "Wat Traimit Temple":
                    key[1] = "62";
                    break;

                case "Wat Suthat Temple":
                    key[1] = "63";
                    break;

                case "Pattaya Floating Market":
                    key[1] = "64";
                    break;

                case "Pattaya Park":
                    key[1] = "65";
                    break;

                case "Underwater World":
                    key[1] = "66";
                    break;

                case "Naklua Beach":
                    key[1] = "67";
                    break;

                case "MiNi Siam, Miniature Park":
                    key[1] = "68";
                    break;

                case "Nong Nooch Botancial Garden":
                    key[1] = "69";
                    break;

                case "Jomtien Beach":
                    key[1] = "70";
                    break;

                case "Sanctuary of Truth Temple":
                    key[1] = "71";
                    break;

                case "Wat Yansangwararam Temple":
                    key[1] = "72";
                    break;

                case "Wat Chalong Temple":
                    key[1] = "73";
                    break;

                case "Laem Chabang, Phromthep Cape":
                    key[1] = "74";
                    break;

                case "Town Night Market":
                    key[1] = "75";
                    break;

                case "Muay Thai Boxing":
                    key[1] = "76";
                    break;

                case "Big Buddha Statue":
                    key[1] = "77";
                    break;

                case "Simon Cabaret Phuket":
                    key[1] = "78";
                    break;

                case "Phuket Fantasea":
                    key[1] = "79";
                    break;

                case "Damnoen Saduak Floating Market":
                    key[1] = "80";
                    break;

                case "Khosan, Bangkok rice market":
                    key[1] = "81";
                    break;

                case "Lumphini Park":
                    key[1] = "82";
                    break;

                case "Street Food Stalls":
                    key[1] = "83";
                    break;

                case "Chatuchak Weekend Market":
                    key[1] = "84";
                    break;

                case "Giant Swing, Religious Place":
                    key[1] = "85";
                    break;

                case "Bangkok National Museum":
                    key[1] = "86";
                    break;

                case "Grand Palace":
                    key[1] = "87";
                    break;

                }
            mBundle.putStringArray("data", key);
            intent.putExtras(mBundle);
            startActivity(intent);
            finish();




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

      //  if (id == R.id.nav_map) {
         //   Intent intent_map = new Intent(this, MapsActivity.class);
         //   startActivity(intent_map);
           // finish();}

         if (id == R.id.nav_info){
            Intent intent_info = new Intent(MapsActivity.this, Info.class);
            startActivity(intent_info);
            finish();

        }else if (id == R.id.nav_navg) {
            Intent intent_navg = new Intent(MapsActivity.this, NavigationActivity.class);
            startActivity(intent_navg);
            finish();

        }else if (id == R.id.nav_about_us) {
            Intent intent_aboutus = new Intent(MapsActivity.this, Aboutus.class);
            startActivity(intent_aboutus);
            finish();
        }

      //  else if (id == R.id.nav_share) {
           /*
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");*/

          /*   Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
             sendIntent.setData(Uri.parse("smsto:"));

             sendIntent.putExtra("sms_body", "Here I am");

             if (sendIntent.resolveActivity(getPackageManager()) != null) {
                 startActivity(sendIntent);
             }
             else{
                 Toast.makeText(this, "Cannot open SMS.", Toast.LENGTH_SHORT).show();
             }
*/


       //  }
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}