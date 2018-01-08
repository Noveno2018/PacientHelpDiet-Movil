package diet.help.pacient.pacienthelpdiet.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import diet.help.pacient.pacienthelpdiet.Adaptadores.Sugerencias_Adaptador;
import diet.help.pacient.pacienthelpdiet.Modelos.DetalleDieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Dieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.Modelos.TipoDietas;
import diet.help.pacient.pacienthelpdiet.R;
import diet.help.pacient.pacienthelpdiet.Servicios.FirebaseReferences;


public class ConsultaDietas_Fragment extends Fragment {
    //comentario

    RecyclerView rv_aliemntos;
    ArrayList<Sugerencias> alimentos=new ArrayList<Sugerencias>();
    ArrayList<String> keysDietas =new ArrayList<String>();
    ArrayList<DetalleDieta> detalleDietas=new ArrayList<DetalleDieta>();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference dietas=database.getReference(FirebaseReferences.DIETA_REFERENCIAS);
    final DatabaseReference tipoDietas=database.getReference(FirebaseReferences.TIPODIETAS_REFERENCIAS);
    final DatabaseReference detalleDietasReference=database.getReference(FirebaseReferences.DETALLE_REFERENCIAS);
    final HashMap<String, String> catalogoTipoDietas = new HashMap<String, String>();
    Sugerencias_Adaptador sugerenciasAdaptador;
    TextView fecha;
    TextView cantidadDietas;
    TextView meriendaResumen;
    TextView desayunoResumen;
    TextView almuerzoResumen;
    Dieta dieta;
    // pruebas
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_consulta_dietas_, container, false);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDate= new Date();
        final String fechaHoy=dateFormat.format(fechaDate);




        fecha= (TextView) view.findViewById(R.id.textFecha);
        meriendaResumen= (TextView) view.findViewById(R.id.meriendaResumen);
        desayunoResumen= (TextView) view.findViewById(R.id.desayunoResumen);
        almuerzoResumen= (TextView) view.findViewById(R.id.almuerzoResumen);


        fecha.setText(fechaHoy);

        ;
        cantidadDietas= (TextView) view.findViewById(R.id.textView8);
        tipoDietas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    TipoDietas det=ds.getValue(TipoDietas.class);
                    catalogoTipoDietas.put(ds.getKey(),det.getTipo());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dietas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                int cantidad=0;
                final HashMap<String, Dieta> catalogoDietas = new HashMap<String, Dieta>();
                final HashMap<String, HashMap<String,Integer>> resumen = new HashMap<String, HashMap<String,Integer>>();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Dieta ali=ds.getValue(Dieta.class);
                    System.out.print(dateFormat.format(ali.getFecha()));
                    String fechaDieta =dateFormat.format(ali.getFecha());
                    if(fechaHoy.equals(fechaDieta)){
                        cantidad++;
                        keysDietas.add(ds.getKey());
                        catalogoDietas.put(ds.getKey(),ali);
                        if (!resumen.containsKey(ali.getHorario())){
                            HashMap<String, Integer> hmap = new HashMap<String, Integer>();
                            resumen.put(ali.getHorario(),hmap);
                        }
                    }
                }

                cantidadDietas.setText("Dietas Para hoy: "+cantidad);

                detalleDietasReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            DetalleDieta det=ds.getValue(DetalleDieta.class);
                            if(keysDietas.indexOf(det.getDietaKey())!=-1){
                                String tipoDieta = catalogoTipoDietas.get(det.getTipodieta_key());
                                String horarioDieta=catalogoDietas.get(det.getDietaKey()).getHorario();
                                if(!resumen.get(horarioDieta).containsKey(tipoDieta)){
                                    resumen.get(horarioDieta).put(tipoDieta,1);
                                }else {
                                    resumen.get(horarioDieta).put(tipoDieta,resumen.get(horarioDieta).get(tipoDieta)+1);
                                }
                            }
                        }

                        String strResumen="";
                        if (resumen.containsKey("Desayuno")&&!resumen.get("Desayuno").isEmpty())
                            for (Map.Entry<String, Integer> entry : resumen.get("Desayuno").entrySet()) {
                                String key = entry.getKey();
                                Integer value = entry.getValue();
                                strResumen +=key+" "+value+"\n";
                            }
                        desayunoResumen.setText(strResumen);

                        strResumen="";
                        if (resumen.containsKey("Almuerzo")&&!resumen.get("Almuerzo").isEmpty())
                            for (Map.Entry<String, Integer> entry : resumen.get("Almuerzo").entrySet()) {
                                String key = entry.getKey();
                                Integer value = entry.getValue();
                                strResumen +=key+" "+value+"\n";
                            }
                        almuerzoResumen.setText(strResumen);

                        strResumen="";
                        if (resumen.containsKey("Merienda")&&!resumen.get("Merienda").isEmpty())
                            for (Map.Entry<String, Integer> entry : resumen.get("Merienda").entrySet()) {
                                String key = entry.getKey();
                                Integer value = entry.getValue();
                                strResumen +=key+" "+value+"\n";
                            }
                        meriendaResumen.setText(strResumen);


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
}
