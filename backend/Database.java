/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sophi
 */
public abstract class Database {

    private final String fileName;
    protected ArrayList<Record> records = new ArrayList<>();

    public Database(String fileName) {
        this.fileName = fileName;
    }

    public void readFromFile() throws FileNotFoundException {
        try {
            File f=new File(fileName);
            if(f.createNewFile())
                System.out.println("File \""+fileName+"\" is created");
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                insertRecord(createRecordFrom(scan.nextLine()));
            }
            scan.close();
        } catch (IOException ex) {
            System.out.println("error");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public ArrayList<Record> returnAllRecords() {
        
        return records;
    }
    public abstract Record createRecordFrom(String line);

    public boolean contains(String key) {
        for (Record u : records) {
            if (u.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Record getRecord(String key) {
        for (Record u : records) {
            if (u.getSearchKey().equals(key)) {
                return u;
            }
        }
        return null;
    }

    public void insertRecord(Record record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        records.remove(getRecord(key));
    }

    public void saveToFile() {
        try {
            FileWriter f = new FileWriter(fileName);
            for (int i = 0; i < records.size(); i++) {
                f.write(records.get(i).lineRepresentation());
            }
            f.close();
        } catch (IOException ex) {
            System.out.println("error");
        }
    }
}

