package com.example.jean.cerimonialistas;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jeansarlon on 26/09/16.
 */

public class MyPartiesFragment extends Fragment {
    View myView;
    FloatingActionButton fccc;
    TextView mTextView;
    ListView lv;
    ArrayList<Party> parties = new ArrayList<Party>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.my_parties_layout, container, false);
        fccc = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        //mTextView = (TextView) myView.findViewById(R.id.tv_result);
        fccc.hide();
        fccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Third", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        requisicaoVolleyC();
        return myView;
    }


    private void requisicaoVolleyC(){
        RequestQueue mRequestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();


        final String url ="http://192.168.58.1:80/cerimonialistas/public/user/1/parties";

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr = response.getJSONArray("message");
                            final JSONObject response1;


                            for(int i=0; i<arr.length(); i++){
                                JSONObject e = (JSONObject) arr.get(i);


                                parties.add(new Party(
                                        e.getString("name"),
                                        e.getString("birthday_people")
                                ));
                            }
                            final ArrayAdapter<Party> ad = new Adapter(getActivity(),R.layout.my_parties_item_list,parties);
                            lv = (ListView)myView.findViewById(R.id.parties_list);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Party p = parties.get(i);
                                    Toast.makeText(getActivity(
                                    ), "Você clicou em: " + p.getName(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            lv.setAdapter(ad);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

// add it to the RequestQueue
        mRequestQueue.add(getRequest);
//        mRequestQueue.add(stringRequest);
    }
}
