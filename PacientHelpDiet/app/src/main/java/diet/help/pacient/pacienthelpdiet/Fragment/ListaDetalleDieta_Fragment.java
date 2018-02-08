package diet.help.pacient.pacienthelpdiet.Fragment;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import diet.help.pacient.pacienthelpdiet.Adaptadores.AdapterDetalleDieta;
import diet.help.pacient.pacienthelpdiet.Modelos.DetalleDieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Dieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Paciente;
import diet.help.pacient.pacienthelpdiet.R;
import diet.help.pacient.pacienthelpdiet.Servicios.FirebaseReferences;


public class ListaDetalleDieta_Fragment extends Fragment {
    RecyclerView rv;
    Spinner cmb_paciente;
    int idpaciente;
    EditText txtFechaDetalleLista, txtNombre, txtCedula;
    Button btnBuscarDetalleLista,btnPasarDatosCatering;
    ArrayList<Dieta> dietas;
    ArrayList<DetalleDieta> detalles;
    ArrayList<Paciente> pacientes;
    AdapterDetalleDieta adapter;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date today= new Date();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference Dietarefercia=database.getReference(FirebaseReferences.DIETA_REFERENCIAS);
    final DatabaseReference DetalleReference=database.getReference(FirebaseReferences.DETALLE_REFERENCIAS);
    final DatabaseReference Preference=database.getReference(FirebaseReferences.PACIENTE_REFERENCIAS);
    final String fechaHoy=dateFormat.format(today);
    ArrayList<String> paciente=new ArrayList<String>();
    String usuario="-L0ePqWn-zBqUUx8FTS8";

    //



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_lista_detalle_dieta, container, false);
        rv = (RecyclerView) vista.findViewById(R.id.rvwDetalledieta);
        txtCedula=(EditText) vista.findViewById(R.id.txtCedula);
        cmb_paciente=(Spinner) vista.findViewById(R.id.sp_paciente);
        btnPasarDatosCatering=(Button) vista.findViewById(R.id.btnPasarListaCatering);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        dietas=new ArrayList<>();
        detalles=new ArrayList<>();
        pacientes=new ArrayList<>();
        adapter=new AdapterDetalleDieta(dietas);
        rv.setAdapter(adapter);
        new LlamarPacientes().execute();
        cmb_paciente.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idpaciente=i;
                if(idpaciente!=0){
                    txtCedula.setText("0123456");
                    Dietarefercia.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dietas.removeAll(dietas);
                            for ( DataSnapshot ds:dataSnapshot.getChildren()){
                                final Dieta dieta=ds.getValue(Dieta.class);
                                dieta.setKey(ds.getKey());
                                dietas.add(dieta);
                                /*DetalleReference.orderByChild("dietaKey").equalTo(dieta.getKey()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot ds1:dataSnapshot.getChildren()){
                                            DetalleDieta detalle= ds1.getValue(DetalleDieta.class);
                                            detalle.setKey(ds1.getKey());
                                            detalles.add(detalle);
                                            dieta.setListadetalledieta(detalles);
                                            dietas.add(dieta);
                                        }
                                        dieta.getListadetalledieta();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });*/
                                adapter.notifyDataSetChanged();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(),"Seleccionar una opcion",Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }
    public void ShowDialogListView (View vista){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setPositiveButton("Aceptar",null);
        builder.setView(rv);
        AlertDialog dialog=builder.create();
        dialog.setTitle("Detalle");
        dialog.show();

    }

    private void ListaPaciente(){
        int i;
        paciente.add("----Seleccione----");
        Log.i("string3",String.valueOf(pacientes.size()));
        for(i=0;i<pacientes.size();i++){
            Log.i("string",pacientes.get(i).getNombre()+" "+pacientes.get(i).getApellido());
            paciente.add(pacientes.get(i).getNombre()+" "+pacientes.get(i).getApellido());
        }
        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, paciente);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmb_paciente.setAdapter(spinnerAdapter);

    }

    private class LlamarPacientes extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Preference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    pacientes.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        pacientes.add(new Paciente(ds.child("cedula").getValue().toString(),ds.child("nombre").getValue().toString(),ds.child("apellido").getValue().toString(),ds.getKey()));
                    }
                    ListaPaciente();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            Log.i("string1", String.valueOf(pacientes.size()));
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListaPaciente();
        }
    }
}
