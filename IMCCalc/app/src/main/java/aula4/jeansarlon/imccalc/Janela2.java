package aula4.jeansarlon.imccalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Janela2 extends AppCompatActivity {
    TextView tvSaida;
    TextView tvPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela2);

        tvSaida = (TextView) findViewById(R.id.tvSaida);
        tvPeso = (TextView) findViewById(R.id.tvPeso);
        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String IMC = extras.getString("IMC","Não veio");
            String PI = extras.getString("PI","Não veio");
            tvSaida.setText(IMC);
            tvPeso.setText(PI);
        }
    }
}