//TODO Replace this import with your manager.

import filesprocessing.DirectoryProcessor;

import java.io.*;
import java.util.Arrays;


public class Tester {
	public static void main(String[] args) throws Exception {

		// Create array of files and sort it by their the length
		File[] command_files = (new File("Ex5\\Tests\\MyCommandFiles")).listFiles();
		Arrays.sort(command_files, (file1, fil2) -> {
			double x = file1.length() - fil2.length();
			return (x > 0) ? (int) Math.ceil(x) : (int) (x);
		});

		// Save the origin output and err streams.
		PrintStream org_out = System.out;
		PrintStream org_err = System.err;

		for (File file : command_files) {
			System.out.println("----------------" + file.getName() + "------------------");
			// --------------- Capture the out and err streams -------------------
			ByteArrayOutputStream bytes_err = new ByteArrayOutputStream();
			PrintStream p_err = new PrintStream(bytes_err, true, "UTF-8");
			System.setErr(p_err);

			ByteArrayOutputStream bytes_out = new ByteArrayOutputStream();
			PrintStream p_out = new PrintStream(bytes_out, true, "UTF-8");
			System.setOut(p_out);
			// -------------------------- Files ------------------------------------------
			String[] newArgs = {"Ex5\\Tests\\Test_directory", "Ex5\\Tests\\MyCommandFiles/" + file.getName()};
			DirectoryProcessor.main(newArgs);

			// --------------- Release the out and err streams -------------------
			System.setErr(org_err);
			System.setOut(org_out);

			// --------------------- Print the err and the out ------------------------------
			if (!bytes_err.toString("UTF-8").equals("")) {
				System.out.print(bytes_err.toString("UTF-8"));
			}
			System.out.println(bytes_out.toString("UTF-8"));
		}
	}
}


