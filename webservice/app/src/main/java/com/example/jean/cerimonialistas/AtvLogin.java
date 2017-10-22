package com.example.jean.cerimonialistas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.io.InputStream;
import java.util.Scanner;

public class AtvLogin extends AppCompatActivity {
    public final static String LOGIN_PREFS = "login_session";
    TextView tvResultado;
    String email;
    String senha;
    String isLogged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_login);

        SharedPreferences settings = (SharedPreferences) getSharedPreferences(LOGIN_PREFS,0);

        // -------------------------------------------------- //
        // Controle de Sessão

        isLogged = settings.getString("user_id", "nada");
        if (isLogged != "nada"){
            Intent intent = new Intent(AtvLogin.this, AtvNavegacao.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        // ----------------------- || ----------------------- //

        tvResultado = (TextView)findViewById(R.id.tvResultado);
        Button btCadastro = (Button) findViewById(R.id.bt_cadastro);
        Button btnLogin2 = (Button) findViewById(R.id.btn_loginVolley);


        // -------------------------------------------------- //
        // On Click do botao Login

        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResultado.setVisibility(View.VISIBLE);
                 email = ((EditText) findViewById(R.id.login)).getText().toString();
                 senha = ((EditText) findViewById(R.id.senha)).getText().toString();

                // monta o objeto Json que será repassado ao WS


                if (email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Preencha os campos login e senha!",Toast.LENGTH_LONG).show();

                }else {
                    requisicaoVolley();
                }
            }
        });
        // ----------------------- || ----------------------- //


        // -------------------------------------------------- //
        // On Click do botao Cadastro

        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AtvLogin.this, AtvCadastro.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        // ----------------------- || ----------------------- //
    }

    private static String getResponseText(InputStream inStream){
        //very nice trick from
        //http://weblog.java.net/blog/pat/archive/2004/10/stupid_scanner._1.html
        return new Scanner(inStream).useDelimiter("//A").next();
    }

    // -------------------------------------------------- //
    // Comunicando com a API
    private void requisicaoVolley(){
        RequestQueue mRequestQueue;


        final String[] user_id = new String[1];
        final String[] user_name = new String[1];
        final String[] user_token = new String[1];
        final String[] user_email = new String[1];
        final String[] user_username = new String[1];

        // Instantiate the cache
        final Cache[][] cache = {{new DiskBasedCache(getCacheDir(), 1024 * 1024)}}; // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache[0][0], network);

        // Start the queue
        mRequestQueue.start();


        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("password", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url = "http://192.168.58.1:80/cerimonialistas/public/login";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Boolean a = null;
                        try {
                            a = response.getBoolean("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (a) {
                            try {
                                JSONObject data = response.getJSONObject("message");
                                user_id[0] = data.get("id").toString();
                                user_name[0] = data.get("name").toString();
                                user_token[0] = response.get("api_token").toString();
                                user_email[0] = data.get("email").toString();
                                ;
                                user_username[0] = data.get("username").toString();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            SharedPreferences prefs = (SharedPreferences) getSharedPreferences(LOGIN_PREFS, 0);
                            SharedPreferences.Editor editor = (prefs).edit();
                            editor.putString("user_id", user_id[0]);
                            editor.putString("user_name", user_name[0]);
                            editor.putString("user_token", user_token[0]);
                            editor.putString("user_email", user_email[0]);
                            editor.putString("user_username", user_username[0]);
                            editor.commit();
                            Intent intent = new Intent(AtvLogin.this, AtvNavegacao.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            tvResultado.setText("Email ou senha incorretos");
                            tvResultado.postDelayed(new Runnable() {
                                public void run() {
                                    tvResultado.setVisibility(View.INVISIBLE);
                                }
                            }, 3000);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                }
        );
        mRequestQueue.add(request);
    }
    // ----------------------- || ----------------------- //
}