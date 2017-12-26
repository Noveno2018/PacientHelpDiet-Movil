package diet.help.pacient.pacienthelpdiet.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import diet.help.pacient.pacienthelpdiet.Adaptadores.Pedidos_Adaptador;
import diet.help.pacient.pacienthelpdiet.Adaptadores.Sugerencias_Adaptador;
import diet.help.pacient.pacienthelpdiet.Interface.OnSelectElementos;
import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.Modelos.TipoDietas;
import diet.help.pacient.pacienthelpdiet.R;
import diet.help.pacient.pacienthelpdiet.Servicios.FirebaseReferences;


public class ListaSugerencia_Fragment extends Fragment{

    RecyclerView rv_aliemntos;
    ArrayList<Sugerencias> alimentos=new ArrayList<Sugerencias>();
    ArrayList<Sugerencias> pedidos=new ArrayList<Sugerencias>();
    CardView btn_adniadir;
    Pedidos_Adaptador pedidos_adaptador;
    final static ArrayList<TipoDietas> tipos =new ArrayList<TipoDietas>();;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference references=database.getReference(FirebaseReferences.ALIMENTOS_REFERENCIAS);
    Sugerencias_Adaptador sugerenciasAdaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lista_sugerencia_, container, false);
        btn_adniadir=(CardView)view.findViewById(R.id.cv_add);
        rv_aliemntos=(RecyclerView) view.findViewById(R.id.rv_listas);
        rv_aliemntos.setLayoutManager(new LinearLayoutManager(getContext()));
        sugerenciasAdaptador =new Sugerencias_Adaptador(alimentos);
        rv_aliemntos.setAdapter(sugerenciasAdaptador);
        rv_aliemntos.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        rv_aliemntos.setLayoutManager(layoutManager);
        references.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alimentos.removeAll(alimentos);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Sugerencias ali=ds.getValue(Sugerencias.class);
                    ali.setKey(ds.getKey());
                    alimentos.add(ali);
                }
                sugerenciasAdaptador.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        sugerenciasAdaptador.setOnSelectElementos(new OnSelectElementos() {
            @Override
            public void onAddClick(int posicion) {
                pedidos.add(alimentos.get(posicion));
                RegistroDietas_Fragment registroDietas_fragment=new RegistroDietas_Fragment();
                registroDietas_fragment.setAlimentos(pedidos);
                Toast.makeText(getContext(),"Se agregado "+alimentos.get(posicion).getKey(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
