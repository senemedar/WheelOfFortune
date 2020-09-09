package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Game {
	
	private static final ArrayList<String> entryList = new ArrayList<>();   // lista haseł do odgadnięcia
	private static final Random randomNumber = new Random();                // seed dla losowania nagród i haseł do odgadnięcia
	private static final List<Object> prizeList = new ArrayList<>();        // lista nagród
	private static final int TOTAL_NO_OF_PRIZES = 12;
	private static final int COST_OF_VOWEL = 200;
	
	private static char[] entryArray;   // wylosowane hasło do odgadnięcia - w formie tabeli
	private static String entry;        // wylosowane hasło do odgadnięcia
	private static boolean[] visible;   // czy dana litera w haśle jest widoczna
	
	public static boolean loadEntries() {
		File wordsFile = new File("src/com/company/words.txt");
		try {
			Scanner sc = new Scanner(wordsFile);
			while (sc.hasNextLine()) {
				entryList.add(sc.nextLine());
			}
			sc.close();
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("Nie znaleziono pliku!");
			return false;
		}
	}
	
	public static ArrayList<String> getEntryList() {
		return entryList;
	}
	
	public static boolean displayEntry(char mode, String... sentence) {
		boolean found = false;
		switch (mode) {
			case '1':    // jeżeli jest "1" to pobieramy nowe hasło
				entry = getRandomEntry().toUpperCase();
				entryArray = entry.toCharArray();
				visible = new boolean[entryArray.length];
				
				// pierwsza i ostatnia litera są zawsze widoczne
				visible[0] = visible[entryArray.length - 1] = true;
				break;
			case '2':     // jeżeli jest "2" to odgadujemy hasło
				if (sentence[0].toUpperCase().equals(entry)) {
					for (int i = 0; i < entryArray.length; i++) {
						visible[i] = true;
						found = true;
					}
				}
				break;
		}   // end of switch
		
		// podglądamy hasło
		for (int i = 0; i < entryArray.length; i++) {
			if (visible[i] || Character.getType(entryArray[i]) == 12) {
				System.out.print(entryArray[i]);
			} else if (mode == entryArray[i]) {
				System.out.print(entryArray[i]);
				visible[i] = true;
				found = true;
			} else {
				System.out.print("_");
			}
		}
		
		System.out.println();
		return found;
	}
	
	private static String getRandomEntry() {
		int max = entryList.size();
		int entryNumber = randomNumber.nextInt(max);
		return entryList.get(entryNumber);
	}
	
	public static void generatePrizes() {
		// nagrody rzeczowe
		prizeList.add("Bankrut");
		prizeList.add("Wycieczka");
		prizeList.add("Uścisk dłoni prezesa");
		
		// generator wartości punktowych
		for (int i = 0; i < (TOTAL_NO_OF_PRIZES - 3); i++) {
			int prizePointsValue = randomNumber.nextInt(300) + 100;
			prizeList.add((prizePointsValue) / 25 * 25);
		}

//		for (Object obj : prizeList) {
//			System.out.println(obj);
//		}
	}
	
	public static Object getPrize() {
		// losujemy nagrodę
		int prizeNumber = randomNumber.nextInt(TOTAL_NO_OF_PRIZES);

		return prizeList.get(prizeNumber);
	}
	
	public static boolean guessLetter(boolean type) {
		// Odgadnij literę:
		//      true = spółgłoska
		//      false = samogłoska
		
		List<String> consonants = Arrays.asList(
				"B", "C", "Ć", "D", "F", "G", "H", "J", "K", "L", "Ł", "M",
				"N", "P", "Q", "R", "S", "T", "V",  "W","X", "Z", "Ź", "Ż");
		
		List<String> vowels = Arrays.asList("A", "Ą", "E", "Ę", "I", "O", "U", "Y");
		
		if (type) {
			System.out.println("Podaj spółgłoskę:");
			String letter = WheelOfFortune.sc.next().toUpperCase();
			if (consonants.contains(letter)) {
				return displayEntry(letter.charAt(0));
			} else {
				System.out.println("To nie jest spółgłoska.\nPrzykro mi, Twój ruch przepadł.");
				return false;
			}
		} else {
			System.out.println("Podaj samogłoskę:");
			String letter = WheelOfFortune.sc.next().toUpperCase();
			if (vowels.contains(letter)) {
				return displayEntry(letter.charAt(0));
			} else {
				System.out.println("To nie jest samogłoska.\nPrzykro mi, Twój ruch przepadł.");
				return false;
			}
		}
	}
	
	public static boolean guessEntry() {
		System.out.println("Podaj hasło:");
		String sentence = WheelOfFortune.sc.next().toUpperCase();
		sentence += WheelOfFortune.sc.nextLine().toUpperCase();
		return Game.displayEntry('2', sentence);
	}
	
}
