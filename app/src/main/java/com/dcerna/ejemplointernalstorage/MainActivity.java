package com.dcerna.ejemplointernalstorage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    RadioGroup optGroupAlmacenamiento;
    Button btnAbrir;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optGroupAlmacenamiento = findViewById(R.id.optGroupAlmacenamiento);
        btnAbrir = findViewById(R.id.btnAbrir);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(view -> guardarArchivo());

    }
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

    private void validarPermiso(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            guardarArchivoMemoriaExterna();
        }
        else if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(this, "Es necesario para escribir el archivo en la memoria externa", Toast.LENGTH_SHORT).show();
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        else{
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    private void guardarArchivo() {
        if (optGroupAlmacenamiento.getCheckedRadioButtonId() == R.id.optExterna){
            validarPermiso();
        }
    }

    private void guardarArchivoMemoriaExterna() {
    }

}