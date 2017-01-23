package BasesDeDatos.PostgreSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Entidad;
import Modelo.Proyecto;




public class ProyectoBD {
    public static int insertarProyecto(int id_proyecto, int id_entidad){
		int rs = 0;
		String query = "insert into proyecto (id_proyecto, id_entidad) values "
				+ "("+id_proyecto+", "+id_entidad+");";
		rs= DataBrowser.gestionar(query);
		return rs;
	}
      public static int eliminarProyecto(int id){
          Proyecto p = getProyecto(id);
          if(p != null){
              String query = "delete from proyecto where id_proyecto = "+id+";";
              return DataBrowser.gestionar(query);
          }
          return 0;
          
      }
	 public static ArrayList<Proyecto> getProyectos(){
		  ArrayList<Proyecto> proyectos =new ArrayList<Proyecto>(); 
		  ResultSet rs = null;
		  String query = "select * from proyecto;";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				Entidad entidad = EntidadBD.getEntidadById(rs.getInt("id_entidad"));
				Proyecto proyecto = new Proyecto(rs.getString("nombre_proyecto"),entidad);
				proyecto.setId(rs.getInt("id_proyecto"));
				proyectos.add(proyecto);
			
			}
                       // rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  if(proyectos.isEmpty())
			  return null;
		  return proyectos;
	 }
	 public static Proyecto getProyecto(int id){
		 Proyecto proyecto = null;
		 ResultSet rs = null;
		 String query = "select * from proyecto where id_proyecto = "+id+";";
		 try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				Entidad entidad  = EntidadBD.getEntidadById(rs.getInt("id_entidad"));
				proyecto = new Proyecto(rs.getString("nombre_proyecto"), entidad);
				proyecto.setId(id);
			}
                       // rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		 return proyecto;
	 }
public static int modificarProyecto(Proyecto proyecto){
     int rs = 0;
            String query = "UPDATE proyecto SET nombre_proyecto = '"+proyecto.getNombre()+"', "
                    + "id_entidad = "+proyecto.getEntidad().getId()+" WHERE id_proyecto = "+proyecto.getId()+";";
            rs = DataBrowser.gestionar(query);
            return rs;
}
}
