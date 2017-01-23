/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasesDeDatos.PostgreSQL;

import Modelo.Divisa;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author snaji
 */
public class DivisaBD {
    public static int insertarDivisa(Divisa divisa){
        String query = "insert into divisa (alphabetic_code, nombre_divisa_spanish, nombre_divisa_english, nombre_divisa_arabic, numeric_code) "
                + "values ('"+divisa.getAlphabetic_code()+"', '"+divisa.getNombre_spanish()+"', '"+divisa.getNombre_english()+"', '"+divisa.getNombre_arabic()+"', '"+divisa.getNumeric_code()+"')";
         return DataBrowser.gestionar(query);
    }
    public static int eliminarDivisa(String alphabetic_code){
        String query = "delete from divisa where alphabetic_code like '"+alphabetic_code+"';";
        return DataBrowser.gestionar(query);
    }
    public static int modificarDivisa(Divisa divisa){
       String query = "UPDATE divisa SET alphabetic_code = '"+divisa.getAlphabetic_code()+"', "
               + "nombre_divisa_spanish = '"+divisa.getNombre_spanish()+"', nombre_divisa_english = '"+divisa.getNombre_english()+"', "
               + "nombre_divisa_arabic = '"+divisa.getNombre_arabic()+"', numeric_code = '"+divisa.getNumeric_code()+"' WHERE alphabetic_code = '"+divisa.getAlphabetic_code()+"';";
        return DataBrowser.gestionar(query);
    }
    public static ArrayList<Divisa> getDivisas(){
        ArrayList<Divisa> divisas = null;
        ResultSet rs = null;
        String query = "select  * from divisa;";
        try {
            rs = DataBrowser.PostgreQuery(query);
            divisas = new ArrayList<Divisa>();
            while(rs.next()){
                Divisa d = new Divisa();
                d.setAlphabetic_code(rs.getString("alphabetic_code"));
                d.setNombre_spanish(rs.getString("nombre_divisa_spanish"));
                d.setNombre_english(rs.getString("nombre_divisa_english"));
                d.setNombre_arabic(rs.getString("nombre_divisa_arabic"));
                d.setNumeric_code(rs.getString("numeric_code"));
                divisas.add(d);
            }
        } catch (Exception ex) {
            Logger.getLogger(DivisaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
	if(divisas.isEmpty())
            return null;
        return divisas;
    }
    public static Divisa getDivisa(String alphabetic_code){
        Divisa divisa = null;
        ResultSet rs = null;
        String query = "select * from divisa where alphabetic_code = '"+alphabetic_code+"':";
        try {
            rs = DataBrowser.PostgreQuery(query);
            while(rs.next()){
                divisa = new Divisa();
                divisa.setAlphabetic_code(alphabetic_code);
                divisa.setNombre_spanish(rs.getString("nombre_divisa_spanish"));
                divisa.setNombre_english(rs.getString("nombre_divisa_english"));
                divisa.setNombre_arabic(rs.getString("nombre_divisa_arabic"));
                divisa.setNumeric_code(rs.getString("numeric_code"));
                
            }
        } catch (Exception ex) {
            Logger.getLogger(DivisaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return divisa;
        
    }
}
