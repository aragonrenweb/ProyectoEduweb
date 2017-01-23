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
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Jesús Aragón
 */
public class Subsection {
    private int id_subsection;
    private int id_subject;
    private String nombre_sub_section;
    private ServletContext servlet;
    private Connection cn;
    private CachedRowSet rs;
    
    public Subsection(){
        
    }

    public Subsection(ServletContext svc) {
        this.id_subsection = 0;
        this.id_subject = 0;
        this.nombre_sub_section = "First choose a subject";
        this.servlet = svc;
    }
    
     public Subsection(String nombre_sub_section) {
        this.id_subsection = 0;
        this.id_subject = 0;
        this.nombre_sub_section = "";
    }

    public int getId_subsection() {
        return id_subsection;
    }

    public void setId_subsection(int id_subsection) {
        this.id_subsection = id_subsection;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getNombre_sub_section() {
        return nombre_sub_section;
    }

    public void setNombre_sub_section(String nombre_sub_section) {
        this.nombre_sub_section = nombre_sub_section;
    }
    
    private Object getBean(String nombreBean){
        ApplicationContext contexto = 
        WebApplicationContextUtils.getRequiredWebApplicationContext(this.servlet);
        Object bean = contexto.getBean(nombreBean);
        return bean;
    }

    private void conectarOracle() throws SQLException{
        DriverManagerDataSource drv =
                (DriverManagerDataSource)this.getBean("dataSource");
        this.cn = drv.getConnection();
        this.rs = RowSetProvider.newFactory().createCachedRowSet();
    }
    
    public ArrayList<Subsection> getSubsections(int idSubject) throws SQLException{
        this.conectarOracle();
        ArrayList<Subsection> listaSubsections = new ArrayList<Subsection>();
        try {
            String consulta = "SELECT * FROM subsection where id_subject =" + idSubject;
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();
            
            while (rs.next())
            {
                Subsection subsections = new Subsection();
                subsections.setId_subsection(rs.getInt("id_subsection"));
                subsections.setId_subject(rs.getInt("id_subject"));
                subsections.setNombre_sub_section(rs.getString("nombre_sub_section"));
                listaSubsections.add(subsections);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsection: " + ex);
        }
        this.rs.close();
        this.cn.close();
        return listaSubsections;
    }
    
    public void getOneSubsections(int idSubsection) throws SQLException{
        this.conectarOracle();
        try {
            String consulta = "SELECT * FROM subsection where id_subsection =" + idSubsection;
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();
            
            while (rs.next())
            {
                this.id_subsection =rs.getInt("id_subsection");
                this.id_subject = rs.getInt("id_subject");
                this.nombre_sub_section = rs.getString("nombre_sub_section");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subsection: " + ex);
        }
        this.rs.close();
        this.cn.close();
    }
     
}
