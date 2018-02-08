package diet.help.pacient.pacienthelpdiet.Modelos;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mauuu on 18/1/2018.
 */

public class Hospitalizacion {
    private String key;
    private boolean active;
    private String fechaIngreso;
    private String motivoIngreso;
    private String numeroCama;
    private String observaciones;
    private String pacienteKey;
    private ArrayList<Paciente> pacientes;

    public Hospitalizacion() {
    }

    public Hospitalizacion(String key, boolean active, String fechaIngreso, String motivoIngreso, String numeroCama, String observaciones, String pacienteKey) {
        this.key = key;
        this.active = active;
        this.fechaIngreso = fechaIngreso;
        this.motivoIngreso = motivoIngreso;
        this.numeroCama = numeroCama;
        this.observaciones = observaciones;
        this.pacienteKey = pacienteKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    public String getNumeroCama() {
        return numeroCama;
    }

    public void setNumeroCama(String numeroCama) {
        this.numeroCama = numeroCama;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPacienteKey() {
        return pacienteKey;
    }

    public void setPacienteKey(String pacienteKey) {
        this.pacienteKey = pacienteKey;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
