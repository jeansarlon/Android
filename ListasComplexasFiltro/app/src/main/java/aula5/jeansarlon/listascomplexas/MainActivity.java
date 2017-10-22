package aula5.jeansarlon.listascomplexas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
    EditText etBusca;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoas.add(new  Pessoa("João", "joao@server.com"));
        pessoas.add(new  Pessoa("Jose", "jose@server.com"));
        final ArrayAdapter<Pessoa> ad = new Adaptador(this,R.layout.item_da_lista,pessoas);
        lv = (ListView)findViewById(R.id.lista);
        lv.setAdapter(ad);

        etBusca = (EditText) findViewById(R.id.etBusca);
        etBusca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence CharSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ad.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
