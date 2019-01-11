package backend;

import java.util.Random;

public class Decryption {
	static int seed;
	static Random rand;
	private Decryption() {
		
	}
	public static String decrypt(String input, String key) {
		getSeedFromKey(key);
		String decText = getDecryptedText(input);
		return decText;
	}
	private static void getSeedFromKey(String key) {
		char[] keySplit = key.toCharArray();
		//Adds char values of each char in user given key
		for(int i = 0; i < keySplit.length; i++) {
			seed += keySplit[i]; //Flaw found. Adding int value of chars is commutative
		}
		rand = new Random(seed);
	}
	private static String getDecryptedText(String input) {
		char[] inSplit = input.toCharArray();
		StringBuilder decText = new StringBuilder();
		for(int i = 0; i < inSplit.length; i++) {
			decText.append(decChar(inSplit[i]));
		}
		return decText.toString();
	}
	private static char decChar(char inChar) {
		int currRand = rand.nextInt(95);
		int dec = inChar - currRand + 32;
		if(dec > 126) {
			dec = inChar - currRand - 63;
		}
		else if(dec < 32) {
			dec = inChar - currRand + 127;
		}
		return (char)dec;
	}
}
