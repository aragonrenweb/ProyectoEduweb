package BasesDeDatos.PostgreSQL;


import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Domicilio;
import Modelo.Entidad;

import Modelo.Sede;


public class SedeBD {
    
    
	public static int insertarSede(Sede sede) {
		int rs = 0;
		Sede aux = SedeBD.getSede(sede.getId());
		if (aux == null) {
			String query = "insert into sede (nombre_sede, id_entidad, id_domicilio) values " + "('" + sede.getNombre()
					+ "', " + sede.getEntidad().getId() + ", " + sede.getDomicilio().getId() + ")";
			rs= DataBrowser.gestionar(query);
		}
		return rs;
	}
        public static int eliminarSede(int id){
            Sede s  = getSede(id);
            if(s != null){
                String query = "delete from sede where id_sede = "+id+";";
                return DataBrowser.gestionar(query);
            }
            return 0;
        }

	 public static ArrayList<Sede> getSedes(){
		  ArrayList<Sede> sedes =new ArrayList<Sede>(); 
		  ResultSet rs = null;
		  String query = "select * from Sede;";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_sede");
				String nombre_sede = rs.getString("nombre_sede");
				Entidad ent_aux = null;
				ent_aux = EntidadBD.getEntidadById(rs.getInt("id_entidad"));
				if(ent_aux != null){
					Domicilio dom_aux = DomicilioBD.getDomicilio(rs.getInt("id_domicilio"));
					if(dom_aux != null){
						//  public Sede(String nombre, Entidad entidad, Domicilio domicilio) 
						Sede sed_aux = new Sede(nombre_sede, ent_aux, dom_aux);
						sed_aux.setId(id);
						sedes.add(sed_aux);
					}
				}
				
			}
			
			//rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return sedes;
	 }
	 
	 public static Sede getSede(int id){
		 Sede sede = null;
		  ResultSet rs = null;
		  String query = "select * from Sede where id_sede = "+id+";";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			 while(rs.next()){
				 String nombre_sede = rs.getString("nombre_sede");
				 Entidad ent_aux = null;
				 ent_aux = EntidadBD.getEntidadById(rs.getInt("id_entidad"));
				 if(ent_aux != null){
					 Domicilio dom_aux = DomicilioBD.getDomicilio(rs.getInt("id_domicilio"));
					 if(dom_aux != null){
						 sede = new Sede(nombre_sede, ent_aux, dom_aux);
						 sede.setId(rs.getInt("id_sede"));
					 }
				 }
			  }
                         //rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return sede;
		 
	 }
         public static int  modificarSede(Sede sede){
              int rs = 0;
            String query = "UPDATE sede SET nombre_sede = '"+sede.getNombre()+"', id_entidad = "+sede.getEntidad().getId()+", "
                    + "id_domicilio = "+sede.getDomicilio().getId()+" WHERE id_sede = "+sede.getId()+";";
            rs = DataBrowser.gestionar(query);
            return rs;
         }
}
