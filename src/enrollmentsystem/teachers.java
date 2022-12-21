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
public class teachers {
    int teachId;
    String teachName;
    String teachDept;
    String teachAddr;
    int teachCont;
    String teachStat;

    DBConnect a = new DBConnect(LoginForm.loguser,LoginForm.logpass,LoginForm.database);
    public void AddTeach(int id, String name, String dept, String addr, int contact, String status){
        
        String query = "insert into teachers values("+id+",'"+name+"','"+dept+"','"+addr+"','"+contact+"','"+status+"',NULL)";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
    
    public void DelTeach(String delete){
        if(delete.equals(";")){
            delete="";
        }
        
        String query = "delete from teachers where teachId in(select teachId from (select * from teachers  " + delete  + ") as x)";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
    public void EditTeach(String name, String dept, String addr, int contact, String status, String update){
        //"', teachAddr ='"+addr+"', contactNum ='"+contact+"', stat ='"+status+
        String query = "update teachers set teachName ='"+name+"', teachDept ='"+dept+"', teachAddr ='"+addr+"', teachCont ='"+contact+"', teachStat ='"+status+"' where teachId in (select teachId from (select * from teachers "+update+") as x)";
        //String query = "update teachers set teachName ='"+name+"'where teachId ="+id+"";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
    public void createUserTeach(String teachId, String teachName){
        
        String query = "create user if not exists '"+teachId+teachName+"'@'localhost' identified by 't"+teachId+teachName+"'";
        
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
     
    public void grantPriv(String database, String teachId, String teachName){
        String query = "grant select, update on "+database+".teachers to '"+teachId+teachName+"' @'localhost'";
        String query2 = "grant select, update on "+database+".assign to '"+teachId+teachName+"' @'localhost'";
        String query3 = "grant select, update on "+database+".subjects to '"+teachId+teachName+"' @'localhost'";
        String query4 = "grant select, update on "+database+".students to '"+teachId+teachName+"' @'localhost'";
        String query5 = "grant select, update on "+database+".enroll to '"+teachId+teachName+"' @'localhost'";
        String query6 = "grant select, update on "+database+".grades to '"+teachId+teachName+"' @'localhost'";
        
       
        
        try{
            a.st.executeUpdate(query);
            a.st.executeUpdate(query2);
            a.st.executeUpdate(query3);
            a.st.executeUpdate(query4);
            a.st.executeUpdate(query5);
            a.st.executeUpdate(query6);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
    
    public void dropTeach(String teachId, String teachName){
       
       
            String query4 = "drop user '"+teachId+teachName+"'@'localhost'";
        
            try{
                a.st.executeUpdate(query4);
            }catch(Exception ex){
                System.out.print("Failed to insert"+ex);
            }
        
        
        
    }
}
