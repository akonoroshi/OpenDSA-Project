package lists;

import java.io.*;
import java.util.ArrayList;

public class StackTest {

	static boolean success = true;
	static int err = 0;

	static void testInt(Stack<Integer> s) {
		// Check empty stack
		if (!checkEmp(s)) {
			success = false;
		}

		// Test length, topValue, push, and pop
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			s.push(100 + i);
			a.add(100 + i);
		}
		if (!check(s, a)) {
			success = false;
		}
	}

	static void testStr(Stack<String> s) {
		// Check empty stack
		if (!checkEmp(s)) {
			success = false;
		}

		// Test length, topValue, push, and pop
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			s.push("String" + i);
			a.add("String" + i);
		}
		if (!check(s, a)) {
			success = false;
		}
	}

	static <E> boolean check(Stack<E> s, ArrayList<E> a) {
		// Check the length of stack
		if (s.length() != a.size()) {
			err++;
			System.err.println("An unexpected length of your stack. \nLength of stack: " + s.length()
					+ "\nLength expected: " + a.size());
			return false;
		}

		// Check topValue
		if (!s.topValue().equals(a.get(a.size() - 1))) {
			err++;
			System.err.println("An unexpected topValue in your stack. \nTopValue in stack: " + s.topValue().toString()
					+ "\nValue expected: " + a.get(a.size() - 1).toString());
			return false;
		}

		// Check values in stack
		for (int i = 0; i < s.length(); i++) {
			E popped = s.pop();
			if (!popped.equals(a.get(a.size() - i - 1))) {
				err++;
				System.err.println("An unexpected value in your stack. \nPopped from stack: " + popped.toString()
						+ "\nValue expected: " + a.get(a.size() - i - 1).toString());
				return false;
			}
		}
		return true;
	}

	static <E> boolean checkEmp(Stack<E> s) {
		// Test topValue with empty stack
		if (s.topValue() != null) {
			err++;
			System.err.println("An unexpected topValue in your empty stack. \nTopValue in stack: "
					+ s.topValue().toString() + "\nValue expected: null");
			return false;
		}

		// Test pop with empty stack
		E popped = s.pop();
		if (popped != null) {
			err++;
			System.err.println("An unexpected value in your empty stack. \nPopped from stack: " + popped.toString()
					+ "\nValue expected: null");
			return false;
		}

		// Test clear
		s.clear();
		if (!s.toString().equals("")) {
			err++;
			System.err.println("The clear method does not work. \nPrinted stack: " + s.toString());
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
			System.out.println("Testing failed. There are " + err + "of error(s) in your codes");
		}
	}

}
