package com.example.app.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.clases.EnlaceMenu;

public class PrincipalActivity extends AppCompatActivity implements EnlaceMenu {
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

    @Override
    public void menu(int id_boton) {
        Intent i_menu = new Intent(this,MenuActivity.class);
        i_menu.putExtra("boton_clicked",id_boton);
        startActivity(i_menu);

    }
}