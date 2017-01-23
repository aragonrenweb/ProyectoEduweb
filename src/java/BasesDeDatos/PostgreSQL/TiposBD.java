package BasesDeDatos.PostgreSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Modelo.Tabla;

public class TiposBD {

    public static int insertarTipoTicket(String nombre_tipoTicket) {
        int rs = 0;
        String nombre = TiposBD.getTipo(0, nombre_tipoTicket, Tabla.tipoticket);
        if (nombre.equalsIgnoreCase("")) {
            String query = "insert into tipoticket (nombre_tipoTicket) values " + "('" + nombre_tipoTicket + "')";
            rs = DataBrowser.gestionar(query);
        }
        return rs;
    }

    public static int eliminarTipoTicket(int id_tipoticket, String nombre_tipoticket) {
        String query = "delete from tipoticket where id_ticket = " + id_tipoticket + " or nombre_tipoticket like '" + nombre_tipoticket + "';";
        return DataBrowser.gestionar(query);
    }
public static int modificarTipoTicket(String nopmbre_tipoTicket){
     int rs = 0;
            String query = "";
            rs = DataBrowser.gestionar(query);
            return rs;
}
    public static int insertarTipoEntidad(String nombre_tipoentidad) {
        int rs = 0;
        String nombre = TiposBD.getTipo(0, nombre_tipoentidad, Tabla.tipoentidad);
        if (nombre.equalsIgnoreCase("")) {
            String query = "insert into tipoentidad (nombre_tipoentidad) values " + "('" + nombre_tipoentidad + "')";
            rs = DataBrowser.gestionar(query);
        }
        return rs;
    }

    public static int eliminarTipoEntidad(int id_tipoentidad, String nombre_tipoentidad) {
        String query = "delete from tipoentidad where id_tipoentidad = " + id_tipoentidad + "or nombre_tipoentidad like '" + nombre_tipoentidad + "';";
        return DataBrowser.gestionar(query);
    }
public static int modificarTipoEntidad(String nombre_tipoentidad){
     int rs = 0;
            String query = "";
            rs = DataBrowser.gestionar(query);
            return rs;
}
    public static int insertarTipoCobro(String nombre_tipocobro) {
        String aux = getTipo(0, nombre_tipocobro, Tabla.tipocobro);
        if (aux.equalsIgnoreCase("")) {
            return 0;
        }
        String query = "insert into tipocobro (nombre_tipoentidad) values ('"+nombre_tipocobro+"')";
        return DataBrowser.gestionar(query);
    }

    public static int eliminarTipoCobro(int id_tipoentidad, String nombre_tipocobro) {
        String query = "delete from tipocobro where id_tipoentidad = " + id_tipoentidad + " or nombre_tipoentidad like '" + nombre_tipocobro + "';";
        return DataBrowser.gestionar(query);
    }
public static int modificarTipoCobro(String nombre_tipocobro){
       int rs = 0;
            String query = "";
            rs = DataBrowser.gestionar(query);
            return rs;
}
    public static int insertarGrupoTicket(String nombre_grupoticket){
        if(getTipo(0,nombre_grupoticket, Tabla.grupoticket).equalsIgnoreCase(""))
            return 0;
        String query = "insert into grupoticket (nombre_grupoticket) values ('"+nombre_grupoticket+"')";
         return DataBrowser.gestionar(query);
    }
    
    public static int eliminarGrupoTicket(int id_grupoticket,  String nombre_grupoticket){
        String query= "delete from grupoticket where id_grupoticket = "+id_grupoticket+" or nombre_grupoticket like '"+nombre_grupoticket+"';";
        return DataBrowser.gestionar(query);
    }
    public static int modificarGrupoTicket(String nombre_grupoticket){
            int rs = 0;
            String query = "";
            rs = DataBrowser.gestionar(query);
            return rs;
    }
    public static ArrayList<String> getTipos(Tabla tabla) {

        ArrayList<String> tipos = null;
        ResultSet rs = null;
        String query = "";

        switch (tabla) {
            case tipoticket:
                query = "select * from tipoticket order by id_tipoticket;";
                break;
            case tipoentidad:
                query = "select * from tipoentidad order by id_tipoentidad;";
                break;
            case tipocobro:
                query = "select * from tipocobro order by id_tipocobro;";
                break;
            case telefono:
                query = "select * from telefono order by id_telefono;";
                break;
            case grupoticket:
                query = "select * from grupoticket order by id_grupoticket;";
                break;

            default:
                break;
        }
        try {
            rs = DataBrowser.PostgreQuery(query);
            tipos = new ArrayList<String>();
            while (rs.next()) {
                switch (tabla) {
                    case tipoticket:
                        tipos.add(rs.getString("nombre_tipoTicket"));
                        break;
                    case tipoentidad:
                        tipos.add(rs.getString("nombre_tipoentidad"));
                        break;
                    case tipocobro:
                        tipos.add(rs.getString("nombre_tipocobro"));
                        break;
                    case telefono:
                        tipos.add(rs.getString("numero_telefono"));
                        break;
                    case grupoticket:
                        tipos.add(rs.getString("nombre_grupoticket"));
                        break;
                    default:
                        break;
                }
            }
            //rs.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (tipos.isEmpty()) {
            return null;
        }
        return tipos;
    }

    public static String getTipo(int id, String nombre, Tabla tabla) {
        ResultSet rs = null;
        String aux = "";
        String query = "";
        switch (tabla) {
            case tipoticket:
                query = "select * from tipoticket where id_tipoticket = " + id + " or nombre_tipoticket like '" + nombre + "';";
                break;
            case tipoentidad:
                query = "select * from tipoentidad where id_tipoentidad = " + id + " or nombre_tipoentidad like '" + nombre + "';";
                break;
            case tipocobro:
                query = "select * from tipocobro where id_tipocobro = " + id + " or nombre_tipocobro like '" + nombre + "';";
                break;
            case telefono:
                query = "select * from telefono where id_telefono = " + id + " or numero_telefono like '" + nombre + "';";
                break;
            case grupoticket:
                query = "select * from grupoticket where id_grupoticket = " + id + " or nombre_grupoticket like '" + nombre + "';";
                break;

            default:
                break;
        }
        try {
            rs = DataBrowser.PostgreQuery(query);
            while (rs.next()) {
                switch (tabla) {
                    case tipoticket:
                        aux = rs.getString("nombre_tipoticket");
                        break;
                    case tipoentidad:
                        aux = rs.getString("nombre_tipoentidad");
                        ;
                        break;
                    case tipocobro:
                        aux = rs.getString("nombre_tipocobro");
                        break;
                    case telefono:
                        aux = rs.getString("numero_telefono");
                        break;
                    case grupoticket:
                        aux = rs.getString("nombre_grupoticket");
                        break;
                    default:
                        break;
                }
            }
            //  rs.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return aux;

    }

}
