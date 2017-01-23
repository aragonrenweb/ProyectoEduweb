/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Jesús Aragón
 */
public class LessonsDatos {
    
    private ServletContext servlet;
    Connection cn;
    private String mensajerror;
    CachedRowSet rs;

    public String getMensajerror() {
        return mensajerror;
    }

    public void setMensajerror(String mensajerror) {
        this.mensajerror = mensajerror;
    }
    
    public LessonsDatos()
    {
    
    }
    
    public LessonsDatos(ServletContext s)
    {
        this.servlet = s;
    }
    
    private Object getBean(String nombrebean, ServletContext servlet)
    {
        ApplicationContext contexto =
                WebApplicationContextUtils.getRequiredWebApplicationContext(servlet);
        Object beanobject = contexto.getBean(nombrebean);
        return beanobject;
    }

    private void conectarOracle() throws SQLException
    {
        this.rs = RowSetProvider.newFactory().createCachedRowSet();
        this.rs.setUrl("jdbc:postgresql://localhost:5432/lessons");
        this.rs.setUsername("postgres");
        this.rs.setPassword("rapunzel");        
    }

    
    public ArrayList<Equipment> ListasEquipamiento() throws SQLException
    {
               
        this.conectarOracle();
        String consulta = "SELECT * FROM activity_equipment";
        this.rs.setCommand(consulta);
        this.rs.execute();
        this.rs.beforeFirst();
        ArrayList<Equipment> lista = new ArrayList();
        while (this.rs.next())
        {
            Equipment e = new Equipment();
            e.setId_activity_equipment(this.rs.getInt("id_activity_equipment"));
            e.setNombre_activity_equipment(this.rs.getString("nombre_activity_equipment"));
            lista.add(e);
        }
        
        
        return lista;
    }    
//    public ArrayList<Equipment> getNombresEquipment() throws SQLException
//    {
//               
//        this.conectarOracle();
//        String consulta = "SELECT distinct id_activity_equipment, nombre_activity_equipment FROM lessons, activity_equipment where activity_equipment.id_activity_equipment = lessons.id_equipment";
//        this.rs.setCommand(consulta);
//        this.rs.execute();
//        this.rs.beforeFirst();
//        ArrayList<Equipment> lista = new ArrayList();
//        while (this.rs.next())
//        {
//            Equipment e = new Equipment();
//            e.setId_activity_equipment(this.rs.getInt("id_activity_equipment"));
//            e.setNombre_activity_equipment(this.rs.getString("nombre_activity_equipment"));
//            lista.add(e);
//        }
//        return lista;
//    }    


}
