package Modelo;

public class Ciudad {
	
    private int id;
    private String nombre_spanish;
    private String nombre_english;
    private String nombre_arabic;
    private Pais pais;
    
    public Ciudad(){
    	this.id = 0;
    	this.nombre_spanish = "";
    	this.nombre_english = "";
    	this.nombre_arabic = "";
    	this.pais = null;
    }
	public Ciudad(int id, String nombre_spanish, String nombre_english, String nombre_arabic, Pais pais) {
		
		this.id = id;
		this.nombre_spanish = nombre_spanish;
		this.nombre_english = nombre_english;
		this.nombre_arabic = nombre_arabic;
		this.pais = pais;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_spanish() {
		return nombre_spanish;
	}
	public void setNombre_spanish(String nombre_spanish) {
		this.nombre_spanish = nombre_spanish;
	}
	public String getNombre_english() {
		return nombre_english;
	}
	public void setNombre_english(String nombre_english) {
		this.nombre_english = nombre_english;
	}
	public String getNombre_arabic() {
		return nombre_arabic;
	}
	public void setNombre_arabic(String nombre_arabic) {
		this.nombre_arabic = nombre_arabic;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}

    

}
