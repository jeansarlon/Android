package com.example.jean.cerimonialistas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jeansarlon on 25/09/16.
 */

public class Adapter extends ArrayAdapter<Party> {
    private LayoutInflater inflater;
    private int resourceId;

    public Adapter(Context context, int resource, List<Party> objects) {
        super( context, resource, objects);
        this.inflater = LayoutInflater.from( context);
        this.resourceId = resource;
    }


    public View getView(int position, View converView, ViewGroup parent){
        final Party party = getItem(position);

        converView = inflater.inflate(resourceId, parent, false);

        TextView tvNome = (TextView) converView.findViewById(R.id.tv_parties_item_list_name);
        TextView tvBirthday_people = (TextView) converView.findViewById(R.id.tv_parties_item_list_people);
        tvNome.setText(party.getName());
        tvBirthday_people.setText(party.getBirthday_people().toString());


        return converView;
    }

}