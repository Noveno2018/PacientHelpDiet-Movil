package diet.help.pacient.pacienthelpdiet.Modelos;

/**
 * Created by mauuu on 14/12/2017.
 */

public class Sugerencias {

    String img,nombre;

    public Sugerencias() {
    }

    public Sugerencias(String img, String nombre) {
        this.img = img;
        this.nombre = nombre;
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
}
