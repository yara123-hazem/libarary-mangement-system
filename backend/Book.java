/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author sophi
 */
public class Book implements Record {
    private String bookId , title,authorName,publisherName;
    private int quantity;
    
    public Book (String id,String title,String authorName, String publisherName , int quantity){
        bookId=id;
        this.title=title;
        this.authorName=authorName;
        this.publisherName=publisherName;
        this.quantity=quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
    @Override
    public String lineRepresentation(){
        return(bookId+"," +title+"," +authorName+"," + publisherName+","+ quantity+"\n");
    }
  
    @Override
    public String getSearchKey() {
        return bookId;
    }
}