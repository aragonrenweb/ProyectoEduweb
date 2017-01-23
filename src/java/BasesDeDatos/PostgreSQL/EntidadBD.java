package BasesDeDatos.PostgreSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Domicilio;
import Modelo.Entidad;



import Modelo.Tabla;

public class EntidadBD {
    public static int insertarEntidad(Entidad entidad) {
		int rs = 0;
		Entidad aux = EntidadBD.getEntidadByDistrict_Code(entidad.getDistrict_code());
		if (aux == null) {
			String query = "insert into entidad (nombre_entidad, district_code, id_domicilio) " + "values ('"
					+ entidad.getNombre() + "', '" + entidad.getDistrict_code() + "', '"
					+ entidad.getDomicilio().getId() + "');";
			rs= DataBrowser.gestionar(query);
		}
		return rs;
	}
	public static int eliminarEntidad(Entidad entidad){
            Entidad e_uno = getEntidadByDistrict_Code(entidad.getDistrict_code());
            Entidad e_dos =  getEntidadById(entidad.getId());
            if(e_uno != null || e_dos != null){
                String query = "delete from entidad where id_entidad = "+entidad.getId()+" or district_code like '"+entidad.getId()+"';";
                return DataBrowser.gestionar(query);
            }
            else return 0;
            
        }
        
	public static int insertarRelacionEntidadTipoEntidad(int id_entidad, int id_tipoEntidad){
		int rs = 0;
		String query = "insert into relacionentidadtipoentidad (id_entidad, id_tipoentidad) values "
				+ "("+id_entidad+","+id_tipoEntidad+");";
		rs= DataBrowser.gestionar(query);
		
		return rs;
	}
        public static int eliminarRelacionEntidadTipoEntidad(int id_entidad, int id_tipoEntidad){
            String query = "delete from relacionentidadtipoentidad where id_entidad = "+id_entidad+" and id_tipoentidad = "+id_tipoEntidad+";";
            return DataBrowser.gestionar(query);
        }
    
	public static ArrayList<Entidad> getEntidades(){
		  ArrayList<Entidad> entidades =new ArrayList<Entidad>(); 
		  ResultSet rs = null;
		  String query = "select * from entidad;";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			
			while(rs.next()){
				
				int id = rs.getInt("id_entidad");
				String nombre = rs.getString("nombre_entidad");
				String district_code = rs.getString("district_code");
				Domicilio domicilio = DomicilioBD.getDomicilio(rs.getInt("id_domicilio"));
				if(domicilio != null){
					Entidad entidad = new Entidad (nombre, district_code, domicilio);
					entidad.setId(id);
					entidades.add(entidad);
					
				}
			}
                       // rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return entidades;
	}
	
	public static Entidad getEntidadByDistrict_Code(String district_code){
		Entidad entidad = null;
		ResultSet rs = null;
		String query = "select * from entidad where district_code like '"+district_code+"';";
		try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_entidad");
				String nombre = rs.getString("nombre_entidad");
				Domicilio domicilio = DomicilioBD.getDomicilio(rs.getInt("id_domicilio"));
				if(domicilio!=null){
					entidad = new Entidad(nombre, district_code, domicilio);
					entidad.setId(id);
				}
			}
                        //rs.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return entidad;
		
	}
	
	public static Entidad getEntidadById(int id){
		Entidad entidad = null;
		ResultSet rs = null;
		String query = "select * from entidad where id_entidad like '"+id+"';";
		try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				
				String nombre = rs.getString("nombre_entidad");
				String district_code = rs.getString("district_code");
				Domicilio domicilio = DomicilioBD.getDomicilio(rs.getInt("id_domicilio"));
				if(domicilio != null){
					entidad = new Entidad(nombre, district_code, domicilio);
					entidad.setId(id);
				}
			}
                      //  rs.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return entidad;
	}
	
	public static ArrayList<String> getTiposEntidad(int id_entidad){
		 ArrayList<String> tipos = null;
		 ResultSet rs = null;
		 String query = "select * from relacionentidadtipoentidad where id_entidad = "+id_entidad+";";
		 try {
			rs = DataBrowser.PostgreQuery(query);
			tipos = new ArrayList<String>();
			while(rs.next()){
				int id_tipoEntidad = rs.getInt("id_tipoentidad");
				String nombre_tipoEntidad = TiposBD.getTipo(id_tipoEntidad, "", Tabla.tipoentidad);
				tipos.add(nombre_tipoEntidad);	
			}
                     //   rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(tipos.isEmpty())
			 return null;
		 return tipos;
	}
	
	public static int modificarEntidad(Entidad entidad){
             int rs = 0;
            String query = "UPDATE entidad SET nombre_entidad = '"+entidad.getNombre()+"', "
                    + "district_code = '"+entidad.getDistrict_code()+"', id_domicilio = "+entidad.getDomicilio().getId()+" WHERE "
            + "id_entidad = "+entidad.getId()+";";
            rs = DataBrowser.gestionar(query);
            return rs;
        }

	
	

}
