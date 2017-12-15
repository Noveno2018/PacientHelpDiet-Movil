package diet.help.pacient.pacienthelpdiet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by mauuu on 14/12/2017.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.AlimentoViewHolder>{

    ArrayList<Alimentos> alimentos;

    public Adaptador(ArrayList<Alimentos> alimentos) {
        this.alimentos = alimentos;
    }

    @Override
    public AlimentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento,parent,false);
        return new AlimentoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AlimentoViewHolder holder, int position) {
        Alimentos alimento=alimentos.get(position);
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
