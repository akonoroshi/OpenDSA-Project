package errorInfo;

import java.io.*;

/**
 * This class deals with actions regarding errors (creating an error log,
 * printing out error messages, and judging success of fail).
 * 
 * @author Yuya Asano
 *
 */
public class ErrorRec {
	static PrintWriter error;

	/**
	 * Creates a new text file to record all errors.
	 * 
	 * @throws FileNotFoundException
	 *             thrown if some errors happen while opening or creating a new
	 *             text file
	 */
	public static void createFile() throws FileNotFoundException {
		error = new PrintWriter("error log");
	}

	/**
	 * Prints out an error message in an error log or a console and increases
	 * the number of errors by 1.
	 * 
	 * @param message
	 *            an error message
	 * @param err
	 *            the number of errors
	 * @param file
	 *            true if you want to record errors in an error log file
	 * @return the number of errors incremented by 1
	 */
	public static int printError(String message, int err, boolean file) {
		if (file) {
			error.println("* ***ODSA Error*** *\n" + message);
		} else {
			System.err.println("* ***ODSA Error*** *\n" + message);
		}
		return err + 1;
	}

	/**
	 * Tells if there exist errors and, if any, prints out the number of errors.
	 * 
	 * @param err
	 *            the number of errors
	 * @param file
	 *            true if you want to record errors in an error log file
	 * @throws FileNotFoundException
	 *             thrown if some errors happen while opening or creating a new
	 *             text file
	 */
	public static void feedback(int err, boolean file) throws FileNotFoundException {
		// If there is no error, create a success file and print out success. If
		// not, report the number of errors
		if (err == 0) {
			PrintWriter output = new PrintWriter("success");
			output.println("Success");
			output.flush();
			output.close();
			System.out.println("Success!");
		} else {
			System.out.println("Testing failed. There are(is) " + err + " error(s) in your codes.");
		}
		if (file) {
			error.flush();
			error.close();
		}
	}

}
