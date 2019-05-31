package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
	
	static int numberOfPass;
	
	private FileIO() {
		
	}
	
	/**
	 * Reads a file and return an array of strings of all lines in text file
	 * 
	 * @param selectedFile File being used
	 * @return Array of strings of every line in file
	 */
	public static String[] readFile(File selectedFile) {
		String currentLine;
		ArrayList<String> passLines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));
			while((currentLine = br.readLine()) != null) {
				passLines.add(currentLine);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] passwords = new String[passLines.size()];
		for(int i = 0; i < passLines.size(); i++) {
			passwords[i] = passLines.get(i);
		}
		return passwords;
	}
	
	/**
	 * Writes to a selected file and concatenates a String from password to details
	 * 
	 * @param selectedFile File path to be written to
	 * @param details Array of strings of first part of line
	 * @param passwords Array of Strings of each password
	 */
	public static void writeFile(File selectedFile, String[] details, String[] passwords) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
			for(int i = 0; i < passwords.length; i++) {
				bw.write(details[i] + passwords[i] + "\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
