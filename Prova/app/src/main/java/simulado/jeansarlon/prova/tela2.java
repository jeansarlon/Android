package simulado.jeansarlon.prova;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class tela2 extends AppCompatActivity {
    private static final String NOME_PREFS = "preferencias";
    TextView tvNome;
    TextView tvEmail;
    ImageView ivTime;
    Button btSalvar;
    Button btFechar;
    String nome;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        ivTime = (ImageView) findViewById(R.id.ivFoto);
        btSalvar = (Button) findViewById(R.id.btSalvar);
        btFechar = (Button) findViewById(R.id.btFechar);

        final Bundle extras = getIntent().getExtras();

        if (extras != null){
              nome = extras.getString("nome", "Não veio");
              email = extras.getString("email", "Não veio");
            int time = extras.getInt("time", 0);
            tvNome.setText(nome);
            tvEmail.setText(email);


            if (time == 1) {
                ivTime.setImageResource(R.drawable.gremio);
            }else{
                ivTime.setImageResource(R.drawable.inter);
            }
        }
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences(NOME_PREFS, 0);
                SharedPreferences.Editor editor = (prefs).edit();
                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.commit();
                Toast.makeText(tela2.this, "Salvo!!", Toast.LENGTH_SHORT).show();
            }
        });

        btFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
