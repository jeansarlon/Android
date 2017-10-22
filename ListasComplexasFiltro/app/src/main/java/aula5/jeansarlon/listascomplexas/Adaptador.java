package aula5.jeansarlon.listascomplexas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Adaptador  extends ArrayAdapter<Pessoa> {
    private LayoutInflater inflater;
    private int resourceId;
    private ArrayList<Pessoa> dadosOriginais;
    private ArrayList<Pessoa> dadosFiltrados;

    public Adaptador(Context context, int resource, List<Pessoa> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
        this.dadosFiltrados = (ArrayList<Pessoa>) objects;
        this.dadosOriginais = (ArrayList<Pessoa>) objects;
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

    public int getCount(){
        return dadosFiltrados.size();
    }
    public Pessoa getItem(int position){
        return dadosFiltrados.get(position);
    }
    public long getItemId(int position){
        return  position;
    }
    public Filter getFilter(){
        return new Filter(){

            @Override
            protected FilterResults performFiltering(CharSequence cs) {
                FilterResults results = new FilterResults();
                String busca = cs.toString();
                // se a busca foi vazia
                if (busca == null || busca.length() == 0){
                    results.values = dadosOriginais;
                    results.count = dadosOriginais.size();
                }else{
                    ArrayList<Pessoa> filterResultsData = new ArrayList<Pessoa>();

                    for (Pessoa pessoa : dadosOriginais){
                        if (pessoa.nome.toLowerCase().contains(busca.toLowerCase())){
                            filterResultsData.add(pessoa);
                        }
                    }
                    results.values = filterResultsData;
                    results.count = filterResultsData.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dadosFiltrados = (ArrayList<Pessoa>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
