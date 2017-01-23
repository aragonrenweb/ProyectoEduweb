package Modelo;

public class Entidad {
    
    private int id;
    private String nombre;
    private String district_code;
    private Domicilio domicilio;

public Entidad() {
        this.id = 0;
    this.nombre = "";
    this.district_code = "";
    this.domicilio = null;
}

public Entidad( String nombre, String district_code, Domicilio domicilio) {
    this.id = 0;
    this.nombre = nombre;
    this.district_code = district_code;
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

public String getDistrict_code() {
    return district_code;
}

public void setDistrict_code(String district_code) {
    this.district_code = district_code;
}

public Domicilio getDomicilio() {
	return domicilio;
}

public void setDomicilio(Domicilio domicilio) {
	this.domicilio = domicilio;
}


    


}
