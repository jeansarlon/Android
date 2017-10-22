package com.example.jean.cerimonialistas;

import android.content.Intent;
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

public class AtvCadastro extends AppCompatActivity {
    EditText etNome;
    EditText etEmail;
    EditText etSenha;
    TextView tvResultado;
    Button btCadastrar;

    String nome;
    String email;
    String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        etNome = (EditText) findViewById(R.id.et_nome);
        etEmail = (EditText) findViewById(R.id.et_email);
        etSenha = (EditText) findViewById(R.id.et_senha);
        tvResultado = (TextView) findViewById(R.id.tv_resultado);
        btCadastrar = (Button) findViewById(R.id.bt_cadastrar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = etNome.getText().toString();
                email = etEmail.getText().toString();
                senha = etSenha.getText().toString();
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Todos os campos são Obrigatórios",Toast.LENGTH_LONG).show();

                } else {
                    requisicaoVolleyC();
                }
            }
        });
    }




    private void requisicaoVolleyC(){
        RequestQueue mRequestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();


        // monta o objeto Json que será repassado ao WS
        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("username", nome);
            params.put("password", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url ="http://192.168.58.1:80/cerimonialistas/public/register";

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
                            tvResultado.setText("Cadastro realizado com sucesso!");


                        } else{
                            tvResultado.setText("Cadastro não realizado, deu alguma merda");
                        }
                        tvResultado.postDelayed(new Runnable() {
                            public void run() {
                                tvResultado.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(getApplicationContext(), AtvLogin.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        }, 5000);
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
