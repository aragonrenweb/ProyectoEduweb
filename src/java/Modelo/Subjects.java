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

public class Subjects {
    private int id_level;
    private int id;
    private String nombre;
    private ServletContext servlet;
    private Connection cn;
    private CachedRowSet rs;


    public Subjects(){

    }

    public Subjects(ServletContext svc) {
        this.id_level = 0;
        this.id = 0;
        this.nombre = "First choose a level";
        this.servlet = svc;
    }

    public Subjects(String nombre) {
        this.id_level = 0;
        this.id = 0;
        this.nombre = nombre;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_level() {
        return id_level;
    }

    public void setId_level(int id_level) {
        this.id_level = id_level;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
    
    private Object getBean(String nombreBean)
    {
        ApplicationContext contexto = 
        WebApplicationContextUtils.getRequiredWebApplicationContext(this.servlet);
        Object bean = contexto.getBean(nombreBean);
        return bean;
    }

    private void conectarOracle() throws SQLException
    {
        DriverManagerDataSource drv =
                (DriverManagerDataSource)this.getBean("dataSource");
        this.cn = drv.getConnection();
        this.rs = RowSetProvider.newFactory().createCachedRowSet();
    }
    
    public ArrayList<Subjects> getSubjects(int idLevel) throws SQLException{
        this.conectarOracle();
        ArrayList<Subjects> listaSubjs = new ArrayList<Subjects>();
        try {
            
            String consulta = "SELECT * FROM subject where id_level =" + idLevel;
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();
            while (rs.next())
            {
                Subjects subjs = new Subjects();
                subjs.setId(rs.getInt("id"));
                subjs.setId_level(rs.getInt("id_level"));
                subjs.setNombre(rs.getString("nombre_subject"));
                listaSubjs.add(subjs);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        this.rs.close();
        this.cn.close();
        return listaSubjs;
    }
    
    public void getOneSubject(int idSubject) throws SQLException{
        this.conectarOracle();
        try {
            String consulta = "SELECT * FROM subject where id =" + idSubject;
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();
            
            while (rs.next())
            {
                this.id = rs.getInt("id");
                this.id_level = rs.getInt("id_level");
                this.nombre = rs.getString("nombre_subject");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        this.rs.close();
        this.cn.close();
    }
}
