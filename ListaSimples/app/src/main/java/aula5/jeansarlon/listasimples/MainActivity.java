package aula5.jeansarlon.listasimples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        s = new
                String[]{
                "Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto",
                "Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto",
                "Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto",
                "Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto"
        };

        ListView lv = (ListView) findViewById(R.id.lvLista);
        lv.setAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                Toast.makeText(getApplicationContext(
                ),"Você clicou em: " +s[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}
