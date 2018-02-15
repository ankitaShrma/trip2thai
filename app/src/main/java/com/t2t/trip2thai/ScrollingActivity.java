package com.t2t.trip2thai;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {
    private String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle b = getIntent().getExtras();
        name = b.getStringArray("data");
        int a = Integer.parseInt(name[1]);


        DatabaseAccess(a);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name[0]="ScrollingActivity";
                Intent intent = new Intent(ScrollingActivity.this, MapsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putStringArray("data", name);
                intent.putExtras(mBundle);

                startActivity(intent);
                finish();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Bundle b = getIntent().getExtras();
        String[] name = b.getStringArray("data");
        switch (name[0]) {

            case "NavigationActivity":
                Intent intent_block = new Intent(ScrollingActivity.this, NavigationActivity.class);
                startActivity(intent_block);
                finish();
                break;
            case "MapsActivity":
                name[1] = "0";
                name[0] = "MapsActivity";
                Bundle mBundle = new Bundle();
                Intent nextIntent = new Intent(ScrollingActivity.this, MapsActivity.class);
                mBundle.putStringArray("data", name);
                nextIntent.putExtras(mBundle);
                startActivity(nextIntent);
                finish();
                break;
             default:
                Intent intent = new Intent(ScrollingActivity.this, NavigationActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }



    private void DatabaseAccess(int a) {
        String[] data;
        data =new String[5];
        DatabaseHelper databasehelper = DatabaseHelper.getObject(this);
        databasehelper.open();
        //name
        data[0] = databasehelper.databaseTostring(a, 1);
        //info
        data[1]= databasehelper.databaseTostring(a, 2);
        //description
        data[2]= databasehelper.databaseTostring(a, 5);

        //lat
        data[3] = databasehelper.databaseTostring(a, 3);
        //lon
        data[4] = databasehelper.databaseTostring(a, 4);

        //close database
        databasehelper.close();
        displayInfo(data);

    }

    public void displayInfo(String[] data)
    {
        TextView title = (TextView) findViewById(R.id.title);
        TextView location = (TextView) findViewById(R.id.location);
        TextView body = (TextView) findViewById(R.id.body);

        title.setText(data[0]);
        location.setText(data[1]);
        body.setText(data[2]);


    }
}
