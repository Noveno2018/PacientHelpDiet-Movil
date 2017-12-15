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

public class Dietas extends AppCompatActivity {
    RecyclerView rv_aliemntos;
    ArrayList<Alimentos> alimentos=new ArrayList<Alimentos>();;
    final static ArrayList<TipoDietas> tipos =new ArrayList<TipoDietas>();;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference references=database.getReference(FirebaseReferences.ALIMENTOS_REFERENCIAS);
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietas);
        rv_aliemntos=(RecyclerView) findViewById(R.id.rv_listas);
        rv_aliemntos.setLayoutManager(new LinearLayoutManager(this));
        adaptador=new Adaptador(alimentos);
        rv_aliemntos.setAdapter(adaptador);
        rv_aliemntos.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        rv_aliemntos.setLayoutManager(layoutManager);
        references.addValueEventListener(new ValueEventListener() {
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
