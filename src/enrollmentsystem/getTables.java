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
public class getTables {
    static String subid,subcode,prelim,midterm,prefinal,finals;
    static String studid;
    static int num;
    DBConnect a = new DBConnect();
    
    public void getTable(){
//        String query1 = "select subId, subCode from subjects where subId = 1";
//        try{
//            a.rs = a.st.executeQuery(query1);
//
//            while(a.rs.next()){
//            subid = a.rs.getString("subId");
//            subcode = a.rs.getString("subCode");
//            }
//        }
//        catch(Exception ex){
//            System.out.print("Failed to insert"+ex);
//        }
        String query = "select subjects.subId, subjects.subCode, grades.prelim, grades.midterm, grades.prefinal, grades.final from enroll join grades on grades.gradeId=enroll.eid join subjects on subjects.subId=enroll.subId where enroll.studid=2 and enroll.subId=1";
           try{ 
            a.rs=a.st.executeQuery(query);
                    while(a.rs.next())
                    {
                        subid = a.rs.getString("subId");
                        subcode = a.rs.getString("subCode"); 
                        prelim = a.rs.getString("prelim"); 
                        midterm = a.rs.getString("midterm"); 
                        prefinal = a.rs.getString("prefinal"); 
                        finals = a.rs.getString("final"); 

                    }
          }
        catch(Exception ex){
            System.out.print("Failed to insert"+ex);
        }              
}
    public static void main(String[] args){
        getTables pr = new getTables();
        pr.getTable();
        System.out.print(subid);
        System.out.print(subcode);
        System.out.print(prelim);
        System.out.print(midterm);
        System.out.print(prefinal);
        System.out.print(finals);
        
    } 
}
