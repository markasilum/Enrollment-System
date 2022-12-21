/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollmentsystem;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author User1
 */
public class CreatePDF1 {

    /**
     * @param args the command line arguments
     */
    
    String table, temp;
    static String subid,subcode,prelim,midterm,prefinal,finals;
    static String studname,studadd,studcrs,studgender,yrlvl, database;
    static int numofsubs, min, max; 
    static int x=0;
    static int i=0, y=1;
    static String[] arr = new String[numofsubs];
    static DBConnect a = new DBConnect();
    static String username = LoginForm.loguser;
    static String studid = username.replaceAll("[^0-9]", "");
   
    static ArrayList<String> list= new ArrayList<String>();
    static String[] subidcnt = new String[list.size()];
   
    public static void main(String[] args) {
          
        Document doc = new Document();
        PdfWriter docWriter = null;
        DecimalFormat df = new DecimalFormat("0.00");
        Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
         
         
        
    try {
            String query1 = "select count(subId) from enroll where studid="+studid;
            a.rs = a.st.executeQuery(query1);
            a.rs.next();
            numofsubs=a.rs.getInt(1);
            String query2 = "select subId from enroll where studid="+studid;
            a.rs = a.st.executeQuery(query2);
            while(a.rs.next()){
               list.add(a.rs.getString("subId"));
            }
            subidcnt = list.toArray(subidcnt);
        
//        BufferedImage bufferedImage = ImageIO.read(new File("D:\\School\\2nd Year\\1st Semester\\Information Management\\University Seal.png"));
//        Image image = bufferedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
         
              
        String path = "C:\\Users\\Mark Emmanuel Asilum\\Desktop\\report.pdf";
        docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));         
        doc.open();  
        float[] columnWidths2 = {2f, 2f};
        float[] columnWidths3 = {1f};
        float[] columnWidths = {2f, 3f, 2f, 2f, 2f,2f};
   
        Image seal = Image.getInstance("D:\\School\\2nd Year\\1st Semester\\Information Management\\University Seal.png");
        seal.setAlignment(Image.ALIGN_RIGHT);
        seal.scaleAbsolute(20,20);
        seal.scaleAbsoluteHeight(30f);
        seal.scaleAbsoluteWidth(30f);
        //PdfPCell cell = new PdfPCell(img[1], true); 
                  
        PdfPTable table2 = new PdfPTable(columnWidths2);
        table2.setWidthPercentage(60f);
//        table2.getDefaultCell().setColspan(100);
//        table2.getDefaultCell().setRowspan(100);
        table2.getDefaultCell().setBorder(0);
        //table2.getDefaultCell().setFixedHeight(70);
        //table2.getDefaultCell().setHorizontalAlignment(50);
        table2.addCell(seal);
        table2.getDefaultCell().setBorder(0);
        table2.addCell("Ateneo De Davao University\nRegistrars Office");
        doc.add(table2);
   
   
   
   PdfPTable table1 = new PdfPTable(columnWidths3);
   table1.setWidthPercentage(90f); 
   Paragraph paragraph1 = new Paragraph();
   
   insertCell(table1, "Student Grade Sheet \t", Element.ALIGN_CENTER, 6, bfBold12,0,255,255,255);   
   paragraph1.add(table1);
   doc.add(paragraph1);
   
   PdfPTable table3 = new PdfPTable(columnWidths2);
   table3.setWidthPercentage(100f);
   
   String query3 = "select database(), students.studid, students.studname, students.studadd, students.studcrs, students.studgender, students.yrlvl from students where studid="+studid;
   a.rs = a.st.executeQuery(query3);
                    while(a.rs.next())
                    {
                        database = a.rs.getString("database()"); 
                        studname = a.rs.getString("studname"); 
                        studadd = a.rs.getString("studadd"); 
                        studcrs = a.rs.getString("studcrs"); 
                        studgender = a.rs.getString("studgender"); 
                        yrlvl = a.rs.getString("yrlvl");
                        
                    }
   table3.getDefaultCell().setBorder(0);
   table3.addCell("Student ID: "+studid);
   table3.getDefaultCell().setBorder(0);
   table3.addCell("School Year: "+database);
   table3.getDefaultCell().setBorder(0);
   table3.addCell("Student Name: "+studname);
   table3.getDefaultCell().setBorder(0);
   table3.addCell("Student Course: "+studcrs);
   doc.add(table3);
   
