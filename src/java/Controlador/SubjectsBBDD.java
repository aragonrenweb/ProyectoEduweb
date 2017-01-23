package Controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import Modelo.Asignatura;
import Modelo.Subjects;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class SubjectsBBDD implements Controller
{

    Connection cn;
    
    private Object getBean(String nombrebean, ServletContext servlet)
    {
        ApplicationContext contexto = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet);
        Object beanobject = contexto.getBean(nombrebean);
        return beanobject;
    }
    
    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception 
    {
        int idLevel = Integer.parseInt(hsr.getParameter("idPais"));
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",hsr.getServletContext());
        this.cn = dataSource.getConnection();
        
        
        try {
            ArrayList<Subjects> listasubjs = new ArrayList<Subjects>();
            Statement st = this.cn.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM subject where id_Level =" + idLevel);
            while (rs.next())
            {
                Subjects subjs = new Subjects();
                subjs.setId(rs.getInt("id"));
                subjs.setId_level(rs.getInt("id_level"));
                subjs.setNombre(rs.getString("nombre_subject"));
                listasubjs.add(subjs);
            }
            mv.addObject("listasubjects", listasubjs);
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        
        mv.addObject("listalevels", this.getLevels());
        
        return mv;
    }

    private ArrayList<Asignatura> getLevels()
    {
        try {
            ArrayList<Asignatura> listaLevels = new ArrayList<Asignatura>();
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM level");
            while (rs.next())
            {
                Asignatura levels = new Asignatura();
                levels.setId(rs.getInt("id"));
                levels.setNombre(rs.getString("nombre"));
                listaLevels.add(levels);
            }
            return listaLevels;
        } catch (SQLException ex) {
            System.out.println("Error leyendo Levels: " + ex);
        }
        return null;
    }
    
    

//    private ArrayList<Subjects> obtenerSubjects()
//    {
//        
//        try {
//            ArrayList<Subjects> listasubjs = new ArrayList<Subjects>();
//            Statement st = this.cn.createStatement();
//            
//            ResultSet rs = st.executeQuery("SELECT * FROM subject");
//            while (rs.next())
//            {
//                Subjects subjs = new Subjects();
//                subjs.setId(rs.getInt("id"));
//                subjs.setId_level(rs.getInt("id_level"));
//                subjs.setNombre(rs.getString("nombre_subject"));
//                listasubjs.add(subjs);
//            }
//            return listasubjs;
//        } catch (SQLException ex) {
//            System.out.println("Error leyendo subjects: " + ex);
//        }
//        return null;
//    }
}

