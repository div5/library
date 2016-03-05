import java.io.*;

public class Book {
  private String ISBN, name, author, category, status;
  private boolean available;
  private int timeOverdue;
  
  private final int OVERDUE_TIME = 14; //14 days or 2 weeks
  
  public Book(String i, String n, String a, String c, String s) {
    ISBN = i;
    name = n;
    author = a;
    category = c;
    status = s;
    
    available = true;
    timeOverdue = 0;
  }
  
  //Accessor methods
  public String getISBN() {
    return ISBN;
  }
  public String getName() {
    return name;
  }
  public String getAuthor() {
    return author;
  }
  public String getCategory() {
    return category;
  }
  public String getStatus() {
    return status;
  }
  public boolean isAvailable() {
    return available;
  }
  
  @Override
  public String toString() {
    return ISBN + "," + name + "," + author + "," + category + "," + status;
  }
  
  //Changes book availability
  public void bookBorrowed() {
    available = false;
    timeOverdue = 0;
  }
  
  public void bookReturned() {
    available = true;
    timeOverdue = 0;
  }
  
  public void advanceDate() { //advances by 1 day
    timeOverdue++;
  }
  
  public boolean isOverdue() {
    if (timeOverdue >= OVERDUE_TIME)
      return true;
    return false;
  }
  
  public void borrowHistory(String borrowerName) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(getName() + "BorrowHistory.txt", true)); //true to append
    bw.write(borrowerName);
    bw.newLine();
    bw.close();
  }
}