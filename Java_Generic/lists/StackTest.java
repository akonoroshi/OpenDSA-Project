package lists;

import java.io.*;
import java.util.Stack;
import errorInfo.ErrorRec;

public class StackTest {

	static int err = 0;
	static final int TEST_SIZE = 10;
	static final boolean file = true;

	static void testInt(lists.Stack<Integer> s) {
		// Check empty stack
		checkEmp(s);

		// Compare Stack with java.util.Stack to test length, topValue,
		// toString, push, and pop
		java.util.Stack<Integer> tester = new java.util.Stack<Integer>();
		for (int i = 0; i < TEST_SIZE; i++) {
			check(s, tester, 100 + i);
		}
	}

	static void testStr(lists.Stack<String> s) {
		// Check empty stack
		checkEmp(s);

		// Compare Stack with java.util.Stack to test length, topValue,
		// toString, push, and pop
		java.util.Stack<String> tester = new java.util.Stack<String>();
		for (int i = 0; i < TEST_SIZE; i++) {
			check(s, tester, "Str" + i);
		}
	}

	static <E> void check(lists.Stack<E> s, java.util.Stack<E> tester, E item) {
		// Add the item to both stacks
		s.push(item);
		tester.push(item);

		// Check the length of stack
		if (s.length() != tester.size()) {
			err = ErrorRec.printError("An unexpected length of " + s.getClass() + ". \nLength of stack: " + s.length()
					+ "\nLength expected: " + tester.size(), err, file);
		}

		// Check topValue
		if (s.topValue() != tester.peek()) {
			err = ErrorRec.printError("An unexpected topValue " + s.getClass() + ". \nTopValue in stack: "
					+ s.topValue().toString() + "\nValue expected: " + tester.peek().toString(), err, file);
		}

		// Check toString
		StringBuffer out = new StringBuffer(tester.size() * 4);
		for (int i = tester.size() - 1; i >= 0; i--) {
			out.append(tester.get(i));
			out.append(" ");
		}
		if (!s.toString().equals(out.toString())) {
			err = ErrorRec.printError("The toString method in " + s.getClass() + " has some errors.\nValues in stack: "
					+ s.toString() + "\nValues expected: " + tester.toString(), err, file);
		}

		// Check values in stack
		java.util.Stack<E> temp = new java.util.Stack<E>();
		int initSize = tester.size();
		for (int i = 0; i < initSize; i++) {
			E popped = s.pop();
			E expected = tester.pop();
			if (popped != expected) {
				err = ErrorRec.printError("An unexpected value in " + s.getClass() + ". \nPopped from stack: "
						+ popped.toString() + "\nValue expected: " + expected.toString(), i, file);
			}
			temp.push(expected);
		}

		// Restore values
		for (int i = 0; i < initSize; i++) {
			E popped = temp.pop();
			s.push(popped);
			tester.push(popped);
		}
	}

	static <E> void checkEmp(lists.Stack<E> s) {
		// Test topValue with empty stack
		if (s.topValue() != null) {
			err = ErrorRec.printError("An unexpected topValue in empty " + s.getClass() + ". \nTopValue in stack: "
					+ s.topValue().toString() + "\nValue expected: null", err, file);
		}

		// Test pop with empty stack
		E popped = s.pop();
		if (popped != null) {
			err = ErrorRec.printError("An unexpected value in empty " + s.getClass() + ". \nPopped from stack: "
					+ popped.toString() + "\nValue expected: null", err, file);
		}

		// Test clear
		s.clear();
		if (!s.toString().equals("")) {
			err = ErrorRec.printError(
					"The clear method in " + s.getClass() + " does not work. \nPrinted stack: " + s.toString(), err,
					file);
		}
	}

	public static void main(String args[]) throws IOException {
		// TODO System.setOut
		if (file) {
			ErrorRec.createFile();
		}

		// Test Integer
		AStack<Integer> as = new AStack<Integer>();
		LStack<Integer> ls = new LStack<Integer>();
		testInt(as);
		testInt(ls);

		// Test String
		AStack<String> as1 = new AStack<String>();
		LStack<String> ls1 = new LStack<String>();
		testStr(as1);
		testStr(ls1);

		ErrorRec.feedback(err);
	}

}
