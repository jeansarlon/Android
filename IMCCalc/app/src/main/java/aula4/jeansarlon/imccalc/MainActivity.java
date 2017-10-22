package aula4.jeansarlon.imccalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgSexo;
    TextView tvPeso;
    TextView tvAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgSexo = (RadioGroup) findViewById(R.id.rdGroup);
        tvPeso = (TextView) findViewById(R.id.etPeso);
        tvAltura = (TextView) findViewById(R.id.etAltura);
    }

    public void onClickBtCalc(View v){
        float peso   = Float.parseFloat(tvPeso.getText().toString());
        float altura = Float.parseFloat(tvAltura.getText().toString());

        Intent intencao = new Intent(MainActivity.this,Janela2.class);
        intencao.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Converte float para duas casas decimais
        String pi = String.format("%.2f",pesoIdeal( altura, verificaSexo() ));

        // Cria os Extra e coloca no Intent | inicia a nova view
        intencao.putExtra("IMC", String.format(  "%.2f", calculaIMC(peso, altura)) );
        intencao.putExtra("PI", pi);
        startActivity(intencao);
    }
    // Função calcula IMC
    public Float calculaIMC(float massa,float altura){
        float imc = (float) (massa / Math.pow(altura,2));
        return imc;
    }

    // Função Calcula Peso Ideal
    public float pesoIdeal(float h, String sexo){
        h= h*100;
        int k;
        if ("M" == sexo){
            k = 4;
        }else{
            k = 2;
        }
        float p = (h-100) - ((h-150)/k);
        return p;
    }

    // Função veriifca Sexo
    public String verificaSexo() {
        switch (rgSexo.getCheckedRadioButtonId()) {
            case R.id.rbFemale:
                return "F";
            case R.id.rbMale:
                return "M";
        }
        return "NULL";
    }
}
