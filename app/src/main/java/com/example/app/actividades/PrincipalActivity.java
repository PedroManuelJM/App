package com.example.app.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.app.R;

public class PrincipalActivity extends AppCompatActivity {
     // Definir datos
    TextView jbl_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        jbl_nombre=findViewById(R.id.pri_lbl_nombre);
        //Recuperamos el parámetro del intent anterior -- > para enviar datos
        String s_usuario=getIntent().getStringExtra("usuario");
        jbl_nombre.setText("Bienvenido: "+s_usuario);


    }

}