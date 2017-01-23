/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasesDeDatos;

import Modelo.User;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author secserver2
 */
public class SQLMicrosoft {

    public static Connection SQLConnection() throws SQLException {
        System.out.println("database.SQLMicrosoft.SQLConnection()");
        String url = "jdbc:sqlserver://ia-pan.odbc.renweb.com:1433;databaseName=ia_pan";
        String loginName = "ia_pan_cust";
        String password = "JuggleGolf+758";
        DriverManager.registerDriver(new SQLServerDriver());
        Connection cn = null;
        try {

            cn = DriverManager.getConnection(url, loginName, password);
        } catch (SQLException ex) {
            System.out.println("No se puede conectar con el Motor");
            System.err.println(ex.getMessage());
        }

        return cn;
    }

    public static ResultSet Query(Connection conn, String queryString) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(queryString);
        //stmt.close();
        //conn.close();
        return rs;
    }

    public static ResultSet SQLQuery(String queryString) throws SQLException {
        return Query(SQLConnection(), queryString);
    }

    public static User consultUserDB(String user,String password) throws Exception {
        User u = null;
       //user = 'shahad' and pswd = 'shahad1234' group = Spring
        String query = "select * from Person where username = '"+user+"' and pswd = HASHBYTES('MD5', CONVERT(nvarchar(4000),'"+password+"'));";
     
         ResultSet rs = SQLQuery(query);
            while(rs.next()){
               
                u = new User();
                u.setUser(rs.getString("username"));
                u.setPassword(password);
                u.setPersonid(rs.getInt("PersonID"));

            }
        return u;
    }
    public static int getSecurityGroupID(String name) throws SQLException{
        int sgid = 0;
        String query ="select groupid from SecurityGroups where Name like '"+name+"'";
         ResultSet rs = SQLQuery(query);
            while(rs.next()){
                sgid = rs.getInt(1);
            }
        return sgid;
    }
    
    public static boolean fromGroup(int groupid, int staffid) throws SQLException{
        boolean aux  = false;
        String query = "select * from SecurityGroupMembership where groupid = "+groupid+" and StaffID = " + staffid;
        ResultSet rs = SQLQuery(query);
            while(rs.next()){
                aux = true;
            }
      
        return aux;
    }
}
