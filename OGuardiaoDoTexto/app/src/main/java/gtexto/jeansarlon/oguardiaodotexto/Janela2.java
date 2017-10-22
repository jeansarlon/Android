package gtexto.jeansarlon.oguardiaodotexto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Janela2 extends AppCompatActivity {
    TextView tvSaida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela2);

        tvSaida = (TextView) findViewById(R.id.tvSaida);
        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String mensagem = extras.getString("TextoNaCaixa","Não Achei Nada");
            tvSaida.setText(mensagem);
        }
    }

}
