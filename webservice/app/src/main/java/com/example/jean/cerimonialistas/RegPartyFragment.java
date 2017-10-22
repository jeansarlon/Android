package com.example.jean.cerimonialistas;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.jean.cerimonialistas.AtvLogin.LOGIN_PREFS;

/**
 * Created by jeansarlon on 26/09/16.
 */

public class RegPartyFragment extends Fragment {
    View myView;
    EditText et_partyName;
    EditText et_partyBirthPeople;
    EditText et_partydate;
    TextView tv_regPartyTitle;
    String userId;
    FloatingActionButton fab;


    String partyName;
    String partyBirthPeople;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.reg_party_layout, container, false);

        et_partyName = (EditText) myView.findViewById(R.id.party_name);
        et_partyBirthPeople = (EditText) myView.findViewById(R.id.party_birth_people);
        et_partydate = (EditText) myView.findViewById(R.id.party_date);
        tv_regPartyTitle = (TextView) myView.findViewById(R.id.tv_regPartyTitle);

        et_partydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Urubu", Toast.LENGTH_SHORT).show();
            }
        });

        Typeface tf = Typeface.createFromAsset(myView.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        tv_regPartyTitle.setTypeface(tf);


        SharedPreferences settings = getActivity().getSharedPreferences(LOGIN_PREFS,0);
        userId = settings.getString("user_id", "nada");


        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partyName = et_partyName.getText().toString();
                partyBirthPeople = et_partyBirthPeople.getText().toString();
                requisicaoVolleyC();
            }
        });

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


        // monta o objeto Json que será repassado ao WS
        JSONObject params = new JSONObject();
        try {
            params.put("id",userId);
            params.put("name", partyName);
            params.put("birthday_people", partyBirthPeople);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url ="http://192.168.58.1:80/cerimonialistas/public/register_parties";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status;

                        try {
                            if (response.getBoolean("success") == true){
                                status = "Cadastrado com sucesso!";
                            } else {
                                status = "Erro ao cadastrar!";
                            }
                            Snackbar.make(getView(), status, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        mRequestQueue.add(request);
    }


}
