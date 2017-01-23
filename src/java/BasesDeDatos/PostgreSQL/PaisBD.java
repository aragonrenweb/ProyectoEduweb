package BasesDeDatos.PostgreSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Cadena;
import Modelo.Pais;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PaisBD {
	
        public static int insertarPais(Pais pais){
            int rs = 0;
            String query = "insert into pais (cc_iso, cc_fips, tld, nombre_spanish, nombre_english, nombre_arabic) "
                    + "values ('"+pais.getCc_iso()+"', "
                    + "'"+pais.getCc_fips()+"', '"+pais.getTld()+"', '"+pais.getNombre_spanish()+"', "
                    + "'"+pais.getNombre_english()+"', '"+pais.getNombre_arabic()+"');";
            rs= DataBrowser.gestionar(query);
            return rs; 
        }
	
        public static int eliminarPais(String aux){
           int rs = 0;
            try {
                Pais p_uno = getPaisById(aux);
                Pais p_dos = getPaisByName(aux);
                
               if(p_uno != null || p_dos != null ) {
                   String query = "delete from pais where cc_fips like '"+p_uno.getCc_fips()+"' or "
                           + "nombre_spanish like '"+p_dos.getNombre_spanish()+"' or "
                           + "nombre_english like '"+p_dos.getNombre_english()+"' or "
                           + "nombre_arabic like '"+p_dos.getNombre_arabic()+"';";
                   rs= DataBrowser.gestionar(query);
               }
               return rs;
            } catch (SQLException ex) {
                Logger.getLogger(PaisBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rs;
        }

	  public static ArrayList<Pais> getPaises(){
		  ArrayList<Pais> paises =new ArrayList<Pais>(); 
		  ResultSet rs = null;
		  String query = "select * from pais;";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				String nombre_spanish = rs.getString("nombre_spanish");
				
				if(nombre_spanish != null){
					nombre_spanish= Cadena.reemplazarAsterisco(nombre_spanish);
				}
				else nombre_spanish = "";
				
					
				String nombre_english = rs.getString("nombre_english");
				if(nombre_english != null)				
					 nombre_english = Cadena.reemplazarAsterisco(nombre_english);
				else nombre_english = "";
				
				String nombre_arabic = rs.getString("nombre_arabic");
				if(nombre_arabic != null)
				 nombre_arabic = Cadena.reemplazarAsterisco(nombre_arabic);
				else  nombre_arabic = "";
				
				Pais p = new Pais(rs.getString("cc_fips"),rs.getString("tld"),rs.getString("cc_iso"),nombre_spanish, nombre_english,nombre_arabic);
				paises.add(p);
				
			}
                        //rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return paises;  
	  }
	  
	  public static Pais getPaisById(String cc_fips) throws SQLException{
		  Pais pais = null;
		  ResultSet rs;
		  String query = "select * from pais where cc_fips = '"+cc_fips+"';";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
                           
				String nombre_spanish = rs.getString("nombre_spanish");
				
				if(nombre_spanish != null){
					nombre_spanish= Cadena.reemplazarComilla(nombre_spanish);
				}
				else nombre_spanish = "";
				
					
				String nombre_english = rs.getString("nombre_english");
				if(nombre_english != null)				
					 nombre_english = Cadena.reemplazarComilla(nombre_english);
				else nombre_english = "";
				
				String nombre_arabic = rs.getString("nombre_arabic");
				if(nombre_arabic != null)
				 nombre_arabic = Cadena.reemplazarComilla(nombre_arabic);
				else  nombre_arabic = "";
				
				pais = new Pais(rs.getString("cc_fips"),rs.getString("tld"),rs.getString("cc_iso"),nombre_spanish, nombre_english,nombre_arabic);
				
			}
                      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
                 
		  return pais;
		  
	  }
	  	  public static Pais getPaisByName(String nombre){
		  Pais pais = null;
		  ResultSet rs = null;
		  String query = "select * from pais where nombre_spanish like '"+nombre+"' or nombre_english like '"+nombre+"' or nombre_arabic like '"+nombre+"';";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				String nombre_spanish = rs.getString("nombre_spanish");
				
				if(nombre_spanish != null){
					nombre_spanish= Cadena.reemplazarComilla(nombre_spanish);
				}
				else nombre_spanish = "";
				
					
				String nombre_english = rs.getString("nombre_english");
				if(nombre_english != null)				
					 nombre_english = Cadena.reemplazarComilla(nombre_english);
				else nombre_english = "";
				
				String nombre_arabic = rs.getString("nombre_arabic");
				if(nombre_arabic != null)
				 nombre_arabic = Cadena.reemplazarComilla(nombre_arabic);
				else  nombre_arabic = "";
				
				pais = new Pais(rs.getString("cc_fips"),rs.getString("tld"),rs.getString("cc_iso"),nombre_spanish, nombre_english,nombre_arabic);
				
				
			}//rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return pais;
		  
	  }
	
		  
	public static int modificarPais(Pais pais){
            int rs = 0;
            String query = "UPDATE pais SET cc_iso = '"+pais.getCc_iso()+"', "
                    + "tld = '"+pais.getTld()+"', nombre_spanish = '"+pais.getNombre_spanish()+"', "
                    + "nombre_english = '"+pais.getNombre_english()+"', nombre_arabic = '"+pais.getNombre_arabic()+"' "
                    + "WHERE cc_fips = '"+pais.getCc_fips()+"';";
            rs = DataBrowser.gestionar(query);
            return rs;
        }
	  
	  
	  
}
