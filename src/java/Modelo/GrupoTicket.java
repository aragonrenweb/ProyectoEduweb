/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author snaji
 */


public class GrupoTicket {
    
    private int id;
    private String nombre;

    public GrupoTicket() {
        this.id = 0;
        this.nombre = "";
    }

    public GrupoTicket(String nombre) {
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
