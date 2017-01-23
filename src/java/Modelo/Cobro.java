package Modelo;

public class Cobro {
    
  private int id;
  private String nombre;

  public Cobro() {
         this.id = 0;
      this.nombre = "";
  }

  public Cobro(String nombre) {
      this.id = 0;
      this.nombre = nombre;
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
  
  
}
