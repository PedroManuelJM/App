package com.example.app.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.app.R;
import com.example.app.clases.EnlaceMenu;
import com.example.app.fragmentos.CarritoFragment;
import com.example.app.fragmentos.CatalogoFragment;
import com.example.app.fragmentos.HistorialFragment;
import com.example.app.fragmentos.OpcionesFragment;

public class MenuActivity extends AppCompatActivity implements EnlaceMenu {

    Fragment[] fragments;
    private static final int CANTIDAD_FRAGMENTOS=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        inicializar_fragmentos();
        int id_boton=getIntent().getIntExtra("boton_clicked",-1); //valor entero
        if(id_boton!=-1)
            menu(id_boton);
    }

    private void inicializar_fragmentos() {
        fragments = new Fragment[CANTIDAD_FRAGMENTOS];
        fragments[0]= new CatalogoFragment();
        fragments[1]= new CarritoFragment();
        fragments[2]= new HistorialFragment();
        fragments[3]= new OpcionesFragment();//cuando lleguemos a mapas
        fragments[4]= new OpcionesFragment();

    }

    @Override
    public void menu(int id_boton) {
        // gestionar fragmentos
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rl_menu,fragments[id_boton]); //cambio de fragmentos
        fragmentTransaction.commit();


    }
}