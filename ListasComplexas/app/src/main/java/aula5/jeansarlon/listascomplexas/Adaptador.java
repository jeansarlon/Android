package aula5.jeansarlon.listascomplexas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class Adaptador  extends ArrayAdapter<Pessoa> {
    private LayoutInflater inflater;
    private int resourceId;

    public Adaptador(Context context, int resource, List<Pessoa> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    public  View getView(int position, View converView, ViewGroup parent){
        Pessoa person = getItem(position);
        converView = inflater.inflate(resourceId, parent, false);
        TextView nome = (TextView)converView.findViewById(R.id.nome);
        TextView email = (TextView)converView.findViewById(R.id.email);
        nome.setText(person.nome);
        email.setText(person.email);
        return converView;
    }
}
