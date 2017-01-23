package Modelo;

public class Ticket {
	   
    private int id;
    private String nombre;
    private Cobro cobro;
    private String tipoCobro;
    private Proyecto proyecto;

    public Ticket() {
        this.id = 0;
        this.nombre = "";
        this.cobro = null;
        this.tipoCobro = "";
        this.proyecto = null;
    }

    public Ticket(String nombre, Cobro cobro, String tipoCobro, Proyecto proyecto) {
        this.id = 0;
        this.nombre = nombre;
        this.cobro = cobro;
        this.tipoCobro = tipoCobro;
        this.proyecto = proyecto;
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

    public Cobro getCobro() {
        return cobro;
    }

    public void setCobro(Cobro cobro) {
        this.cobro = cobro;
    }

    public String getTipoCobro() {
		return tipoCobro;
	}

	public void setTipoCobro(String tipoCobro) {
		this.tipoCobro = tipoCobro;
	}

	public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

  
    
}
