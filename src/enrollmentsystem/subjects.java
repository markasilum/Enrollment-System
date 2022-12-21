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
public class subjects {
    int subId;
    int subCode;
    String subDes;
    int subUnit;
    String subSched;
    
    DBConnect a = new DBConnect(LoginForm.loguser,LoginForm.logpass,LoginForm.database);
    
    public void AddSub(int id, int code, String descr, int unit, String sched){
        
        String query = "insert into subjects values("+id+",'"+code+"','"+descr+"','"+unit+"','"+sched+"',NULL)";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
    
    public void DelSub(String delete){
        if(delete.equals(";")){
            delete="";
        }
        
        String query = "delete from subjects where subId in(select subId from (select * from subjects  " + delete  + ") as x)";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
    
    public void EditSub(int code, String descr, int unit, String sched,String update){
        
        String query = "update subjects set subCode ='"+code+"', subDes ='"+descr+"', subUnit ='"+unit+"', subSched ='"+sched+"' where subId in (select subId from (select * from subjects "+update+") as x)";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
}
