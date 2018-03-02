package lists;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class QueueTest {

	static boolean success = true;
	static int err = 0;
	static final int TEST_SIZE = 10;
	static final boolean file = true;
	static PrintWriter error;

	static void testInt(Queue<Integer> q) {
		// Check empty queue
		checkEmp(q);

		// Compare Queue with java.util.Queue to test length, topValue,
		// toString, push, and pop
		Deque<Integer> tester = new LinkedList<Integer>();
		for (int i = 0; i < TEST_SIZE; i++) {
			check(q, tester, 100 + i);
		}
	}

	static void testStr(Queue<String> q) {
		// Check empty queue
		checkEmp(q);

		// Compare Queue with java.util.Queue to test length, topValue,
		// toString, push, and pop
		Deque<String> tester = new LinkedList<String>();
		for (int i = 0; i < TEST_SIZE; i++) {
			check(q, tester, "Str" + i);
		}
	}

	static <E> void checkEmp(Queue<E> q) {
		// Test frontValue with empty queue
		if (q.frontValue() != null) {
			error("An unexpected topValue in empty " + q.getClass() + ". \nTopValue in queue: "
					+ q.frontValue().toString() + "\nValue expected: null");
		}

		// Test pop with empty queue
		E dequeued = q.dequeue();
		if (dequeued != null) {
			error("An unexpected value in empty " + q.getClass() + ". \nDequeued from queue: " + dequeued.toString()
					+ "\nValue expected: null");
		}

		// Test clear
		q.clear();
		if (!q.toString().equals("")) {
			error("The clear method in " + q.getClass() + " does not work. \nPrinted queue: " + q.toString());
		}
	}

	static <E> void check(Queue<E> q, Deque<E> tester, E item) {
		// Add the item to both queues
		q.enqueue(item);
		tester.addLast(item);

		// Check the length of queue
		if (q.length() != tester.size()) {
			error("An unexpected length of " + q.getClass() + ". \nLength of queue: " + q.length()
					+ "\nLength expected: " + tester.size());
		}

		// Check frontValue
		if (!q.frontValue().equals(tester.peekFirst())) {
			error("An unexpected topValue " + q.getClass() + ". \nTopValue in queue: " + q.frontValue().toString()
					+ "\nValue expected: " + tester.peekFirst().toString());
		}

		// Check toString
		StringBuffer out = new StringBuffer(tester.size() * 4);
		for (int i = 0; i < tester.size(); i++) {
			E itemT = tester.pollFirst();
			tester.addLast(itemT);
			out.append(itemT);
			out.append(" ");
		}
		if (!q.toString().equals(out.toString())) {
			error("The toString method in " + q.getClass() + " has some errors.\nValues in queue: " + q.toString()
					+ "\nValues expected: " + out.toString());
		}
		
		// Check values in queue
		for (int i = 0; i < tester.size(); i++) {
			E dequeued = q.dequeue();
			E expected = tester.pollFirst();
			if (dequeued != expected) {
				error("An unexpected value in " + q.getClass() + ". \nPopped from queue: " + dequeued.toString()
						+ "\nValue expected: " + expected.toString());
			}
			// Restore values
			q.enqueue(expected);
			tester.addLast(expected);
		}
	}

	static void error(String message) {
		err++;
		success = false;
		if (file) {
			error.println("* ***ODSA Error*** *\n" + message);
		}
		else {
			System.err.println("* ***ODSA Error*** *\n" + message);
		}
	}

	/*static void test(Queue<Integer> q, Queue<Integer> Q1) {
		String temp;
		q.enqueue(10);
		q.enqueue(20);
		q.enqueue(15);
		temp = q.toString();
		if (!temp.equals("10 20 15 "))
			success = false;
		while (q.length() > 0)
			Q1.enqueue(q.dequeue());
		temp = Q1.toString();
		if (!temp.equals("10 20 15 "))
			success = false;
		temp = q.toString();
		if (!temp.equals(""))
			success = false;
	}*/

	public static void main(String args[]) throws IOException {
		if (file) {
			error = new PrintWriter("error log");
		}
		AQueue<Integer> aq = new AQueue<Integer>();
		AQueue<String> aq1 = new AQueue<String>();
		LQueue<Integer> lq = new LQueue<Integer>();
		LQueue<String> lq1 = new LQueue<String>();

		// test(AQ, AQ1);
		// test(LQ, LQ1);
		testInt(aq);
		testInt(lq);

		testStr(aq1);
		testStr(lq1);

		if (success) {
			PrintWriter output = new PrintWriter("success");
			output.println("Success");
			output.flush();
			output.close();
			System.out.println("Success!");
		} else {
			System.out.println("Testing failed");
		}
		if (file) {
			error.flush();
			error.close();
		}
	}

}
