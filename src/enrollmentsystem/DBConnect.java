/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollmentsystem;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Mark Emmanuel Asilum
 */
public class DBConnect {
    public Connection con, con2;
    public Statement st;
    public ResultSet rs;
    public String name, pass, database="?";
    //
    public DBConnect(String Name, String Pass, String Database)
    {
        this.name = Name;
        this.pass = Pass;
        this.database = Database;
         try{
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database+"?zeroDateTimeBehavior=convertToNull",name,pass);
             st = con.createStatement();
//             con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database+"?zeroDateTimeBehavior=convertToNull");
//             st = con2.createStatement();
             
         }catch(Exception ex){
             
         }
    }
    public DBConnect(String Name, String Pass)
    {
        this.name = Name;
        this.pass = Pass;
        //this.database = Database;
         try{
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/",name,pass);
             st = con.createStatement();
             
         }catch(Exception ex){
             
         }
    }
    public DBConnect()
    {
     
        //this.database = Database;
         try{
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1st_sy2022_2023?zeroDateTimeBehavior=convertToNull","root","root");
             st = con.createStatement();
             
         }catch(Exception ex){
             
         }
    }
 
    
}
