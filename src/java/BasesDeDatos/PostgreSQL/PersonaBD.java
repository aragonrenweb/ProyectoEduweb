package BasesDeDatos.PostgreSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Cadena;

import Modelo.Domicilio;
import Modelo.Entidad;
import Modelo.Funcion;
import Modelo.Pais;
import Modelo.Persona;
import Modelo.Telefono;
import Modelo.Tabla;


public class PersonaBD {
    
	public static int insertarPersona(Persona persona) {
		int rs = 0;
		if (PersonaBD.getPersona(persona.getUsuario(), persona.getPswd()) == null) {
                   
			String query = "insert into Persona (nombre_persona, apellidos_persona, usuario, pswd, fecha_nacimiento, email_persona,genero, cc_fips) values ("
					+ "'" + persona.getNombre() + "', '" + persona.getApellidos() + "', '" + persona.getUsuario()
					+ "', '" + persona.getPswd() + "', '" + persona.getFecha_nacimiento() + "', " + "'"
					+ persona.getEmail() + "', '"+persona.getGenero()+"', '" + persona.getPais().getCc_fips() + "');";
			rs = DataBrowser.gestionar(query);
		}
		return rs;
	}
                
	public static int eliminarPersona(Persona persona) {
		int rs = 0;
		Persona aux = getPersona(persona.getUsuario(), persona.getPswd());
		if (aux != null) {
                    //String query ="delete from pais where cc_fips = '"+cc_fips+"';";
                    String query = "delete from persona where usuario like '"+persona.getUsuario()+"' and pswd like '"+persona.getPswd()+"';";
                    rs= DataBrowser.gestionar(query);
		} else {
                    return 0;
		}
		return rs;
	}
	public static int insertarPersonaFuncion(int id_funcion,int id_persona,int id_entidad){
		int rs = 0;
		String query  = "insert into personafuncion (id_persona, id_funcion, id_entidad) values "
				+ "("+id_persona+","+id_funcion+","+id_entidad+");";
		rs= DataBrowser.gestionar(query);
		return rs;
	}
        public static int eliminarPersonaFuncion(int id_funcion,int id_persona,int id_entidad){
            String query  = "delete from personafuncion where id_funcion = "+id_funcion+ " and id_persona = "+id_persona+" and id_entidad = "+id_entidad+";";
            return DataBrowser.gestionar(query);
        }
        public static int insertarPersonaTelefono(int id_persona, int id_telefono){
		int rs = 0;
		String query = "insert into personatelefono (id_persona, id_telefono) values "
				+ "("+id_persona+","+id_telefono+")";
		rs= DataBrowser.gestionar(query);
		return rs;
	}
        public static int eliminarPersonaTelefono(int id_persona, int id_telefono){
            String query = "delete from personatelefono where id_persona = "+id_persona+" and id_telefono = "+id_telefono+";";
            return DataBrowser.gestionar(query);
        }
        
        public static int insertarPersonaDomicilio(int id_persona, int id_domicilio){
            int rs = 0;
            
            String query = "insert into personaDomicilio (id_persona, id_domicilio) "
                    + "values ("+id_persona+", "+id_domicilio+")";
            
            rs = DataBrowser.gestionar(query);
 
            return rs;
        }
        public static int eliminarPersonaDomicilio(int id_persona, int id_domicilio){
            String query = "delete from personaDomicilio where id_persona = "+id_persona+ " and  id_domicilio = "+id_domicilio+";";
             return DataBrowser.gestionar(query);
        }
        public static int insertatPersonaEntidad(int id_persona, int id_entidad){
       
            String query = "insert into personaentidad (id_persona, id_entidad) "
                    + "values ("+id_persona+", "+id_entidad+")";
            return DataBrowser.gestionar(query);
        }
        public static int eliminarPersonaEntidad(int id_persona, int id_entidad){
            String query = "delete from personaentidad where id_persona = "+id_persona+ " and id_entidad = "+id_entidad+";";
            return DataBrowser.gestionar(query); 
        }  
        
       

