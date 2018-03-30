package lists;

import java.io.*;
import java.util.AbstractList;
import java.util.LinkedList;

import errorInfo.ErrorRec;

public class ListTest {
	// The number of items stored in stack during the test
	static final int TEST_SIZE = 10;
	// True if you want to create a text file to record errors
	static final boolean useFile = true;
	// Instance of ErrorRec class which holds the number of errors and prints
	// out error messages
	static ErrorRec record;

	static long time1, time2;

	static boolean SUCCESS = true;

	static void doSomething(Object it) {
	}

	static String toString(List<Integer> L) {
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
	static boolean find(List<Integer> L, int k) {
		for (L.moveToStart(); !L.isAtEnd(); L.next())
			if (k == L.getValue())
				return true; // Found k
		return false; // k not found
	}
	/* *** ODSAendTag: listfind *** */

	static void testAppend(List<Integer> L2) {
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

	static void test(List<Integer> L) {
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

	static void testInt(List<Integer> l) {
		// Check empty queue
		checkEmp(l);

		// Compare Queue with java.util.Queue to test length, frontValue,
		// toString, enqueue, and dequeue
		AbstractList<Integer> tester = new LinkedList<Integer>();
		for (int i = 0; i < TEST_SIZE; i++) {
			checkIns(l, tester, 100 + i);
		}
		
		l.clear();
		tester.clear();
		for (int i = 0; i < TEST_SIZE; i++) {
			checkApp(l, tester, 100 + i);
		}
	}

	static void testStr(List<String> l) {
		// Check empty queue
		checkEmp(l);

		// Compare Queue with java.util.Queue to test length, frontValue,
		// toString, enqueue, and dequeue
		AbstractList<String> tester = new LinkedList<String>();
		for (int i = 0; i < TEST_SIZE; i++) {
			checkIns(l, tester, "Str" + i);
		}
		
		l.clear();
		tester.clear();
		for (int i = 0; i < TEST_SIZE; i++) {
			checkApp(l, tester, "Str" + i);
		}
	}

	static <E> void checkEmp(List<E> l) {
		// Test frontValue with empty queue
		if (l.currPos() != 0) {
			record.printError("An unexpected topValue in empty " + l.getClass() + ". \nTopValue in queue: "
					+ l.currPos() + "\nValue expected: 0");
		}

		// Test dequeue with empty queue
		E removed = l.remove();
		if (removed != null) {
			record.printError("An unexpected value in empty " + l.getClass() + ". \nDequeued from queue: "
					+ removed.toString() + "\nValue expected: null");
		}

		// Test clear
		l.clear();
		if (!l.toString().equals("")) {
			record.printError(
					"The clear method in " + l.getClass() + " does not work. \nPrinted queue: " + l.toString());
		}
	}
	
	static <E> void checkIns(List<E> l, AbstractList<E> tester, E item) {
		// Insert the item to both queues
		tester.add(l.currPos(), item);
		l.insert(item);
		check(l, tester);
	}
	
	static <E> void checkApp(List<E> l, AbstractList<E> tester, E item) {
		// Append the item to both queues
		tester.add(item);
		l.append(item);
		check(l, tester);
	}
	
	static <E> void check(List<E> l, AbstractList<E> tester) {
		// Check the length of queue
		if (l.length() != tester.size()) {
			record.printError("An unexpected length of " + l.getClass() + ". \nLength of queue: " + l.length()
					+ "\nLength expected: " + tester.size());
		}

		// Check frontValue
		if (l.getValue() != tester.get(l.currPos())) {
			record.printError("An unexpected topValue " + l.getClass() + ". \nTopValue in queue: "
					+ l.getValue().toString() + "\nValue expected: " + tester.get(l.currPos()).toString());
		}

		// Check toString
		/*StringBuffer out = new StringBuffer(tester.size() * 4);
		for (int i = 0; i < tester.size(); i++) {
			E itemT = tester.pollFirst();
			tester.addLast(itemT);
			out.append(itemT);
			out.append(" ");
		}
		if (!l.toString().equals(out.toString())) {
			record.printError("The toString method in " + l.getClass() + " has some errors.\nValues in queue: "
					+ l.toString() + "\nValues expected: " + out.toString());
		}*/

		// Check values in queue
		for (int i = 0; i < tester.size(); i++) {
			E expected = tester.remove(l.currPos());
			E removed = l.remove();
			if (removed != expected) {
				record.printError("An unexpected value in " + l.getClass() + ". \nPopped from queue: "
						+ removed.toString() + "\nValue expected: " + expected.toString());
			}
			// Restore values
			tester.add(l.currPos(), expected);
			l.insert(expected);
		}
	}

	public static void main(String args[]) throws IOException {
		// Create a file to record errors if necessary
		record = new ErrorRec(useFile, "ListTest");

		AList<Integer> al = new AList<Integer>();
		LList<Integer> ll = new LList<Integer>();

		/*test(al);
		test(ll);
		al.clear();
		ll.clear();
		testAppend(al);
		testAppend(ll);*/
		
		testInt(al);
		testInt(ll);

		// Get a feedback about the result (success or fail)
		record.feedback();

		if (TEST_SIZE != 0)
			timing();

	}

	static void timing() {
		System.out.println("Do the timing test");
		LList<Integer> LT = new LList<Integer>();
		time1 = System.currentTimeMillis();
		for (int i = 0; i < TEST_SIZE; i++) {
			LT.insert(10);
			LT.insert(15);
			LT.insert(20);
			LT.insert(25);
			LT.clear();
		}
		time2 = System.currentTimeMillis();
		long totaltime = (time2 - time1);
		System.out.println("Timing test on " + TEST_SIZE + " iterations: " + totaltime);

		time1 = System.currentTimeMillis();
		for (int i = 0; i < TEST_SIZE; i++) {
			Link<Integer> temp = new Link<Integer>(null, null);
			temp = new Link<Integer>(null, null);
			temp = new Link<Integer>(null, null);
			temp = new Link<Integer>(null, null);
			temp = new Link<Integer>(null, null);
		}
		time2 = System.currentTimeMillis();
		totaltime = (time2 - time1);
		System.out.println("Timing test2 on " + TEST_SIZE + " iterations: " + totaltime);
	}

}
