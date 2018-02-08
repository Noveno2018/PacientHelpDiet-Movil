package diet.help.pacient.pacienthelpdiet.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import diet.help.pacient.pacienthelpdiet.Modelos.Consulta;
import diet.help.pacient.pacienthelpdiet.R;

/**
 * Created by Janneth on 31/01/2018.
 */


public class Consulta_Adaptador extends RecyclerView.Adapter<Consulta_Adaptador.ViewHolderPersonajes> {

    ArrayList<Consulta> listPersonajes;

    public Consulta_Adaptador(ArrayList<Consulta> listPersonajes) {
        this.listPersonajes = listPersonajes;
    }

    @Override
    public ViewHolderPersonajes onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.consulta_recycler,null,false);
        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPersonajes holder, int position) {
        holder.etiNombre.setText(listPersonajes.get(position).getNombre());
        holder.etiInformacion.setText(listPersonajes.get(position).getInfo());
        holder.foto.setImageResource(listPersonajes.get(position).getFoto());

    }

    @Override
    public int getItemCount()
    {
        return listPersonajes.size();
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {
        TextView etiNombre,etiInformacion;
        ImageView foto;

        public ViewHolderPersonajes(View itemView) {
            super(itemView);
            etiNombre=(TextView)itemView.findViewById(R.id.idNombre);
            etiInformacion=(TextView)itemView.findViewById(R.id.idInfo);
            foto=(ImageView)itemView.findViewById(R.id.idImagen);

        }
    }
}