	public static ArrayList<Persona> getPersonas(){
		
		  ArrayList<Persona> personas =new ArrayList<Persona>(); 
		  ResultSet rs = null;
		  String query = "select * from persona;";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				
				//(String nombre, String apellidos, String usuario, String pswd, String dia_nacimiento, String mes_naciemiento, String anio_nacimeinto, String email, Pais pais)
			
				Persona p = new Persona();
				p.setId(rs.getInt("id_persona"));
				p.setNombre(rs.getString("nombre_persona"));
				p.setApellidos(rs.getString("apellidos_persona"));
				p.setUsuario(rs.getString("usuario"));
				p.setPswd(rs.getString("pswd"));
				
				p.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
				
				String [] fecha_nacimiento = Cadena.quitarGuion(rs.getString("fecha_nacimiento"));
				p.setAnio_nacimiento(fecha_nacimiento[0]);
				p.setMes_nacimiento(fecha_nacimiento[1]);
				p.setDia_nacimiento(fecha_nacimiento[2]);
				
				p.setEmail(rs.getString("email_persona"));
				Pais pais = PaisBD.getPaisById(rs.getString("cc_fips"));
				p.setPais(pais);
				personas.add(p);
			}
                       // rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return personas;  
	}
	
  
	public static Persona getPersona(String usuario, String pswd){
		Persona persona = null;
		ResultSet rs = null;
		// pswd = HASHBYTES('MD5', CONVERT(nvarchar(4000),'"+password+"')
		
		String  query = "select * from persona where usuario like '"+usuario+"' and pswd like '"+pswd+"';";
		try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				persona = new Persona();

				persona.setId(rs.getInt("id_persona"));
				persona.setNombre(rs.getString("nombre_persona"));
				persona.setApellidos(rs.getString("apellidos_persona"));
				persona.setUsuario(rs.getString("usuario"));
				persona.setPswd(rs.getString("pswd"));
				persona.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
				
				String [] fecha_nacimiento = Cadena.quitarGuion(rs.getString("fecha_nacimiento"));
				persona.setAnio_nacimiento(fecha_nacimiento[0]);
				persona.setMes_nacimiento(fecha_nacimiento[1]);
				persona.setDia_nacimiento(fecha_nacimiento[2]);
				persona.setGenero(rs.getString("genero"));
				persona.setEmail(rs.getString("email_persona"));
				Pais pais = PaisBD.getPaisById(rs.getString("cc_fips"));
				persona.setPais(pais);
				
			}
                       // rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return persona;	
	}
	
	public static ArrayList<Domicilio> getPersonaDomicilios(int id_persona){
		ArrayList<Domicilio> domicilios = null;
		 ResultSet rs = null;
		 String query = "select * from personadomicilio where id_persona = "+id_persona+";";
		 try {
			rs = DataBrowser.PostgreQuery(query);
			domicilios = new ArrayList<Domicilio>();
			while(rs.next()){
				
				Domicilio dom = DomicilioBD.getDomicilio(rs.getInt("id_domicilio"));
				domicilios.add(dom);
			}
                       // rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(domicilios.isEmpty())
			 return null;
		return domicilios;
	}
	
	public static ArrayList<Funcion> getPersonaFuncion(int id_persona){
		ArrayList<Funcion> funciones = null;
		ResultSet rs = null;
		String query = "select * from personafuncion where id_persona = "+id_persona+";";
		try {
			rs = DataBrowser.PostgreQuery(query);
			funciones = new ArrayList<Funcion>();
			while(rs.next()){
				Funcion funcion = FuncionBD.getFuncion(rs.getInt("id_funcion"), "");
				if(funcion != null){
					Entidad entidad = EntidadBD.getEntidadById(rs.getInt("id_entidad"));
					if(entidad != null){
						funcion.setEntidad(entidad);
						funciones.add(funcion);						
					}
				}
			}
                        rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return funciones;
	}
	
	public static ArrayList<Telefono> getPersonaTelefono(int id_persona){
		ArrayList<Telefono> telefonos = null;
		ResultSet rs = null;
		String query = "select * from telefono where id_persona = "+id_persona+";";
		try {
			rs = DataBrowser.PostgreQuery(query);
			telefonos = new ArrayList<Telefono>();
			while(rs.next()){
				
				String  numero = TiposBD.getTipo(rs.getInt("id_telefono"), "", Tabla.telefono);
				Telefono telefono = new Telefono(numero);
				telefono.setId(rs.getInt("id_telefono"));
				telefonos.add(telefono);
			}
                      //  rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(telefonos.isEmpty()){
			return null;
		}
		return telefonos;
		
	}
       
        public static int modificarPersona(Persona persona){
            int rs = 0;
            String query = "UPDATE persona SET nombre_persona = '"+persona.getNombre()+"', "
                    + "apellidos_persona = '"+persona.getApellidos()+"', usuario = '"+persona.getUsuario()+"', "
                    + "pswd = '"+persona.getPswd()+"', fecha_nacimiento = '"+persona.getFecha_nacimiento()+"', "
                    + "email_persona = '"+persona.getEmail()+"', genero = '"+persona.getGenero()+"', "
                    + "cc_fips = '"+persona.getPais().getCc_fips()+"' WHERE id_persona = "+persona.getId()+";";
             rs = DataBrowser.gestionar(query);
            return rs;
        }
	
}
