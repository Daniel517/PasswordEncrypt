package backend;

import java.io.File;
import java.util.Random;

public class Decryption {
	/**
	 * Seed used for random number generation
	 */
	static int seed;
	/**
	 * Random object used to get random number
	 */
	static Random rand;
	private Decryption() {
		
	}
	/**
	 * Decrypts input text using custom algorithm
	 * 
	 * @param input text to be decrypted
	 * @param key key for decryption algorithm
	 * @return decrypted text
	 */
	public static String decrypt(String input, String key) {
		getSeedFromKey(key);
		String decText = getDecryptedText(input);
		return decText;
	}
	/**
	 * Decrypts a selected file
	 * 
	 * @param fileSelected File to be decrypted
	 * @param key Key used for decryption
	 */
	public static void decryptFile(File fileSelected, String key) {
		System.out.println(fileSelected);
		//
		//
		//DO THIS
		//
		//
	}
	/**
	 * Runs algorithm to get seed from the user given key and creates Random instance with seed
	 * 
	 * @param key user given key
	 */
	private static void getSeedFromKey(String key) {
		//Splits input key to array of chars
		char[] keySplit = key.toCharArray();
		//Adds char values of each char in user given key
		for(int i = 0; i < keySplit.length; i++) {
			seed += keySplit[i]; //Flaw found. Adding int value of chars is commutative
		}
		//Creates Random instance using the aquired seed
		rand = new Random(seed);
	}
	/**
	 * Splits input text into char array and sends each character to decryption algorithm. Each 
	 * decrypted char is appended to a string.
	 * 
	 * @param input text to be decrypted
	 * @return decrypted text
	 */
	private static String getDecryptedText(String input) {
		//Splits the input string into array of chars
		char[] inSplit = input.toCharArray();
		StringBuilder decText = new StringBuilder();
		//Sends each char to be decrypted and appends it to stringbuilder
		for(int i = 0; i < inSplit.length; i++) {
			decText.append(decChar(inSplit[i]));
		}
		return decText.toString();
	}
	/**
	 * Decrypts a char using algorithm
	 * 
	 * @param inChar char to be decrypted
	 * @return decrypted char
	 */
	private static char decChar(char inChar) {
		//Gets a random number to be used in decryption step
		int currRand = rand.nextInt(95);
		/*Gets decrypted char value by subtracting generated number and adding offset of 32. Using 
		 *an offset of 32 because I want the range of 32-126 which are valid keyboard ascii 
		 *characters.
		 */
		int dec = inChar - currRand + 32;
		/*If decrypted char value about range, use different algorithm to wrap around the range. 
		 *For example, 127 would be 32.
		*/
		if(dec > 126) {
			dec = inChar - currRand - 63;
		}
		/*If decrypted char value about range, use different algorithm to wrap around the range. 
		 *For example, 31 would be 126.
		*/
		else if(dec < 32) {
			dec = inChar - currRand + 127;
		}
		return (char)dec;
	}
}
