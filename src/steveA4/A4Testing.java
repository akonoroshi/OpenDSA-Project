package steveA4;

import java.util.ArrayList;

/**
 * Tests circular, doubly linked list with dummy header node from assignment 4.
 * 
 * @author Steven Huss-Lederman
 * @version 1, 15 March 2014
 */
public class A4Testing {
	final static String ERR = "  ***ERROR***";
	static int numErr = 0;

	/**
	 * Runs test cases.
	 * 
	 * @param args
	 *            Not used.
	 * @throws DLListException
	 *             Only happens if tested code fails in unexpected way.
	 */
	public static void main(String[] args) throws DLListException {
		final int NUM = 8;

		// Holds items on list.
		ArrayList<Integer> list = new ArrayList<Integer>();
		// Holds values.
		int value, otherValue;

		/* Empty */
		System.out.println("Empty list:");
		DLList<Integer> dll = new DLList<Integer>();
		check(dll, list, -1);

		if (!dll.isAtEnd()) {
			error("Empty list not at end");
		}

		if (dll.getValue() != null) {
			error("Empty list curr not null");
		}

		// Should not change situation.
		dll.moveToStart();
		dll.moveToEnd();
		dll.prev();
		dll.next();
		check(dll, list, -1);

		try {
			System.out.println("item removed was " + dll.remove());
			error("Allowed to remove item from empty list");
		} catch (DLListException ex) {
			// Expected exception
		}

		/* Illegal ops with null */
		try {
			dll.append(null);
			error("Allowed to append null");
		} catch (DLListException ex) {
			// Expected exception
		}
		try {
			dll.insert(null);
			error("Allowed to insert null");
		} catch (DLListException ex) {
			// Expected exception
		}

		// Try bad values.
		if (dll.moveToPos(-1)) {
			error("returned true for moveToPos(-1)");
		}
		if (dll.moveToPos(0)) {
			error("returned true for moveToPos(0) on empty list");
		}
		check(dll, list, -1);

		System.out.println("empty list is: " + dll);

		/* Try insert at start */
		System.out.println("Inserting " + NUM + " items:");
		reset(dll, list);
		for (int i = 0; i < NUM; i++) {
			int item = i + 100;
			list.add(0, item);
			dll.insert(item);
			check(dll, list, 0);
		}

		/* Try appending items */
		System.out.println("Appending " + NUM + " items:");
		reset(dll, list);
		for (int i = 0; i < NUM; i++) {
			int item = i + 100;
			list.add(item);
			dll.append(item);
			check(dll, list, i);
		}

		/* See if next from end goes to start */
		System.out.println("Next at end goes to start");
		dll.next();
		check(dll, list, 0);

		/* Now insert one at end */
		value = 99;
		System.out.println("Insert " + value + " at end:");
		dll.moveToEnd();
		dll.insert(value);
		list.add(list.size() - 1, value);
		check(dll, list, list.size() - 2);

		/* prev through list to see if correct */
		System.out.println("Check prev through list:");
		dll.moveToEnd();
		for (int i = list.size() - 1; i >= 0; i--) {
			value = list.get(i);
			otherValue = dll.getValue();
			if (otherValue != value) {
				error("at index " + i + " got " + otherValue + " instead of "
						+ value);
			}
			check(dll, list, i);
			dll.prev();
		}
		check(dll, list, list.size() - 1);

		/* Remove at start & end */
		System.out.println("Remove at start/end:");
		value = list.remove(0);
		dll.moveToStart();
		otherValue = dll.remove();
		if (otherValue != value) {
			error("remove at start gave " + otherValue + " instead of " + value);
		}
		check(dll, list, 0);

		value = list.remove(list.size() - 1);
		dll.moveToEnd();
		otherValue = dll.remove();
		if (otherValue != value) {
			error("remove at end gave " + otherValue + " instead of " + value);
		}
		check(dll, list, 0);

		/* Remove odd numbered items */
		System.out.println("Remove odd numbered:");
		dll.moveToStart();
		int size = list.size();
		int toRemove = 0;
		for (int i = 1; i < size; i += 2) {
			value = list.remove(++toRemove);
			dll.next();
			otherValue = dll.remove();
			if (otherValue != value) {
				error("remove odds at " + i + " gave " + otherValue
						+ " instead of " + value);
			}
			int cur;
			if (i < size - 1) {
				cur = (i + 1) / 2;
			} else {
				cur = 0;
			}
			check(dll, list, cur);
		}

		/* add back odd numbered items */
		System.out.println("Add back odd numbered:");
		dll.moveToStart();
		dll.next();
		size = list.size();
		for (int i = 1; i < size; i += 2) {
			value = i + 101;
			list.add(i, value);
			dll.insert(value);
			check(dll, list, i);
			dll.next();
			dll.next();
		}

		System.out.println("Check moveToPos");
		dll.clear();
		dll.append(13);
		int loc;
		loc = 0;
		if (!dll.moveToPos(loc)) {
			error("moveToPos on list size 1 should allow go to to " + loc);
		}
		loc = -1;
		if (dll.moveToPos(loc)) {
			error("moveToPos on list size 1 should not allow go to to " + loc);
		}
		loc = 1;
		if (dll.moveToPos(loc)) {
			error("moveToPos on list size 1 should not allow go to to " + loc);
		}

		System.out.println("Check remove with one node");
		dll.clear();
		value = 13;
		dll.append(value);
		otherValue = dll.remove();
		if (otherValue != value) {
			error("remove when one node gave " + otherValue + " instead of "
					+ value);
		}
		// Wipe list since nothing in dll.
		list = new ArrayList<Integer>();
		check(dll, list, -1);

		/* Try another type */
		System.out.println("Try String:");
		DLList<String> dlls = new DLList<String>();
		dlls.append("Second item");
		dlls.insert("First item");
		System.out.println("2 items for String: " + dlls);

		System.out.println();
		if (numErr == 0) {
			System.out.println("There were no detected errors");
		} else {
			error("Total number of detected errors was " + numErr);
		}
	}

	static void reset(DLList<Integer> dll, ArrayList<Integer> list) {
		list.clear();
		dll.clear();
	}

	static void check(DLList<Integer> dll, ArrayList<Integer> list, int cur) {
		System.out.print("  " + dll);
		System.out.println(" with curr = "+ dll.getValue());
		Integer value;
		if (dll.length() != list.size()) {
			error("list size " + dll.length() + " not " + list.size());
		}
		int curPos = dll.currPos();
		if (cur != curPos) {
			error("curPos is " + curPos + " not " + cur);
		}
		dll.moveToStart();
		for (Integer item : list) {
			value = dll.getValue();
			if (item != value) {
				error("not where expected on list: got " + value
						+ " instead of " + item);
			}
			dll.next();
		}
		dll.moveToPos(curPos);
	}

	static void error(String msg) {
		numErr++;
		System.out.println(ERR + msg);
	}
}