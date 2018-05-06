package lists;
import java.io.*;

public class DoubleTest {

static final int testsize = 0;
static long time1, time2;

static boolean SUCCESS = true;

static void doSomething(Object it) { }

static String toString(DList L) {
  // Save the current position of the list
  int oldPos = L.currPos();
  StringBuffer out = new StringBuffer((L.length() + 1) * 4);

  L.moveToStart();
  out.append("< ");
  for (int i = 0; i < oldPos; i++) {
    out.append(L.getValue());
    out.append(" ");
    L.next();
  }
  out.append("| ");
  for (int i = oldPos; i < L.length(); i++) {
    out.append(L.getValue());
    out.append(" ");
    L.next();
  }
  out.append(">");
  L.moveToPos(oldPos); // Reset the fence to its original position
  return out.toString();
}

/* *** ODSATag: listfind *** */
// Return true if k is in list L, false otherwise
static boolean find(DList L, int k) {
  for (L.moveToStart(); !L.isAtEnd(); L.next())
    if (k == (Integer)L.getValue()) return true; // Found k
  return false;                                  // k not found
}
/* *** ODSAendTag: listfind *** */

static void testAppend(DList L2) {
  L2.append(10);
  String temp = toString(L2);
  if (!temp.equals("< | 10 >")) {
    SUCCESS = false;
    System.out.println("Expected " + "< | 10 >" + ", got " + temp);
  }
  L2.append(20);
  L2.append(15);
  temp = toString(L2);
  if (!temp.equals("< | 10 20 15 >")) {
    SUCCESS = false;
    System.out.println("Expected " + "< | 10 20 15 >" + ", got " + temp);
  }
}

static void test(DList L) {
  L.moveToStart();
  L.insert(5);
  L.insert(7);
  L.next();
  L.next();
  L.insert(3);
  L.insert(17);
  String temp = toString(L);
  if (!temp.equals("< 7 5 | 17 3 >"))
    SUCCESS = false;

  Object it;
/* *** ODSATag: listiter *** */
for (L.moveToStart(); !L.isAtEnd(); L.next()) {
  it = L.getValue();
  doSomething(it);
}
/* *** ODSAendTag: listiter *** */

  if (!find(L, 5))
    SUCCESS = false;
  if (!find(L, 3))
    SUCCESS = false;
  if (find(L, 10))
    SUCCESS = false;

  L.moveToPos(2);
  it = L.remove();
  temp = toString(L);
  if (!temp.equals("< 7 5 | 3 >"))
    SUCCESS = false;
}

public static void main(String args[]) throws IOException {
  DList DL = new DList();

  test(DL);
  DL.clear();
  testAppend(DL);
  
  if (SUCCESS) {
    PrintWriter output = new PrintWriter("success");
    output.println("Success");
    output.flush();
    output.close();
    System.out.println("Success!");
  } else {
    System.out.println("Testing failed");
  }

  if (testsize == 0) {
    return;
  }
  System.out.println("Do the timing test");
  DList DT = new DList();
  time1 = System.currentTimeMillis();
  for (int i = 0; i < testsize; i++) {
    DL.insert(10);
    DL.insert(15);
    DL.insert(20);
    DL.insert(25);
    DL.clear();
  }
  time2 = System.currentTimeMillis();
  long totaltime = (time2-time1);
  System.out.println("Timing test on " + testsize + " iterations: " + totaltime);

  time1 = System.currentTimeMillis();
  for (int i = 0; i < testsize; i++) {
    Link temp = new Link(null, null);
    temp = new Link(null, null);
    temp = new Link(null, null);
    temp = new Link(null, null);
    temp = new Link(null, null);
  }
  time2 = System.currentTimeMillis();
  totaltime = (time2-time1);
  System.out.println("Timing test2 on " + testsize + " iterations: " + totaltime);
}

}
