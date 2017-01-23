/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasesDeDatos.PostgreSQL;

import Modelo.Telefono;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author snaji
 */
public class TelefonoBD {
    
	public static int insertarTelefono(String telefono){
		int rs = 0;
		String query  = "insert into telefono (numero_telefono) values "
				+ "('"+telefono+"')";
		rs= DataBrowser.gestionar(query);
		return rs;	
	}
        
	public static int eliminarTelefono(int id_telefono, String telefono){
            String query = "delete from telefono where id_telefono = "+id_telefono+" or numero_telefono like '"+telefono+"':";
            return DataBrowser.gestionar(query);
        }
        
         public static ArrayList<Telefono> getTelefonos(){
            ArrayList<Telefono> telefonos = null;
            ResultSet rs = null;
            String query = "select * from telefono;";
            try {
                rs = DataBrowser.PostgreQuery(query);
                telefonos = new ArrayList<Telefono>();
                while(rs.next()){
                    Telefono t = new Telefono();
                    t.setId(rs.getInt("id_telefono"));
                    t.setNumero(rs.getString("numero_telefono"));
                    telefonos.add(t);
                }
            } catch (Exception ex) {
                Logger.getLogger(TelefonoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
			
              return telefonos;
         }
         public static Telefono getTelefono(int id){
             Telefono t = null;
            try {
                
                ResultSet rs = null;
                String query = "select * from telefono where id_telefono = "+id+";";
                rs = DataBrowser.PostgreQuery(query);
                while(rs.next()){
                    t = new Telefono();
                    t.setId(rs.getInt("id_telefono"));
                    t.setNumero(rs.getString("numero_telefono"));
                }
                return t;
            } catch (Exception ex) {
                Logger.getLogger(TelefonoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
             return t;
         }
    public static int modificarTelefono(Telefono telefono){
         int rs = 0;
            String query = "UPDATE telefono SET numero_telefono = '"+telefono.getNumero()+"' WHERE id_telefono = "+telefono.getId()+";";
            rs = DataBrowser.gestionar(query);
            return rs;
    }
}
