package aula6.jeansarlon.listadecompras;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adaptador  extends ArrayAdapter<Produto> {
    private LayoutInflater inflater;
    private int resourceId;

    public Adaptador(Context context, int resource, List<Produto> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    public  View getView(int position, View converView, ViewGroup parent){
        final Produto prod = getItem(position);
        converView = inflater.inflate(resourceId, parent, false);
        CheckBox cb = (CheckBox) converView.findViewById(R.id.cbc);
        TextView tvNome = (TextView) converView.findViewById(R.id.tvNome);
        TextView tvPreco = (TextView) converView.findViewById(R.id.tvPreco);
        tvNome.setText(prod.getNome());
        tvPreco.setText(" (R$" + String.format("%.2f", prod.getPreco()) + ")");
        if (prod.isChecked()){
            cb.setChecked(true);
        }
        cb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                CheckBox cb = (CheckBox) v.findViewById(R.id.cbc);
                if (!cb.isChecked()){
                    prod.setIsChecked(false);
                    Toast.makeText(getContext(
                    ), prod.getNome() + " Removido à lista", Toast.LENGTH_SHORT).show();

                }else {
                    prod.setIsChecked(true);
                    Toast.makeText(getContext(
                    ), prod.getNome() + " Adicionado à lista", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return converView;
    }
}
