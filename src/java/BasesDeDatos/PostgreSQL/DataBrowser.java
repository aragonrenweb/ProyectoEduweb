package BasesDeDatos.PostgreSQL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * @author snaji
 *
 */
public class DataBrowser {
	private static Connection conn = null;
	private static String url = "jdbc:postgresql://localhost:5432/Lessons";
	private static String usr = "postgres";
	private static String passwd = "rapunzel";

	/*private static String url = "jdbc:postgresql://localhost:5432/mydb";
	private static String usr = "postgres";
	private static String passwd = "sven";*/

	private static Connection GetConnection() throws Exception {
        
		try {
			Class.forName("org.postgresql.Driver").newInstance();

		} catch (Exception e) {
			System.err.print("ClassNotFoundException: ");
			System.out.println(e.toString());
			System.err.println(e.getMessage());
		}
		return DriverManager.getConnection(url, usr, passwd);
	}

	
	public static ResultSet QueryConsultar(Connection conn, String queryString) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		stmt = conn.prepareStatement(queryString);
		rs = stmt.executeQuery();
               // stmt.close();
                conn.close();
		return rs;
	}

	public static ResultSet PostgreQuery(String queryString) throws Exception {
		ResultSet rs = null;
			rs = QueryConsultar(GetConnection(), queryString);
		return rs;
	}

	public DataBrowser() throws Exception {

		this.QueryCreate();
		this.AddPaises();

	}

	private void QueryCreate() throws Exception {
		String query = "";
		PreparedStatement stmt = null;
	conn = GetConnection();
		try {

			query = "create table pais( cc_iso varchar(2),"
					+ "	cc_fips varchar(2)   primary key,	tld varchar(3),	nombre_spanish varchar(200) unique,"
					+ " nombre_english varchar(200) unique, nombre_arabic varchar(200) unique);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();

			query = "create table ciudad( id_ciudad serial primary key,	cc_fips varchar(2), nombre_spanish varchar(200),"
					+ "	nombre_english varchar(200), nombre_arabic varchar(200), "
					+ "CONSTRAINT ciudad_cc_fips_FK FOREIGN KEY (cc_fips) REFERENCES pais ON DELETE CASCADE);";

			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table domicilio( id_domicilio serial primary key, lineaUno varchar(100),"
					+ "	lineaDos varchar(100), cp varchar(50), cc_fips varchar(2), id_ciudad int,"
					+ "	CONSTRAINT domicilio_cc_fips_FK FOREIGN KEY (cc_fips) REFERENCES pais ON DELETE CASCADE,"
					+ "	CONSTRAINT domicilio_id_ciudad_FK FOREIGN KEY (id_ciudad) REFERENCES ciudad ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table telefono(id_telefono serial primary key, numero_telefono varchar(100) unique);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table funcion(id_funcion serial primary key, nombre_funcion varchar(100) unique,"
					+ "	descripcion varchar(100));";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table persona(id_persona serial primary key, nombre_persona varchar(100),"
					+ " apellidos_persona varchar(100),	usuario varchar(100), pswd varchar(200), fecha_nacimiento date,"
					+ "	email_persona varchar(100), genero char(1), cc_fips varchar(2),"
					+ "	CONSTRAINT persona_cc_fips_FK FOREIGN KEY (cc_fips) REFERENCES pais ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table personaDomicilio(id_persona int, id_domicilio int,"
					+ " CONSTRAINT personaDomicilio_PK PRIMARY KEY (id_persona,id_domicilio),"
					+ " CONSTRAINT personaDomicilio_id_persona_FK FOREIGN KEY (id_persona) "
					+ "REFERENCES persona ON DELETE CASCADE,"
					+ " CONSTRAINT personaDomicilio_id_domicilio_FK FOREIGN KEY (id_domicilio) "
					+ "REFERENCES domicilio ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table personaTelefono(id_persona int, id_telefono int,"
					+ " CONSTRAINT personaTelefono_PK PRIMARY KEY (id_persona,id_telefono),"
					+ " CONSTRAINT personaTelefono_id_persona_FK FOREIGN KEY (id_persona) "
					+ "REFERENCES persona ON DELETE CASCADE,"
					+ "	CONSTRAINT personaTelefono_id_telefono_FK FOREIGN KEY (id_telefono) "
					+ "REFERENCES telefono ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();

			query = "create table tipoEntidad(id_tipoEntidad serial primary key, nombre_tipoEntidad varchar(100) unique);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table entidad(id_entidad serial primary key, nombre_entidad varchar(100) unique,"
					+ " district_code varchar(100),	id_domicilio int,"
					+ "	CONSTRAINT entidad_id_domicilio_FK FOREIGN KEY (id_domicilio) REFERENCES domicilio ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table personaFuncion(id_persona int, id_funcion int, id_entidad int,"
					+ " CONSTRAINT personaFuncion_PK PRIMARY KEY (id_persona,id_funcion),"
					+ "	CONSTRAINT personaFuncion_id_persona_FK FOREIGN KEY (id_persona) "
					+ "REFERENCES persona ON DELETE CASCADE,"
					+ "	CONSTRAINT personaFuncion_id_funcion_FK FOREIGN KEY (id_funcion) "
					+ "REFERENCES funcion ON DELETE CASCADE,"
					+ " CONSTRAINT personaFuncion_id_entidad_FK FOREIGN KEY (id_entidad) "
					+ "REFERENCES entidad ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table relacionEntidadTipoEntidad(id_entidad int, id_tipoEntidad int,"
					+ " CONSTRAINT relacionEntidadTipoEntidad_PK PRIMARY KEY (id_entidad,id_tipoEntidad),"
					+ "	CONSTRAINT relacionEntidadTipoEntidad_id_entidad_FK FOREIGN KEY (id_entidad) "
					+ "REFERENCES entidad ON DELETE CASCADE,"
					+ "	CONSTRAINT relacionEentidadTipoEntidad_id_tipoEntidad_FK FOREIGN KEY (id_tipoEntidad) "
					+ "REFERENCES tipoEntidad ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table sede(	id_sede serial primary key,	nombre_sede varchar(100), id_entidad int,"
					+ "	id_domicilio int,"
					+ " CONSTRAINT sede_id_entidad_FK FOREIGN KEY (id_entidad) REFERENCES entidad ON DELETE CASCADE,"
					+ "	CONSTRAINT sede_id_domicilio_FK FOREIGN KEY (id_domicilio) REFERENCES domicilio ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();

			query = "create table proyecto(id_proyecto serial primary key, nombre_proyecto varchar(200), id_entidad int, "
					+ "CONSTRAINT proyecto_id_entidad_FK FOREIGN KEY (id_entidad) REFERENCES entidad ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table cobro(id_cobro serial primary key, tipo_cobro int,"
					+ "	CONSTRAINT cobro_nombre_cobro_CH CHECK (tipo_cobro = 0 OR tipo_cobro = 1));";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table divisa(alphabetic_code varchar(3)  primary key, nombre_divisa_spanish varchar(100),"
					+ "	nombre_divisa_english varchar(100), nombre_divisa_arabic varchar(100),"
					+ "	numeric_code varchar(3));";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table divisaPais(alphabetic_code varchar(3), cc_fips varchar(2),"
					+ "	CONSTRAINT divisaPais_PK PRIMARY KEY (alphabetic_code,cc_fips),"
					+ "	CONSTRAINT divisaPais_alphabetic_code_FK FOREIGN KEY (alphabetic_code) "
					+ "REFERENCES divisa ON DELETE CASCADE,"
					+ "	CONSTRAINT divisaPais_cc_fips_FK FOREIGN KEY (cc_fips) REFERENCES pais ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table importe( id_importe serial primary key, cantidad real, alphabetic_code varchar(3),"
					+ "	CONSTRAINT importe_alphabetic_code_FK FOREIGN KEY (alphabetic_code) "
					+ "REFERENCES divisa ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table tipoCobro(id_tipoCobro  serial primary key, nombre_tipoCobro varchar(100) unique);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table ticket(id_ticket serial primary key, nombre_ticket varchar(100), id_cobro int, "
					+ "id_tipoCobro int, id_proyecto int,"
					+ "	CONSTRAINT ticket_id_cobro_FK FOREIGN KEY (id_cobro) REFERENCES cobro ON DELETE CASCADE,"
					+ "	CONSTRAINT ticket_id_tipoCobro_FK FOREIGN KEY (id_tipoCobro) REFERENCES tipoCobro ON DELETE CASCADE,"
					+ "	CONSTRAINT ticket_id_proyecto_FK FOREIGN KEY (id_proyecto) REFERENCES proyecto ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table tiempo(id_tiempo serial primary key, descripcion_tiempo varchar(200),	incio timestamp,"
					+ "	tiempoTotal real, id_ticket int,"
					+ "	CONSTRAINT tiempo_id_ticket_FK FOREIGN KEY (id_ticket) REFERENCES ticket ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table grupoTicket( id_grupoTicket serial primary key, nombre_grupoTicket varchar(100) unique);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table tipoTicket( id_tipoTicket serial primary key,	nombre_tipoTicket varchar(100) unique);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table ticketGrupoTicket( id_ticket int,	id_grupoTicket int,"
					+ "	CONSTRAINT ticketGrupoTicket_PK PRIMARY KEY (id_ticket,id_grupoTicket),"
					+ "	CONSTRAINT ticketGrupoTicket_id_ticket_FK FOREIGN KEY (id_ticket) "
					+ "REFERENCES ticket ON DELETE CASCADE,"
					+ "	CONSTRAINT ticketGrupoTicket_id_grupoTicket_FK FOREIGN KEY (id_grupoTicket) "
					+ "REFERENCES grupoTicket ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
                        query = "create table personaEntidad (id_persona int, id_entidad int, "
                                + "CONSTRAINT personaEntidad_PK PRIMARY KEY (id_persona, id_entidad), "
                                + "CONSTRAINT personaEntidad_id_persona_FK FOREIGN KEY (id_persona) REFERENCES persona ON DELETE CASCADE, "
                                + "CONSTRAINT personaEntidad_id_entidad_FK FOREIGN KEY (id_entidad) REFERENCES entidad ON DELETE CASCADE)";
                        
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			query = "create table grupoTicketTipoTicket(id_grupoTicket int, id_tipoTicket int,"
					+ "	CONSTRAINT grupoTicketTipoTicket_PK PRIMARY KEY (id_grupoTicket,id_tipoTicket),"
					+ "	CONSTRAINT grupoTicketTipoTicket_id_grupoTicket_FK FOREIGN KEY (id_grupoTicket) "
					+ "REFERENCES grupoTicket ON DELETE CASCADE,"
					+ "	CONSTRAINT grupoTicketTipoTicket_id_tipoTicket_FK FOREIGN KEY (id_tipoTicket) "
					+ "REFERENCES tipoTicket ON DELETE CASCADE);";
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			//stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void AddPaises() throws Exception {
		String cadena;
		FileReader fr;
		PreparedStatement stmt = null;
		conn = GetConnection();
		try {
                    //C:\Users\snaji\Documents\NetBeansProjects\WebApplication1\src\conf
			fr = new FileReader("C:/Users/snaji/Documents/NetBeansProjects/WebApplication1/src/conf/paises.txt");
                      //  fr = new FileReader("/Users/shahadbawi/NetBeansProjects/WebApplication1/src/conf/paises.txt");
			BufferedReader b = new BufferedReader(fr);
			while ((cadena = b.readLine()) != null) {
				stmt = conn.prepareStatement(cadena);
				stmt.executeUpdate();
			}
			b.close();
			fr.close();
			stmt.close();
			conn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        public static void eliminarTablas(){
            String cadena;
            FileReader fr;
		PreparedStatement stmt = null;
            try {
                conn = GetConnection();
                fr = new FileReader("C:/Users/snaji/Documents/NetBeansProjects/WebApplication1/src/conf/drops.txt");
                BufferedReader b = new BufferedReader(fr);
                while ((cadena = b.readLine()) != null) {
                    stmt = conn.prepareStatement(cadena);
                    stmt.executeUpdate();
                }
                b.close();
                fr.close();
		stmt.close();
		conn.close();
            } catch (Exception ex) {
                Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
	public static int gestionar(String query){
		int rs = 0;
		Statement stmt = null;
		try  {
                    conn = GetConnection();
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(query);
			

			stmt.close();
                        conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return rs; 
	}
  


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	
	
	

	
	public static int insertarGrupoTicketTipoTicket(int id_grupoTicket, int id_tipoTicket){
		int rs = 0;
		String query = "insert into grupotickettipoticket (id_grupoticket, id_tipoticket) values "
				+ "("+id_grupoTicket+", "+id_tipoTicket+");";
		rs= gestionar(query);
		return rs;
	}
	
	
	
	
	

}
