package aula8.jeansarlon.persistenciadedados;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String NOME_PREFS = "preferencias";
    EditText et;
    Button btnSalvar;
    Button btnFechar;
    String textoParaSalvar = "Digite algo aqui";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et        = (EditText) findViewById(R.id.entrada1);
        btnSalvar = (Button) findViewById(R.id.botao_salvar);
        btnFechar = (Button) findViewById(R.id.botao_encerrar);

        //retaurar dados

        SharedPreferences settings = (SharedPreferences) getSharedPreferences(NOME_PREFS,0);
        textoParaSalvar = settings.getString("textoentrada1", "Digite algo aqui");

        et.setText(textoParaSalvar);
    }
    public void onClickSalvar(View v){
        textoParaSalvar = et.getText().toString();
        SharedPreferences prefs = (SharedPreferences) getSharedPreferences(NOME_PREFS,0);
        SharedPreferences.Editor editor = (prefs).edit();
        editor.putString("textoentrada1",textoParaSalvar);
        editor.commit();
    }
    public void onClickSair(View v){
        finish();;
    }
}
