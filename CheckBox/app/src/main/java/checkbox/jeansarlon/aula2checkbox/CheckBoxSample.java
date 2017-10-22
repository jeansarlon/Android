package checkbox.jeansarlon.aula2checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class CheckBoxSample extends AppCompatActivity {
    CheckBox cb_amargo, cb_branco, cb_aoleite;
    String saida = "Eu gosto de chocolate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        cb_amargo = (CheckBox) findViewById(R.id.cb_amargo);
        cb_branco = (CheckBox) findViewById(R.id.cb_branco);
        cb_aoleite = (CheckBox) findViewById(R.id.cb_aoleite);
    }

    public void verifica_cb(View v) {
        if (cb_amargo.isChecked()) {
            saida += " Amargo ";
        }
        if (cb_branco.isChecked()) {
            saida += " Branco ";
        }
        if (cb_aoleite.isChecked()) {
            saida += " Ao leite ";
        }
        if (!cb_aoleite.isChecked() && cb_branco.isChecked() && cb_amargo.isChecked()) {
            saida += " Nao Gosta ";
        }
        Toast.makeText(CheckBoxSample.this, saida, Toast.LENGTH_SHORT).show();

    }

    public void click_cb(View v) {
        CheckBox cbClicado = (CheckBox) v;

        if (cbClicado.isChecked()) {
            switch (cbClicado.getId()) {
                case R.id.cb_amargo:
                    saida += " Amargo ";
                    break;
                case R.id.cb_branco:
                    saida += " Branco ";
                    break;
                case R.id.cb_aoleite:
                    saida += " Ao leite ";
                    break;
            }
        } else{
            switch (cbClicado.getId()) {
                case R.id.cb_amargo:
                    saida = saida.replace(" Amargo ", "");
                    break;
                case R.id.cb_branco:
                    saida = saida.replace(" Branco ", "");
                    break;
                case R.id.cb_aoleite:
                    saida = saida.replace(" Ao leite ", "");
                    break;
            }
        }
        Toast.makeText(CheckBoxSample.this, saida, Toast.LENGTH_SHORT).show();
    }
}
