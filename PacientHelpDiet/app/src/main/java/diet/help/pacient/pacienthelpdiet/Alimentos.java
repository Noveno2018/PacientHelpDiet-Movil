package diet.help.pacient.pacienthelpdiet;

/**
 * Created by mauuu on 14/12/2017.
 */

public class Alimentos {

    String img,nombre;

    public Alimentos() {
    }

    public Alimentos(String img, String nombre) {
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
