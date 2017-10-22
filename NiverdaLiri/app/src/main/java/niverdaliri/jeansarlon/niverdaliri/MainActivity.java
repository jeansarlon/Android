package niverdaliri.jeansarlon.niverdaliri;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Pessoa> convidados = new ArrayList<Pessoa>();
    EditText etBusca;
    Button btDev;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btDev = (Button) findViewById(R.id.bt_dev);
        //img = (ImageView) findViewById(R.id.imageView);
//        img.setImageDrawable(getResources().getDrawable(R.drawable.facebook));


        btDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getApplicationContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });


        convidados.add(new Pessoa("Darcy Anibal Serrudo de Vargas", "01"));
        convidados.add(new Pessoa("Roselaine Martins de Vargas", "01"));
        convidados.add(new Pessoa("Liriele Martins de Vargas", "01"));
        convidados.add(new Pessoa("Helena Luz Martins", "01"));
        convidados.add(new Pessoa("Angelica Gimenez", "01"));
        convidados.add(new Pessoa("Douglas Martins de Vargas", "01"));

        convidados.add(new Pessoa("Jean Sarlon Cabral da Rocha", "02"));
        convidados.add(new Pessoa("Juliane Martins de Vargas", "02"));
        convidados.add(new Pessoa("Ernesto Junior Ribarr", "02"));
        convidados.add(new Pessoa("Felipe Fraga", "02"));
        convidados.add(new Pessoa("Renata Cabral", "02"));
        convidados.add(new Pessoa("Rafael Ferreira", "02"));
        convidados.add(new Pessoa("Natalia Kerber", "02"));

        convidados.add(new Pessoa("Juliane Guimaraes", "03"));
        convidados.add(new Pessoa("Cristian Cabral", "03"));
        convidados.add(new Pessoa("Tassia Radi", "03"));
        convidados.add(new Pessoa("Julio Almeida", "03"));
        convidados.add(new Pessoa("Angela Cabral", "03"));
        convidados.add(new Pessoa("Gabriel Guimaraes", "03"));
        convidados.add(new Pessoa("Igor Almeida", "03"));

        convidados.add(new Pessoa("Joao Honorio Vargas", "04"));
        convidados.add(new Pessoa("Elisangela dos Santos", "04"));
        convidados.add(new Pessoa("Nathan Vargas", "04"));
        convidados.add(new Pessoa("Gabriela Vargas", "04"));
        convidados.add(new Pessoa("Carine Silva Gonçalves", "04"));
        convidados.add(new Pessoa("Jose Carlos Dupke", "04"));
        convidados.add(new Pessoa("Alzira Dupke", "04"));

        convidados.add(new Pessoa("Alfredo Jobim Vargas", "05"));
        convidados.add(new Pessoa("Jeniffer Vargas", "05"));
        convidados.add(new Pessoa("Manuela Matos", "05"));
        convidados.add(new Pessoa("Isabel Vargas", "05"));
        convidados.add(new Pessoa("Ubiratan Serpa", "05"));
        convidados.add(new Pessoa("Amanda Vargas Ramos", "05"));
        convidados.add(new Pessoa("João Vitor Vargas", "05"));

        convidados.add(new Pessoa("Anderson Serpa", "06"));
        convidados.add(new Pessoa("Julia Vargas", "06"));
        convidados.add(new Pessoa("Brayan Serpa", "06"));
        convidados.add(new Pessoa("Eduardo Vargas", "06"));
        convidados.add(new Pessoa("Lucilene Vargas", "06"));
        convidados.add(new Pessoa("Sophia Vargas", "06"));
        convidados.add(new Pessoa("Eduarda Vargas", "06"));

        convidados.add(new Pessoa("Emerson Silva", "07"));
        convidados.add(new Pessoa("Tamirys Vargas", "07"));
        convidados.add(new Pessoa("Ana Paula Vargas", "07"));
        convidados.add(new Pessoa("Ana Maria Vargas", "07"));
        convidados.add(new Pessoa("Leandro Leote Ramos", "07"));
        convidados.add(new Pessoa("Rafael Matos", "07"));

        convidados.add(new Pessoa("Auri Quintana", "08"));
        convidados.add(new Pessoa("Ana Verginia Vargas", "08"));
        convidados.add(new Pessoa("Amanda Quintana", "08"));
        convidados.add(new Pessoa("Andressa Quintana", "08"));
        convidados.add(new Pessoa("Fabio Lopes", "08"));
        convidados.add(new Pessoa("Jose Roberto Dupke", "08"));
        convidados.add(new Pessoa("Marlete Dupke", "08"));

        convidados.add(new Pessoa("Adirlei Vieira", "09"));
        convidados.add(new Pessoa("Rita de Cassia Vieira", "09"));
        convidados.add(new Pessoa("Bruno Expedito Vieira", "09"));
        convidados.add(new Pessoa("Nicoli Vargas Vieira", "09"));
        convidados.add(new Pessoa("Otavio Vargas Vieira", "09"));
        convidados.add(new Pessoa("Caroline Dupke", "09"));
        convidados.add(new Pessoa("Gustavo Souza", "09"));

        convidados.add(new Pessoa("Milton Cesar Raphaelli", "10"));
        convidados.add(new Pessoa("Veridiana Souza", "10"));
        convidados.add(new Pessoa("Sergio Castro", "10"));
        convidados.add(new Pessoa("Angelina Vargas", "10"));
        convidados.add(new Pessoa("Gilberto Vargas", "10"));
        convidados.add(new Pessoa("Joana Gonçalves", "10"));
        convidados.add(new Pessoa("Vinicius Vargas", "10"));

        convidados.add(new Pessoa("Julio Cesar Raphaelli", "11"));
        convidados.add(new Pessoa("Lilian Raphaelli", "11"));
        convidados.add(new Pessoa("Elisa Raphaelli", "11"));
        convidados.add(new Pessoa("Braian Oliveira", "11"));
        convidados.add(new Pessoa("Gabriele Dutra", "11"));
        convidados.add(new Pessoa("Milton Raphaelli", "11"));
        convidados.add(new Pessoa("Antonia Lucia Raphaelli", "11"));

        convidados.add(new Pessoa("Dieferson Martins", "12"));
        convidados.add(new Pessoa("Emilay Centeno", "12"));
        convidados.add(new Pessoa("Giulia Martins", "12"));
        convidados.add(new Pessoa("Robelim Santos", "12"));
        convidados.add(new Pessoa("Neida Martins dos Santos", "12"));
        convidados.add(new Pessoa("Guilherme Martins", "12"));
        convidados.add(new Pessoa("Rodolfo Martins", "12"));

        convidados.add(new Pessoa("Evandro Cesar dos Santos", "13"));
        convidados.add(new Pessoa("Neusa Martins dos Santos", "13"));
        convidados.add(new Pessoa("Bernardo Martins", "13"));
        convidados.add(new Pessoa("Bruno Martins", "13"));
        convidados.add(new Pessoa("Eduardo Martins", "13"));
        convidados.add(new Pessoa("Joaquim Martins", "13"));
        convidados.add(new Pessoa("Karoline Martins", "13"));

        convidados.add(new Pessoa("Adão Fernandes Martins", "14"));
        convidados.add(new Pessoa("Fabiana Dornelles", "14"));
        convidados.add(new Pessoa("Eduardo Faleiro", "14"));
        convidados.add(new Pessoa("Gabriel Faleiro", "14"));
        convidados.add(new Pessoa("Murilo Martins", "14"));
        convidados.add(new Pessoa("Joel Pereira", "14"));
        convidados.add(new Pessoa("Maria Pereira", "14"));

        convidados.add(new Pessoa("Edegar da Silva Martins", "15"));
        convidados.add(new Pessoa("Carla Josiane Faleiro", "15"));
        convidados.add(new Pessoa("Jaluzia Beatriz da Rosa", "15"));
        convidados.add(new Pessoa("Kauan Martins", "15"));
        convidados.add(new Pessoa("Gustavo Martins", "15"));
        convidados.add(new Pessoa("Silvia Cardoso", "15"));
        convidados.add(new Pessoa("Vitor Martins", "15"));

        convidados.add(new Pessoa("Geni Martins", "16"));
        convidados.add(new Pessoa("Leonardo Mendes", "16"));
        convidados.add(new Pessoa("Priscila Martins Mendes", "16"));
        convidados.add(new Pessoa("Nelson do Santos", "16"));
        convidados.add(new Pessoa("Irene dos Santos", "16"));
        convidados.add(new Pessoa("Magnolia Martins dos Santos", "16"));

        convidados.add(new Pessoa("Sergio Costa", "17"));
        convidados.add(new Pessoa("Eni Costa", "17"));
        convidados.add(new Pessoa("Nei Alves Monteiro", "17"));
        convidados.add(new Pessoa("Saionara Costa", "17"));

        convidados.add(new Pessoa("Alvey Miguel Cardoso", "18"));
        convidados.add(new Pessoa("Ilva Zanotelli Queiroz", "18"));
        convidados.add(new Pessoa("Nilson Pletes", "18"));
        convidados.add(new Pessoa("Antonia Pletes", "18"));
        convidados.add(new Pessoa("Paulo Marek", "18"));
        convidados.add(new Pessoa("Elisabeth Marek", "18"));

        convidados.add(new Pessoa("Claudio Silva", "19"));
        convidados.add(new Pessoa("Marcia Beatriz dos Santos", "19"));
        convidados.add(new Pessoa("Luisa dos Santos", "19"));
        convidados.add(new Pessoa("Marcelo Carvalho", "19"));
        convidados.add(new Pessoa("Tatiana Wasum", "19"));
        convidados.add(new Pessoa("Guilherme Carvalho", "19"));
        convidados.add(new Pessoa("Mariana Carvalho", "19"));

        convidados.add(new Pessoa("Juares Carvalho", "20"));
        convidados.add(new Pessoa("Andrea Paula Carvalho", "20"));
        convidados.add(new Pessoa("João Antonio Carvalho", "20"));
        convidados.add(new Pessoa("Jose Augusto Carvalho", "20"));
        convidados.add(new Pessoa("Pedro Henrique Carvalho", "20"));
        convidados.add(new Pessoa("Sergio Cechin ", "20"));
        convidados.add(new Pessoa("Tania Ferreira", "20"));
        convidados.add(new Pessoa("Rafaela Ferreira", "20"));

        convidados.add(new Pessoa("Guilherme Goulart", "21"));
        convidados.add(new Pessoa("Larissa Bello", "21"));
        convidados.add(new Pessoa("Guilherme Junior", "21"));
        convidados.add(new Pessoa("Rafael Leote", "21"));
        convidados.add(new Pessoa("Bianca Goulart", "21"));
        convidados.add(new Pessoa("Angelita Goulart", "21"));
        convidados.add(new Pessoa("Brayan Goulart", "21"));
        convidados.add(new Pessoa("Andrew Menezes", "21"));

        convidados.add(new Pessoa("Mileni Goulart", "22"));
        convidados.add(new Pessoa("Andrieli Gimenez", "22"));
        convidados.add(new Pessoa("Amanda Serpa", "22"));
        convidados.add(new Pessoa("Caroline Xavier", "22"));
        convidados.add(new Pessoa("Fabielli Souza", "22"));
        convidados.add(new Pessoa("Larissa Kleinkauf", "22"));
        convidados.add(new Pessoa("Raphaela Dessbesell", "22"));

        convidados.add(new Pessoa("Leonardo Kowalski", "23"));
        convidados.add(new Pessoa("Matheus Campos", "23"));
        convidados.add(new Pessoa("Rafael Duarte", "23"));
        convidados.add(new Pessoa("Thaylor Pacheco", "23"));
        convidados.add(new Pessoa("Victor Tavares", "23"));
        convidados.add(new Pessoa("Wilherme Campos", "23"));

        convidados.add(new Pessoa("Carmen Carolina Klafke", "24"));
        convidados.add(new Pessoa("Cassia Marcele Alves", "24"));
        convidados.add(new Pessoa("Isadora Mingurre", "24"));
        convidados.add(new Pessoa("Julia Lima", "24"));
        convidados.add(new Pessoa("Kauê Martins", "24"));
        convidados.add(new Pessoa("Lucas Sampaio", "24"));
        convidados.add(new Pessoa("Matheus Rocha da Silva", "24"));

        convidados.add(new Pessoa("Joao Patrick", "25"));
        convidados.add(new Pessoa("Krislei Barcellos", "25"));
        convidados.add(new Pessoa("Laisa Bello", "25"));
        convidados.add(new Pessoa("Luis Carlos Pereira", "25"));
        convidados.add(new Pessoa("Rafaela Machado", "25"));
        convidados.add(new Pessoa("Ruan Lenzi", "25"));
        convidados.add(new Pessoa("Yasmim Rodrigues", "25"));





         final ArrayAdapter<Pessoa> ad = new Adaptador(this,R.layout.item,convidados);


        lv = (ListView)findViewById(R.id.lv_lista);
        lv.setAdapter(ad);

        etBusca = (EditText) findViewById(R.id.etbusca);
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
    public static String FACEBOOK_URL = "https://www.facebook.com/jean.sarlon";
    public static String FACEBOOK_PAGE_ID = "jean.sarlon";

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

}
