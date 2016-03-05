import java.util.ArrayList;
import java.io.*;

public abstract class Borrower {
  private ArrayList<Book> books, booksBorrowed, overdueBooks;
  private final int MAX_BOOKS;
  
  public Borrower(ArrayList<Book> b,int m) {
    books = b;
    MAX_BOOKS = m;
    booksBorrowed = new ArrayList<Book>(MAX_BOOKS);
    overdueBooks = new ArrayList<Book>(MAX_BOOKS);
  }
  
  //Accessor Methods
  public ArrayList<Book> getBooksBorrowed() {
    return booksBorrowed;
  }
  public ArrayList<Book> getOverdueBooks() {
    return overdueBooks;
  }
  
  //Browse method
  public void browse(String category) {
    for (Book b : books) {
      if (b.getCategory().equals(category)) {
        System.out.println(b);
      }
    }
  }
  
  //Availability method
  public void availability(String s) {
    for (Book b : books) {
      if (b.getISBN().equals(s) || b.getName().equals(s)) {
        System.out.println(b.isAvailable());
        return;
      }
    }
    System.out.println("Book cannot be found");
  }
  
  //Abstract methods
  //Borrow method
  public void borrowBook(String s) throws IOException{
    if (booksBorrowed.size() == MAX_BOOKS)
      System.out.println("Exceeded allowed books that can be borrowed(" + MAX_BOOKS + 
                         "). Currently borrowed " + booksBorrowed.size() + " books.");
    else {
      for (Book b : books) {
        if (b.getISBN().equals(s) || b.getName().equals(s)) {
          booksBorrowed.add(b);
          b.borrowHistory(toString());
          System.out.println("Borrowed book");
          return;
        }
      }
      System.out.println("Book cannot be found");
    }
  }
  
  //Return method
  public void returnBook(String s) {
    if (booksBorrowed.size() == 0)
      System.out.println("Cannot return any books.");
    else {
      for (Book b : booksBorrowed) {
        if (b.getISBN().equals(s) || b.getName().equals(s))
          booksBorrowed.remove(b);
        System.out.println("Returned book.");
        return;
      }
    }
    System.out.println("Book cannot be found");
  }
  
  public void add() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter("Student.txt", true));
    bw.newLine();
    bw.write(toString());
    bw.close();
  }
  
  public void checkForOverdue() {
    int numOfOverdue = 0;
    if (booksBorrowed.size() == 0) {
      System.out.println("You did not borrow any books.");
      return;
    }
    for (Book b : booksBorrowed) {
      if (b.isOverdue())
        numOfOverdue++;
    }
    if (numOfOverdue > 0)
      System.out.println("You have " + numOfOverdue + " books.");
  }
  
  public void addOverdueBook(Book b) {
    overdueBooks.add(b);
  }
  
}