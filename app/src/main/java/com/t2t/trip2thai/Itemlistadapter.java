package com.t2t.trip2thai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Itemlistadapter extends BaseAdapter {
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    private Context mcontext;
    private List<Item> mProductlist;

    //constructor


    public Itemlistadapter(Context mcontext, List<Item> mProductlist) {
        this.mcontext = mcontext;
        this.mProductlist = mProductlist;
    }

    @Override
    public int getCount() {
        return mProductlist.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mProductlist.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(mcontext,R.layout.item_list,null);
        ImageView tvimag=(ImageView)v.findViewById(R.id.tv_imag);
        TextView tvName=(TextView)v.findViewById(R.id.tv_name);
        TextView tvinfo=(TextView)v.findViewById(R.id.tv_info);
      //  TextView tvdescription=(TextView)v.findViewById(R.id.tv_description);
        //set text for textview
        tvimag.setImageResource(mProductlist.get(position).getImages());
        tvName.setText(mProductlist.get(position).getName());
        tvinfo.setText(mProductlist.get(position).getInfo());
     //   tvdescription.setText(mProductlist.get(position).getDescription());
        //save product id to tag
        v.setTag(mProductlist.get(position).getId());

        return v;
    }


}
