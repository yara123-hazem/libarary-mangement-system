/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sophi
 */
public class StudentBookDatabase extends Database {   

    public StudentBookDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public StudentBook createRecordFrom(String line) {
        String part[] = line.split(",");
        String date[]=part[2].split("-");
        int day=Integer.parseInt(date[0]);
        int month=Integer.parseInt(date[1]);
        int year=Integer.parseInt(date[2]);
        LocalDate d =LocalDate.of(year,month,day);
        StudentBook l = new StudentBook(part[0],part[1], d);
        return l;
    }
    
//    public ArrayList<StudentBook> returnAllRecords() {
//        ArrayList<StudentBook> rec = new ArrayList<>();
//        for(int i=0 ; i<records.size();i++){ 
//            rec.add((StudentBook)records.get(i));
//        }
//        return rec;
//    }
    
    @Override
    public StudentBook getRecord(String key) {
        return (StudentBook)super.getRecord(key);
        //casting return of super(User) to Librarian User
    }
}
