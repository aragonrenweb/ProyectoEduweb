package Modelo;

public class Funcion {
	   private int id;
	    private String nombre;
	    private String descripcion;
	    private Entidad entidad;

	    public Funcion() {
	        this.id = 0;
	        this.nombre = "";
	        this.descripcion = "";
	        this.entidad = null;
	    }

	    
	    public Funcion( String nombre, String descripcion) {
	        this.id = 0;
	        this.nombre = nombre;
	        this.descripcion = descripcion;
	        this.entidad = null;
	    }
	    public Funcion( String nombre, String descripcion, Entidad entidad){
	    	this.id = 0;
	    	this.nombre = nombre;
	    	this.descripcion = descripcion;
	    	this.entidad = entidad;
	    	
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

	    public String getDescripcion() {
	        return descripcion;
	    }

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }


		public Entidad getEntidad() {
			return entidad;
		}


		public void setEntidad(Entidad entidad) {
			this.entidad = entidad;
		}
	    
	    
	 
}
