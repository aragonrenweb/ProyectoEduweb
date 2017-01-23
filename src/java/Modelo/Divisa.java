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
public class Divisa {
    
      
      private int id;
      private String alphabetic_code;
        private String nombre_spanish;
         private String nombre_english;
          private String nombre_arabic;
          private String numeric_code;

    public Divisa() {
         this.id = 0;
         this.alphabetic_code="";
       this.nombre_spanish = "";
       this.nombre_english= "";
       this.nombre_spanish = "";
       this.numeric_code = "";
    }

    public Divisa(String alphabetic_code, String nombre_spanish, String nombre_english, String nombre_arabic,String numeric_code ) {
        this.id = 0;
        this.alphabetic_code = alphabetic_code;
        this.nombre_spanish = nombre_spanish;
        this.nombre_english = nombre_english;
        this.nombre_arabic = nombre_arabic;
        this.numeric_code = numeric_code;
        
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

    public String getAlphabetic_code() {
        return alphabetic_code;
    }

    public void setAlphabetic_code(String alphabetic_code) {
        this.alphabetic_code = alphabetic_code;
    }

    public String getNumeric_code() {
        return numeric_code;
    }

    public void setNumeric_code(String numeric_code) {
        this.numeric_code = numeric_code;
    }


        
    
}
