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
public class Tiempo {
        private int id;
        private String descripcion;
        //TODO ver cómo interpretar el tiempo
        private float tiempoTotal;
        private Ticket ticket;

    public Tiempo() {
               this.id = 0;
        this.descripcion = "";
        this.tiempoTotal = 0;
        this.ticket = null;
    }

    public Tiempo( String descripcion, float tiempoTotal, Ticket ticket) {
        this.id = 0;
        this.descripcion = descripcion;
        this.tiempoTotal = tiempoTotal;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(float tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
        
}
