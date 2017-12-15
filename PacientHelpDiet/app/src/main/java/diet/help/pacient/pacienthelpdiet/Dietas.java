package diet.help.pacient.pacienthelpdiet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Dietas extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner cmb_Tipos_Dietas;
    RecyclerView rv_aliemntos;
    ArrayList<TipoDietas>tipo;
    TipoDietas tipoDieta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietas);
        cmb_Tipos_Dietas=(Spinner) findViewById(R.id.sp_tipo_dietas);
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference references=database.getReference(FirebaseReferences.CATEGORIAS_REFERENCIAS);
        references.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items=dataSnapshot.getChildren().iterator();
                while (items.hasNext()){
                    DataSnapshot item=items.next();
                    tipoDieta=new TipoDietas(item.child("tipo").getValue().toString(),item.child("descripcion").getValue().toString());
                    Log.i("Tipos",item.child("tipo").getValue().toString());
                    Log.i("Tipos",item.child("descripcion").getValue().toString());
                    Log.i("Tipos5",tipoDieta.getTipo());
                    Log.i("Tipos6",tipoDieta.getDescripcion());

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void ListaTipos(){
        ArrayList<String> tiposdietas=new ArrayList<String>();
        for(int i=0;i<tipo.size();i++){
            tiposdietas.add(tipo.get(i).getTipo());
        }
        ArrayAdapter<String> sp_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposdietas);
        sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmb_Tipos_Dietas.setAdapter(sp_adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(
                getApplicationContext(),
                parent.getItemAtPosition(position).toString() + " Seleccionado" ,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
