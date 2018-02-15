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

public class TabPhuket extends Fragment {


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
        mproductlist.add(new Item(1, R.drawable.bigbuddha,
                "The Big Buddha of Phuket",
                "Chalong Bay"));
        mproductlist.add(new Item(2, R.drawable.phuketbeach,
                "Banana Beach",
                "West Coast"));
        mproductlist.add(new Item(3, R.drawable.simoncabaret,
                "Simon Cabaret",
                "Patong"));
        mproductlist.add(new Item(4, R.drawable.phuketfantasea,
                "Phuket Fantasea",
                "Kamala"));
        mproductlist.add(new Item(5, R.drawable.watchalong,
                "Wat Chalong",
                "Chalong Bay"));
        mproductlist.add(new Item(6, R.drawable.laem,
                "Laem Phromthep Viewpoint",
                "Rawai"));
        mproductlist.add(new Item(7, R.drawable.nightmarket,
                "Phuket Town Night Market",
                "Phuket Town"));
        mproductlist.add(new Item(8, R.drawable.muayboxing,
                "Muay Thai Boxing",
                "Patong"));


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
                key[0] = "TabPhuket";
                switch (arg2) {


                    case 0:
                        key[1] = "77";
                        break;
                    case 1:
                        key[1] = "50";
                        break;
                    case 2:
                        key[1] = "78";
                        break;
                    case 3:
                        key[1] = "79";
                        break;
                    case 4:
                        key[1] = "73";
                        break;
                    case 5:
                        key[1] = "74";
                        break;
                    case 6:
                        key[1] = "75";
                        break;
                    case 7:
                        key[1] = "76";
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