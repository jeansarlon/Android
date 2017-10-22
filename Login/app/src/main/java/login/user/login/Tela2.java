package login.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class Tela2 extends AppCompatActivity {
    private static  String NOME_PREF = "preferencia";
    String acao;
    Integer cont_erro;
    Integer cont_sucesso;
    ImageView iv_status;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        Bundle extras = getIntent().getExtras();
        pref = getSharedPreferences(NOME_PREF, 0);
        editor = pref.edit();


        cont_erro = pref.getInt("cont_erro", 0);
        cont_sucesso = pref.getInt("cont_sucesso", 0);




        acao = extras.getString("acao");
        iv_status = (ImageView) findViewById(R.id.iv_status);
        switch (acao){
            case "registro":
                iv_status.setImageResource(R.drawable.registro);
                break;
            case "login_sucesso":
                iv_status.setImageResource(R.drawable.sucesso);
                editor.putInt("cont_sucesso", cont_sucesso +1);
                editor.commit();
                cont_sucesso = pref.getInt("cont_sucesso", 0);
                break;
            case "login_erro":
                iv_status.setImageResource(R.drawable.erro);
                editor.putInt("cont_erro", cont_erro +1);
                editor.commit();
                break;
        }


    }
}
