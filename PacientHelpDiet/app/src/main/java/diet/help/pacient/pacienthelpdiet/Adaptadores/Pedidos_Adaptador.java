package diet.help.pacient.pacienthelpdiet.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.R;

/**
 * Created by mauuu on 26/12/2017.
 */

public class Pedidos_Adaptador extends RecyclerView.Adapter<Pedidos_Adaptador.AlimentoViewHolder>{

    ArrayList<Sugerencias> alimentos;

    public Pedidos_Adaptador(ArrayList<Sugerencias> alimentos) {
        this.alimentos = alimentos;
    }

    @Override
    public Pedidos_Adaptador.AlimentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_sugerencias,parent,false);
        return new Pedidos_Adaptador.AlimentoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Pedidos_Adaptador.AlimentoViewHolder holder, int position) {
        Sugerencias alimento=alimentos.get(position);
        Glide.with(holder.itemView.getContext()).load(alimento.getImg()).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img);
        holder.nombre.setText(alimento.getNombre());
    }

    @Override
    public int getItemCount() {
        return alimentos.size();
    }

    public static class AlimentoViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView nombre;
        public AlimentoViewHolder(View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.ig_img);

            nombre=(TextView) itemView.findViewById(R.id.tv_nombre);
        }
    }
}
