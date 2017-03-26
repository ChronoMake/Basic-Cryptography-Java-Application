/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto_sys;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author arjun
 */
public class DBConnect {
    Connection conn=null;
    public static Connection ConnDB(){
        try{System.out.println("here");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cryptosys","Arjun","Riseofcolonia@13");
            JOptionPane.showMessageDialog(null,"Connected to database");
            System.out.println("Connected");
            return conn;
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        
       return null; }
    }
    
}
