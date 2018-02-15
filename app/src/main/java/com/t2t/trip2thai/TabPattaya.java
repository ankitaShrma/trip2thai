package com.t2t.trip2thai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TabPattaya extends Fragment {


    private ListView myListView;
    private Itemlistadapter adapter;
    private List<Item> mproductlist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_pattaya, container, false);

        myListView = (ListView) rootView.findViewById(R.id.listView);
        mproductlist = new ArrayList<>();
        //Add sample data for list
        //we can get data from db,webservice here
        mproductlist.add(new Item(1, R.drawable.noon,
                "Nong Nooch Village",
                "34/1 Moo 7 Na Jomtien, Sattahip"));
        mproductlist.add(new Item(2, R.drawable.jomtien, "Jomtien Beach",
                "About 165 km south-east of Bangkok in Chonburi Province"));
        mproductlist.add(new Item(3, R.drawable.sanctuary,
                "Sanctuary of Truth",
                "206/2 Moo 5, Soi Naklua 12 , Naklua, Banglamung"));
        mproductlist.add(new Item(4, R.drawable.watsansa,
                "Wat Yansangwararam",
                "Na Jomtien Km 160, Sukhumvit Road"));
        mproductlist.add(new Item(5, R.drawable.minisiam,
                "Mini Siam",
                "387 Moo 6 Sukhumvit Rd., Pattaya City Naklua, Banglamung"));
        mproductlist.add(new Item(6, R.drawable.floating,
                "Pattaya Floating Market",
                "451/304 Mu 12, Sukhumvit Road, Tambon Nong Prue, Amphoe Bang Lamung"));
        mproductlist.add(new Item(7, R.drawable.pattayapark,
                "Pattaya Park",
                "345 Jomtien Beach, Pattaya City"));
        mproductlist.add(new Item(8, R.drawable.world,
                "Underwater World Pattaya",
                "22/22 Moo 11,Sukhumvit Rd.,Nongprue, Banglamung"));
        mproductlist.add(new Item(9, R.drawable.naklua,
                "Naklua Beach",
                "Pattaya-Naklua Rd. Banglamung, Chonburi"));


        //  Initialize adapter
        adapter = new Itemlistadapter(getActivity().getApplicationContext(), mproductlist);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                // Log.v("TAG", "CLICKED row number: " + arg2);

                String[] key;
                key = new String[2];
                Intent intent = new Intent(getActivity(), ScrollingActivity.class);
                Bundle mBundle = new Bundle();
                key[0] = "TabPattaya";
                switch (arg2) {


                    case 0:
                        key[1] = "69";
                        break;
                    case 1:
                        key[1] = "70";
                        break;
                    case 2:
                        key[1] = "71";
                        break;
                    case 3:
                        key[1] = "72";
                        break;
                    case 4:
                        key[1] = "68";
                        break;
                    case 5:
                        key[1] = "64";
                        break;
                    case 6:
                        key[1] = "65";
                        break;
                    case 7:
                        key[1] = "66";
                        break;
                    case 8:
                        key[1] = "67";
                        break;

                }
                mBundle.putStringArray("data", key);
                intent.putExtras(mBundle);
                startActivity(intent);
                // finish();

            }});


        return rootView;

    }


}