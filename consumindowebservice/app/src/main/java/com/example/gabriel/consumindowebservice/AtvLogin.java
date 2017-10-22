package com.example.gabriel.consumindowebservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AtvLogin extends AppCompatActivity {
TextView tvResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_login);

        tvResultado = (TextView)findViewById(R.id.tvResultado);
        Button btnLogin = (Button)findViewById(R.id.button);
        Button btnLogin2 = (Button) findViewById(R.id.btn_loginVolley);

        btnLogin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.login)).getText().toString();
                String senha = ((EditText) findViewById(R.id.senha)).getText().toString();
                // monta o objeto Json que será repassado ao WS
                JSONObject params = new JSONObject();
                try {
                    params.put("email", email);
                    params.put("senha", senha);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Assincrona().execute(params);
            }
        });
        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requisicaoVolley();
            }
        });
    }

    private static String getResponseText(InputStream inStream){
        //very nice trick from
        //http://weblog.java.net/blog/pat/archive/2004/10/stupid_scanner._1.html
        return new Scanner(inStream).useDelimiter("//A").next();
    }

    public class Assincrona extends AsyncTask<JSONObject, String,String>{
        @Override
        protected String doInBackground(JSONObject... param) {
            JSONObject params = param[0];

            HttpURLConnection urlConnection = null;

            try {
                //cria conexao
                URL urlToRequest = new URL("http://192.168.58.1:80/PDM2/WS_Aula_PDM/v1/login");
                urlConnection = (HttpURLConnection) urlToRequest.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setConnectTimeout(5000);
                urlConnection.setReadTimeout(5000);
                urlConnection.setRequestMethod("POST");

                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(params.toString());
                writer.flush();
                writer.close();

                //cria objeto de resposta json

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                JSONObject resposta = new JSONObject(getResponseText(in));

                urlConnection.disconnect();
                return  resposta.toString();

            } catch (Exception e) {
                Log.d("DEBUG", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvResultado.setText(s);
        }
    }
    private void requisicaoVolley(){
        RequestQueue mRequestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();





        String email = ((EditText) findViewById(R.id.login)).getText().toString();
        String senha = ((EditText) findViewById(R.id.senha)).getText().toString();
        // monta o objeto Json que será repassado ao WS
        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("senha", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url ="http://192.168.58.1:80/PDM2/WS_Aula_PDM/v1/login)";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tvResultado.setText(response.toString());
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
