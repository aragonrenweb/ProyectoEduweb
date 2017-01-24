/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jesús Aragón
 */
public class Usuario {
    
            private int id_usuario;
	    private String nombre;
	    private String primer_apellido;
            private String segundo_apellido;
            private String email;
	    private String usuario;
	    private String password;
            private int typeuser;
	    //TODO cambiar String por date
	    private String fecha_nacimiento;
	    private String dia_nacimiento;
	    private String mes_nacimiento;
	    private String anio_nacimiento;
	    private String genero; 


    public Usuario() {
        this.id_usuario = 0;
        this.nombre = "";
        this.primer_apellido = "";
        this.segundo_apellido = "";
        this.email = "";
        this.usuario = "";
        this.password = "";
        this.typeuser = 0;
        this.fecha_nacimiento = "";
        this.dia_nacimiento = "";
        this.mes_nacimiento = "";
        this.anio_nacimiento = "";
        this.genero = "";
        
    }
            
    public Usuario(int id_usuario, String nombre, String primer_apellido, String segundo_apellido, String email, String usuario, String password, int typeuser, String fecha_nacimiento, String dia_nacimiento, String mes_nacimiento, String anio_nacimiento, String genero) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
        this.typeuser = typeuser;
        this.fecha_nacimiento = fecha_nacimiento;
        this.dia_nacimiento = dia_nacimiento;
        this.mes_nacimiento = mes_nacimiento;
        this.anio_nacimiento = anio_nacimiento;
        this.genero = genero;
    
    }

    public Usuario(int id_usuario, String nombre, String primer_apellido, String segundo_apellido, String email, String usuario, String password, int typeuser) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
        this.typeuser = typeuser;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(int typeuser) {
        this.typeuser = typeuser;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
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
