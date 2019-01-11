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
	public static String encrypt(String input, String key) {
		getSeedFromKey(key);
		String encText = getEncryptedText(input);
		return encText;
	}
	/**
	 * Runs algorithm to get seed from the user given key and creates Random instance with seed
	 * 
	 * @param key User given key
	 */
	private static void getSeedFromKey(String key) {
		char[] keySplit = key.toCharArray();
		//Adds char values of each char in user given key
		for(int i = 0; i < keySplit.length; i++) {
			seed += keySplit[i]; //Flaw found. Adding int value of chars is commutative
		}
		rand = new Random(seed);
	}
	private static String getEncryptedText(String input) {
		char[] inSplit = input.toCharArray();
		StringBuilder encText = new StringBuilder();
		for(int i = 0; i < inSplit.length; i++) {
			encText.append(encChar(inSplit[i]));
		}
		return encText.toString();
	}
	private static char encChar(char inChar) {
		int intChar = inChar;
		int currRand = rand.nextInt(95);
		int enc = intChar + currRand - 32;
		if(enc < 32) {
			enc = intChar + currRand + 63;
		}
		else if(enc > 126) {
			enc = inChar + currRand - 127;
		}
		return (char)enc;
	}
}









