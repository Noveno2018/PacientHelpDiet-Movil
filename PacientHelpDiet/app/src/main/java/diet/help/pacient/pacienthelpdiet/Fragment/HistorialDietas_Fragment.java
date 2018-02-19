package diet.help.pacient.pacienthelpdiet.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import diet.help.pacient.pacienthelpdiet.Adaptadores.AdapterDetalleDietaHistorial;
import diet.help.pacient.pacienthelpdiet.Modelos.DetalleDieta;
import diet.help.pacient.pacienthelpdiet.Modelos.Dieta;
import diet.help.pacient.pacienthelpdiet.R;
import diet.help.pacient.pacienthelpdiet.Servicios.FirebaseReferences;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistorialDietas_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class HistorialDietas_Fragment extends Fragment {

    public HistorialDietas_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HistorialDietas_Fragment.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rv;
    EditText txtFecha;
    Button btnCalendario;
    ArrayList<Dieta> dietas;
    ArrayList<DetalleDieta>detalleDietas;
    Calendar C = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista=inflater.inflate(R.layout.fragment_historial_dietas_, container, false);
        txtFecha=(EditText) vista.findViewById(R.id.txtFechaDietaFiltrar);
        //btnTeclado=(Button) vista.findViewById(R.id.btnTeclado);
        btnCalendario=(Button) vista.findViewById(R.id.btnCalendario);

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,C.get(Calendar.YEAR),C.get(Calendar.MONTH),C.get(Calendar.DAY_OF_MONTH)).show();
            }

        });


        txtFecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(vista.getWindowToken(), 0);
            }
        });

        rv = (RecyclerView) vista.findViewById(R.id.rvwDetalledieta);
        rv.setLayoutManager(new LinearLayoutManager(getContext( )));
        dietas=  new ArrayList<>();
        detalleDietas=new ArrayList<>();
        final AdapterDetalleDietaHistorial adapter=new AdapterDetalleDietaHistorial(dietas);
        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        rv.setAdapter(adapter);

        String myUserId = "-L0ePqWn-zBqUUx8FTS8";
        DatabaseReference dietasreferences=database.getReference(FirebaseReferences.DIETA_REFERENCIAS);
        final DatabaseReference detalledietas =database.getReference(FirebaseReferences.DETALLE_REFERENCIAS);
        dietasreferences.orderByChild("pacienteKey").equalTo(myUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dietas.removeAll(dietas);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    final Dieta dieta =snapshot.getValue(Dieta.class);
                    dieta.setKey(snapshot.getKey());
                    detalledietas.orderByChild("dietaKey").equalTo(dieta.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()){
                                DetalleDieta detallediet=ds.getValue(DetalleDieta.class);
                                detallediet.setKey(ds.getKey());
                                detalleDietas.add(detallediet);
                                dieta.setListadetalledieta(detalleDietas);
                                dietas.add(dieta);
                            }
                            dieta.getListadetalledieta();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        txtFecha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return vista;
    }
    private DatePickerDialog.OnDateSetListener date =
            new DatePickerDialog.OnDateSetListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    C.set(Calendar.YEAR,year);
                    C.set(Calendar.MONTH,monthOfYear);
                    C.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    updateLabel();
                }

            };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void updateLabel(){
        String myFormat="yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.forLanguageTag("ECUADOR"));
        txtFecha.setText(sdf.format(C.getTime()));
    }

    // TODO: Rename method, update argument and hook method into UI event



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
