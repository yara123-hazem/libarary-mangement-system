/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import static constants.FileNames.BOOKS_FILENAME;
import static constants.FileNames.STUDENTSBOOKS_FILENAME;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author sophi
 */
public class LibrarianRole implements Role {

    private BookDatabase booksDatabase;
    private StudentBookDatabase sBDatabase;

    public LibrarianRole() {
        booksDatabase = new BookDatabase(BOOKS_FILENAME);
        try {
            booksDatabase.readFromFile();
        } catch (FileNotFoundException ex) {
            System.out.println("error reading Books file");
            //Logger.getLogger(LibrarianRole.class.getName()).log(Level.SEVERE, null, ex);
        }
        sBDatabase = new StudentBookDatabase(STUDENTSBOOKS_FILENAME);
        try {
            sBDatabase.readFromFile();
        } catch (FileNotFoundException ex) {
            System.out.println("error reading StudentsBooks file");
            //Logger.getLogger(LibrarianRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addBook(String id, String title, String authorName, String publisherName, int quantity) {
        Book b = new Book(id, title, authorName, publisherName, quantity);
        booksDatabase.insertRecord(b);
        booksDatabase.saveToFile();
    }

    public Book[] getListOfBooks() {
        ArrayList<Record> rec = booksDatabase.returnAllRecords();

        Book[] array = new Book[rec.size()];
        int i = 0;
        for (Record r : rec) {
            array[i++] = (Book) r;
        }
        return array;

    }

    public StudentBook[] getListOfBorrowingOperations() {

        ArrayList<Record> rec = sBDatabase.returnAllRecords();

        StudentBook[] array = new StudentBook[rec.size()];
        int i = 0;
        for (Record r : rec) {
            array[i++] = (StudentBook) r;
        }
        return array;
    }

    public int borrowBook(String studentId, String bookId, LocalDate borrowDate) {
        if (sBDatabase.contains(studentId + "," + bookId)) {
            return 1;
        }
        if (booksDatabase.getRecord(bookId).getQuantity() == 0) {
            return 0;
        }
        Book b = booksDatabase.getRecord(bookId);
        b.setQuantity(b.getQuantity() - 1);
        StudentBook sB = new StudentBook(studentId, bookId, borrowDate);
        sBDatabase.insertRecord(sB);
        logout();
        //booksDatabase.saveToFile();
        //sBDatabase.saveToFile();
        return 2;
    }

    private double calculateLateFee(StudentBook sB, LocalDate returnDate) {
        long daysBetween = ChronoUnit.DAYS.between(sB.getBorrowDate(), returnDate);
        if (daysBetween < 7) {
            return 0;
        }
        return (daysBetween - 7) * 0.5;
    }

    public double returnBook(String studentId, String bookId, LocalDate returnDate) {
        Book b = booksDatabase.getRecord(bookId);
        StudentBook sB = sBDatabase.getRecord(studentId + "," + bookId);
        b.setQuantity(b.getQuantity() + 1);
        double lateFee = calculateLateFee(sB, returnDate);
        sBDatabase.deleteRecord(sB.getSearchKey());
        logout();
        //booksDatabase.saveToFile();
        //sBDatabase.saveToFile();
        return lateFee;
    }

    @Override
    public void logout() {
        booksDatabase.saveToFile();
        sBDatabase.saveToFile();
    }
}
