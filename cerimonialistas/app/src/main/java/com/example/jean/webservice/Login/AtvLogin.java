package com.example.jean.webservice.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jean.webservice.AtvCadastro;
import com.example.jean.webservice.Navigation.AtvNavegacao;
import com.example.jean.webservice.Helpers.Base;
import com.example.jean.webservice.Helpers.Http;
import com.example.jean.webservice.R;
import com.example.jean.webservice.User;

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
    JSONObject res_user;
    String res_token;
    User user;

    String res_user_name;
    String res_user_email;
    String res_user_id;
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
        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("password", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Http http = new Http(getApplicationContext());
        http.post(Base.Routes.login, params, new Http.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {

                try {
                    res_token = result.getString("token");
                    res_user = result.getJSONObject("user");

                    res_user_name = res_user.getString("name");
                    res_user_email = res_user.getString("email");
                    res_user_id = res_user.getString("id");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SharedPreferences prefs = (SharedPreferences) getSharedPreferences(LOGIN_PREFS, 0);
                SharedPreferences.Editor editor = (prefs).edit();
                editor.putString("user_id", res_user_id);
                editor.putString("user_name", res_user_name);
                editor.putString("user_token", res_token);
                editor.putString("user_email", res_user_email);
                editor.commit();


                Intent intent = new Intent(AtvLogin.this, AtvNavegacao.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


//        RequestQueue mRequestQueue;
//
//        // Instantiate the cache
//        final Cache[][] cache = {{new DiskBasedCache(getCacheDir(), 1024 * 1024)}}; // 1MB cap
//
//        // Set up the network to use HttpURLConnection as the HTTP client.
//        Network network = new BasicNetwork(new HurlStack());
//
//        // Instantiate the RequestQueue with the cache and network.
//        mRequestQueue = new RequestQueue(cache[0][0], network);
//
//        // Start the queue
//        mRequestQueue.start();
//
//
//        JSONObject params = new JSONObject();
//        try {
//            params.put("email", email);
//            params.put("password", senha);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        final String url = "http://ize.blee.com.br/api/login2";
//
//        JsonObjectRequest request = new JsonObjectRequest(
//                Request.Method.POST, url, params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        System.out.println(response);
//                        try {
//                            res_token = response.getString("token");
//                            res_user = response.getJSONObject("user");
//
//                            res_user_name = res_user.getString("name");
//                            res_user_email = res_user.getString("email");
//                            res_user_id = res_user.getString("id");
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        SharedPreferences prefs = (SharedPreferences) getSharedPreferences(LOGIN_PREFS, 0);
//                        SharedPreferences.Editor editor = (prefs).edit();
//                        editor.putString("user_id", res_user_id);
//                        editor.putString("user_name", res_user_name);
//                        editor.putString("user_token", res_token);
//                        editor.putString("user_email", res_user_email);
//                        editor.commit();
//
//
//                        Intent intent = new Intent(AtvLogin.this, AtvNavegacao.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println(error);
//                    }
//                }
//        );
//        int socketTimeout = 30000;//30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        request.setRetryPolicy(policy);
//        mRequestQueue.add(request);
    }
    // ----------------------- || ----------------------- //
}