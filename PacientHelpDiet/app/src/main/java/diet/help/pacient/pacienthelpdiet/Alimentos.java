package diet.help.pacient.pacienthelpdiet;

/**
 * Created by mauuu on 14/12/2017.
 */

public class Alimentos {

    String img,nombre,tipodieta_key;

    public Alimentos() {
    }

    public Alimentos(String img, String nombre, String tipodieta_key) {
        this.img = img;
        this.nombre = nombre;
        this.tipodieta_key = tipodieta_key;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipodieta_key() {
        return tipodieta_key;
    }

    public void setTipodieta_key(String tipodieta_key) {
        this.tipodieta_key = tipodieta_key;
    }
}
