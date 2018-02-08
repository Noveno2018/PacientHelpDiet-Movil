package diet.help.pacient.pacienthelpdiet.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    ArrayList<Hospitalizacion> hospitalizacions=new ArrayList<Hospitalizacion>();
    private EventBus eventBus=EventBus.getDefault();
    RecyclerView rv_pacientes;
    Hospitalizacion hospitalizacion=new Hospitalizacion();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_consulta_paciente_, container, false);
        rv_pacientes=(RecyclerView) view.findViewById(R.id.rv_listas);
        pacienteAdaptador =new Paciente_Adaptador(hospitalizacions);
        rv_pacientes.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_pacientes.setAdapter(pacienteAdaptador);
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference referenceshospitalizacion=database.getReference(FirebaseReferences.HOSPITALIZACION_REFERENCIAS);
        final DatabaseReference referencespaciente=database.getReference(FirebaseReferences.PACIENTE_REFERENCIAS);
        referenceshospitalizacion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hospitalizacions.removeAll(hospitalizacions);
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    Log.i("datos",ds.getKey().toString()+" "+ds.child("pacienteKey").getValue().toString()+" "+ds.child("motivoIngreso").getValue().toString()+" "+ds.child("observaciones").getValue().toString());
                    hospitalizacion.setKey(ds.getKey().toString());
                    hospitalizacion.setPacienteKey(ds.child("pacienteKey").getValue().toString());
                    hospitalizacion.setMotivoIngreso(ds.child("motivoIngreso").getValue().toString());
                    hospitalizacion.setObservaciones(ds.child("observaciones").getValue().toString());
                    Log.i("datos","key"+referencespaciente.child(hospitalizacion.getPacienteKey()));
                    referencespaciente.child(hospitalizacion.getPacienteKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds1:dataSnapshot.getChildren()){
                                    Paciente pacient = new Paciente();
                                    Log.i("datos","key"+ds1.child("nombre").getValue().toString());
                                    pacient.setNombre(ds1.child("nombre").getValue().toString());
                                    pacient.setApellido(ds1.child("apellido").getValue().toString());
                                    pacient.setImg(ds1.child("img").getValue().toString());
                                    pacientes.add(pacient);
                                    hospitalizacion.setPacientes(pacientes);
                                    hospitalizacions.add(hospitalizacion);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                pacienteAdaptador.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
}
