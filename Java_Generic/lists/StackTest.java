package lists;

import java.io.*;
import java.util.Stack;

public class StackTest {

	static boolean success = true;
	static int err = 0;

	static void testInt(lists.Stack<Integer> s) {
		// Check empty stack
		if (!checkEmp(s)) {
			success = false;
		}

		// Compare Stack with java.util.Stack to test length, topValue, toString,
		// push, and pop
		java.util.Stack<Integer> tester = new java.util.Stack<Integer>();
		for (int i = 0; i < 10; i++) {
			s.push(100 + i);
			tester.add(100 + i);
		}
		if (!check(s, tester)) {
			success = false;
		}
	}

	static void testStr(lists.Stack<String> s) {
		// Check empty stack
		if (!checkEmp(s)) {
			success = false;
		}

		// Compare Stack with java.util.Stack to test length, topValue, toString,
		// push, and pop
		java.util.Stack<String> tester = new java.util.Stack<String>();
		for (int i = 0; i < 10; i++) {
			s.push("Str" + i);
			tester.add("Str" + i);
		}
		if (!check(s, tester)) {
			success = false;
		}
	}

	static <E> boolean check(lists.Stack<E> s, java.util.Stack<E> tester) {
		// Check the length of stack
		if (s.length() != tester.size()) {
			err++;
			System.err.println("An unexpected length of " + s.getClass() + ". \nLength of stack: " + s.length()
					+ "\nLength expected: " + tester.size());
			return false;
		}

		// Check topValue
		if (!s.topValue().equals(tester.get(tester.size() - 1))) {
			err++;
			System.err.println("An unexpected topValue " + s.getClass() + ". \nTopValue in stack: "
					+ s.topValue().toString() + "\nValue expected: " + tester.get(tester.size() - 1).toString());
			return false;
		}

		// Check toString
		StringBuffer out = new StringBuffer(tester.size() * 4);
		for (int i = tester.size() - 1; i >= 0; i--) {
			out.append(tester.get(i));
			out.append(" ");
		}
		System.out.println("Values in " + s.getClass() + ": " + s.toString() + "\nValues expected: " + out.toString());
		if (!s.toString().equals(out.toString())) {
			err++;
			System.err.println("The toString method in " + s.getClass() + " has some errors.");
			return false;
		}

		// Check values in stack
		for (int i = 0; i < s.length(); i++) {
			E popped = s.pop();
			if (!popped.equals(tester.get(tester.size() - i - 1))) {
				err++;
				System.err.println("An unexpected value in " + s.getClass() + ". \nPopped from stack: "
						+ popped.toString() + "\nValue expected: " + tester.get(tester.size() - i - 1).toString());
				return false;
			}
		}
		return true;
	}

	static <E> boolean checkEmp(lists.Stack<E> s) {
		// Test topValue with empty stack
		if (s.topValue() != null) {
			err++;
			System.err.println("An unexpected topValue in empty " + s.getClass() + ". \nTopValue in stack: "
					+ s.topValue().toString() + "\nValue expected: null");
			return false;
		}

		// Test pop with empty stack
		E popped = s.pop();
		if (popped != null) {
			err++;
			System.err.println("An unexpected value in empty " + s.getClass() + ". \nPopped from stack: "
					+ popped.toString() + "\nValue expected: null");
			return false;
		}

		// Test clear
		s.clear();
		if (!s.toString().equals("")) {
			err++;
			System.err.println(
					"The clear method in " + s.getClass() + " does not work. \nPrinted stack: " + s.toString());
			return false;
		}

		return true;
	}

	public static void main(String args[]) throws IOException {
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

		if (success) {
			PrintWriter output = new PrintWriter("success");
			output.println("Success");
			output.flush();
			output.close();
			System.out.println("Success!");
		} else {
			System.out.println("Testing failed. There are " + err + " error(s) in your codes.");
		}
	}

}
