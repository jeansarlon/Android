package simulado.jeansarlon.prova;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etNome;
    RadioGroup radioGroup;
    RadioButton rbGremio;
    RadioButton rbInter;
    Button btAvancar;
    int time;

    String saveNome = "";
    String saveEmail = "";

    String toSaveName = "";
    String toSaveEmail= "";
    private static final String NOME_PREFS = "preferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.tvEmail);
        radioGroup = (RadioGroup) findViewById(R.id.rdGroup);
        btAvancar = (Button) findViewById(R.id.btAvancar);

        SharedPreferences settings = getSharedPreferences(NOME_PREFS, 0);
        saveNome = settings.getString("nome", "Nome");
        saveEmail = settings.getString("email", "Email");
        etNome.setText(saveNome);
        etEmail.setText(saveEmail);

        btAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rdGremio:
                        time = 1;
                        break;
                    case R.id.rdInter:
                        time = 2;
                        break;
                }
                toSaveName = etNome.getText().toString();
                toSaveEmail = etEmail.getText().toString();

                Intent intencao = new Intent(MainActivity.this, tela2.class);
                intencao.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intencao.putExtra("nome", toSaveName);
                intencao.putExtra("email", toSaveEmail);
                intencao.putExtra("time", time);
                startActivity(intencao);
            }
        });
    }
}