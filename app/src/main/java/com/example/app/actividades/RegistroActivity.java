package com.example.app.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app.R;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    EditText jtxt_dni,jtxt_nombre,jtxt_apellidos,jtxt_fecha_nac,jtxt_correo,
             jtxt_telefono,jtxt_direccion,jtxt_clave,jtxt_confirmar_clave;
    Spinner jsp_distritos;
    CheckBox jchk_terminos;
    RadioGroup jrg_sexo;
    RadioButton jrb_opcion_sexo;
    Button jbtn_registrar,jbtn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        jtxt_dni=findViewById(R.id.reg_txt_dni);
        jtxt_nombre=findViewById(R.id.reg_txt_nombre);
        jtxt_apellidos=findViewById(R.id.reg_txt_apellidos);
        jtxt_fecha_nac=findViewById(R.id.reg_txt_fecha_nac);
        jtxt_correo=findViewById(R.id.reg_txt_correo);
        jtxt_telefono=findViewById(R.id.reg_txt_telefono);
        jtxt_direccion=findViewById(R.id.reg_txt_direccion);
        jtxt_clave=findViewById(R.id.reg_txt_clave);
        jtxt_confirmar_clave=findViewById(R.id.reg_txt_confirmar_clave);
        jsp_distritos=findViewById(R.id.reg_sp_distritos);
        jchk_terminos=findViewById(R.id.reg_chk_terminos);
        jrg_sexo=findViewById(R.id.reg_rg_sexo);
        jbtn_registrar=findViewById(R.id.reg_btn_registrar);
        jbtn_cancelar=findViewById(R.id.reg_btn_cancelar);
        //llenar el spinner por código
        jsp_distritos.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione distrito","Ancón","Bellavista","Comas"}));

        // eventos onclick listener --> los botones importante
        jtxt_fecha_nac.setOnClickListener(this);
        jchk_terminos.setOnClickListener(this);
        jbtn_registrar.setOnClickListener(this);
        jbtn_cancelar.setOnClickListener(this);


    }
    // IMPORTANTE EL EVENTO ONCLICK LISTENER
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_txt_fecha_nac:
                recoger_fecha_nacimiento();
                break;
            case R.id.reg_chk_terminos:
                aceptar_terminos();
                break;
            case R.id.reg_btn_registrar:
                registrar_nuevo_usuario();
                break;
            case R.id.reg_btn_cancelar:
                cancelar_registro();
                break;
                
        }
    }


    private void recoger_fecha_nacimiento() {
        Toast.makeText(this,"Recoger fecha nacimiento", Toast.LENGTH_LONG).show();
    }

    private void aceptar_terminos() {
        jbtn_registrar.setEnabled(jchk_terminos.isChecked()? true : false);
    }
    private void registrar_nuevo_usuario() {
        // llamar al web service para regisstrar
    }
    private void cancelar_registro() {
        Intent i_login= new Intent(this,LoginActivity.class);
        startActivity(i_login);
        finish();// elimine temporal el form_registro
    }

}