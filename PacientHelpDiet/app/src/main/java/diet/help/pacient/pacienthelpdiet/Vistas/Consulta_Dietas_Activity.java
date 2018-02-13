package diet.help.pacient.pacienthelpdiet.Vistas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import diet.help.pacient.pacienthelpdiet.Adaptadores.Consulta_Adaptador;
import diet.help.pacient.pacienthelpdiet.Adaptadores.Consulta_Adaptador_Detalle;
import diet.help.pacient.pacienthelpdiet.Adaptadores.Sugerencias_Adaptador;
import diet.help.pacient.pacienthelpdiet.Modelos.Consulta;
import diet.help.pacient.pacienthelpdiet.Modelos.DetalleDieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Dieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.Modelos.TipoDietas;
import diet.help.pacient.pacienthelpdiet.R;
import diet.help.pacient.pacienthelpdiet.Servicios.FirebaseReferences;


public class Consulta_Dietas_Activity extends AppCompatActivity  {
// cambio sin rama local
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
    TextView nombreDetalle;
    TextView detalleDetalle;
    LinearLayout myLinearLayout;
    LinearLayout myLinearLayout2;
    ImageView foto;
    String [] resumenstr;

    Dieta dieta;
    ArrayList<Consulta>listaPersonajes;
    RecyclerView recyclerPersonajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_dietas);
        myLinearLayout =(LinearLayout)findViewById(R.id.myLinear);
        myLinearLayout2 =(LinearLayout)findViewById(R.id.myLinear2);
        foto=(ImageView)findViewById(R.id.idImagenDetalle);
        nombreDetalle =(TextView)findViewById(R.id.idNombreDetalle) ;
        detalleDetalle=(TextView)findViewById(R.id.idDetalleDetalle) ;
        listaPersonajes = new ArrayList<>();
        recyclerPersonajes = (RecyclerView) findViewById(R.id.RecyclerId);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(this));
        resumenstr = new String[5];
        //llenarPersonajesVo();


        // Inflate the layout for this fragment
        //View view=inflater.inflate(R.layout.fragment_consulta_dietas_, container, false);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDate= new Date();
        final String fechaHoy=dateFormat.format(fechaDate);
        fecha= (TextView) findViewById(R.id.textFecha);
