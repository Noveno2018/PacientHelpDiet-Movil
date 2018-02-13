package diet.help.pacient.pacienthelpdiet.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import diet.help.pacient.pacienthelpdiet.Modelos.Dieta;
import diet.help.pacient.pacienthelpdiet.R;

/**
 * Created by Isaac on 12/12/2017.
 */

public class AdapterDetalleDieta extends RecyclerView.Adapter<AdapterDetalleDieta.DetalleDietaViewHolder>  {
    ArrayList<Dieta> dietas1;


    public AdapterDetalleDieta(ArrayList<Dieta> dietas1) {
        this.dietas1 = dietas1;
    }

    @Override
    public AdapterDetalleDieta.DetalleDietaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View VistaDatosDieta= LayoutInflater.from(parent.getContext()).inflate(R.layout.elementos_detalle_dieta,parent,false);
        DetalleDietaViewHolder detalledietasholder=new DetalleDietaViewHolder(VistaDatosDieta);
        return detalledietasholder;
    }

    @Override
    public void onBindViewHolder(DetalleDietaViewHolder holder, int position) {
        Dieta dieta =dietas1.get(position);
            holder.txtHorarioEntregaDieta.setText(dieta.getHorario());
            holder.txtDescripcion.setText(dieta.getObservaciones());
    }

    @Override
    public int getItemCount() {
        return dietas1.size();
    }

    public  static class DetalleDietaViewHolder extends RecyclerView.ViewHolder{
        TextView lblHorarioEntregaDieta, lblDescripcion;
        EditText txtHorarioEntregaDieta, txtDescripcion;
        public DetalleDietaViewHolder(View itemView) {
            super(itemView);
            txtHorarioEntregaDieta=(EditText) itemView.findViewById(R.id.txtHorario);
            lblHorarioEntregaDieta=(TextView) itemView.findViewById(R.id.lbHorario);
            txtDescripcion=(EditText) itemView.findViewById(R.id.txtDescripcion);
            lblDescripcion=(TextView) itemView.findViewById(R.id.lblDescripcion);
        }
    }
}
