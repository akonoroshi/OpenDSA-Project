package lists;
import java.io.*;
import java.util.Stack;

public class StackTest {

static boolean SUCCESS = true;

static void test(Stack s, Stack s1) {
    String temp;
    
    // Test pop with empty stack
    if (s.pop() != null)
    	SUCCESS = false;
    
    // Test topValue with empty stack
    if (s.topValue() != null)
    	SUCCESS = false;
    
    //Test push and pop
    s.push(10);
    s.push(20);
    s.push(15);
    temp = s.toString();
    if (!temp.equals("15 20 10 "))
      SUCCESS = false;
    while(s.length() > 0)
      s1.push(s.pop());
    temp = s1.toString();
    if (!temp.equals("10 20 15 "))
      SUCCESS = false;
    temp = s.toString();
    if (!temp.equals(""))
      SUCCESS = false;
    
    // Test topValue
    //if (s1.topValue() != 10)
    	//SUCCESS = false;
    
    // Test clear
    s1.clear();
    if (!temp.equals(""))
        SUCCESS = false;
    
    // Test length
    s.clear();
    s.push(100);
    s.push(200);
    s.push(150);
    if (s.length() != 3)
    	SUCCESS = false;
}

public static void main(String args[]) throws IOException {
  AStack<Integer> as = new AStack<Integer>();
  AStack<Integer> as1 = new AStack<Integer>();
  LStack<Integer> ls = new LStack<Integer>();
  LStack<Integer> ls1 = new LStack<Integer>();

  test(as, as1);
  test(ls, ls1);
  
  // Test String
  
  if (SUCCESS) {
    PrintWriter output = new PrintWriter("success");
    output.println("Success");
    output.flush();
    output.close();
    System.out.println("Success!");
  } else {
    System.out.println("Testing failed");
  }
}

}