//        meriendaResumen= (TextView) view.findViewById(R.id.meriendaResumen);
//        desayunoResumen= (TextView) view.findViewById(R.id.desayunoResumen);
//        almuerzoResumen= (TextView) view.findViewById(R.id.almuerzoResumen);


       fecha.setText("Fecha hoy:"+ fechaHoy);
       cantidadDietas= (TextView) findViewById(R.id.textView8);


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
                final HashMap<String, HashMap<String,String>> resumen = new HashMap<String, HashMap<String,String>>();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Dieta ali=ds.getValue(Dieta.class);
                    System.out.print(dateFormat.format(ali.getFecha()));
                    String fechaDieta =dateFormat.format(ali.getFecha());
                    if(fechaHoy.equals(fechaDieta)){
                        cantidad++;
                        keysDietas.add(ds.getKey());
                        catalogoDietas.put(ds.getKey(),ali);
                        if (!resumen.containsKey(ali.getHorario())){
                            HashMap<String, String> hmap = new HashMap<String, String>();
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
                                resumen.get(horarioDieta).put(det.getDietaKey()+";;"+tipoDieta,catalogoDietas.get(det.getDietaKey()).getObservaciones());

                                /*
                                if(!resumen.get(horarioDieta).containsKey(tipoDieta)){
                                    resumen.get(horarioDieta).put(tipoDieta,1);
                                }else {
                                    resumen.get(horarioDieta).put(tipoDieta,resumen.get(horarioDieta).get(tipoDieta)+1);
                                }
                                */
                            }
                        }

                        String strResumen="";
                        int cantidad_unitaria=0;
                        ArrayList<Consulta> listaDetalles = new ArrayList<>();
                        if (resumen.containsKey("Desayuno")&&!resumen.get("Desayuno").isEmpty())
                            for (Map.Entry<String, String> entry : resumen.get("Desayuno").entrySet()) {
                                String key = entry.getKey();
                                String tipoDieta=key.split(";;")[1];
                                listaDetalles.add(new Consulta(tipoDieta,entry.getValue()));
                                cantidad_unitaria++;
                            }
                        //desayunoResumen.setText(strResumen);
                        resumenstr[0]=String.valueOf(cantidad_unitaria)+" Desayunos para hoy";
                        listaPersonajes.add(new Consulta("Desayunos",resumenstr[0],R.drawable.desayuno,listaDetalles));

                        strResumen="";
                        cantidad_unitaria=0;
                        listaDetalles = new ArrayList<>();
                        if (resumen.containsKey("Almuerzo")&&!resumen.get("Almuerzo").isEmpty())
                            for (Map.Entry<String, String> entry : resumen.get("Almuerzo").entrySet()) {
                                String key = entry.getKey();
                                String tipoDieta=key.split(";;")[1];
                                listaDetalles.add(new Consulta(tipoDieta,entry.getValue()));
                                cantidad_unitaria++;
                            }
                        //almuerzoResumen.setText(strResumen);
                        resumenstr[1]=String.valueOf(cantidad_unitaria)+" Almuerzos para hoy";
                        listaPersonajes.add(new Consulta("Almuerzos",resumenstr[1],R.drawable.almuerzos,listaDetalles));


                        strResumen="";
                        cantidad_unitaria=0;
                        listaDetalles = new ArrayList<>();
                        if (resumen.containsKey("Merienda")&&!resumen.get("Merienda").isEmpty())
                            for (Map.Entry<String, String> entry : resumen.get("Merienda").entrySet()) {
                                String key = entry.getKey();
                                String tipoDieta=key.split(";;")[1];
                                listaDetalles.add(new Consulta(tipoDieta,entry.getValue()));
                                cantidad_unitaria++;
                            }
                        //meriendaResumen.setText(strResumen);
                        resumenstr[2]=String.valueOf(cantidad_unitaria)+" Meriendas para hoy";
                        listaPersonajes.add(new Consulta("Meriendas",resumenstr[2],R.drawable.meriendas,listaDetalles));

                        cantidad_unitaria=0;
                        listaDetalles = new ArrayList<>();
                        if (resumen.containsKey("Colacion 1")&&!resumen.get("Colacion 1").isEmpty())
                            for (Map.Entry<String, String> entry : resumen.get("Colacion 1").entrySet()) {
                                String key = entry.getKey();
                                String tipoDieta=key.split(";;")[1];
                                listaDetalles.add(new Consulta(tipoDieta,entry.getValue()));
                                cantidad_unitaria++;
                            }
                        //meriendaResumen.setText(strResumen);
                        resumenstr[3]=String.valueOf(cantidad_unitaria)+" Colaciones 1 para hoy";
                        listaPersonajes.add(new Consulta("Colacion 1",resumenstr[3],R.drawable.colacion1,listaDetalles));


                        cantidad_unitaria=0;
                        listaDetalles = new ArrayList<>();
                        if (resumen.containsKey("Colacion 2")&&!resumen.get("Colacion 2").isEmpty())
                            for (Map.Entry<String, String> entry : resumen.get("Colacion 2").entrySet()) {
                                String key = entry.getKey();
                                String tipoDieta=key.split(";;")[1];
                                listaDetalles.add(new Consulta(tipoDieta,entry.getValue()));
                                cantidad_unitaria++;
                            }
                        //meriendaResumen.setText(strResumen);
                        resumenstr[4]=String.valueOf(cantidad_unitaria)+" Colaciones 2 para hoy";
                        listaPersonajes.add(new Consulta("Colacion 2",resumenstr[4],R.drawable.colacion2,listaDetalles));




                        Consulta_Adaptador adapter=new Consulta_Adaptador(listaPersonajes);
                        recyclerPersonajes.setAdapter(adapter);
                        recyclerPersonajes.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                            @Override
                            public void onRequestDisallowInterceptTouchEvent(boolean b) {

                            }

                            @Override
                            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                                try {
                                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                                    if (child != null ) {

                                        int position = recyclerView.getChildAdapterPosition(child);




                                        listaPersonajes= listaPersonajes.get(position).getListaDetalles();
                                        if (!listaPersonajes.isEmpty()){
                                            switch (position){
                                                case 0:
                                                    nombreDetalle.setText("Desayunos");
                                                    detalleDetalle.setText(resumenstr[0]);
                                                    foto.setImageResource(R.drawable.desayuno);
                                                    cantidadDietas.setText("");
                                                    break;
                                                case 1:
                                                    nombreDetalle.setText("Almuerzos");
                                                    detalleDetalle.setText(resumenstr[1]);
                                                    foto.setImageResource(R.drawable.almuerzos);
                                                    cantidadDietas.setText("");
                                                    break;
                                                case 2:
                                                    nombreDetalle.setText("Meriendas");
                                                    detalleDetalle.setText(resumenstr[2]);
                                                    foto.setImageResource(R.drawable.meriendas);
                                                    cantidadDietas.setText("");
                                                    break;
                                                case 3:
                                                    nombreDetalle.setText("Colacion 1");
                                                    detalleDetalle.setText(resumenstr[3]);
                                                    foto.setImageResource(R.drawable.colacion1);
                                                    cantidadDietas.setText("");
                                                    break;
                                                case 4:
                                                    nombreDetalle.setText("Colacion 2");
                                                    detalleDetalle.setText(resumenstr[4]);
                                                    foto.setImageResource(R.drawable.colacion2);
                                                    cantidadDietas.setText("");
                                                    break;
                                            }
                                            myLinearLayout2.setVisibility(View.VISIBLE);
                                            myLinearLayout.setVisibility(View.VISIBLE);
                                            Consulta_Adaptador_Detalle adapter=new Consulta_Adaptador_Detalle(listaPersonajes);
                                            recyclerPersonajes.setAdapter(adapter);
                                        }




                                        //Toast.makeText(MyActivity.this,"The Item Clicked is: "+ position ,Toast.LENGTH_SHORT).show();

                                        return true;
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                return false;
                            }

                            @Override
                            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

                            }
                        });

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
    }


}
