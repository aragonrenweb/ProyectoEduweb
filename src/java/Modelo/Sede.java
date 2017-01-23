package Modelo;

public class Sede {
	  private int id;
	    private String nombre;
	    private Entidad entidad;
	    private Domicilio domicilio;

	    public Sede() {
	        
	        this.id = 0;
	        this.nombre = "";
	        this.entidad = null;
	        this.domicilio = null;
	    }

	    public Sede(String nombre, Entidad entidad, Domicilio domicilio) {
	        this.id = 0;
	        this.nombre = nombre;
	        this.entidad = entidad;
	        this.domicilio = domicilio;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public Entidad getEntidad() {
	        return entidad;
	    }

	    public void setEntidad(Entidad entidad) {
	        this.entidad = entidad;
	    }

	    public Domicilio getDomicilio() {
	        return domicilio;
	    }

	    public void setDomicilio(Domicilio domicilio) {
	        this.domicilio = domicilio;
	    }
	    
}
