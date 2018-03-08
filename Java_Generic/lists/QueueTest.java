package lists;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import errorInfo.ErrorRec;

/**
 * This program checks if all the methods in AQueue and LQueue classes work
 * properly.
 * 
 * @author Yuya Asano
 *
 */
public class QueueTest {

	static int err = 0;
	static final int TEST_SIZE = 10;
	static final boolean file = true;

	static void testInt(Queue<Integer> q) {
		// Check empty queue
		checkEmp(q);

		// Compare Queue with java.util.Queue to test length, frontValue,
		// toString, enqueue, and dequeue
		Deque<Integer> tester = new LinkedList<Integer>();
		for (int i = 0; i < TEST_SIZE; i++) {
			check(q, tester, 100 + i);
		}
	}

	static void testStr(Queue<String> q) {
		// Check empty queue
		checkEmp(q);

		// Compare Queue with java.util.Queue to test length, frontValue,
		// toString, enqueue, and dequeue
		Deque<String> tester = new LinkedList<String>();
		for (int i = 0; i < TEST_SIZE; i++) {
			check(q, tester, "Str" + i);
		}
	}

	static <E> void checkEmp(Queue<E> q) {
		// Test frontValue with empty queue
		if (q.frontValue() != null) {
			err = ErrorRec.printError("An unexpected topValue in empty " + q.getClass() + ". \nTopValue in queue: "
					+ q.frontValue().toString() + "\nValue expected: null", err, file);
		}

		// Test dequeue with empty queue
		E dequeued = q.dequeue();
		if (dequeued != null) {
			err = ErrorRec.printError("An unexpected value in empty " + q.getClass() + ". \nDequeued from queue: "
					+ dequeued.toString() + "\nValue expected: null", err, file);
		}

		// Test clear
		q.clear();
		if (!q.toString().equals("")) {
			err = ErrorRec.printError(
					"The clear method in " + q.getClass() + " does not work. \nPrinted queue: " + q.toString(), err,
					file);
		}
	}

	static <E> void check(Queue<E> q, Deque<E> tester, E item) {
		// Add the item to both queues
		q.enqueue(item);
		tester.addLast(item);

		// Check the length of queue
		if (q.length() != tester.size()) {
			err = ErrorRec.printError("An unexpected length of " + q.getClass() + ". \nLength of queue: " + q.length()
					+ "\nLength expected: " + tester.size(), err, file);
		}

		// Check frontValue
		if (!q.frontValue().equals(tester.peekFirst())) {
			err = ErrorRec.printError("An unexpected topValue " + q.getClass() + ". \nTopValue in queue: "
					+ q.frontValue().toString() + "\nValue expected: " + tester.peekFirst().toString(), err, file);
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
			err = ErrorRec.printError("The toString method in " + q.getClass() + " has some errors.\nValues in queue: "
					+ q.toString() + "\nValues expected: " + out.toString(), err, file);
		}

		// Check values in queue
		for (int i = 0; i < tester.size(); i++) {
			E dequeued = q.dequeue();
			E expected = tester.pollFirst();
			if (dequeued != expected) {
				err = ErrorRec.printError("An unexpected value in " + q.getClass() + ". \nPopped from queue: "
						+ dequeued.toString() + "\nValue expected: " + expected.toString(), i, file);
			}
			// Restore values
			q.enqueue(expected);
			tester.addLast(expected);
		}
	}

	/**
	 * Runs tests on generic AQueue and LQueue Class with Integer and String.
	 * 
	 * @param args
	 *            not used
	 * @throws IOException
	 *             thrown if some errors happen while opening or creating a new
	 *             text file
	 */
	public static void main(String args[]) throws IOException {
		// Create a file to record errors if necessary
		if (file) {
			ErrorRec.createFile();
		}

		// Test integer
		AQueue<Integer> aq = new AQueue<Integer>();
		LQueue<Integer> lq = new LQueue<Integer>();
		testInt(aq);
		testInt(lq);

		// Test String
		AQueue<String> aq1 = new AQueue<String>();
		LQueue<String> lq1 = new LQueue<String>();
		testStr(aq1);
		testStr(lq1);

		// Get a feedback about the result (success or fail)
		ErrorRec.feedback(err, file);
	}

}
