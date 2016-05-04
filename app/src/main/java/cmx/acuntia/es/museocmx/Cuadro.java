package cmx.acuntia.es.museocmx;

public class Cuadro {

    private String nombre;
    private String autor;
    private String descripcion;
    private int categoria;
    private int imagen;

    public Cuadro(String nombre, String autor, int categoria, String descripcion, int imagen){
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setDescripción(String descripcion){
        this.descripcion = descripcion;
    }

    public void setCategoria(int categoria){
        this.categoria=categoria;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre(){return nombre;}
    public String getAutor(){return autor;}
    public int getCategoria(){return categoria;}
    public String getDescripcion(){return descripcion;}
    public int getImagen() {
        return imagen;
    }
}
