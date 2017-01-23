package BasesDeDatos.PostgreSQL;


import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Funcion;



public class FuncionBD {
    
	public static int insertarFuncion(Funcion funcion){
		int rs = 0;
		Funcion fun = FuncionBD.getFuncion(funcion.getId(), funcion.getNombre());
		
		if(fun == null){
			String query = "insert into funcion (nombre_funcion, descripcion) "
					+ "values ('"+funcion.getNombre()+"', '"+funcion.getDescripcion()+"');";
			rs= DataBrowser.gestionar(query);			
		}
		return rs;
	}
        
        public static int eliminarFuncion(int id, String nombre){
            Funcion f = getFuncion(id, nombre);
            if(f != null){
                String query = "delete from funcion where id = "+id+" or nombre_funcion like '"+nombre+"';";
                return DataBrowser.gestionar(query);
            }
            return 0;
        }
	
	public static ArrayList<Funcion> getFunciones(){
		ArrayList<Funcion> funciones = null;
		 ResultSet rs = null;
		 String query = "select * from funcion;";
		 try {
			rs = DataBrowser.PostgreQuery(query);
			funciones = new ArrayList<Funcion>();
			while(rs.next()){
				//( String nombre, String descripcion)
				Funcion funcion = new Funcion(rs.getString("nombre_funcion"), rs.getString("descripcion"));
				funcion.setId(rs.getInt("id_funcion"));
				funciones.add(funcion);
			}
                      //  rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(funciones.isEmpty())
			 return null;
		return funciones;
	}

	public static Funcion getFuncion(int id, String nombre){
		Funcion funcion = null;
		ResultSet rs = null;
		String query = "select * from funcion where id_funcion = "+id+" or nombre_funcion like '"+nombre+"';";
		try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				funcion = new Funcion(rs.getString("nombre_funcion"), rs.getString("descripcion"));
				funcion.setId(rs.getInt("id_funcion"));
			}
                     //   rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return funcion;
	}
        public static int modificarFuncion(Funcion funcion){
 int rs = 0;
            String query = "UPDATE funcion SET nombre_funcion = '"+funcion.getNombre()+"', descripcion = '"+funcion.getDescripcion()+"' WHERE id_funcion = "+funcion.getId()+";";
            rs = DataBrowser.gestionar(query);
            return rs;            
        }
}