   PdfPTable table4 = new PdfPTable(columnWidths3);
   table4.setWidthPercentage(100f);
   table4.getDefaultCell().setBorder(0);
   table4.addCell("Student Year: "+yrlvl);   
   doc.add(table4);
   
   Paragraph paragraph2 = new Paragraph();
   PdfPTable table5 = new PdfPTable(columnWidths);
   table5.setWidthPercentage(100f);
   
  
   
   insertCell(table5, "SubID", Element.ALIGN_CENTER, 1, bfBold12,1,255,255,255);  
   insertCell(table5, "Sub Code", Element.ALIGN_CENTER, 1, bfBold12,1,255,255,255);  
   insertCell(table5, "Prelim", Element.ALIGN_CENTER, 1, bfBold12,1,255,255,255);  
   insertCell(table5, "Midterm", Element.ALIGN_CENTER, 1, bfBold12,1,255,255,255);  
   insertCell(table5, "Prefinal", Element.ALIGN_CENTER, 1, bfBold12,1,255,255,255);  
   insertCell(table5, "Final", Element.ALIGN_CENTER, 1, bfBold12,1,255,255,255);    
   paragraph2.add(table5);
   doc.add(paragraph2);
   
   
   
   
   Paragraph paragraph4 = new Paragraph();
   PdfPTable table6 = new PdfPTable(columnWidths);
   table6.setWidthPercentage(100f); 
   while(i < numofsubs){
                String query = "select subjects.subId, subjects.subCode, grades.prelim, grades.midterm, grades.prefinal, grades.final from (enroll left outer join grades on grades.gradeId=enroll.eid) left outer join subjects on subjects.subId=enroll.subId where enroll.studid="+studid+" and enroll.subId="+subidcnt[i];
                a.rs = a.st.executeQuery(query);
                    while(a.rs.next())
                    {                        
                        subid = a.rs.getString("subId");
                        subcode = a.rs.getString("subCode"); 
                        prelim = a.rs.getString("prelim"); 
                        midterm = a.rs.getString("midterm"); 
                        prefinal = a.rs.getString("prefinal"); 
                        finals = a.rs.getString("final");                       
                    }
       
        insertCell(table6, subid, Element.ALIGN_CENTER, 1, bf12,1,255,255,255);  
        insertCell(table6, subcode, Element.ALIGN_CENTER, 1, bf12,1,255,255,255);  
        insertCell(table6, prelim, Element.ALIGN_CENTER, 1, bf12,1,255,255,255);  
        insertCell(table6, midterm, Element.ALIGN_CENTER, 1, bf12,1,255,255,255);  
        insertCell(table6, prefinal, Element.ALIGN_CENTER, 1, bf12,1,255,255,255);  
        insertCell(table6, finals, Element.ALIGN_CENTER, 1, bf12,1,255,255,255); 
        i=i+1;


   }
    paragraph4.add(table6);
    doc.add(paragraph4);
    
    //"select students.studid, students.studname, students.studadd, students.studcrs, students.studgender, students.yrlvl, subjects.subId, subjects.subCode, grades.prelim, grades.midterm, grades.prefinal, grades.final
    //from (students left outer join enroll on enroll.studid=students.studid) left outer join subjects on enroll.subId=subjects.subId left outer join grades on grades.gradeId=enroll.eid where students.studid=2;";
     

        File file = new File("C:\\Users\\Mark Emmanuel Asilum\\Desktop\\report.pdf"); 
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
        
       
    }

  catch(Exception ex){
        ex.printStackTrace();
    }

  finally
  {
   if (doc != null){
    //close the document
    doc.close();
   }
   if (docWriter != null){
    //close the writer
    docWriter.close();
   }
  }  
   try{
  Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + "c:\\docs\\report.pdf");
    }catch(Exception e){

    } 
  
}
    
    
  private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font,int border, int r, int g, int b){
  
  //create a new cell with the specified Text and Font
  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
  //set the cell alignment
  
  
  cell.setHorizontalAlignment(align);
  //set the cell column span in case you want to merge two or more cells
  cell.setColspan(colspan);
  //in case there is no text and you wan to create an empty row
  if(text.trim().equalsIgnoreCase("")){
   cell.setMinimumHeight(10f);
  }
  if(border==0)
    cell.setBorder(Rectangle.NO_BORDER);
  else
    cell.setBorder(Rectangle.BOX);  
  cell.setBackgroundColor(new BaseColor(r,g,b));
  //add the call to the table
  table.addCell(cell);

 }   
    
}