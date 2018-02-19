package diet.help.pacient.pacienthelpdiet.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import diet.help.pacient.pacienthelpdiet.Modelos.Paciente;
import diet.help.pacient.pacienthelpdiet.Modelos.Sugerencias;
import diet.help.pacient.pacienthelpdiet.R;

public class ContenedorDietas_Fragment extends Fragment {

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EventBus eventBus=EventBus.getDefault();
    ImageView img;
    TextView nombre;
    Paciente pacient;

    public ContenedorDietas_Fragment() {
    }

    @SuppressLint("ValidFragment")
    public ContenedorDietas_Fragment(Paciente pacient) {
        this.pacient = pacient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_contenedor_dietas_, container, false);
        View contenedor=(View)container.getParent();
        appBarLayout=(AppBarLayout)contenedor.findViewById(R.id.appbar);
        tabLayout=new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        appBarLayout.addView(tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.vw_pager);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        img=(ImageView)view.findViewById(R.id.iv_paciente);
        nombre=(TextView)view.findViewById(R.id.tv_nombre_paciente);
        Glide.with(getContext()).load(pacient.getImg()).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(img);
        nombre.setText(pacient.getNombre()+" "+pacient.getApellido());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBarLayout.removeView(tabLayout);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter{
        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        String [] titulotabs={"Crear Registro","Añadir Sugerencias"};

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:return new RegistroDietas_Fragment(pacient);
                case 1:return new ListaSugerencia_Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulotabs[position];
        }
    }
}
