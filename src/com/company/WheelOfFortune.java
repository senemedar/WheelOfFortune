package com.company;

import java.util.Scanner;

public class WheelOfFortune {
	public static final Scanner sc = new Scanner(System.in);


	private static boolean firstDraw = true;

	public static void startGame() {
		// generujemy nagrody dla tej rundy
		Game.generatePrizes();
		
		// tworzymy graczy
		Player player1 = new Player("Michał");
		Player player2 = new Player("Monika");
		
		// pierwszy gracz na miejsce!!
//		Game.nextPlayer();
		
		// zaczynamy grę!
		System.out.println("Witamy w kole fortuny!!\n");
		System.out.println("Są dzisiaj z nami: " +
				Game.getCurrentPlayer().getName() + " i " +
				Player.getPlayersList()[1].getName() + "!"
		);
		System.out.println("Zaczynamy od gracza: " + Game.getCurrentPlayer().getName());
		
		
		// losujemy pierwsze hasło
		System.out.println("\nNowe hasło to:");
		if (Game.loadEntries()) {
			Game.displayEntry('1');     // "1" określa nowe hasło
		} else {
			// gra nie może załadować listy haseł
			// można coś z tym zrobić
		}
		
		// losujemy nową nagrodę
		Game.drawPrize(firstDraw);
		firstDraw = false;

		do {
			System.out.println("\nWybierz opcję:");
			System.out.println("1. Zgaduję spółgłoskę");
			System.out.println("2. Kupuję samogłoskę");
			System.out.println("3. Odgaduję hasło");
			System.out.println("9. Oglądam statystyki");
			
			int choice = sc.nextInt();
			switch (choice) {
				case 1:
					Game.guessLetter(true);
					break;
					
				case 2:
					Game.guessLetter(false);
					break;
					
				case 3:
					Game.guessEntry();
					break;
					
				case 9:
					for (Player player : Player.getPlayersList()) {
						System.out.println(player);
					}
					System.out.println();
					break;
					
				default:
					break;
			}   // end of switch
			
			System.out.println("\nOdgadujemy hasło:");
			Game.displayEntry('0');
		
		} while (true);
	}
}
