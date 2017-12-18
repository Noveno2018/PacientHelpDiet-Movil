package diet.help.pacient.pacienthelpdiet.Modelos;

import java.util.ArrayList;

/**
 * Created by mauuu on 12/12/2017.
 */

public class DetalleDieta {
    String dietaKey,especificaciones,tipodieta_key;


    public DetalleDieta() {
    }

    public DetalleDieta(String dietaKey, String especificaciones, String tipodieta_key) {
        this.dietaKey = dietaKey;
        this.especificaciones = especificaciones;
        this.tipodieta_key = tipodieta_key;
    }

    public String getDietaKey() {
        return dietaKey;
    }

    public void setDietaKey(String dietaKey) {
        this.dietaKey = dietaKey;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getTipodieta_key() {
        return tipodieta_key;
    }

    public void setTipodieta_key(String tipodieta_key) {
        this.tipodieta_key = tipodieta_key;
    }
}
