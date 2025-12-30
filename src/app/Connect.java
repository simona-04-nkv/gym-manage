
package app;


import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class Connect {
    public Connection conn;
    public Statement stmt;
    
    
    public Connect()
    {
        try{
            conn=DriverManager.getConnection("jdbc:sqlite:kursova.db");
            System.out.println("Successfully connected to database!");
        }catch (SQLException e) {
            System.out.println("Error with: " + e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void select(){
        //String sql=""
    }
    
    public void close(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Throwable ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
