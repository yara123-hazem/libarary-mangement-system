/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import static constants.FileNames.LIBRARIANS_FILENAME;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author sophi
 */
public class AdminRole implements Role {

    private LibrarianUserDatabase database;

    public AdminRole() {
        database = new LibrarianUserDatabase(LIBRARIANS_FILENAME);
        try {
            database.readFromFile();
        } catch (FileNotFoundException ex) {
            System.out.println("error reading Librarians file");
        }
    }

    public void addLibrarian(String librarianId, String name, String email, String address, String phoneNumber) {
        LibrarianUser record = new LibrarianUser(librarianId, name, email, address, phoneNumber);
        database.insertRecord(record);
        this.logout();
    }

    public LibrarianUser[] getListOfLibrarians() {
        ArrayList<Record> rec=database.returnAllRecords();
        
        LibrarianUser[] array = new LibrarianUser[rec.size()];
        int i=0;
        for(Record r:rec){
            array[i++]=(LibrarianUser)r;
        }
        return array;
    }

    public void removeLibrarian(String key) {
        database.deleteRecord(key);
        this.logout();
    }

    @Override
    public void logout() {
        database.saveToFile();
    }
}
