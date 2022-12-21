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
public class grades {
    DBConnect a = new DBConnect(LoginForm.loguser,LoginForm.logpass,LoginForm.database);
    public void addGrade(String prelim, String midterm, String prefinal, String fin, String eid){
        
        //select eid from enroll where studid= x and subId =x;
        String query = "update grades set prelim='"+prelim+"', midterm='"+midterm+"', prefinal='"+prefinal+"', final='"+fin+"' where gradeId="+eid;
        //String query2= "update grades set prelim='A', midterm='A', prefinal='A', final='A' where enroll_eid=1";
        
        try{
            a.st.executeUpdate(query);
    
        }catch(Exception ex){
            System.out.print("Failed to insert grades"+ex);
        }
    
    }
}
