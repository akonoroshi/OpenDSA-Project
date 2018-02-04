package lists;

import java.io.*;
import java.util.ArrayList;

public class StackTest {

	static boolean SUCCESS = true;

	static void testInt(Stack<Integer> s) {
		// String temp;

		// Check empty stack
		if (!checkEmp(s)) {
			SUCCESS = false;
		}

		/*
		 * s.push(10); s.push(20); s.push(15); temp = s.toString(); if
		 * (!temp.equals("15 20 10 ")) SUCCESS = false; while(s.length() > 0)
		 * s1.push(s.pop()); temp = s1.toString(); if
		 * (!temp.equals("10 20 15 ")) SUCCESS = false; temp = s.toString(); if
		 * (!temp.equals("")) SUCCESS = false;
		 */
		// Test topValue
		// if (s1.topValue() != 10)
		// SUCCESS = false;

		// Test push and pop
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			s.push(100 + i);
			a.add(100 + i);
		}
		if (!check(s, a)) {
			SUCCESS = false;
		}
		// Test length
		/*
		 * s.clear(); s.push(100); s.push(200); s.push(150); if (s.length() !=
		 * 3) SUCCESS = false;
		 */
	}

	static void testStr(Stack<String> s) {
		// Check empty stack
		if (!checkEmp(s)) {
			SUCCESS = false;
		}

		// Test push and pop
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			s.push("String" + i);
			a.add("String" + i);
		}
		if (!check(s, a)) {
			SUCCESS = false;
		}
	}

	static boolean check(Stack s, ArrayList a) {
		// Check the length of stack
		if (s.length() != a.size()) {
			return false;
		}

		// Check values in stack
		for (int i = 0; i < s.length(); i++) {
			if (!s.pop().equals(a.get(a.size() - i - 1))) {
				return false;
			}
		}
		return true;
	}

	static boolean checkEmp(Stack s) {
		// Test pop with empty stack
		if (s.pop() != null)
			return false;

		// Test topValue with empty stack
		if (s.topValue() != null)
			return false;

		// Test clear
		s.clear();
		if (!s.toString().equals(""))
			return false;
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
