package Modelo;

public class Telefono {
	   private int id;
	     private String numero;

	    public Telefono() {
	        this.id = 0;
	        this.numero = "";
	    }

	    public Telefono( String numero) {
	        this.id = 0;
	        this.numero = numero;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getNumero() {
	        return numero;
	    }

	    public void setNumero(String numero) {
	        this.numero = numero;
	    }
}
