package diet.help.pacient.pacienthelpdiet;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

public class Dietas extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //prueba commit git krakrn
<<<<<<< HEAD
=======
    //Janneth
>>>>>>> ea079560301ab35b5fedbca653a012c773489146
    Spinner cmb_Tipos_Dietas;
    RecyclerView rv_aliemntos;
    ArrayList<Alimentos> alimentos=new ArrayList<Alimentos>();;
    final static ArrayList<TipoDietas> tipos =new ArrayList<TipoDietas>();;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference references=database.getReference(FirebaseReferences.CATEGORIAS_REFERENCIAS);
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietas);
        cmb_Tipos_Dietas=(Spinner) findViewById(R.id.sp_tipo_dietas);
        cmb_Tipos_Dietas.setOnItemSelectedListener(this);
        new GetTipos().execute();
        rv_aliemntos=(RecyclerView) findViewById(R.id.rv_listas);
        rv_aliemntos.setLayoutManager(new LinearLayoutManager(this));
        adaptador=new Adaptador(alimentos);
        rv_aliemntos.setAdapter(adaptador);
        rv_aliemntos.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        rv_aliemntos.setLayoutManager(layoutManager);

    }

    private void ListaTipos(){
        ArrayList<String> tiposdietas=new ArrayList<String>();
        tiposdietas.add("----Seleccione----");
        Log.i("string3",String.valueOf(tipos.size()));
        for(int i=0;i<tipos.size();i++){
            Log.i("string",tipos.get(i).getTipo());
            tiposdietas.add(tipos.get(i).getTipo());
        }
        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tiposdietas);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmb_Tipos_Dietas.setAdapter(spinnerAdapter);
    }

    private class GetTipos extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            references.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    tipos.clear();
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        tipos.add(new TipoDietas(ds.child("tipo").getValue().toString(),ds.child("descripcion").getValue().toString(),ds.getKey()));
                    }
                    ListaTipos();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            Log.i("string1",String.valueOf(tipos.size()));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListaTipos();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).toString()!= "----Seleccione----") {
            final Alimentos a=new Alimentos();
            String s;
            final DatabaseReference references1 = database.getReference(FirebaseReferences.ALIMENTOS_REFERENCIAS);
            references.orderByChild("tipo").equalTo(parent.getItemAtPosition(position).toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        references1.orderByChild("tipodieta_key").equalTo(ds.getKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                alimentos.removeAll(alimentos);
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    Log.i("img",ds.child("img").getValue().toString());
                                    Alimentos ali=ds.getValue(Alimentos.class);
                                    alimentos.add(ali);
                                }
                                adaptador.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else{
            DatabaseReference references1 = database.getReference(FirebaseReferences.ALIMENTOS_REFERENCIAS);
            references1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    alimentos.removeAll(alimentos);
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Alimentos ali=ds.getValue(Alimentos.class);
                        alimentos.add(ali);
                    }
                    adaptador.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
