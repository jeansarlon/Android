package aula7.jeansarlon.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Janela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela);
    }

    public void onClickTrocrar(View v){
        ImageView ivFoto = (ImageView) findViewById(R.id.Image);
        ivFoto.setImageResource(R.drawable.checkmark);
    }
}
