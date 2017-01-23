package BasesDeDatos.PostgreSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Cobro;




public class CobroBD {
        
 
	public static ArrayList<Cobro> getCobros(){
		ArrayList<Cobro> cobros = null;
		ResultSet rs = null;
		String query = "select * from cobro;";
		try {
			rs = DataBrowser.PostgreQuery(query);
			cobros = new ArrayList<Cobro>();
			while(rs.next()){
				Cobro cobro = new Cobro(rs.getString("tipo_cobro"));
				cobro.setId(rs.getInt("id_cobro"));
				cobros.add(cobro);
				
			}
                       // rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cobros.isEmpty())
			return null;
		return cobros;
	}
	 public static Cobro getCobro(int id){
		 Cobro cobro = null;
		 ResultSet rs = null;
		 String query = "select * from cobro where id_cobro = "+id+";";
	
		try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				cobro = new Cobro();
				cobro.setId(id);
				cobro.setNombre(rs.getString("tipo_cobro"));
			}
                        //rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cobro;
	 }
         
         
         public static int modificarCobro(Cobro cobro){
              int rs = 0;
            String query = "UPDATE cobro SET tipo_cobro = "+cobro.getNombre()+" WHERE id_cobro = "+cobro.getId()+ ";";
            rs = DataBrowser.gestionar(query);
            return rs;
         }
}
