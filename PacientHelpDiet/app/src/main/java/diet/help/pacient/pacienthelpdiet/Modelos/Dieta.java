package diet.help.pacient.pacienthelpdiet.Modelos;

import java.util.ArrayList;

/**
 * Created by mauuu on 12/12/2017.
 */

public class Dieta {

    private String horario,pacienteKey,fecha,observaciones;
    private ArrayList<DetalleDieta> listadetalledieta=new ArrayList<DetalleDieta>();

    public Dieta() {
    }

    public Dieta(String horario, String pacienteKey, String fecha, String observaciones) {
        this.horario = horario;
        this.pacienteKey = pacienteKey;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPacienteKey() {
        return pacienteKey;
    }

    public void setPacienteKey(String pacienteKey) {
        this.pacienteKey = pacienteKey;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ArrayList<DetalleDieta> getListadetalledieta() {
        return listadetalledieta;
    }

    public void setListadetalledieta(ArrayList<DetalleDieta> listadetalledieta) {
        this.listadetalledieta = listadetalledieta;
    }
}
