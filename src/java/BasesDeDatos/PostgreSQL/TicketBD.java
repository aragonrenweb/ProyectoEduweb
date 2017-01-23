package BasesDeDatos.PostgreSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Cobro;
import Modelo.Proyecto;
import Modelo.Tabla;
import Modelo.Ticket;

public class TicketBD {
    public static int insertatTicket(Ticket ticket, int id_tipoCobro){
		int rs = 0;
		String query = "insert into ticket (nombre_ticket, id_cobro, id_tipocobro, id_proyecto) "
				+ "values ('"+ticket.getNombre()+"', "
						+ ""+ticket.getCobro().getId()+", "
								+ ""+id_tipoCobro+", "+ticket.getProyecto().getId()+");";
		rs= DataBrowser.gestionar(query);
		return rs;
	}
    
	public static int eliminarTicket(int id){
            Ticket t = getTicket(id);
            if(t != null){
                String query = "delete from ticket where id_ticket = "+id+";";
                return DataBrowser.gestionar(query);
            }
            return 0;
        }
        
        public static int insertarTicketGrupoTicket(int id_ticket, int id_grupoticket){
            String query = "insert into ticketgrupoticket (id_ticket, id_grupoticket) "
                    + "values ("+id_ticket+", "+id_grupoticket+");";
            
           return DataBrowser.gestionar(query); 
        }
        public static int eliminarTicketGrupoTicket(int id_ticket, int id_grupoticket){
            String query = "delete from ticketgrupoticket where id_ticket = "+id_ticket+" and id_grupoticket = "+id_grupoticket+";";
             return DataBrowser.gestionar(query); 
        }
	 public static ArrayList<Ticket> getTickets(){
		 ArrayList<Ticket> tickets = null;
		 ResultSet rs = null;
		  String query = "select * from ticket;";
		  try {
			rs = DataBrowser.PostgreQuery(query);
			tickets = new ArrayList<Ticket>();
			while(rs.next()){
				Ticket ticket = new Ticket();
				ticket.setId(rs.getInt("id_ticket"));
				ticket.setNombre(rs.getString("nombre_ticket"));
				String tipoCobro = TiposBD.getTipo(rs.getInt("id_cobro"), "", Tabla.tipocobro);
				ticket.setTipoCobro(tipoCobro);
				Proyecto proyecto = ProyectoBD.getProyecto(rs.getInt("id_proyecto"));
				ticket.setProyecto(proyecto);
				tickets.add(ticket);
			}
                      //  rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return tickets;
	 }
	 
	 public static Ticket getTicket(int id){
		 Ticket ticket = null;
		 ResultSet rs = null;
		 String query = "select * from ticket where id_ticket = "+id+";";
		 try {
			rs = DataBrowser.PostgreQuery(query);
			while(rs.next()){
				ticket = new Ticket();
				ticket.setId(id);
				ticket.setNombre(rs.getString("nombre_ticket"));
				Cobro cobro = CobroBD.getCobro(rs.getInt("id_cobro"));
				ticket.setCobro(cobro);
				String tipoCobro = TiposBD.getTipo(rs.getInt("id_tipocobro"), "", Tabla.tipocobro);
				ticket.setTipoCobro(tipoCobro);
				Proyecto proyecto = ProyectoBD.getProyecto(rs.getInt("id_proyecto"));
				ticket.setProyecto(proyecto);
			}
                    //    rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return ticket;
	 }
         
         //TODO
         public static int modificarTicket(Ticket ticket){
              int rs = 0;
            String query = "UPDATE ticket SET nombre_ticket = '"+ticket.getNombre()+"', "
                    + "id_cobro = "+ticket.getTipoCobro()+"";
            rs = DataBrowser.gestionar(query);
            return rs;
         }
}
