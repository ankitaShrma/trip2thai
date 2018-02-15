package com.t2t.trip2thai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TabBangkok extends Fragment {


        private ListView myListView;
        private Itemlistadapter adapter;
        private List<Item> mproductlist;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.tab_bangkok, container, false);

            myListView = (ListView) rootView.findViewById(R.id.listView);
            mproductlist = new ArrayList<>();
            //Add sample data for list
            //we can get data from db,webservice here
            mproductlist.add(new Item(1, R.drawable.grandpalace,
                    "Grand Palace",
                    "Na Phra Lan Road, Phra Borom Maha Ratchawang, Phra Nakhon "));
            //   "Royal Residence of generations.Half-day sightseeing tour"));
            mproductlist.add(new Item(1, R.drawable.watpho,
                    "Wat Pho",
                    "Immediately south of the Grand Palace"));
            // "Temple of the Reclining Buddha, where you'll find a statue so big (45 m long and 15 m high)"));
            mproductlist.add(new Item(1, R.drawable.watarun,
                    "Wat Arun",
                    "Arun Amarin Road"));
            // "Triumphant complex, Sunset is really the time to take in this place in all its glory"));
            mproductlist.add(new Item(1, R.drawable.wattraimit,
                    "Wat Traimit ",
                    "Mittaphap Thai-China Rd, Talat Noi, Samphanthawong"));
            //   "Temple of the Golden Buddha"));
            mproductlist.add(new Item(1, R.drawable.watsuthat,
                    "Wat Suthat",
                    "Bamrung Muang Road, Sao Chingcha, Phra Nakhon"));
            // "Bangkok's Buddhist temples. Less popular so more peaceful and intimate experiences "));
            mproductlist.add(new Item(1, R.drawable.giantswing,
                    "Giant Swing",
                    "Bamrung Muang Rd; In front of Wat Suthat"));
            //     "Bangkok's most eye-catching sights,"));
            mproductlist.add(new Item(1, R.drawable.wangnapalace,
                    "National Museum & Wang Na Palace",
                    "Na Phra That Road"));
            //    " Regalia, Religious and ceremonial artifacts, ceramics, games, weaponry, musical instruments and the Viceroy's throne"));
            mproductlist.add(new Item(1, R.drawable.chatuchak,
                    "Chatuchak Market",
                    "Adjacent to the Kamphaengpecth Station (MRT)"));
            //   "Bangkok's sprawling weekend market.Shoppers find everything from jewelry and religious icons to animals and delicious street foods"));
            mproductlist.add(new Item(1, R.drawable.mandarin,
                    "Mandarin Oriental, Bangkok",
                    "Oriental Ave, Bang Rak"));
            //    " In Riverside, with a great location, beautiful pools, and several restaurants, including the Riverside Terrace overlooking the Chao Phraya River. "));
            mproductlist.add(new Item(1, R.drawable.domnoen,
                    "Damnoen Saduak Floating Market",
                    "Ratchaburi (about 1.5 hours outside Bangkok)"));
            //   "Interesting market experience.Buy fresh and delicious foods and interact with locals in an authentic way."));
            mproductlist.add(new Item(1, R.drawable.khaosan,
                    "Khao San Road",
                    "Banglamphu area of Phra Nakhon district about 1 kilometre"));
            //    "Bangkok's infamous backpacker district, a neighborhood jam-packed with guesthouses, food vendors, clothing stalls, and travelers from every corner of the globe"));
            mproductlist.add(new Item(1, R.drawable.lumpini,
                    "Lumpini Park",
                    "Rama IV Road; Between Ratchadamri and Witthayu "));
            //  "Green oasis amidst the traffic and chaos of Bangkok, enjoy the shade of a Chinese pagoda, or take a boat out on the lake"));
            mproductlist.add(new Item(1, R.drawable.streetfood,
                    "Street Food Stalls",
                    "Chinatown ; \t Oldtown ; \t Sukhumvit ; \t Soi Rambuttri ; \t Silom and Sathorn"));
            //  "Grilled meats and fish, soups, fresh fruit, and myriad other dishes"));


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
                    key[0] = "TabBangkok";
                    switch (arg2) {


                        case 0:
                            key[1] = "87";
                            break;
                        case 1:
                            key[1] = "60";
                            break;
                        case 2:
                            key[1] = "61";
                            break;
                        case 3:
                            key[1] = "62";
                            break;
                        case 4:
                            key[1] = "63";
                            break;
                        case 5:
                            key[1] = "85";
                            break;
                        case 6:
                            key[1] = "86";
                            break;
                        case 7:
                            key[1] = "84";
                            break;
                        case 8:
                            key[1] = "5";
                            break;
                        case 9:
                            key[1] = "80";
                            break;
                        case 10:
                            key[1] = "81";
                            break;
                        case 11:
                            key[1] = "82";
                            break;
                        case 12:
                            key[1] = "83";
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
