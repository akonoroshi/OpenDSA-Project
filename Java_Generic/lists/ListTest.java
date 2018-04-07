package lists;

import java.io.*;
import java.util.AbstractList;
import java.util.LinkedList;
import errorInfo.ErrorRec;
import java.lang.Math;

public class ListTest {
	// The number of items stored in stack during the test
	static final int TEST_SIZE = 8;
	// True if you want to create a text file to record errors
	static final boolean useFile = true;
	// Instance of ErrorRec class which holds the number of errors and prints
	// out error messages
	static ErrorRec record;

	static long time1, time2;

	/* *** ODSATag: listfind *** */
	// Return true if k is in list L, false otherwise
	static boolean find(List<Integer> L, int k) {
		for (L.moveToStart(); !L.isAtEnd(); L.next())
			if (k == L.getValue())
				return true; // Found k
		return false; // k not found
	}
	/* *** ODSAendTag: listfind *** */

	static void testInt(List<Integer> l) {
		// Check empty list
		checkEmp(l);

		doSomethingOnEmpList(l);

		// Compare list with java.util.list to test length, frontValue,
		// toString, enlist, and delist
		AbstractList<Integer> tester = new LinkedList<Integer>();
		for (int i = 0; i < TEST_SIZE; i++) {
			checkIns(l, tester, 100 + i);
		}

		reset(l, tester);
		for (int i = 0; i < TEST_SIZE; i++) {
			checkApp(l, tester, 100 + i);
		}

		doSomethingOnNonEmpList(l, tester);
	}

	static void testStr(List<String> l) {
		// Check empty list
		checkEmp(l);

		doSomethingOnEmpList(l);

		// Compare list with java.util.list to test length, frontValue,
		// toString, enlist, and delist
		AbstractList<String> tester = new LinkedList<String>();
		for (int i = 0; i < TEST_SIZE; i++) {
			checkIns(l, tester, "Str" + i);
		}

		reset(l, tester);
		for (int i = 0; i < TEST_SIZE; i++) {
			checkApp(l, tester, "Str" + i);
		}

		doSomethingOnNonEmpList(l, tester);
	}

	static <E> void reset(List<E> l, AbstractList<E> tester) {
		l.clear();
		tester.clear();
	}

	static <E> void doSomethingOnEmpList(List<E> l) {
		// Nothing changes
		l.moveToStart();
		l.moveToEnd();
		l.prev();
		l.next();
		checkEmp(l);
	}

	static <E> void checkEmp(List<E> l) {
		// Test frontValue with empty list
		if (l.currPos() != 0) {
			record.printError("An unexpected topValue in empty " + l.getClass() + ". \nTopValue in list: " + l.currPos()
					+ "\nValue expected: 0");
		}

		// Test delist with empty list
		E removed = l.remove();
		if (removed != null) {
			record.printError("An unexpected value in empty " + l.getClass() + ". \nDelistd from list: "
					+ removed.toString() + "\nValue expected: null");
		}

		// Test move to bad positions
		if (l.moveToPos(-1)) {
			record.printError("An empty " + l.getClass() + " returned true for moveToPos(-1)");
		}

		// Test clear
		l.clear();
		if (!l.toString().equals("< | >")) {
			record.printError(
					"The clear method in " + l.getClass() + " does not work. \nPrinted list: " + l.toString());
		}
	}

	static <E> void doSomethingOnNonEmpList(List<E> l, AbstractList<E> tester) {
		// Test moveToEnd
		l.moveToEnd();
		l.prev();
		check(l, tester, l.length() - 1);

		// Test moveToStart
		l.moveToStart();
		check(l, tester, 0);
		
		// Keep removing items from the middle of the list
		int curr = l.length() / 2;
		for (int i = 0; i < l.length(); i++) {
			curr = (int) (curr + i * Math.pow(-1, i));
			l.moveToPos(curr);
			E removed = l.remove();
			E expected = tester.remove(curr);
			if (removed != expected) {
				record.printError("Unexpected removed value in the middle of " + l.getClass() + ".\nRemoved value: "
						+ removed + "\nExpected value: " + expected);
			}
			if (l.isAtEnd()) {
				l.prev();
				check(l, tester, curr - 1);
				l.next();
			} else {
				check(l, tester, curr);
			}
			l.insert(expected);
			tester.add(curr, expected);
		}
	}

	static <E> void checkIns(List<E> l, AbstractList<E> tester, E item) {
		// Insert the item to both lists
		tester.add(l.currPos(), item);
		l.insert(item);
		check(l, tester, l.currPos());
	}

	static <E> void checkApp(List<E> l, AbstractList<E> tester, E item) {
		// Append the item to both lists
		tester.add(item);
		l.append(item);
		check(l, tester, l.currPos());
	}

	static <E> void check(List<E> l, AbstractList<E> tester, int curr) {
		// Check the length of list
		if (l.length() != tester.size()) {
			record.printError("An unexpected length of " + l.getClass() + ". \nLength of list: " + l.length()
					+ "\nLength expected: " + tester.size());
		}

		// Check the current position
		if (l.currPos() != curr) {
			record.printError("An unexpected current position of " + l.getClass() + ". \nCurrent position of list: "
					+ l.currPos() + "\nPosition expected: " + curr);
		}

		// Check the value stored in the current position
		if (l.getValue() != tester.get(curr)) {
			record.printError("An unexpected topValue " + l.getClass() + ". \nTopValue in list: "
					+ l.getValue().toString() + "\nValue expected: " + tester.get(curr).toString());
		}

		// Check toString
		StringBuffer out = new StringBuffer(tester.size() * 4);
		out.append("< ");
		for (int i = 0; i < curr; i++) {
			out.append(tester.get(i));
			out.append(" ");
		}
		out.append("| ");
		for (int i = curr; i < tester.size(); i++) {
			out.append(tester.get(i));
			out.append(" ");
		}
		out.append(">");
		if (!l.toString().equals(out.toString())) {
			record.printError("The toString method in " + l.getClass() + " has some errors.\nValues in list: "
					+ l.toString() + "\nValues expected: " + out.toString());
		}

		// Check values in list
		l.moveToStart();
		for (int i = 0; i < tester.size(); i++) {
			E expected = tester.remove(i);
			E removed = l.remove();
			if (removed != expected) {
				record.printError("An unexpected value in " + l.getClass() + ". \nPopped from list: "
						+ removed.toString() + "\nValue expected: " + expected.toString());
			}
			// Restore values
			tester.add(i, expected);
			l.insert(expected);
			l.next();
		}
		l.moveToPos(curr);
	}

	public static void main(String args[]) throws IOException {
		// Create a file to record errors if necessary
		record = new ErrorRec(useFile, "ListTest");

		AList<Integer> al = new AList<Integer>();
		LList<Integer> ll = new LList<Integer>();
		DList<Integer> dl = new DList<Integer>();
		testInt(al);
		testInt(ll);
		testInt(dl);

		AList<String> al1 = new AList<String>();
		LList<String> ll1 = new LList<String>();
		DList<String> dl1 = new DList<String>();
		testStr(al1);
		testStr(ll1);
		testStr(dl1);

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
