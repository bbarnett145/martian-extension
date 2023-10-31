package prob1;

import java.io.File;
import prob2.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MartianManagerIOTest {
	// This should be correct, but change the package name if yours is different
	static String path = "src/prob1/";
	// These are 6 test files names, stored in an array.
	static String[] inFileNames = {"inMartians1.txt", "inMartians2.txt", "inMartians3.txt",
			"inMartians4.txt", "inMartians5.txt", "inMartians6.txt"};
	// Output file name. The package (prob1) should be correct, but change if necessary.
	static String outFileName = "src/prob1/outMartians.txt";

	public static void main(String[] args) {
		//-----------------------------------------------
		// Test Writing
		//-----------------------------------------------
//		testWriteMartians(outFileName);

		// Comment out below:
		
		//-----------------------------------------------
		// Test Reading
		//-----------------------------------------------
//		testReadMartians(path + inFileNames[0]);
//		testReadMartians(path + inFileNames[1]);
//		testReadMartians(path + inFileNames[2]);
//		testReadMartians(path + inFileNames[3]);
//		testReadMartians(path + inFileNames[4]);
		testReadMartians(path + inFileNames[5]);

		//-----------------------------------------------
		// Test reading a previously written output file
		//-----------------------------------------------
//		testReadMartians_previouslyWritten(path + "outMartians.txt");
	}

	public static void testWriteMartians(String fileName) {
		System.out.println("-->testWriteMartians()");
		// Build MartianManager
		MartianManager mm = buildMartianManager();
		// Write MartianManager to file.
		MartianManagerIO.writeMartians(fileName, mm);
		
		// Read and display written file.
		System.out.println("*** Write Report ***");
		Scanner input;
		try {
			input = new Scanner(new File(fileName));
			while(input.hasNext()) {
				System.out.println(input.nextLine());
			}
			input.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Can't find file");
		}
		catch (RuntimeException r) {
			System.out.println("Error reading written file. Incorrect format");
		}
		System.out.println();
	}

	public static void testReadMartians(String fileName) {
		ReadReport report = MartianManagerIO.readMartians(fileName);
		System.out.println("-->testReadMartians()");
		System.out.println(report.getReport());
	}

	public static void testReadMartians_previouslyWritten(String fileName) {
		// Build MartianManager
		MartianManager mm = buildMartianManager();
		// Write MartianManager to file.
		MartianManagerIO.writeMartians(fileName, mm);

		ReadReport report = MartianManagerIO.readMartians(fileName);
		System.out.println("-->testReadMartians_previouslyWritten()");
		System.out.println(report.getReport());
	}

	private static MartianManager buildMartianManager() {
		MartianManager mm = new MartianManager();
		mm.addMartian(new GreenMartian(1, 2));
		mm.addMartian(new RedMartian(2, 3, 4));
		mm.addMartian(new RedMartian(3, 4, 5));
		mm.addMartian(new GreenMartian(4, 5));
		mm.addMartian(new RedMartian(5, 6, 7));
		return mm;
	}


}
