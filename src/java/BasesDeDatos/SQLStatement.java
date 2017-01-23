package database;


/*
 * SQLStatement.java       
 * Simple JDBC Sample using Pervasive JDBC driver. 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//import org.w3c.dom.Document;
import java.io.PrintWriter;
import java.util.Calendar;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import pervasive.jdbc.*;

public class SQLStatement {

    public static Connection SageConnection() throws SQLException {
        String url = "jdbc:pervasive://192.168.60.31:1583/INSTITUTOALBERTOEINS?transport=tcp";
        try {
            Class.forName("com.pervasive.jdbc.v2.Driver");

        } catch (Exception e) {
            System.err.print("ClassNotFoundException: ");
            System.out.println(e.toString());
            System.err.println(e.getMessage());
        }
        Connection conn = null;
        conn = DriverManager.getConnection(url, "Peachtree", "anny0510");

        return conn;
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

    public static ResultSet SageQuery(String queryString) throws SQLException {
        return Query(SageConnection(), queryString);
    }

    public static String fechaTimestamp() {
        String aux = "";
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        aux = currentTimestamp.toString();
        aux = aux.replaceAll(" ", "___").replaceAll("\\.", "_").replaceAll(":", "__");
        return aux;
    }

    public static void consultDB() throws Exception {

        String query //= "select * from Customers where customerid = 'C00855'";

                = "SELECT																											 	"
                + "																													  "
                + " Customers.CustomerID																								  "
                + ", cast(replace(Customers.CustomerID,'C','') as INTEGER) FamilyCode												  "
                + ", if(  JrnlHdr.JrnlKey_Journal= 1 , 'Payment' , 'Charge') Descripcion												  "
                + ", JrnlHdr.TransactionDate																							  "
                + ", JrnlHdr.Reference																								  "
                + ", if(JrnlRow.Amount>=0,JrnlRow.Amount,null) Debito																  "
                + ", if(JrnlRow.Amount<0,JrnlRow.Amount,null) Credito																  "
                + " FROM   {oj ((JrnlHdr JrnlHdr INNER JOIN JrnlRow JrnlRow ON JrnlHdr.PostOrder=JrnlRow.PostOrder) 					  "
                + "	 		LEFT OUTER JOIN Customers Customers ON JrnlRow.CustomerRecordNumber=Customers.CustomerRecordNumber) 	  "
                + "	 		LEFT OUTER JOIN Chart Chart ON JrnlRow.GLAcntNumber=Chart.GLAcntNumber}									  "
                + " WHERE  														  "
                + " 		JrnlHdr.TransactionDate<={d '2016-03-17'} AND																  "
                + "	  (JrnlHdr.JrnlKey_Journal = 3 or JrnlHdr.JrnlKey_Journal = 1)													  "
                + "	 AND JrnlRow.RowType=0																							  "
                + "	 AND Customers.CustomerID = 'C00855' 																			  "
                + "	 AND Chart.AccountType = 1																						  "
                + "	 AND NOT (JrnlHdr.Reference= 'SALDO' and JrnlRow.Amount < 0)														  " //+"Order by JrnlHdr.TransactionDate desc	;																			  "
                ;

        try {
            ResultSet rs = SageQuery(query);
//		            ResultSetMetaData rsmd = rs.getMetaData();

            String xml = JDBCUtil.toXml(rs);

            // String filename = "C:/Users/Moises/Downloads/data.xml";
            String filename = "C:/Users/secserver2/Documents/NetBeansProjects/data " + fechaTimestamp() + ".xml";
            try (PrintWriter out = new PrintWriter(filename)) {
                out.println(xml);
            }

        } catch (SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
        }

    }

}
