import java.util.ArrayList;

public class Student extends Borrower {
  
  private String OSIS, lastName, firstName, grade, officialClass;
  
  public Student(ArrayList<Book> b, String os, String ln, String fn, String g, String oc) {
    super(b,2);
    OSIS = os;
    lastName = ln;
    firstName = fn;
    grade = g;
    officialClass = oc;
  }
  
  //Accessor methods
  public String getOSIS() {
    return OSIS;
  }
  public String getLastName() {
    return lastName;
  }
  public String getFirstName() {
    return firstName;
  }
  public String getGrade() {
    return grade;
  }
  public String getOfficialClass() {
    return officialClass;
  }
  
  @Override
  public String toString() {
    return OSIS + "," + lastName + "," + firstName + "," + grade + "," + officialClass;
  }
  
}