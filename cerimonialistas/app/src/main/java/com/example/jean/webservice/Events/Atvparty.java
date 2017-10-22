package com.example.jean.webservice.Events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jean.webservice.Guests.AtvGuests;
import com.example.jean.webservice.Helpers.Http;
import com.example.jean.webservice.R;

public class Atvparty extends AppCompatActivity {
    Integer event;
    LinearLayout guestsList;
    Http http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_party);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        http = new Http(getApplicationContext());

        guestsList = (LinearLayout) findViewById(R.id.guests_list);


        Bundle budle = getIntent().getExtras();
        event = budle.getInt("event_id");

//        Api http = new Api(url,"get",getApplicationContext(),null);
//        http.request(new Api.VolleyCallback() {
//            @Override
//            public void onSuccess(JSONObject result) {
//                System.out.println(result);
//            }
//        });


//        http.get(url, new Http.VolleyCallback() {
//            @Override
//            public void onSuccess(JSONObject result) {
//                System.out.println(result);
//            }
//        });

        guestsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AtvGuests.class);
                intent.putExtra("event_id", event);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }
}
