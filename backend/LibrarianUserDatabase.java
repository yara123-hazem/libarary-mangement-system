/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.ArrayList;

/**
 *
 * @author sophi
 */
public class LibrarianUserDatabase extends Database {   

    public LibrarianUserDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public LibrarianUser createRecordFrom(String line) {
        String part[] = line.split(",");
        LibrarianUser l = new LibrarianUser(part[0],part[1], part[2], part[3], part[4]);
        return l;
    }
    
//    public ArrayList<LibrarianUser> returnAllRecords() {
//        ArrayList<LibrarianUser> rec = new ArrayList<>();
//        for(int i=0 ; i<records.size();i++){ 
//            rec.add((LibrarianUser)records.get(i));
//        }
//        return rec;
//    }
    
    @Override
    public LibrarianUser getRecord(String key) {
        return (LibrarianUser)super.getRecord(key);
        //casting return of super(User) to Librarian User
    }
}