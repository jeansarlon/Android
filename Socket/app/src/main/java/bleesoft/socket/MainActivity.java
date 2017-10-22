package bleesoft.socket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    Button bt_send;
    EditText et_text;

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://10.1.1.182:8080");
        } catch (URISyntaxException e) {}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_text = (EditText) findViewById(R.id.et_text);
        bt_send = (Button) findViewById(R.id.bt_send);
        mSocket.connect();
        mSocket.on("new message", onNewMessage);
        mSocket.connect();

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSend();
            }
        });
    }

    private void attemptSend() {

        String message = et_text.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            return;
        }
        mSocket.emit("new message", message);
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    try {
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }

                    Toast.makeText(MainActivity.this, "message: " + message, Toast.LENGTH_SHORT).show();;
                }
            });

        }
    };
}
