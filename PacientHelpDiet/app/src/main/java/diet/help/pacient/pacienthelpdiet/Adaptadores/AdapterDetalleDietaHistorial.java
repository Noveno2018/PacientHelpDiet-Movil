package diet.help.pacient.pacienthelpdiet.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import diet.help.pacient.pacienthelpdiet.Modelos.Dieta;

/**
 * Created by Mauro on 12/12/2017.
 */

public class AdapterDetalleDietaHistorial extends RecyclerView.Adapter<AdapterDetalleDietaHistorial.DetalleDietaViewHolder> implements Filterable{

    ArrayList<Dieta> dietas;
    private ArrayList<Dieta> dietasFilter;
    private CustomFilter mFilter;


    public AdapterDetalleDietaHistorial(ArrayList dietas) {
        this.dietas=dietas;
        this.dietasFilter=new ArrayList<>();
        this.dietasFilter.addAll(dietas);
        this.mFilter=new CustomFilter(AdapterDetalleDietaHistorial.this);

    }

    @Override
    public AdapterDetalleDietaHistorial.DetalleDietaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View VistaDatosDieta= LayoutInflater.from(parent.getContext()).inflate(R.layout.elementos_historialdieta_paciente,parent,false);
        DetalleDietaViewHolder detalledietasholder=new DetalleDietaViewHolder(VistaDatosDieta);
        return detalledietasholder;
    }


    @Override
    public void onBindViewHolder(DetalleDietaViewHolder holder, final int position) {
            Dieta dieta = dietas.get(position);
            holder.txtObservacionDieta.setText(dietasFilter.get(position).getObservaciones());
            holder.txtObservacionDieta.setText(dietasFilter.get(position).getObservaciones());
            holder.txtHorarioEntregaDieta.setText(dietasFilter.get(position).getHorario());
            Date fechaDieta = dietasFilter.get(position).getFecha();
            DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
            String fechaDietaConv = fecha.format(fechaDieta);
            holder.lbltFechaDieta.setText(fechaDietaConv);
            holder.txtEspecificacionDieta.setText(dietasFilter.get(position).getListadetalledieta().get(position).getEspecificaciones());
            //holder.txtEspecificacionDieta.setText(dieta.getListadetalledieta().get(position).getEspecificaciones())
    }
    @Override
    public int getItemCount() {

        return dietasFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public  static class DetalleDietaViewHolder extends RecyclerView.ViewHolder{
        TextView lblHorarioEntregaDieta,lblObservacion,txtHorarioEntregaDieta, txtObservacionDieta,lbltFechaDieta,txtEspecificacionDieta;
        public DetalleDietaViewHolder(View itemView) {
            super(itemView);
            txtHorarioEntregaDieta=(TextView) itemView.findViewById(R.id.txtHorario);
            txtObservacionDieta=(TextView)itemView.findViewById(R.id.txtObservaciones);
            lblHorarioEntregaDieta=(TextView) itemView.findViewById(R.id.lbHorario);
            lblObservacion=(TextView) itemView.findViewById(R.id.lbObservacion);
            lbltFechaDieta=(TextView) itemView.findViewById(R.id.lbFecha);
            txtEspecificacionDieta=(TextView) itemView.findViewById(R.id.txtEspecificacion);

        }
    }

    /*Filtro*/
    public class CustomFilter extends Filter{
        private AdapterDetalleDietaHistorial listAdapter;

        private CustomFilter(AdapterDetalleDietaHistorial listAdapter){
            super();
            this.listAdapter=listAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            dietasFilter.clear();
            final FilterResults results =new FilterResults();
            if (constraint.length()==0){
                dietasFilter.addAll(dietas);
            }else{
                final String filterPattern=constraint.toString().toLowerCase().trim();
                for (final Dieta dieta:dietas){
                    Date d =dieta.getFecha();
                    DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
                    String convertido = fecha.format(d);
                    if( convertido.toLowerCase().contains(filterPattern)){
                        dietasFilter.add(dieta);
                    }
                }
            }
            results.values=dietasFilter;
            results.count=dietasFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.listAdapter.notifyDataSetChanged();

        }
    }

}
