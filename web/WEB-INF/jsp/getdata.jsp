    <%@page import="java.sql.*" %>
  <%@page import="java.util.*" %>
  <%@page import="org.json.JSONObject" %>

 <%
     Connection con= null;
  try{
   Class.forName("com.mysql.jdbc.Driver").newInstance();
 con =      DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","password");

         ResultSet rs = null;
         List empdetails = new LinkedList();
        JSONObject responseObj = new JSONObject();

         String query = "SELECT id,name from employee";
          PreparedStatement pstm= con.prepareStatement(query);

            rs = pstm.executeQuery();
           JSONObject empObj = null;

         while (rs.next()) {
                               String name = rs.getString("name");
            int empid = rs.getInt("id");
            empObj = new JSONObject();
            empObj.put("name", name);
            empObj.put("empid", empid);
            empdetails.add(empObj);
         }
         responseObj.put("empdetails", empdetails);
    out.print(responseObj.toString());
     }
     catch(Exception e){
        e.printStackTrace();
    }finally{
        if(con!= null){
            try{
            con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
  %>
   

       