package aula4.jeansarlon.validacpf;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {
    EditText inputCPF;
    TextView teste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCPF = (EditText) findViewById(R.id.et_inputCPF);
        teste = (TextView) findViewById(R.id.tv_teste);

        inputCPF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Boolean v = isCPF( s.toString());
                teste.setText(v.toString());
                if (s.toString().isEmpty()){
                    teste.setText("Digite o CPF");
                    teste.setTextColor(Color.rgb(000, 000, 000));
                }else if (v.toString() == "true") {
                    teste.setText("CPF válido");
                    teste.setTextColor(Color.rgb(42, 192, 42));
                }else{
                    teste.setText("CPF inválido");
                    teste.setTextColor(Color.rgb(215, 44, 44));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static boolean isCPF(String CPF){
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);
        char dig10, dig11;
        int sm, i, r, num = 0, peso;
         try {
             sm = 0;
             peso = 10;
             for (i=0; i<9; i++) {
                 num = (int)(CPF.charAt(i) - 48);
                 sm = sm + (num * peso);
                 peso = peso - 1;
             }
             r = 11 - (sm % 11);
             if ((r == 10) || (r == 11))
                 dig10 = '0';
             else dig10 = (char)(r + 48);
             sm = 0;
             peso = 11;
             for(i=0; i<10; i++) {
                 num = (int)(CPF.charAt(i) - 48);
                 sm = sm + (num * peso);
                 peso = peso - 1;
             }

             r = 11 - (sm % 11);
             if ((r == 10) || (r == 11))
                 dig11 = '0';
             else
                 dig11 = (char)(r + 48);
             if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
             else
                 return(false);
         } catch (InputMismatchException erro){
             return(false);
         }
    }
}
