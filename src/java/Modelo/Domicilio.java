package Modelo;

public class Domicilio {
	 private int id;
	    private String lineaUno;
	    private String lineaDos;
	    private String cp;
	    private Pais pais;
	    private Ciudad ciudad;

	    public Domicilio() {
	         this.id = 0;
	        this.lineaUno = "";
	        this.lineaDos = "";
	        this.cp = "";
	        this.pais=null;
	        this.ciudad=null;
	    }

	    public Domicilio(String lineaUno, String lineaDos, String cp, Pais pais,Ciudad ciudad) {
	        this.lineaUno = lineaUno;
	        this.lineaDos = lineaDos;
	        this.cp = cp;
	        this.pais=pais;
	        this.ciudad=ciudad;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getLineaUno() {
	        return lineaUno;
	    }

	    public void setLineaUno(String lineaUno) {
	        this.lineaUno = lineaUno;
	    }

	    public String getLineaDos() {
	        return lineaDos;
	    }

	    public void setLineaDos(String lineaDos) {
	        this.lineaDos = lineaDos;
	    }

	    public String getCp() {
	        return cp;
	    }

	    public void setCp(String cp) {
	        this.cp = cp;
	    }

	    public Pais getPais() {
	        return pais;
	    }

	    public void setPais(Pais pais) {
	        this.pais = pais;
	    }

	    public Ciudad getCiudad() {
	        return ciudad;
	    }

	    public void setCiudad(Ciudad ciudad) {
	        this.ciudad = ciudad;
	    }
	    
	    
}
