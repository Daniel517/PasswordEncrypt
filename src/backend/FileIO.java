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
			System.out.println(passLines.get(i));
		}
		return passwords;
	}
	public static void writeFile(File selectedFile, String[] details, String[] passwords) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
			for(int i = 0; i < passwords.length; i++) {
				System.out.println(details[i] + passwords[i]);
				bw.write(details[i] + passwords[i] + "\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
