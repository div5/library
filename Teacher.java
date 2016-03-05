import java.util.ArrayList;

public class Teacher extends Borrower {
  
  private String ID, name;
  
  public Teacher(ArrayList<Book> b, String i, String n) {
    super(b,4);
    ID = i;
    name = n;
  }
  
  //Accessor methods
  public String getID() {
    return ID;
  }
  public String getName() {
    return name;
  }
  
  @Override
  public String toString() {
    return ID + "," + name;
  }
  
}