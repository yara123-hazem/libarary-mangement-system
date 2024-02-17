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
public class BookDatabase extends Database {   

    public BookDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public Book createRecordFrom(String line) {
        String part[] = line.split(",");
        Book b = new Book(part[0],part[1], part[2], part[3],Integer.parseInt(part[4]));
        return b;
    }
    
//    public ArrayList<Book> returnAllRecords() {
//        ArrayList<Book> rec = new ArrayList<>();
//        for(int i=0 ; i<records.size();i++){ 
//            rec.add((Book)records.get(i));
//        }
//        return rec;
//    }
    
    @Override
    public Book getRecord(String key) {
        return (Book)super.getRecord(key);
        //casting return of super(User) to Book
    }
}