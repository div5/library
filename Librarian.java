import java.io.*;

public class Librarian {
  
  public void addBook(Book b) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter("Book.txt", true)); //true to append
    bw.newLine();
    bw.write(b.toString());
    bw.close();
  }
  
  public void removeBook(String s) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("Book.txt"));
    BufferedWriter bw = new BufferedWriter(new FileWriter("Book.txt", false)); //false to overwrite
    String line = null;
    while ((line = br.readLine()) != null) {
      if (!line.contains(s))
        bw.write(line);
    }
    br.close();
    bw.close();
  }
  
  public void borrowHistory(String s) throws IOException { //uses ISBN or name
    BufferedReader br = new BufferedReader(new FileReader("Book.txt"));
    String line = null;
    while ((line = br.readLine()) != null) {
      if (line.contains(s)) {
        String[] words = line.split(",");
        BufferedReader br2 = new BufferedReader(new FileReader(words[1] + "BorrowHistory.txt"));
        String line2 = null;
        while ((line2 = br2.readLine()) != null) {
          System.out.println(line2);
        }
        br2.close();
        return;
      }
    }
    br.close();
  }
  
}