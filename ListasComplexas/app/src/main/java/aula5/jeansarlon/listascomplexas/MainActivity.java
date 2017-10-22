package aula5.jeansarlon.listascomplexas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoas.add(new  Pessoa("João", "joao@server.com"));
        pessoas.add(new  Pessoa("Jose", "jose@server.com"));
        ArrayAdapter<Pessoa> ad = new Adaptador(this,R.layout.item_da_lista,pessoas);
        ListView lv = (ListView)findViewById(R.id.lista);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(
                ), "Você clicou em: " + pessoas.get(position).nome, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
