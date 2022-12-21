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
public class enroll {
    
    DBConnect a = new DBConnect(LoginForm.loguser,LoginForm.logpass,LoginForm.database);
    public void EnrollStud(int eid, int studid, int subId){
        String query = "insert into enroll values("+eid+","+studid+","+subId+")";
        String query2 = "insert into grades values("+eid+", '', '', '', '')";
        try{
            a.st.executeUpdate(query);
            a.st.executeUpdate(query2);
        }catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }
    }
    
    public void UpdateUnits(int stid){
        String query ="update students set totUnits=(select total from (select studid as x, (select sum(subUnit) from students, enroll, subjects where students.studid=enroll.studid and enroll.subId=subjects.subId and students.studid=x) as total from students) as y where x="+stid+") where studid="+stid;
         try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert");
        }
    }
    public void UpdateEnrolledStud(int sbid){
        String query ="update subjects set numOfStud=(select total from (select subId as x, (select count(students.studid) from students, enroll, subjects where students.studid=enroll.studid and enroll.subId=subjects.subId and subjects.subId=x) as total from subjects) as y where x="+sbid+") where subId="+sbid;
        try{
            a.st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print("Failed to insert "+ex);
        }
    }
    public void DropSub(int studid, int subId, int eid){
        String query = "delete from enroll where studid=" +studid+" and subId="+subId;
        //String query2 = "delete from enroll where eid="+eid;
        try{
            a.st.executeUpdate(query);
            //a.st.executeUpdate(query2);
        }catch(Exception ex){
            System.out.print("Failed to insert "+ex);
        }
    }
}
