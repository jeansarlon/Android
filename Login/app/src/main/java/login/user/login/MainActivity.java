package login.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static  String NOME_PREF = "preferencia";
    Button bt_registrar;
    Button bt_limpar;
    Button bt_entrar;

    EditText et_login;
    EditText et_senha;

    TextView tv_cont_sucesso;
    TextView tv_cont_erro;

    String valor_et_login;
    String valor_et_senha;

    ImageView iv_imagem;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_registrar = (Button) findViewById(R.id.bt_registrar);
        bt_limpar = (Button) findViewById(R.id.bt_limpar);
        bt_entrar = (Button) findViewById(R.id.bt_entrar);

        et_login = (EditText) findViewById(R.id.et_login);
        et_senha = (EditText) findViewById(R.id.et_senha);

        tv_cont_sucesso = (TextView) findViewById(R.id.tv_cont_sucesso);
        tv_cont_erro = (TextView) findViewById(R.id.tv_cont_erro);

        iv_imagem = (ImageView) findViewById(R.id.iv_imagem);

        bt_entrar.setEnabled(false);
        pref = getSharedPreferences(NOME_PREF, 0);
        editor = pref.edit();
        iv_imagem.setImageResource(R.drawable.capa);


        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor_et_login = et_login.getText().toString();
                valor_et_senha = et_senha.getText().toString();

                if (!valor_et_login.isEmpty() && !valor_et_senha.isEmpty() ){

                    editor.putString("login", valor_et_login);
                    editor.putString("senha", valor_et_senha);
                    editor.commit();

                    Intent intencao = new Intent(MainActivity.this, Tela2.class);
                    intencao.putExtra("acao", "registro");
                    startActivity(intencao);
                } else {
                    Toast.makeText(MainActivity.this, "Os campos devem estar preenchidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                onResume();
            }
        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor_et_login = et_login.getText().toString();
                valor_et_senha = et_senha.getText().toString();
                Intent intencao = new Intent(MainActivity.this, Tela2.class);
                String login = pref.getString("login","");
                String senha = pref.getString("senha","");

                if (login.equals(valor_et_login) && senha.equals(valor_et_senha) ) {
                    intencao.putExtra("acao", "login_sucesso");
                }
                else{
                    intencao.putExtra("acao", "login_erro");
                }
                startActivity(intencao);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        pref = getSharedPreferences(NOME_PREF, 0);

        if (!pref.getString("login","").isEmpty()){
            bt_entrar.setEnabled(true);
            bt_registrar.setEnabled(false);
        }else {
            bt_entrar.setEnabled(false);
            bt_registrar.setEnabled(true);
        }

        Integer sucesso = pref.getInt("cont_sucesso", 0);
        Integer erro = pref.getInt("cont_erro", 0);
        tv_cont_sucesso.setText("Sucesso: "+ sucesso.toString());
        tv_cont_erro.setText("Erro: " + erro.toString());
    }
}
