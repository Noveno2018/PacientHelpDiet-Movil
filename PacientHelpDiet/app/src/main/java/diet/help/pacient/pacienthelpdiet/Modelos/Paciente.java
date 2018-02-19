package diet.help.pacient.pacienthelpdiet.Modelos;

/**
 * Created by mauuu on 12/12/2017.
 */

public class Paciente {
    String cedula,nombre,apellido,telefono,direccion, estadocivil,fechanacimiento,alergias,tiposangre,antecendentes,userKey,key,img;

    public Paciente() {
    }

    public Paciente(String cedula, String nombre, String apellido, String telefono, String direccion, String estadocivil, String fechanacimiento, String alergias, String tiposangre, String antecendentes, String userKey) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estadocivil = estadocivil;
        this.fechanacimiento = fechanacimiento;
        this.alergias = alergias;
        this.tiposangre = tiposangre;
        this.antecendentes = antecendentes;
        this.userKey = userKey;
    }

    public Paciente(String cedula, String nombre, String apellido, String telefono, String direccion, String estadocivil, String fechanacimiento, String alergias, String tiposangre, String antecendentes, String userKey, String key) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estadocivil = estadocivil;
        this.fechanacimiento = fechanacimiento;
        this.alergias = alergias;
        this.tiposangre = tiposangre;
        this.antecendentes = antecendentes;
        this.userKey = userKey;
        this.key = key;
    }

    public Paciente(String cedula, String nombre, String apellido, String key) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.key = key;
    }

    public Paciente(String nombre, String apellido, String antecendentes, String key, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.antecendentes = antecendentes;
        this.key = key;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getTiposangre() {
        return tiposangre;
    }

    public void setTiposangre(String tiposangre) {
        this.tiposangre = tiposangre;
    }

    public String getAntecendentes() {
        return antecendentes;
    }

    public void setAntecendentes(String antecendentes) {
        this.antecendentes = antecendentes;
    }
}
