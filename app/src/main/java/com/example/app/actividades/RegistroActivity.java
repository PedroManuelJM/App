package com.example.app.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

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


        llenar_distritos();
        // eventos onclick listener --> los botones importante
        jtxt_fecha_nac.setOnClickListener(this);
        jchk_terminos.setOnClickListener(this);
        jbtn_registrar.setOnClickListener(this);
        jbtn_cancelar.setOnClickListener(this);


    }

    private void llenar_distritos() {
        AsyncHttpClient ahc_distrito = new AsyncHttpClient();
        String s_url ="http://chefsociety.atwebpages.com/webservice/distritos.php";

        ahc_distrito.post(s_url, null, new BaseJsonHttpResponseHandler() {

            @Override  // SI HAY RESPUESTA HACIA TU WEBSERVICE
            // CÓDIGO ESTADO --> IDENTIFICAR LA RESPUESTA
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if(statusCode == 200){
                    try {
                        JSONArray jsonArray = new JSONArray(rawJsonResponse);
                        // 43 distritos + el seleccione distrito = 44
                        String[] s_distritos = new String[jsonArray.length()+1];
                               s_distritos[0]= "Seleccione distrito";
                               for(int i=1; i<= jsonArray.length();i++){
                                   s_distritos[i] = jsonArray.getJSONObject(i-1).getString("nombre_distrito");
                               }
                                // pasando los datos al spinner
                               jsp_distritos.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, s_distritos));



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Error code: "+ statusCode,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
              Toast.makeText(getApplicationContext(),"Error al cargar distritos",Toast.LENGTH_LONG).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });
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
        final String CERO="0";
        final String BARRA="/";
        //CALENDARIO PARA OBTENER FECHA & HORA
        final Calendar c= Calendar.getInstance();
        // Variables para obtener la fecha
        final int mes = c.get(Calendar.MONTH);
        final int dia = c.get(Calendar.DAY_OF_MONTH);
        final int anio = c.get(Calendar.YEAR);
        //Toast.makeText(this,"Recoger fecha nacimiento", Toast.LENGTH_LONG).show();
        DatePickerDialog recoger_fecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month +1;
                // Formateo el dia obtenido: antepone el 0 si son menores de 10
                String diaFormateado=(dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                // Formateo el dia obtenido: antepone el 0 si son menores de 10
                String mesFormateado=(mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                jtxt_fecha_nac.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
            }
        },anio,mes,dia) ;
         recoger_fecha.show();
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