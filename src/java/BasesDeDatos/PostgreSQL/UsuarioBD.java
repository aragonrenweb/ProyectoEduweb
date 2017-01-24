/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasesDeDatos.PostgreSQL;




import Modelo.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author Jesús Aragón
 */
public class UsuarioBD {
    
    public static Usuario getUsuario(String usuario, String password){
        
		Usuario usuario1 = null;
		ResultSet rs = null;
		// pswd = HASHBYTES('MD5', CONVERT(nvarchar(4000),'"+password+"')
		
		String  query = "select * from usuarios where usuario = '"+usuario+"' and password = '"+password+"';";
		try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				usuario1 = new Usuario();

				usuario1.setId_usuario(rs.getInt("id_usuario"));
				usuario1.setNombre(rs.getString("nombre"));
				usuario1.setPrimer_apellido(rs.getString("primer_apellido"));
                                usuario1.setSegundo_apellido(rs.getString("segundo_apellido"));
				usuario1.setUsuario(rs.getString("usuario"));
                                usuario1.setEmail(rs.getString("email"));
				usuario1.setPassword(rs.getString("password"));
                                usuario1.setTypeuser(rs.getInt("typeuser"));
				
				
			}
                       // rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return usuario1;	
	}
}
