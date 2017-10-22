package asynctasktreads.jeansarlon.asynctasktreads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.img_buceta);
    }

    public Bitmap loadImageFromNetwork(String urlImg){
        Bitmap img = null;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL(urlImg);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream input = conexao.getInputStream();
            img = BitmapFactory.decodeStream(input);
        }catch (IOException e ){

        }
        return img;
    }

    public void clica(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap imgAux = loadImageFromNetwork("http://pldh.net/media/dreamworld/687.png");
                // Método para poder acessar elementos UI na MainThread apartir de uma outra thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageBitmap(imgAux);
                    }
                });
            }
        }).start();
    }

}
