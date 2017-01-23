/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Jesus
 */
public class Archivo {
    
    private int id_archivos;
    private String nombre_archivo;
    private ServletContext servlet;
    private Connection cn;
    private CachedRowSet rs;

    public Archivo(int id_archivos, String nombre_archivo, ServletContext svc) {
        this.id_archivos = id_archivos;
        this.nombre_archivo = nombre_archivo;
        this.servlet = svc;
    }

    public Archivo(ServletContext svc) {
        this.id_archivos = 0;
        this.nombre_archivo = "";
        this.servlet = svc;
    }
    
    
    public int getId_archivos() {
        return id_archivos;
    }

    public void setId_archivos(int id_archivos) {
        this.id_archivos = id_archivos;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
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
    
    public void getFile(int idLesson) throws SQLException{
        this.conectarOracle();
        String consulta = "select * from archivos where id_lessons=" + idLesson;
        this.rs.setCommand(consulta);
        this.rs.execute(this.cn);
        this.rs.beforeFirst();
        while (rs.next()){
            this.id_archivos = rs.getInt("id_lessons");
            this.nombre_archivo = rs.getString("nombre_archivo");
        }
        this.rs.close();
        this.cn.close();
    }
    
    public void insertArchivo(int idLesson, String nombreArchivo) throws SQLException{
        this.conectarOracle();
        if(nombreArchivo != null){
            String insertArchivosLessons = "insert into archivos (id_lessons, nombre_archivo) values (?,?)";
        
            PreparedStatement pst1 = this.cn.prepareStatement(insertArchivosLessons);
        
            pst1.setInt(1, idLesson);
            pst1.setString(2, nombreArchivo);
            
            pst1.executeUpdate();
        }
        this.rs.close();
        this.cn.close();
    }
    
    public void deleteArchivo(int idLesson) throws SQLException{
        this.conectarOracle();
        String consulta = "delete from archivos where id_lessons=" + idLesson;
        this.rs.setCommand(consulta);
        this.rs.execute(this.cn);
        this.rs.beforeFirst();
        this.rs.close();
        this.cn.close();
    }
}
