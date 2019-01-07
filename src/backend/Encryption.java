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
	public static void getSeedFromKey(String key) {
		char[] keySplit = key.toCharArray();
		//Adds char values of each char in user given key
		for(int i = 0; i < keySplit.length; i++) {
			seed += keySplit[i]; //Flaw found. Adding int value of chars is commutative
		}
		rand = new Random(seed);
	}
	public static String getEncryptedText(String input) {
		char[] inSplit = input.toCharArray();
		StringBuilder encText = new StringBuilder();
		for(int i = 0; i < inSplit.length; i++) {
			encText.append((char) (inSplit[i] + rand.nextInt(94)));
		}
		return encText.toString();
	}
}
