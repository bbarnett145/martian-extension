package prob1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import prob2.*;

public class MartianManagerIO {

	/**
	 *  DO NOT ALTER THIS METHOD.
	 */
	public static void writeMartians(String fileName, MartianManager mm) {
		File file = new File(fileName);
        try {
			writeMartiansFile(file, mm);
		}
        catch (FileNotFoundException e) {
			System.out.println("Error writing file");
			e.printStackTrace();
		}
	}
	
	/**
	 *  YOU  WRITE THIS METHOD.
	 *  
	 *  Write the martians in the MartianManager to the file. The format is exactly the same
	 *  as specified in the homework document for reading valid data: G I V or R I V T.
	 */
	private static void writeMartiansFile(File file, MartianManager mm) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		for(int i = 0; i < mm.getNumMartians(); i++) {
			Martian m = mm.getMartianAt(i);
			if(m instanceof RedMartian rm) {
				writer.print("R " + rm.getId() + " " + rm.getVolume() + " " + rm.getTenacity() + "\n");
			}
			else {
				writer.print("G " + m.getId() + " " + m.getVolume() + "\n");
			}
		}
		writer.close();
	}

	/**
	 *  DO NOT ALTER THIS METHOD.
	 */
	public static ReadReport readMartians(String fileName) {
		File file = new File(fileName);
		ReadReport report = null;
        try {
			report = readMartiansFile(file);
		}
        catch (FileNotFoundException e) {
			System.out.println("Error reading file");
			e.printStackTrace();
		}
		return report;
	}
	
	/**
	 * YOU WRITE THIS METHOD.
	 * 
	 * Reads a text file that contains Martian data and returns a ReadReport object. Details
	 * are in the homework document. 
	 * 
	 * @param file
	 * @return
	 * @throws RuntimeException
	 * @throws FileNotFoundException
	 */
	private static ReadReport readMartiansFile(File file) throws RuntimeException, FileNotFoundException {
		String fileName = file.getName();
		int numsLinesRead = 0;
		int numsSuccessfullyAdded = 0;
		int numAlreadyExist = 0;
		int numIllFormed = 0;
		MartianManager mm = new MartianManager();
		Scanner scnr = new Scanner(file);
		
		while(scnr.hasNext()) {
			String line = scnr.nextLine();
			String[] tokens = line.split("\\s+");
			
			// Initial valid Martian syntax test
			if(tokens.length > 4) {
				numIllFormed++;
			}
			if(tokens.length == 3) {
				// Reading a GreenMartian
				if(tokens[0].equals("G")) {
					int id = -1;
					int vol = -1;
					// Verify ID is valid
					if(isInteger(tokens[1])) {
						id = Integer.parseInt(tokens[1]);
					}
					// Verify Volume is valid
					if(isInteger(tokens[2])) {
						vol = Integer.parseInt(tokens[2]);
					}
					// Check if valid GreenMartian already exists
					if(id >= 0 && vol >= 0) {
						if(mm.addMartian(new GreenMartian(id, vol)) == true) {
						mm.addMartian(new GreenMartian(id, vol));
						numsSuccessfullyAdded++;
						}
						else {
							numAlreadyExist++;
						}
					}
					else {
						numIllFormed++;
					}
				}
				else {
					numIllFormed++;
				}
			}
			if(tokens.length == 4) {
				// Reading a RedMartian
				if(tokens[0].equals("R") && tokens.length == 4) {
					int id = -1;
					int vol = -1;
					int ten = -1;
					// Verify ID is valid
					if(isInteger(tokens[1])) {
						id = Integer.parseInt(tokens[1]);
					}
					// Verify Volume is valid
					if(isInteger(tokens[2])) {
						vol = Integer.parseInt(tokens[2]);
					}
					// Verify Tenacity is valid
					if(isInteger(tokens[3])) {
						ten = Integer.parseInt(tokens[3]);
					}
					// Check if valid RedMartian already exists
					if(id >= 0 && vol >= 0 && ten >= 0) {
						if(mm.addMartian(new RedMartian(id, vol, ten)) == true) {
						mm.addMartian(new RedMartian(id, vol, ten));
						numsSuccessfullyAdded++;
						}
						else {
							numAlreadyExist++;
						}
					}
					else {
						numIllFormed++;
					}
				}
				else {
					numIllFormed++;
				}
			}
			numsLinesRead++;
		}
		scnr.close();
		if(numsLinesRead > 0) {
			ReadReport r = new ReadReport(mm, fileName, numsLinesRead, numsSuccessfullyAdded, numAlreadyExist, numIllFormed);
			return r;
		}
		return null;
	}
	
	public static boolean isInteger(String s) {
		try {
			int x = Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
	}

}
