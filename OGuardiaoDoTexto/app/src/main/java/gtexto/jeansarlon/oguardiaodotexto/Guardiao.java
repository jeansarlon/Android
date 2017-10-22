package gtexto.jeansarlon.oguardiaodotexto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Guardiao extends AppCompatActivity {
    EditText etEntradaDeTexto;
    TextView tvTextoQueMuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardiao);

        etEntradaDeTexto = (EditText) findViewById(R.id.etEntradaDeTexto);
        tvTextoQueMuda = (TextView) findViewById(R.id.tvTextoQueMuda);

        etEntradaDeTexto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                tvTextoQueMuda.setText(s);
                if (s.toString().contains("trocar")){
                    Intent intencao = new Intent(Guardiao.this,Janela2.class);
                    intencao.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Outras coisas bacnaas aqui
                    intencao.putExtra("TextoNaCaixa",s.toString());
                    startActivity(intencao);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    public void onClickMudaTexto(View v){
        String textoEntrada = etEntradaDeTexto.getText().toString();
        if (!textoEntrada.isEmpty()){
            tvTextoQueMuda.setText(textoEntrada);
        }else{
            etEntradaDeTexto.setError("Por favor digite algo aqui!");
        }
    }
}
