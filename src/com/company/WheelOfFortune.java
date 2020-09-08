package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WheelOfFortune {
	private static final Scanner sc = new Scanner(System.in);
	private static final int COST_OF_VOWEL = 200;

	public static void startGame() {
		// generujemy nagrody dla tej rundy
		Game.generatePrizes();
		
		// tworzymy graczy
		Player player1 = new Player("Michał");
		Player player2 = new Player("Monika");
		
		System.out.println(player1);
		System.out.println(player2);
		System.out.println();
		
		// losujemy nowe hasło
		System.out.println("Nowe hasło to:");
		if (Game.loadEntries()) {
			Game.displayEntry('1');     // "1" określa nowe hasło
		}
		
		// losujemy nową nagrodę
		Object prize = getPrize();
		
		boolean guess = false;
		do {
			if (prize instanceof Boolean) {
				// wyzeruj konto zawodnika
			} else {
				System.out.println("\nWybierz opcję:");
				System.out.println("1. Zgaduję spółgłoskę");
				System.out.println("2. Kupuję samogłoskę");
				System.out.println("3. Odgaduję hasło");
				
				int choice = sc.nextInt();
				switch (choice) {
					case 1:
						guess = guessLetter(true);
						break;
					case 2:
						guess = guessLetter(false);
						break;
					case 3:
						guess = guessEntry();
						break;
				}
			}
			
			System.out.println(guess);
			
			
		} while (true);

	}
	
	private static boolean guessLetter(boolean type) {
		// Odgadnij literę:
		//      true = spółgłoska
		//      false = samogłoska
//		String[] consonants = {
//				"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "X", "Z", "W", "Y"
//		};
		List<String> consonants = Arrays.asList(
				"B", "C", "Ć", "D", "F", "G", "H", "J", "K", "L", "Ł", "M",
				"N", "P", "Q", "R", "S", "T", "V",  "W","X", "Z", "Ź", "Ż");
		List<String> vowels = Arrays.asList("A", "Ą", "E", "Ę", "I", "O", "U", "Y");
		
		if (type) {
			System.out.println("Podaj spółgłoskę:");
			String letter = sc.next().toUpperCase();
			if (consonants.contains(letter)) {
				return Game.displayEntry(letter.charAt(0));
			} else {
				System.out.println("To nie jest spółgłoska.\nPrzykro mi, straciłeś ruch.");
				return false;
			}
		} else {
			System.out.println("Podaj samogłoskę:");
			String letter = sc.next().toUpperCase();
			if (vowels.contains(letter)) {
				return Game.displayEntry(letter.charAt(0));
			} else {
				System.out.println("To nie jest samogłoska.\nPrzykro mi, straciłeś ruch.");
				return false;
			}
		}
	}
	
	private static boolean guessEntry() {
		System.out.println("Podaj hasło:");
		String sentence = sc.next().toUpperCase();
		sentence += sc.nextLine().toUpperCase();
		return Game.displayEntry('2', sentence);
	}
	
	private static Object getPrize() {
		String ending;
		Object prize = Game.getPrize();
		if (prize instanceof String) {
			if (prize.equals("Bankrut")) {
				System.out.println("Gratulacje! Zostałeś bankrutem!");
				return false;
			} else {
				ending = "!";
			}
		} else {
			ending = " punktów";
		}
		
		System.out.println("\nW tej rundzie gramy o: " + prize + ending);
		
		return prize;
	}
}
