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
public class students {
    int studid;
    String studname;
    String studadd;
    String studcrs;
    String studgender;
    String yrlvl;

    DBConnect a = new DBConnect(LoginForm.loguser, LoginForm.logpass,LoginForm.database);
    
    
    public void AddStud(int id, String name, String addr, String course, String gender, String yearlvl){
        
        String query = "insert into students values("+id+",'"+name+"','"+addr+"','"+course+"','"+gender+"','"+yearlvl+"',NULL)";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to add student");
        }
    }
    
    public void TotalUnits(int units){
        String query = "update students set totUnits=(select total from(select studid as x, (select sum(subUnit) from students, enroll, subjects where students.studid=enroll.studid and enroll.subId=subjects.subId and students.studid=x) as total from students)as y where x =3) where studid=3";
        
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
    
    public void DelStud(String delete){
        if(delete.equals(";")){
            delete="";
        }
        
        String query = " delete from students where studid in(select studid from (select * from students  " + delete  + ") as x)";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
    public void EditStud(String name, String addr, String course, String gender, String yearlvl,String update){
        //int id, String name, String addr, String course, String gender, String yearlvl
        //String query = "update students set "+update+"where studid in(select studid from(select * from students where"+update+") as x)";
        //String query = "update students set studname ='"+name+"', studadd ='"+addr+"', studcrs ='"+course+"', studgender ='"+gender+"', yrlvl ='"+yearlvl+"'where studid ="+id+"";
        if(update.equals(";")){
            update="";
        }
        String query = "update students set studname ='"+name+"', studadd ='"+addr+"', studcrs ='"+course+"', studgender ='"+gender+"', yrlvl ='"+yearlvl+"' where studid in (select studid from (select * from students "+update+") as x)";
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
    
    public void createUserStud(String studid, String studname){
        
        String query = "create user if not exists '"+studid+studname+"'@'localhost' identified by 's"+studid+studname+"'";
        
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
     
    public void grantPriv(String current, String studid, String studname){
        String query = "grant select on "+current+".students to '"+studid+studname+"' @'localhost'";
        String query2 = "grant select on "+current+".enroll to '"+studid+studname+"' @'localhost'";
        String query3 = "grant select on "+current+".grades to '"+studid+studname+"' @'localhost'";
        String query4 = "grant select on "+current+".invoice to '"+studid+studname+"' @'localhost'";
        String query5 = "grant select on "+current+".subjects to '"+studid+studname+"' @'localhost'";
        String query6 = "grant select on "+current+".transcharg to '"+studid+studname+"' @'localhost'";
       
        
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
    public void dropStud(String studidd, String studnamee){
       
       
            String query4 = "drop user '"+studidd+studnamee+"'@'localhost'";
        
            try{
                a.st.executeUpdate(query4);
            }catch(Exception ex){
                System.out.print("Failed to insert"+ex);
            }
        
        
        
    }
}

