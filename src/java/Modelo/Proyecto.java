package Modelo;

public class Proyecto {
    private int id;
    private String nombre;
    private Entidad entidad;

   public Proyecto() {
       this.id = 0;
       this.nombre = "";
       this.entidad = null;
   }

   public Proyecto( String nombre, Entidad entidad) {
       this.id = 0;
       this.nombre = nombre;
       this.entidad = entidad;
   }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public Entidad getEntidad() {
       return entidad;
   }

   public void setEntidad(Entidad entidad) {
       this.entidad = entidad;
   }

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}
    
    
}
