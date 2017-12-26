package diet.help.pacient.pacienthelpdiet.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.ArrayList;

import diet.help.pacient.pacienthelpdiet.Adaptadores.Pedidos_Adaptador;
import diet.help.pacient.pacienthelpdiet.Adaptadores.Sugerencias_Adaptador;
import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.R;

public class RegistroDietas_Fragment extends Fragment {

    Spinner sp_listapacientes;
    ArrayList<Sugerencias> alimentos=new ArrayList<Sugerencias>();
    RecyclerView rv_pedidos;
    Pedidos_Adaptador pedidos_adaptador;

    public void setAlimentos(ArrayList<Sugerencias> alimentos) {
        this.alimentos = alimentos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_registro_dietas_, container, false);
        rv_pedidos=(RecyclerView)view.findViewById(R.id.rv_detalle);
        sp_listapacientes=(Spinner)view.findViewById(R.id.sp_pacientes);
        rv_pedidos.setLayoutManager(new LinearLayoutManager(getContext()));
        pedidos_adaptador=new Pedidos_Adaptador(alimentos);
        rv_pedidos.setAdapter(pedidos_adaptador);
        rv_pedidos.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        rv_pedidos.setLayoutManager(layoutManager);

        pedidos_adaptador.notifyDataSetChanged();
        return view;
    }


}