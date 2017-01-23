package Modelo;



public class Pais {
	 
	    private String cc_fips;
	    private String cc_iso;
	    private String tld;
	    private String nombre_spanish;
	    private String nombre_english;
	    private String nombre_arabic;

	    public Pais() {

	     
	        this.nombre_spanish = "";
	        this.nombre_english = "";
	        this.nombre_arabic = "";
	    }

	    public Pais(String nombre_spanish, String nombre_english, String nombre_arabic) {
	  
	        this.nombre_spanish = nombre_spanish;
	        this.nombre_english = nombre_english;
	        this.nombre_arabic = nombre_arabic;
	    }

	    public Pais(String cc_fips, String cc_iso, String tld, String nombre_spanish, String nombre_english, String nombre_arabic) {
	  
	        this.cc_fips = cc_fips;
	        this.cc_iso = cc_iso;
	        this.tld = tld;
	        this.nombre_spanish = nombre_spanish;
	        this.nombre_english = nombre_english;
	        this.nombre_arabic = nombre_arabic;
	    }



	    public String getCc_fips() {
	        return cc_fips;
	    }

	    public void setCc_fips(String cc_fips) {
	        this.cc_fips = cc_fips;
	    }

	    public String getCc_iso() {
	        return cc_iso;
	    }

	    public void setCc_iso(String cc_iso) {
	        this.cc_iso = cc_iso;
	    }

	    public String getTld() {
	        return tld;
	    }

	    public void setTld(String tld) {
	        this.tld = tld;
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


	    
	    
	 
	    
	   
}
