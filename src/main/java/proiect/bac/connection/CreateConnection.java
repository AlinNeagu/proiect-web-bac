package proiect.bac.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateConnection {

	static Statement st;
	
    public static Connection getConnection() throws SQLException
    {	
    Connection conn = null;
        try{
        	 String url = "jdbc:sqlserver://localhost:1433;"+"databaseName=Proiect2016Bacalaureat";
             String username = "ioananenciu";
             String password = "bac2016Proiect";
           conn = DriverManager.getConnection(url,username,password);
            }
        catch(SQLException e){
        	System.out.println(e);
        }
        st = conn.createStatement();
        return conn;
    }
    
    
    public void executeSQLCommand(String sql) throws Exception {
        st.executeUpdate(sql);
      }
      public void checkData(String sql) throws Exception {
        ResultSet rs = st.executeQuery(sql);
        ResultSetMetaData metadata = rs.getMetaData();

        for (int i = 0; i < metadata.getColumnCount(); i++) {
          System.out.print("\t"+ metadata.getColumnLabel(i + 1)); 
        }
        System.out.println("\n----------------------------------");

        while (rs.next()) {
          for (int i = 0; i < metadata.getColumnCount(); i++) {
            Object value = rs.getObject(i + 1);
            if (value == null) {
              System.out.print("\t       ");
            } else {
              System.out.print("\t"+value.toString().trim());
            }
          }
          System.out.println("");
        }
      }
}

/*
package proiect.bac.connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class CreateConnection {

	private static Connection conn = null;

		public static Connection getConnection() throws Exception
	    {
	        if(conn!=null){
	        	return conn;
	        }


        	Properties props = new Properties();
    		props.load(new FileInputStream("D:/JavaEE/ProiectBAC/src/config.properties"));
    		
    		System.out.println(Arrays.toString(props.stringPropertyNames().toArray()));
    		
    		  String url = props.getProperty("url");
              String username = props.getProperty("username");
              String password = props.getProperty("password");
            conn = DriverManager.getConnection(url,username,password);
	        
	       
	        return conn;
	    }
		
		
		
	
}

*/
