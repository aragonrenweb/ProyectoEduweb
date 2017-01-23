package BasesDeDatos.PostgreSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Ciudad;
import Modelo.Domicilio;
import Modelo.Pais;



public class DomicilioBD {
    
    public static int insertarDomicilio(Domicilio domicilio){
        Domicilio aux = getDomicilio(domicilio.getId());
        if(aux == null){
            String query = "insert into domicilio (lineauno, lineados, cp, cc_fips, id_ciudad) "
                    + "values ('"+domicilio.getLineaUno()+"', '"+domicilio.getLineaDos()+"' ,'"+domicilio.getCp()+"', '"+domicilio.getPais().getCc_fips()+"', "+domicilio.getCiudad()+");";
            return DataBrowser.gestionar(query);
           
        }
        return 0;  
    }
    
    public static int eliminarDomicilio(int id_domicilio){
        String query = "delete from domicilio where id_domicilio = "+id_domicilio+";";
          return DataBrowser.gestionar(query);
    }
	 public static ArrayList<Domicilio> getDomicilios(){
		  ArrayList<Domicilio> domicilios =new ArrayList<Domicilio>(); 
		  ResultSet rs = null;
		  String query = "select * from domicilio;";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				String lineaUno = rs.getString("lineauno");
				if(lineaUno == null)
					lineaUno = "";
				String lineaDos = rs.getString("lineados");
				if(lineaDos ==null)
					lineaDos="";
				String cp = rs.getString("cp");
				if(cp == null)
					cp = "";
				int id_ciudad = rs.getInt("id_ciudad");
				Ciudad ciu_aux = CiudadBD.getCiudad(id_ciudad);
				if(ciu_aux != null){

				Domicilio dom_aux = new Domicilio(lineaUno, lineaDos, cp,ciu_aux.getPais(), ciu_aux);
				dom_aux.setId(rs.getInt("id_domicilio"));
				domicilios.add(dom_aux);
				
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return domicilios;
	 }
	 
	 public static Domicilio getDomicilio(int id){
		 Domicilio domicilio = null;
		  ResultSet rs = null;
		  String query = "select * from domicilio where id_domicilio = "+id+";";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				Pais pais = PaisBD.getPaisById(rs.getString("cc_fips"));
				if(pais != null){
					Ciudad ciudad = CiudadBD.getCiudad(rs.getInt("id_ciudad"));
					if(ciudad != null){

						domicilio = new Domicilio(rs.getString("lineauno"), rs.getString("lineaDos"),rs.getString("cp"), pais, ciudad);
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return domicilio;
		 
	 }
         public static int modificarDomicilio(Domicilio domicilio){
              int rs = 0;
            String query = "UPDATE domicilio SET lineauno = '"+domicilio.getLineaUno()+"', "
                    + "lineados = '"+domicilio.getLineaDos()+"',  cp = '"+domicilio.getCp()+"', "
                    + "cc_fips = '"+domicilio.getPais().getCc_fips()+"', id_ciudad = "+domicilio.getCiudad().getId()+ ""
            + " WHERE id_domicilio = "+domicilio.getId()+";";
            
            rs = DataBrowser.gestionar(query);
            return rs;
         }
}
