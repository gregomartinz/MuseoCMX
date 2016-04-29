package cmx.acuntia.es.museocmx;

public class Cuadro {

    private String nombre;
    private String autor;
    private String descripcion;
    private int categoria;

    public Cuadro(String nombre, String autor, int categoria, String descripcion){
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.descripcion = descripcion;
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

    public String getNombre(){return nombre;}
    public String getAutor(){return autor;}
    public int getCategoria(){return categoria;}
    public String getDescripcion(){return descripcion;}
}
