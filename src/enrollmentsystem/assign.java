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
public class assign {
    DBConnect a = new DBConnect(LoginForm.loguser,LoginForm.logpass,LoginForm.database);
    public void AssignTeach(int teachid, int subId, String date){
        String query = "insert into assign values("+teachid+","+subId+","+date+")";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
    
    public void UpdateAssSub(int teachId){
        String query = "update teachers set assSub=(select total from (select teachId as x, (select count(teachers.teachId) from teachers, assign, subjects where teachers.teachId=assign.teachId and assign.subId=subjects.subId and teachers.teachId=x) as total from teachers) as y where x="+teachId+") where teachId="+teachId;
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
    public void DeleteAssign(int teachid, int subId){
        String query = "delete from assign where teachId=" +teachid+" and subId="+subId;
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
        
}
