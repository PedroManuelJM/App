package com.example.app.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app.R;
import com.example.app.actividades.LoginActivity;
import com.example.app.clases.InternaDB;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpcionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpcionesFragment extends Fragment implements View.OnClickListener {

    Button jbtn_cerrar_sesion;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpcionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpcionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OpcionesFragment newInstance(String param1, String param2) {
        OpcionesFragment fragment = new OpcionesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_opciones, container, false);
        jbtn_cerrar_sesion = vista.findViewById(R.id.opc_btn_cerrar_sesion);
        jbtn_cerrar_sesion.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.opc_btn_cerrar_sesion:
                cerrar_sesion();
                break;
        }
    }

    private void cerrar_sesion() {
        // objeto de la db
        InternaDB bd=new InternaDB(getContext());
        bd.borrar_sesion();
        Intent i_login=new Intent(getContext(), LoginActivity.class);
        startActivity(i_login);
    }
}