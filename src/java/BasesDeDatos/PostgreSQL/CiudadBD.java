package BasesDeDatos.PostgreSQL;


import Modelo.Cadena;
import Modelo.Ciudad;
import Modelo.Pais;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;



public class CiudadBD {
            public static void insertarCiudad(Ciudad ciudad) throws IOException{
                Ciudad c = getCiudad(ciudad.getId());
                    String query = "insert into ciudad (cc_fips, nombre_spanish, nombre_english, nombre_arabic) "
                            + "values ('"+ciudad.getPais().getCc_fips()+"', '"+ciudad.getNombre_arabic()+"' , "
                            + "'"+ciudad.getNombre_english()+"', '"+ciudad.getNombre_spanish()+"' );";
                    
                    DataBrowser.gestionar(query);
               
           
        }
	
           public static int eliminarCiudad(int id){
               Ciudad c = getCiudad(id);
               if(c != null){
                   String query = "delete from ciudad where id_ciudad = "+id+";";
                  return  DataBrowser.gestionar(query);
                 
               }
               else{
                   return 0;
               }
               
           }
	 public static ArrayList<Ciudad> getCiudades(){
		  ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>(); 
		  ResultSet rs = null;
		  String query = "select * from ciudad;";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				String nombre_spanish = rs.getString("nombre_spanish");
				
				if(nombre_spanish != null){
                                        
					nombre_spanish= Cadena.reemplazarAsterisco(nombre_spanish);
                                        
				}
				else nombre_spanish = "";
				
					
				String nombre_english = rs.getString("nombre_english");
				if(nombre_english != null){				
					 nombre_english = Cadena.reemplazarAsterisco(nombre_english);
                                }
				else nombre_english = "";
				
				String nombre_arabic = rs.getString("nombre_arabic");
				if(nombre_arabic != null){
				 nombre_arabic = Cadena.reemplazarComilla(nombre_arabic);
                                }
				else  nombre_arabic = "";
				
				Pais p = PaisBD.getPaisById(rs.getString("cc_fips"));
				if(p!=null){
					
					ciudades.add(new Ciudad(rs.getInt("id_ciudad"), nombre_spanish, nombre_english, nombre_arabic, p));
				}
				
			}
                     //   rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  if(ciudades.isEmpty())
			  return null;
			
		  return ciudades;
	 }
	
	public static Ciudad getCiudad(int id){
		Ciudad aux = null;

		  ResultSet rs = null;
		  String query = "select * from ciudad where id_ciudad = "+id+";";
	
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
				
				Pais p = PaisBD.getPaisById(rs.getString("cc_fips"));
				if(p!=null){
					
					
					aux = new Ciudad(rs.getInt("id_ciudad"), nombre_spanish, nombre_english, nombre_arabic, p);
				}
			}
                    //    rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
		
	}
        public static int modificarCiudad(Ciudad ciudad){
            int rs = 0;
            String query = "UPDATE ciudad SET cc_fips = '"+ciudad.getPais().getCc_fips()+"', "
                    + "nombre_spanish = '"+ciudad.getNombre_spanish()+"', nombre_english = '"+ciudad.getNombre_english()+"', "
                    + "nombre_arabic = '" +ciudad.getNombre_arabic()+"' WHERE id_ciudad = "+ciudad.getId()+";";
            rs = DataBrowser.gestionar(query);
            return rs;
        }
	
}
