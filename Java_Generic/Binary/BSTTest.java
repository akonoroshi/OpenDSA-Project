package Binary;

import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import utils.KVPair;
import utils.Permute;
import errorInfo.ErrorRec;

public class BSTTest {
	static final int TESTSIZE = 100;
	static final int OFFSET = 100;
	// These get set by the testing routine
	static long time1, time2, totaltime;
	// Instance of ErrorRec class which holds the number of errors and prints
	// out error messages
	static ErrorRec record;
	static boolean useFile = true;

	static <T> void visit(BinNode<T> rt) {
		// System.out.print(rt.value() + " ");
	}

	static <E extends Comparable<E>> boolean checkBST(BSTNode<E> rt, Vector<E> sorted) {
		if (rt == null)
			return true; // Empty subtree
		// Inorder traversal
		checkBST(rt.left(), sorted);
		// Compare values
		if (rt.value().compareTo(sorted.remove(0)) != 0) {
			return false;
		}
		checkBST(rt.right(), sorted);
		// If the vector is empty, BST stores all of the necessary items
		return sorted.isEmpty();
	}

	public static void main(String args[]) throws IOException {
		// Create a useFile to record errors if necessary
		record = new ErrorRec(useFile, "StackTest");
		// Create an array that stores random numbers
		Integer[] A = new Integer[TESTSIZE];
		int i;
		// BST to be tested
		BST<KVPair<Integer, Integer>> b = new BST<KVPair<Integer, Integer>>();
		// Create a vector that holds sorted KVPair
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

		// Sort the KVPairs
		Collections.sort(sortedPair);
		// Inorder traversal gives the sorted order.
		if (!checkBST(b.root(), sortedPair)) {
			record.printError("Oops! It was not a BST!");
		}

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
		for (i = 0; i < A.length; i++) {
			btest.insert(new KVPair<Integer, Integer>(new Integer(A[i]), new Integer(i + OFFSET)));
		}
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
		// Create a vector that holds sorted Integer
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

		// Now, build the BST
		// Each record will have a random key value from the permutation.
		for (i = 0; i < AA.length; i++) {
			bb.insert(new Integer(AA[i]));
		}

		// Sort the vector
		Collections.sort(sortedInt);
		// Inorder traversal gives the sorted order.
		if (!checkBST(bb.root(), sortedInt)) {
			record.printError("Oops! It was not a BST!");
		}

		// Get a feedback about the result (success or fail)
		record.feedback();
	}

}
