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
public class Asignatura {
    
    private int id;
    private String nombre;
    private ServletContext servlet;
    private Connection cn;
    private CachedRowSet rs;


    public Asignatura(ServletContext svc) 
    {
        this.id = 0;
        this.nombre = "Select one level";
        this.servlet = svc;

    }

    public Asignatura(String nombre) {
        this.id = 0;
        this.nombre = nombre;

    }
    
    public Asignatura(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    private Object getBean(String nombreBean) {
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
    
    public ArrayList<Asignatura> getLevels() throws SQLException{
        this.conectarOracle();
        ArrayList<Asignatura> listaLevels = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM level";
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();
            while (rs.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs.getInt("id"));
                levels.setNombre(rs.getString("nombre"));
                listaLevels.add(levels);
            }

        } catch (SQLException ex) {
            System.out.println("Error leyendo Levels: " + ex);
        }
        this.rs.close();
        this.cn.close();
        return listaLevels;
    }
    
    public ArrayList<Asignatura> getLevelsForStudents() throws SQLException
    {
        this.conectarOracle();
        ArrayList<Asignatura> listaLevels = new ArrayList<Asignatura>();
        try {
            this.conectarOracle();
            String consulta = "SELECT * FROM level";
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();
            while (rs.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs.getInt("id"));
                levels.setNombre(rs.getString("nombre"));
                listaLevels.add(levels);
            }            
        } catch (SQLException ex) {
            System.out.println("Error leyendo Levels: " + ex);
        }
        this.rs.close();
        this.cn.close();
        return listaLevels;
    }
     
    public void getOneLevels(int numeroLevel) throws SQLException{
        this.conectarOracle();
        try {
            String consulta = "SELECT * FROM level where id =" + numeroLevel;
            this.rs.setCommand(consulta);
            this.rs.execute(this.cn);
            this.rs.beforeFirst();
            while (rs.next())
            {
                this.id = rs.getInt("id");
                this.nombre = rs.getString("nombre");
            }

        } catch (SQLException ex) {
            System.out.println("Error leyendo Levels: " + ex);
        }
        this.rs.close();
        this.cn.close();
    }
}
