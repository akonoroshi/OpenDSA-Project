package Binary;

import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import utils.KVPair;
import utils.Permute;
import errorInfo.ErrorRec;

public class BSTTest {
	static final int TESTSIZE = 1000000;
	static final int OFFSET = 1000000;
	static long time1, time2, totaltime; // These get set by the testing routine
	// Instance of ErrorRec class which holds the number of errors and prints
	// out error messages
	static ErrorRec record;

	static <T> void visit(BinNode<T> rt) {
		// System.out.print(rt.value() + " ");
	}

	static <E extends Comparable<E>> boolean checkBST(BSTNode<E> rt, Vector<E> sorted) {
		// TODO stack or list containing value
		if (rt == null)
			return true; // Empty subtree
		checkBST(rt.left(), sorted);
		// TODO compare values
		if (!rt.value().equals(sorted.remove(0))) {
			return false;
		}
		checkBST(rt.right(), sorted);
		// TODO is stack or list empty?
		return sorted.isEmpty();
	}

	public static void main(String args[]) throws IOException {
		Integer[] A = new Integer[TESTSIZE];
		int i;
		BST<KVPair<Integer, Integer>> b = new BST<KVPair<Integer, Integer>>();
		Vector<KVPair<Integer, Integer>> sortedPair = new Vector<KVPair<Integer, Integer>>();

		// Initialize to simply be the values from 0 to TESTSIZE-1
		// Ultimately, these are going to be our random keys
		for (i = 0; i < A.length; i++) {
			A[i] = i;
		}
		// Now, generate a permutation on the numbers
		Permute.permute(A);

		// Now, build the BST
		// Each record will have a random key value from the permutation.
		// Since we actually store KVPairs, we will give it a "data" value
		// that is simply the count + OFFSET (so we can distinguish "data" from keys)
		for (i = 0; i < A.length; i++) {
			b.insert(new KVPair<Integer, Integer>(new Integer(A[i]), new Integer(i + OFFSET)));
			sortedPair.add(new KVPair<Integer, Integer>(new Integer(A[i]), new Integer(i + OFFSET)));
		}
		Collections.sort(sortedPair);
		// Make sure that the thing is really a BST
		/*
		 * if (!checkBST(b.root(), sortedPair)) {
		 * System.out.println("Oops! It was not a BST!"); SUCCESS = false; }
		 */
		// Now, let's test delete by randomly removing all the keys
		Permute.permute(A);
		for (i = 0; i < A.length; i++) {
			KVPair<Integer, Integer> key = new KVPair<Integer, Integer>(new Integer(A[i]), null);
			KVPair<Integer, Integer> k = b.remove(key);
			if (b.size() != (TESTSIZE - i - 1)) {
				record.printError("Oops! Wrong size. Should be " + (TESTSIZE - i - 1) + " and it is " + b.size());
			}
			if (k.key().compareTo(A[i]) != 0) {
				record.printError("Oops! Wrong key value. Should be " + A[i] + " and it is " + k.key());
			}
		}

		// Now we are going to run timing tests on our two traversals
		Permute.permute(A);
		BST<KVPair<Integer, Integer>> btest = new BST<KVPair<Integer, Integer>>();
		for (i = 0; i < A.length; i++)
			btest.insert(new KVPair<Integer, Integer>(new Integer(A[i]), new Integer(i + OFFSET)));
		time1 = System.currentTimeMillis();
		Preorder.preorder(btest.root());
		time2 = System.currentTimeMillis();
		totaltime = (time2 - time1);
		System.out.println("Preorder time: " + totaltime);
		time1 = System.currentTimeMillis();
		Preorder.preorder2(btest.root());
		time2 = System.currentTimeMillis();
		totaltime = (time2 - time1);
		System.out.println("Preorder2 time: " + totaltime);

		// Finally, let's test with simple Integer values
		Integer[] AA = new Integer[TESTSIZE];
		BST<Integer> bb = new BST<Integer>();
		Vector<Integer> sortedInt = new Vector<Integer>();

		// Initialize to simply be the values from 0 to TESTSIZE-1
		// Ultimately, these are going to be our random keys
		int newlen = TESTSIZE / 10;
		for (i = 0; i < AA.length; i++) {
			AA[i] = Permute.random(newlen);
			sortedInt.add(AA[i]);
		}
		// Now, generate a permutation on the numbers
		Permute.permute(AA);

		// Sort the vector
		Collections.sort(sortedInt);

		// Now, build the BST
		// Each record will have a random key value from the permutation.
		for (i = 0; i < AA.length; i++)
			bb.insert(new Integer(AA[i]));

		// Make sure that the thing is really a BST
		if (!checkBST(bb.root(), sortedInt)) {
			record.printError("Oops! It was not a BST!");
		}

		// Get a feedback about the result (success or fail)
		record.feedback();
	}

}
