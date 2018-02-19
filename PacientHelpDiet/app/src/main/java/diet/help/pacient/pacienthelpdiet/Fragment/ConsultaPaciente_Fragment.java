package diet.help.pacient.pacienthelpdiet.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import diet.help.pacient.pacienthelpdiet.Adaptadores.Paciente_Adaptador;
import diet.help.pacient.pacienthelpdiet.Interface.OnSelectElementos;
import diet.help.pacient.pacienthelpdiet.Modelos.Hospitalizacion;
import diet.help.pacient.pacienthelpdiet.Modelos.Paciente;
import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.R;
import diet.help.pacient.pacienthelpdiet.Servicios.FirebaseReferences;

public class ConsultaPaciente_Fragment extends Fragment {

    Paciente_Adaptador pacienteAdaptador;
    ArrayList<Paciente> pacientes=new ArrayList<Paciente>();
    private EventBus eventBus=EventBus.getDefault();
    RecyclerView rv_pacientes;
    Paciente paciente;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_consulta_paciente_, container, false);
        rv_pacientes=(RecyclerView) view.findViewById(R.id.rv_listas);
        pacienteAdaptador =new Paciente_Adaptador(pacientes);
        rv_pacientes.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_pacientes.setAdapter(pacienteAdaptador);
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference referenceshospitalizacion=database.getReference(FirebaseReferences.HOSPITALIZACION_REFERENCIAS);
        final DatabaseReference referencespaciente=database.getReference(FirebaseReferences.PACIENTE_REFERENCIAS);
        referencespaciente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pacientes.removeAll(pacientes);
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    paciente=new Paciente();
                    paciente.setKey(ds.getKey().toString());
                    paciente.setNombre(ds.child("nombre").getValue().toString());
                    paciente.setApellido(ds.child("apellido").getValue().toString());
                    paciente.setImg(ds.child("img").getValue().toString());
                    paciente.setAntecendentes(ds.child("antecendentes").getValue().toString());
                    pacientes.add(paciente);
                }
                pacienteAdaptador.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        pacienteAdaptador.setOnSelectElementos(new OnSelectElementos() {
            @Override
            public void onAddClick(int posicion) {
                Paciente p =new Paciente(pacientes.get(posicion).getNombre(),pacientes.get(posicion).getApellido(),pacientes.get(posicion).getAntecendentes(),pacientes.get(posicion).getKey(),pacientes.get(posicion).getImg());
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedor,new ContenedorDietas_Fragment(p)).commit();
            }
        });
        return view;
    }
}
