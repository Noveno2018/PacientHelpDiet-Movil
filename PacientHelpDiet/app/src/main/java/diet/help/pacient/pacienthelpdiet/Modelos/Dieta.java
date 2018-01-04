package diet.help.pacient.pacienthelpdiet.Modelos;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mauuu on 12/12/2017.
 */

public class Dieta {

    private String horario,pacienteKey,observaciones,key;
    private Date fecha;
    private ArrayList<DetalleDieta> listadetalledieta;

    public Dieta() {
    }

    public Dieta(String horario, String pacienteKey, String observaciones, Date fecha) {
        this.horario = horario;
        this.pacienteKey = pacienteKey;
        this.observaciones = observaciones;
        this.fecha = fecha;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<DetalleDieta> getListadetalledieta() {
        return listadetalledieta;
    }

    public void setListadetalledieta(ArrayList<DetalleDieta> listadetalledieta) {
        this.listadetalledieta = listadetalledieta;
    }
}
