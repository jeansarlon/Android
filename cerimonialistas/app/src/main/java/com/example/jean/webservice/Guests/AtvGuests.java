package com.example.jean.webservice.Guests;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.jean.webservice.Helpers.Base;
import com.example.jean.webservice.Helpers.Http;
import com.example.jean.webservice.Helpers.RecyclerItemClickListener;
import com.example.jean.webservice.R;
import com.example.jean.webservice.adabpter.GuestsAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AtvGuests extends AppCompatActivity {
    Http http;
    Integer event;
    JSONObject myDataset;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Integer pos;
    private View viewC;
    private Base base = new Base();
    ArrayList<Guest> guests_array = new ArrayList<Guest>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_guests);
        mRecyclerView = (RecyclerView) findViewById(R.id.guests_recycler_view);

        Bundle budle = getIntent().getExtras();
        event = budle.getInt("event_id");
        String url = Base.Routes.getAllGuests(event);

        http = new Http(getApplicationContext());
        http.get(url, new Http.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                myDataset = result;
                try {
                    JSONArray jsonArray = result.getJSONObject("guests").getJSONArray("data");
                    Gson gson = new Gson();
                    for(int i = 0; i < jsonArray.length(); i++){
                        Guest post = gson.fromJson(jsonArray.getJSONObject(i).toString(), Guest.class);
                        guests_array.add(post);

                    }
                    setUpRecyclerView();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new GuestsAdapter(getApplicationContext(), guests_array);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        registerForContextMenu(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(getBaseContext(), "Single Click on position        :"+position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                view.setBackgroundColor(1);
                viewC = view;
            }
        }));
    }


    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.guests_recycler_view) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle("Ações");

            MenuInflater inflater = this.getMenuInflater();
            inflater.inflate(R.menu.guests_context_menu, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        String[] menuItems = getResources().getStringArray(R.array.context_menu);
        return true;
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
        viewC.setBackgroundColor(Color.WHITE);
    }
}
