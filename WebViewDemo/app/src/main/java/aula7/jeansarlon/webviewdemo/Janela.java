package aula7.jeansarlon.webviewdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class Janela extends Activity {
    private Button btnlr;
    private EditText etEnderecoUrl;
    private WebView wvPagina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela);
        btnlr = (Button) findViewById(R.id.OnClickIr);
        etEnderecoUrl = (EditText) findViewById(R.id.etUri);

        wvPagina = (WebView) findViewById(R.id.webView);
        wvPagina.getSettings().setJavaScriptEnabled(true);
        wvPagina.getSettings().setBuiltInZoomControls(true);
        wvPagina.setWebViewClient(new WebViewClient());
    }
    private String getURl(){
        String url =etEnderecoUrl.getText().toString();
        if (url.startsWith("http://")){
            return url;
        }else{
            return "http://" + url;
        }
    }
    public void onClicklr(View v){
        wvPagina.loadUrl(getURl());
    }
}
