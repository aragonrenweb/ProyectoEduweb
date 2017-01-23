package Modelo;


public class Persona {
	
	   private int id;
	    private String nombre;
	    private String apellidos;
	    private String usuario;
	    private String pswd;
	    //TODO cambiar String por date
	    private String fecha_nacimiento;
	    private String dia_nacimiento;
	    private String mes_nacimiento;
	    private String anio_nacimiento;
	    private String email;
            private String genero; 
	    private Pais pais;
	    
	    public Persona(){
	        this.id = 0;
	        this.nombre = "";
	        this.apellidos = "";
	        this.usuario = "";
	        this.pswd = "";
	        this.fecha_nacimiento = "";
	        this.email = "";
                this.genero = "";
	        this.pais = null; 
	    }

	    public Persona(int id, String nombre, String apellidos, String usuario, String pswd, String dia_nacimiento, String mes_naciemiento, String anio_nacimeinto, String email, String genero,Pais pais) {
	     this.id = id;
	        this.nombre = nombre;
	        this.apellidos = apellidos;
	        this.usuario = usuario;
	    
	           
	            this.pswd = Cadena.Encriptar(pswd);
	       this.dia_nacimiento = dia_nacimiento;
	        this.mes_nacimiento = mes_naciemiento;
	        this.anio_nacimiento = anio_nacimeinto;
	        this.fecha_nacimiento = this.anio_nacimiento + "-" + this.mes_nacimiento + "-" + this.dia_nacimiento;
	        this.email = email;
                this.genero = genero;
	        this.pais = pais;
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

	    public String getApellidos() {
	        return apellidos;
	    }

	    public void setApellidos(String apellidos) {
	        this.apellidos = apellidos;
	    }

	    public String getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(String usuario) {
	        this.usuario = usuario;
	    }

	    public String getPswd() {
	        return pswd;
	    }

	    public void setPswd(String pswd) {
	    	
				
		
	        this.pswd = pswd;
	    }

	    public String getFecha_nacimiento() {
	        return fecha_nacimiento;
	    }

	    public void setFecha_nacimiento(String fecha_nacimiento) {
	        this.fecha_nacimiento = fecha_nacimiento;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public Pais getPais() {
	        return pais;
	    }

	    public void setPais(Pais pais) {
	        this.pais = pais;
	    }

	    public String getDia_nacimiento() {
	        return dia_nacimiento;
	    }

	    public void setDia_nacimiento(String dia_nacimiento) {
	        this.dia_nacimiento = dia_nacimiento;
	    }

	    public String getMes_nacimiento() {
	        return mes_nacimiento;
	    }

	    public void setMes_nacimiento(String mes_nacimiento) {
	        this.mes_nacimiento = mes_nacimiento;
	    }

	    public String getAnio_nacimiento() {
	        return anio_nacimiento;
	    }

	    public void setAnio_nacimiento(String anio_nacimiento) {
	        this.anio_nacimiento = anio_nacimiento;
	    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
	    
	 

}
