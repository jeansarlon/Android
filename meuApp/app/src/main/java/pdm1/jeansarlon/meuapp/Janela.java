package pdm1.jeansarlon.meuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Janela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela);
    }

    public void troca(View v){
        TextView tv  = (TextView) findViewById(R.id.tvTexto);

        EditText randmim = (EditText)findViewById(R.id.randMenor);
        int min = Integer.parseInt(randmim.getText().toString());

        EditText randmax = (EditText)findViewById(R.id.randMaior);
        int max = Integer.parseInt(randmax.getText().toString());
        radomNumber N = new radomNumber(min,max);
        tv.setText(N.getNumber());
    }
}
