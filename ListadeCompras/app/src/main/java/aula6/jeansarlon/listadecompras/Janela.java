package aula6.jeansarlon.listadecompras;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Janela extends AppCompatActivity{
    ListView lv;
    ArrayList<Produto> produtos = new ArrayList<Produto>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela);


        produtos.add(new Produto("Arroz", 5.00));
        produtos.add(new Produto("Feijão", 5.00));
        produtos.add(new Produto("Batata", 3.00));
        produtos.add(new Produto("Brocolis", 4.99));
        produtos.add(new Produto("Milho", 7.33));
        produtos.add(new Produto("Tomate", 4.20));
        produtos.add(new Produto("Cebola", 3.76));
        produtos.add(new Produto("Café", 5.00));
        produtos.add(new Produto("Açucar", 3.00));
        produtos.add(new Produto("Óleo", 2.00));
        produtos.add(new Produto("Pão", 4.70));
        produtos.add(new Produto("Água", 3.00));
        produtos.add(new Produto("Suco", 1.00));
        produtos.add(new Produto("Coca-Cola", 5.50));
        produtos.add(new Produto("Pinga", 10.00));
        produtos.add(new Produto("Biscoitos", 5.00));
        produtos.add(new Produto("Molho de tomate", 3.00));
        produtos.add(new Produto("Temperos", 5.00));
        produtos.add(new Produto("Shampoo", 15.00));
        produtos.add(new Produto("Sabonete", 4.20));

        ArrayAdapter<Produto> ad = new Adaptador(this,R.layout.item,produtos);
        lv = (ListView)findViewById(R.id.lista);
        lv.setAdapter(ad);
    }
    public void calcTotal(View v){
        double total = 0;
        for (Produto prod: produtos){
            if (prod.isChecked() == true){
                total += prod.getPreco();
            }
        }
        Toast.makeText(getApplicationContext(
        )," Total: "+ String.format("%.2f",total), Toast.LENGTH_SHORT).show();
    }
}
