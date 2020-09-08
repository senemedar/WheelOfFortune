package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private static final ArrayList<String> entryList = new ArrayList<>();   // lista haseł do odgadnięcia
	private static final Random randomNumber = new Random();                // seed dla losowania nagród i haseł do odgadnięcia
	private static final List<Object> prizeList = new ArrayList<>();        // lista nagród
	
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
	
	public static boolean displayEntry(char letter, String... sentence) {
		boolean found = false;
		if (letter == '1') {    // jeżeli jest "1" to pobieramy nowe hasło
			entry = getRandomEntry().toUpperCase();
			entryArray = entry.toCharArray();
			visible = new boolean[entryArray.length];
			
			// pierwsza i ostatnia litera są zawsze widoczne
			visible[0] = visible[entryArray.length - 1] = true;
		} else if (letter == '2') {     // jeżeli jest "2" to odgadujemy hasło
			if (sentence[0].toUpperCase().equals(entry)) {
				for (int i = 0; i < entryArray.length; i++) {
					visible[i] = true;
					found = true;
				}
			}
		}
		
		for (int i = 0; i < entryArray.length; i++) {
			if (visible[i] || Character.getType(entryArray[i]) == 12) {
				System.out.print(entryArray[i]);
			} else if (letter == entryArray[i]) {
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
		prizeList.add("Bankrut");
		prizeList.add("Wycieczka");
		prizeList.add("Uścisk dłoni prezesa");
		
		// generator wartości punktowych
		for (int i = 0; i < 9; i++) {
			int prizePointsValue = randomNumber.nextInt(300) + 100;
			prizeList.add((prizePointsValue) / 25 * 25);
		}

//		for (Object obj : prizeList) {
//			System.out.println(obj);
//		}
	}
	
	public static Object getPrize() {
		int prizeNumber = randomNumber.nextInt(12);
		return prizeList.get(prizeNumber);
	}
}
