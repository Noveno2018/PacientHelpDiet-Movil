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
import diet.help.pacient.pacienthelpdiet.Servicios.FirebaseReferences;
import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.Modelos.TipoDietas;
import diet.help.pacient.pacienthelpdiet.R;

public class Dieta_Activity extends AppCompatActivity {
    RecyclerView rv_aliemntos;
    ArrayList<Sugerencias> alimentos=new ArrayList<Sugerencias>();;
    final static ArrayList<TipoDietas> tipos =new ArrayList<TipoDietas>();;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference references=database.getReference(FirebaseReferences.ALIMENTOS_REFERENCIAS);
    Sugerencias_Adaptador sugerenciasAdaptador;

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

    }
}
