package diet.help.pacient.pacienthelpdiet.Modelos;

import java.util.ArrayList;

/**
 * Created by Janneth on 31/01/2018.
 */

public class Consulta {
    private String nombre;
    private String info;
    private int foto;
     private ArrayList<Consulta> listaDetalles;

    public Consulta() {
    }

    public Consulta(String nombre, String info) {
        this.nombre = nombre;
        this.info = info;

    }
    public Consulta(String nombre, String info, int foto) {
        this.nombre = nombre;
        this.info = info;
        this.foto = foto;
    }

    public Consulta(String nombre, String info, int foto,ArrayList<Consulta> listaDetalles) {
        this.nombre = nombre;
        this.info = info;
        this.foto = foto;
        this.listaDetalles=listaDetalles;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public ArrayList<Consulta> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(ArrayList<Consulta> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
}
