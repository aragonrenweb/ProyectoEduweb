/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Jesus
 */
public class LessonsFunc {
    
    public LessonsFunc(){
        
    }
    
    public ArrayList<Subjects> takeSubject(Connection cn, int numero) throws SQLException{
        
        
        ArrayList<Subjects> listasubjs = new ArrayList<>();
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subject where id_level =" + numero);
            
            while (rs.next())
            {
                Subjects subjs = new Subjects();
                subjs.setId(rs.getInt("id"));
                subjs.setId_level(rs.getInt("id_level"));
                subjs.setNombre(rs.getString("nombre_subject"));
                listasubjs.add(subjs);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error leyendo subjects: " + ex);
        }
        return null;
    }
}
