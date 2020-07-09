package pe.edu.ulima.pw.restaurantes.forms;

public class EditarPlatoForm {
    private String plato_id;
    private String plato_nombre;
    private String plato_categoria;
    private String plato_estado;

    public EditarPlatoForm() {
    }

    public String getPlato_id() {
        return plato_id;
    }

    public void setPlato_id(String plato_id) {
        this.plato_id = plato_id;
    }

    public String getPlato_estado() {
        return plato_estado;
    }

    public void setPlato_estado(String plato_estado) {
        this.plato_estado = plato_estado;
    }

    public String getPlato_nombre() {
        return plato_nombre;
    }

    public void setPlato_nombre(String plato_nombre) {
        this.plato_nombre = plato_nombre;
    }

    public String getPlato_categoria() {
        return plato_categoria;
    }

    public void setPlato_categoria(String plato_categoria) {
        this.plato_categoria = plato_categoria;
    }
}