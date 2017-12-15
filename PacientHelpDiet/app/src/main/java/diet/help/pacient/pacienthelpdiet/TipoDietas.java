package diet.help.pacient.pacienthelpdiet;

import java.util.ArrayList;

/**
 * Created by mauuu on 12/12/2017.
 */

public class TipoDietas {

    private String tipo, descripcion,key;

    public TipoDietas() {
    }

    public TipoDietas(String tipo, String descripcion, String key) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.key = key;
    }

    public TipoDietas(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
