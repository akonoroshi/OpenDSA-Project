package errorInfo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ErrorRec {
	static PrintWriter error;

	public static void createFile() throws FileNotFoundException {
		error = new PrintWriter("error log");
	}

	public static int printError(String message, int err, boolean file) {
		if (file) {
			error.println("* ***ODSA Error*** *\n" + message);
		} else {
			System.err.println("* ***ODSA Error*** *\n" + message);
		}
		return err++;
	}

	public static void feedback(int err) throws FileNotFoundException {
		if (err == 0) {
			PrintWriter output = new PrintWriter("success");
			output.println("Success");
			output.flush();
			output.close();
			System.out.println("Success!");
		} else {
			System.out.println("Testing failed. There are(is) " + err + " error(s) in your codes.");
		}

		error.flush();
		error.close();
	}

}
