package com.example.jean.webservice.adabpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jean.webservice.Events.Party;
import com.example.jean.webservice.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class Adapter extends ArrayAdapter<Party> {
    private LayoutInflater inflater;
    private int resourceId;
    ImageView imageView;

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
        imageView = (ImageView) converView.findViewById(R.id.iv_event_cover);
         String data = party.getDate() + "   "+ party.getTime();
        tvNome.setText(data.toString());
        tvBirthday_people.setText(party.getName().toString());

        Picasso.with(getContext())
                .load("https://vybezapp.com/wp-content/uploads/2017/03/clipboard-with-pencil-4.png")
                .into(imageView);


        return converView;
    }

}