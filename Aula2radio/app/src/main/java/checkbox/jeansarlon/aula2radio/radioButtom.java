package checkbox.jeansarlon.aula2radio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class radioButtom extends AppCompatActivity {
    RadioGroup rgSexo;
    String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buttom);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
    }
    public void verificaRG(View v){

        switch (rgSexo.getCheckedRadioButtonId()){
            case R.id.rdFemale:
                sexo = "Feminino";
                break;
            case R.id.rdMale:
                sexo = "Masculino";
                break;
        }
        Toast.makeText(radioButtom.this, "Você selecionou: "+sexo, Toast.LENGTH_SHORT).show();
    }
}