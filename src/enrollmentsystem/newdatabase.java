/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollmentsystem;

/**
 *
 * @author Mark Emmanuel Asilum
 */
public class newdatabase {
    DBConnect a = new DBConnect(LoginForm.loguser,LoginForm.logpass,LoginForm.database);
    public void createdatabase(String databasename){
        String query ="create database "+databasename;
        
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
}
