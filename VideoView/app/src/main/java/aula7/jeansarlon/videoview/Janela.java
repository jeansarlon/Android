package aula7.jeansarlon.videoview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class Janela extends AppCompatActivity {
    private VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela);
        vv = (VideoView) findViewById(R.id.video);
    }

    public void OnClickReproduz(View v){
        String path = "android.resource://"+
                getPackageName()+
                System.getProperty("file.separator")+
                R.raw.small;
        vv.setVideoURI(Uri.parse(path));
        vv.start();
    }
}

