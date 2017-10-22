package salvararquivo.jeansarlon.salvararquivo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    String NOMEDOARQUIVO = "arquivo";
    String string = "Hello World";
    EditText entrada1;
    Button btnSalvar;
    Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada1 = (EditText) findViewById(R.id.entrada1);
        btnSalvar = (Button) findViewById(R.id.botao_salvar);
        btnFechar = (Button) findViewById(R.id.botao_fechar);
        string = lerArquivoDoArmazenamentoInterno();
        if (string != null){
            entrada1.setText(string);
        }else {
            Toast.makeText(getApplicationContext(), "Não Achei arquivo:", Toast.LENGTH_SHORT).show();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos;
                try {
                    fos = openFileOutput(NOMEDOARQUIVO, Context.MODE_PRIVATE);
                    string = entrada1.getText().toString();
                    fos.write(string.getBytes());
                    fos.close();
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private String lerArquivoDoArmazenamentoInterno(){
        String eol = System.getProperty("line.separator");
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(openFileInput(NOMEDOARQUIVO)));
            String line;
            StringBuffer buffer = new StringBuffer();
            while((line = input.readLine() ) != null){
                buffer.append(line + eol);
            }
            input.close();
            return buffer.toString();
        } catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}