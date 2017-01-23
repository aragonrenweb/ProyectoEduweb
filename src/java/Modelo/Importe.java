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
public class Importe {
     private int id;
     private float cantidad;
     private Divisa divisa;

    public Importe() {
                this.id = 0;
        this.cantidad = 0;
        this.divisa = null;
    }

    public Importe(float cantidad, Divisa divisa) {
        this.id = 0;
        this.cantidad = cantidad;
        this.divisa = divisa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }
    
     
     
}
