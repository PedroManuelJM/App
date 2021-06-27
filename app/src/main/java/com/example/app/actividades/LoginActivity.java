package com.example.app.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.R;
import com.example.app.clases.Hash;
import com.example.app.clases.InternaDB;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText jtxt_correo,jtxt_clave;
    CheckBox jchk_recordar;
    Button jbtn_ingresar,jbtn_salir;
    TextView jlbl_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // instanciar variables
        jtxt_correo=findViewById(R.id.log_txt_correo);
        jtxt_clave=findViewById(R.id.log_txt_clave);
        jchk_recordar=findViewById(R.id.log_chk_recordar);
        jbtn_ingresar=findViewById(R.id.log_btn_ingresar);
        jbtn_salir=findViewById(R.id.log_btn_salir);
        jlbl_registrar=findViewById(R.id.log_lbl_registro);

        jchk_recordar.setOnClickListener(this); // setearlo a todos los botones
        jbtn_ingresar.setOnClickListener(this);
        jbtn_salir.setOnClickListener(this);
        jlbl_registrar.setOnClickListener(this);

        // validar si el usuario recordo sesión
        // creamos la base de datos
        InternaDB bd=new InternaDB(getApplicationContext());
        // se verifica si recordo sesión y se extrae los datos
        if(bd.recordo_sesion()){
            validar_sesion(bd.buscar_campo("CORREO"),bd.buscar_campo("CLAVE"));
        }

        //jtxt_correo.setText("usuario@mail.com");
        //jtxt_clave.setText("123456");

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log_chk_recordar:
                recordar_sesion(); // metodo
                break;
            case R.id.log_btn_ingresar:
                validar_sesion(jtxt_correo.getText().toString().trim(),jtxt_clave.getText().toString().trim());
                break;
            case R.id.log_btn_salir:
                salir_aplicacion();
                break;
            case R.id.log_lbl_registro:
                registrar_usuario();
                break;
        }
    }


    private void recordar_sesion() {
        String s_recordar = jchk_recordar.isChecked() ? "activado":"desactivado";
        System.out.println("Recordar sesión: "+ s_recordar);
    }
    private void validar_sesion(String s_correo,String s_clave) {

        if (validar_usuario(s_correo,s_clave)) {
            if(jchk_recordar.isChecked()){
                Hash hash=new Hash();
                // si esta marcado la opcióon recordar sesión
                InternaDB bd= new InternaDB(getApplicationContext()); // la bd
                bd.agregar_usuario(s_correo,hash.StringToHash(s_clave,"SHA1")); // agregando el usuario a la BD
            }

            //cargamos la actividad de bienvenida
            Intent i_principal = new Intent(getApplicationContext(), PrincipalActivity.class);
            // pasar parametro por el intent para comunicar con la actividad
            i_principal.putExtra("usuario","Benito") ;
            startActivity(i_principal);

        }else{
            // mostrar mensaje de error
            Toast.makeText(getApplicationContext(),"Correo o clave incorrecta",
                    Toast.LENGTH_LONG).show();

        }
    }

    private boolean validar_usuario(String s_correo, String s_clave) {
        //validacion a base de datos
        boolean b_valido=false;
        Hash hash=new Hash();
        InternaDB bd= new InternaDB(getApplicationContext()); // la bd
       //
        if(!bd.recordo_sesion()){
            s_clave=hash.StringToHash(s_clave,"SHA1");
        }

        if(s_correo.equals("usuario@mail.com")&& s_clave.equals("7c4a8d09ca3762af61e59520943dc26494f8941b"))
            b_valido=true;
        return b_valido;
    }

    private void salir_aplicacion() {
        System.exit(1);
    }
    private void registrar_usuario() {
        Intent i_registro = new Intent(getApplicationContext(), RegistroActivity.class); //
        startActivity(i_registro);
    }
}