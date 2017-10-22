package com.example.jean.webservice.Events;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.jean.webservice.Helpers.Base;
import com.example.jean.webservice.Helpers.Http;
import com.example.jean.webservice.R;
import com.example.jean.webservice.adabpter.Adapter;

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
    private Base base = new Base();


    String token;

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
        SharedPreferences settings = (SharedPreferences) getActivity().getSharedPreferences(Base.LOGIN_PREFS,0);
        String userId = settings.getString("user_id", "nada");

        final String url = Base.Routes.getAllEvents(userId);

        Http http = new Http(getActivity());
        http.get(url, new Http.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray jsonArray = result.getJSONArray("message");
                    System.out.println(jsonArray);

                    // TODO: 26/02/17 Não posso usar Gson pois a classe Party ja converte dateTime para Data
//                    Gson gson = new Gson();
//                    for(int i = 0; i < jsonArray.length(); i++){
//                        Party party = gson.fromJson(jsonArray.getJSONObject(i).toString(), Party.class);
//                        parties.add(party);
//
//                    }


                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject e = (JSONObject) jsonArray.get(i);

                        parties.add(new Party(
                                e.getInt("id"),
                                e.getString("name"),
                                e.getString("birthday_people"),
                                e.getString("date")
                        ));
                    }
                    final ArrayAdapter<Party> ad = new Adapter(getActivity(),R.layout.my_parties_item_list,parties);
                    lv = (ListView)myView.findViewById(R.id.parties_list);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Party p = parties.get(i);

                            Intent intent = new Intent(getActivity(), Atvparty.class);
                            intent.putExtra("event_id", p.getId());
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                        }
                    });
                    lv.setAdapter(ad);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
