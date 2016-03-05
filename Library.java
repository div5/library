import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Library {
  private static Scanner scn = new Scanner(System.in);
  
  public static void main(String[] args) throws IOException {
    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    ArrayList<Student> students = new ArrayList<Student>();
    
    initializeArrays(books,students,teachers); //calls static method
    
    System.out.println("Choose user:\n1.Borrower\n2.Librarian");
    int user = scn.nextInt();
    
    //Borrower
    if (user == 1) {
      System.out.println("Choose borrower:\n1.Student\n2.Teacher");
      int userB = scn.nextInt();
      
      //Student
      if (userB == 1) {
        System.out.println("Input student details as: OSIS,Last Name,First Name,Grade,Official Class");
        String[] words = scn.next().split(",");
        Student borrower = new Student(books,words[0],words[1],words[2],words[3],words[4]);
        students.add(borrower);
        borrowerMethod(borrower, books);
      }
      
      //Teacher
      else if (userB == 2) {
        System.out.println("Input teacher details as: ID,Name");
        String[] words = scn.next().split(",");
        Teacher borrower = new Teacher(books,words[0],words[1]);
        teachers.add(borrower);
        borrowerMethod(borrower, books);
      }
      
      else {
        scn.close();
        return;
      }
    }
    
    //Librarian
    else if (user == 2) {
      Librarian l = new Librarian();
      boolean isEnd = false;
      do {
        System.out.println("Choose action:\n1.Add book\n2.Remove book\n3.Check borrow history\n4.Check for overdue books\n5.Exit");
        int action = scn.nextInt();
        if (action == 1) { //Add book
          System.out.println("Add book info as: ISBN,Name,Author,Category,Status");
          String[] words = scn.next().split(",");
          l.addBook(new Book(words[0],words[1],words[2],words[3],words[4]));
        }
        else if (action == 2) { //Remove book
          System.out.println("Specify book ISBN or name to be removed");
          String s = scn.next();
          l.removeBook(s);
        }
        else if (action == 3) { //Check borrow history
          System.out.println("Specify book ISBN or name for borrow history");
          String s = scn.next();
          l.borrowHistory(s);
        }
        else if (action == 4) { //Overdue books
          for (Book b : books) {
            if (b.isOverdue()) {
              b.advanceDate();
              System.out.println("A day has passed. . .\n");
            }
          }
          for (Teacher t : teachers) {
            for (Book b : t.getBooksBorrowed()) {
              if (b.isOverdue()) {
                t.addOverdueBook(b);
                System.out.println("Teacher " + t.getName() + " has the book(s) overdue: " + t.getOverdueBooks());
              }
              else
                System.out.println("No overdue books have been found.");
            }
          }
          for (Student s : students) {
            for (Book b : s.getBooksBorrowed()) {
              if (b.isOverdue()) {
                s.addOverdueBook(b);
                System.out.println("Student " + s.getFirstName() + " " + s.getLastName() + " has the book(s) overdue: " + s.getOverdueBooks());
              }
              else
                System.out.println("No overdue books have been found.");
            }
          }
        }
        else
          isEnd = true;
      } while (!isEnd);
    }
    
    else
      System.out.println("Invalid user. Ending program . . .");
    scn.close();
    return;
  }
  
  public static void initializeArrays(ArrayList<Book> books, ArrayList<Student> students, ArrayList<Teacher> teachers) 
    throws IOException {
    File f1 = new File("Book.txt");
    if(!f1.exists())
      f1.createNewFile();
    File f2 = new File("Student.txt");
    if(!f2.exists())
      f2.createNewFile();
    File f3 = new File("Teacher.txt");
    if(!f3.exists())
      f3.createNewFile();
    
    //Intializes the arrays
    BufferedReader br1 = new BufferedReader(new FileReader(f1));
    String line1 = null;
    BufferedReader br2 = new BufferedReader(new FileReader(f2));
    String line2 = null;
    BufferedReader br3 = new BufferedReader(new FileReader(f3));
    String line3 = null;
    
    if ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null && (line3 = br3.readLine()) != null) {
      while ((line1 = br1.readLine()) != null) {
        String[] words = line1.split(",");
        books.add(new Book(words[0],words[1],words[2],words[3],words[4]));
      }
      while ((line2 = br2.readLine()) != null) {
        String[] words = line2.split(",");
        teachers.add(new Teacher(books,words[0],words[1]));
      }
      
      while ((line3 = br3.readLine()) != null) {
        String[] words = line3.split(",");
        students.add(new Student(books,words[0],words[1],words[2],words[3],words[4]));
      }
    }
    br1.close();
    br2.close();
    br3.close();
  }
  
  public static void borrowerMethod(Borrower borrower, ArrayList<Book> books) throws IOException {
    boolean isEnd = false;
    do {
      System.out.println("Choose action:\n1.Browse\n2.Check availability\n3.Borrow\n4.Return\n5.Exit");
      int action = scn.nextInt();
      if (action == 1) { //Browse
        System.out.println("Choose a category");
        String category = scn.next();
        borrower.browse(category);
      }
      else if (action == 2) { //Check availability
        System.out.println("Specify book ISBN or name");
        String input = scn.next();
        borrower.availability(input);
      }
      else if (action == 3) { //Borrow book
        System.out.println("Specify book ISBN or name");
        String input = scn.next();
        borrower.borrowBook(input);
        for (Book b : books) {
          if (b.getISBN().equals(input) || b.getName().equals(input))
            b.bookBorrowed();
        }
      }
      else if (action == 4) { //Return book
        System.out.println("Specify book ISBN or name");
        String input = scn.next();
        borrower.returnBook(input);
        for (Book b : books) {
          if (b.getISBN().equals(input) || b.getName().equals(input))
            b.bookReturned();
        }
      }
      else 
        isEnd = true;
    } while (!isEnd);
  }
}
