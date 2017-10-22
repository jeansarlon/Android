package com.example.jean.webservice.adabpter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.jean.webservice.Guests.Guest;
import com.example.jean.webservice.R;

import java.util.ArrayList;


public class GuestsAdapter extends RecyclerView.Adapter<GuestsAdapter.ViewHolder> {

    private int position;
    private ArrayList<Guest> guests;
    Context mContext;

    /**
     * Constructor
     */

    public GuestsAdapter(Context applicationContext, ArrayList<Guest> myDataset) {
        this.guests = myDataset;
        this.mContext = applicationContext;
    }

    /**
     * getPosition
     *****************/

    public int getPosition() {
        return this.position;
    }

    /**
     * setPosition
     *****************/

    public void setPosition(int position) {
        this.position = position;
    }


    /**
     * onCreateViewHolder()
     */

    @Override
    public GuestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.guests_item_list, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    /**
     * onBindViewHolder
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Guest item = guests.get(position);
        holder.tvName.setText(guests.get(position).getName()+ " " + guests.get(position).getLastname());
        holder.tvAge.setText(guests.get(position).getAge().toString());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(getPosition());
                return false;
            }
        });



    }

    /**
     * getItemCount()
     */

    @Override
    public int getItemCount() {
        return guests.size();
    }


    /**
     * ViewHolder Class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName;
        final TextView tvAge;
        final CardView item_list;



        public ViewHolder(View v) {
            super(v);
            tvAge = (TextView) v.findViewById(R.id.tv_guests_item_list_age);
            item_list = (CardView) v.findViewById(R.id.guest_item);
            tvName = (TextView) v.findViewById(R.id.tv_guests_item_list_name);
        }
    }

    /**
     * onViewRecycled
     */
    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }


}
