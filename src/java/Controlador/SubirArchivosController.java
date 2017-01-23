/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SubirArchivosForm;
import java.io.File;
import java.nio.file.Files;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author MacAragon
 */
public class SubirArchivosController implements Controller{
  
    Connection cn;
    
    
    private Object getBean(String nombrebean, ServletContext servlet)
    {
        ApplicationContext contexto = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet);
        Object beanobject = contexto.getBean(nombrebean);
        return beanobject;
    }
 
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String archivo = request.getParameter("TXTnombreArchivo");
        
        int idLessons = Integer.parseInt(request.getParameter("TXTidLessons"));
        
        
        
        ModelAndView mv = new ModelAndView("lessons");
        DriverManagerDataSource dataSource;
        dataSource = (DriverManagerDataSource)this.getBean("dataSource",request.getServletContext());
        this.cn = dataSource.getConnection();
        
        
        File file = new File(archivo);
    
        try{    
            
            FileInputStream fis = new FileInputStream(file);
            PreparedStatement ps = cn.prepareStatement("INSERT INTO archivos VALUES (?,?,?)");

            ps.setInt(2, idLessons);
            ps.setString(3, file.getName());
            ps.setBinaryStream(4, fis, file.length());

            int numArchivo = ps.executeUpdate();
            ps.close();
            fis.close();
//            Path f = Paths.get("C:/Users/MacAragon/Desktop/shahad.txt");
//            
//                InputStream in = Files.newInputStream(f);
//                    
//                PreparedStatement ps = cn.prepareStatement("INSERT INTO archivos VALUES (?,?,?,?)");
//                ps.setInt(1, 1);
//                ps.setInt(2, 4);
//                ps.setString(3, "C:/Users/MacAragon/Desktop/shahad.txt");
//                ps.setBinaryStream(4, in, Files.size(f));
//                ps.executeUpdate();
//                ps.close();
            mv.addObject("ArchivoSubido", numArchivo);
        }catch (SQLException ex) {
            String error = ex.toString();
            mv.addObject("error", error);
        }
        
          
        return new ModelAndView("Lessons");
    }

 
}
