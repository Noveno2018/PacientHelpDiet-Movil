package diet.help.pacient.pacienthelpdiet.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import diet.help.pacient.pacienthelpdiet.Adaptadores.Sugerencias_Adaptador;
import diet.help.pacient.pacienthelpdiet.Modelos.DetalleDieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Dieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Paciente;
import diet.help.pacient.pacienthelpdiet.Servicios.FirebaseReferences;
import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.Modelos.TipoDietas;
import diet.help.pacient.pacienthelpdiet.R;

public class Dieta_Activity extends AppCompatActivity {
    RecyclerView rv_aliemntos;
    ArrayList<Sugerencias> alimentos=new ArrayList<Sugerencias>();
    ArrayList<TipoDietas> tipos =new ArrayList<TipoDietas>();
    ArrayList<Paciente> pacientes=new ArrayList<Paciente>();
    ArrayList<DetalleDieta> detalleDietas=new ArrayList<DetalleDieta>();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference references=database.getReference(FirebaseReferences.ALIMENTOS_REFERENCIAS);
    Sugerencias_Adaptador sugerenciasAdaptador;
    Dieta dieta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietas);
        rv_aliemntos=(RecyclerView) findViewById(R.id.rv_listas);
        rv_aliemntos.setLayoutManager(new LinearLayoutManager(this));
        sugerenciasAdaptador =new Sugerencias_Adaptador(alimentos);
        rv_aliemntos.setAdapter(sugerenciasAdaptador);
        rv_aliemntos.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        rv_aliemntos.setLayoutManager(layoutManager);

        references.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alimentos.removeAll(alimentos);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Sugerencias ali=ds.getValue(Sugerencias.class);
                    alimentos.add(ali);
                }
                sugerenciasAdaptador.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference referenciasconsulta=database.getReference(FirebaseReferences.DIETA_REFERENCIAS);

        referenciasconsulta.orderByChild("fecha").equalTo("27/10/2017").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot ds1:dataSnapshot.getChildren()){
                    Paciente pacientes1=new Paciente();
                    pacientes1.setKey(ds1.child("pacient_key").getValue().toString());
                    pacientes.add(pacientes1);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for(int i=0;i<pacientes.size();i++){
            DatabaseReference referencepaciente=database.getReference(FirebaseReferences.PACIENTE_REFERENCIAS+"/"+ pacientes.get(i).getKey());
            referencepaciente.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds2:dataSnapshot.getChildren()){
                        Paciente paciente=new Paciente();
                        paciente.setCedula(ds2.child("cedula").getValue().toString());
                        paciente.setNombre(ds2.child("nombre").getValue().toString());
                        paciente.setApellido(ds2.child("apellido").getValue().toString());
                        pacientes.add(paciente);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }




    }
}
