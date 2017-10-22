package niverdaliri.jeansarlon.niverdaliri;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeansarlon on 25/09/16.
 */

public class Adaptador  extends ArrayAdapter<Pessoa> {
    private LayoutInflater inflater;
    private int resourceId;
    private ArrayList<Pessoa> dadosOriginais;
    private ArrayList<Pessoa> dadosFiltrados;
    private Button btEquals;

    public Adaptador(Context context, int resource, List<Pessoa> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
        this.dadosFiltrados = (ArrayList<Pessoa>) objects;
        this.dadosOriginais = (ArrayList<Pessoa>) objects;
    }


    public View getView(int position, View converView, ViewGroup parent){
        final Pessoa pess = getItem(position);

        converView = inflater.inflate(resourceId, parent, false);
        final CheckBox cb = (CheckBox) converView.findViewById(R.id.cbc);
        TextView tvNome = (TextView) converView.findViewById(R.id.tvNome);
        TextView tvMesa = (TextView) converView.findViewById(R.id.tv_mesa);
        tvNome.setText(pess.getNome());
        tvMesa.setText(" Mesa: " + pess.getMesa().toString());

        if (pess.isChecked()){
            cb.setChecked(true);
        }


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!cb.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            getContext());
                    builder.setCancelable(false);
                    builder.setTitle("Tem certeza que deseja desconfirmar este convidado?");

                    builder.setPositiveButton("SIM",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    cb.setChecked(false);
                                    pess.setChecked(false);
                                    Toast.makeText(getContext(
                    ), pess.getNome() + " Foi desconfirmado(a)", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.setNegativeButton("NÃO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    cb.setChecked(true);
                                    pess.setChecked(true);

                                }
                            });
                    builder.create().show();

                } else {
                    pess.setChecked(true);
                    Toast.makeText(getContext(
                    ), pess.getNome() + " Foi confirmado(a)", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return converView;
    }

    public int getCount(){return dadosFiltrados.size();}
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
                        if (pessoa.getNome().toLowerCase().contains(busca.toLowerCase())){
                            filterResultsData.add(pessoa);
                        } else{
                            if(pessoa.getMesa().contains( busca)){
                                filterResultsData.add(pessoa);
                            }
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