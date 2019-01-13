package backend;

import java.util.Random;

public class Encryption {
	/**
	 * Seed used for random number generation
	 */
	static int seed;
	/**
	 * Random object used to get random number
	 */
	static Random rand;
	private Encryption() {
		
	}
	/**
	 * Encrypts input text using custom algorithm
	 * 
	 * @param input text to be encrypted
	 * @param key key for encryption algorithm
	 * @return encrypted text
	 */
	public static String encrypt(String input, String key) {
		System.out.println(key);
		getSeedFromKey(key);
		String encText = getEncryptedText(input);
		return encText;
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
	 * Splits input text into char array and sends each character to encryption algorithm. Each 
	 * encrypted char is appended to a string.
	 * 
	 * @param input text to be encrypted
	 * @return encrypted text
	 */
	private static String getEncryptedText(String input) {
		//Splits the input string into array of chars
		char[] inSplit = input.toCharArray();
		StringBuilder encText = new StringBuilder();
		//Sends each char to be decrypted and appends it to stringbuilder
		for(int i = 0; i < inSplit.length; i++) {
			encText.append(encChar(inSplit[i]));
		}
		return encText.toString();
	}
	/**
	 * Encrypts a char using algorithm
	 * 
	 * @param inChar char to be encrypted
	 * @return encrypted char
	 */
	private static char encChar(char inChar) {
		int currRand = rand.nextInt(95);
		/*Gets decrypted char value by adding generated number and subtracting offset of 32. 
		 *Using an offset of 32 because I want the range of 32-126 which are valid keyboard ascii 
		 *characters.
		 */
		int enc = inChar + currRand - 32;
		/*If encrypted char value about range, use different algorithm to wrap around the range. 
		 *For example, 31 would be 126.
		 */
		if(enc < 32) {
			enc = inChar + currRand + 63;
		}
		/*If encrypted char value about range, use different algorithm to wrap around the range. 
		 *For example, 127 would be 32.
		 */
		else if(enc > 126) {
			enc = inChar + currRand - 127;
		}
		return (char)enc;
	}
}









