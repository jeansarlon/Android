package lanternabugada.jeansarlon.lanternabugada;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Camera mCamera;
    boolean flashIsOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashIsOn = false;

        Button btnLigarLed = (Button) findViewById(R.id.bt_Ligar);

        btnLigarLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!verificarPermissao())
                    return;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    CameraManager cameraManager = (CameraManager)
                            getSystemService(Context.CAMERA_SERVICE);
                    String cameraId = null;

                    try {
                        cameraId = cameraManager.getCameraIdList()[0];
                        if (!flashIsOn) {
                            cameraManager.setTorchMode(cameraId, true);
                            flashIsOn = true;
                        } else {
                            cameraManager.setTorchMode(cameraId, false);
                            flashIsOn = false;
                        }
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                } else {

                    if (mCamera == null)
                        mCamera = Camera.open();

                    if (mCamera != null) {
                        android.hardware.Camera.Parameters params = mCamera.getParameters();

                        if (!flashIsOn) {
                            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            flashIsOn = true;
                            ((Button) findViewById(R.id.bt_Ligar)).setText("Desligar");
                        } else {
                            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            flashIsOn = false;
                            ((Button) findViewById(R.id.bt_Ligar)).setText("Ligar");
                        }

                        mCamera.setParameters(params);
                        mCamera.startPreview();
                    }
                } // fim else
            }
        });
    } //fim onCreate

    boolean verificarPermissao(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 0);
            return false;
        } else {
            return true;
        }
    }

}
