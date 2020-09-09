package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WheelOfFortune {
	public static final Scanner sc = new Scanner(System.in);
	private static Object prize;
	private static Player currentPlayer;
	private static boolean firstDraw = true;

	public static void startGame() {
		// generujemy nagrody dla tej rundy
		Game.generatePrizes();
		
		// tworzymy graczy
		Player player1 = new Player("Michał");
		Player player2 = new Player("Monika");
		
		// pierwszy gracz na miejsce!!
		currentPlayer = Player.getPlayersList()[0];
		
		// zaczynamy grę!
		System.out.println("Witamy w kole fortuny!!\n");
		System.out.println("Są dzisiaj z nami: " +
				currentPlayer.getName() + " i " +
				Player.getPlayersList()[1].getName() + "!"
		);
		System.out.println("Zaczynamy od gracza: " + currentPlayer.getName());
		
		
		// losujemy pierwsze hasło
		System.out.println("\nNowe hasło to:");
		if (Game.loadEntries()) {
			Game.displayEntry('1');     // "1" określa nowe hasło
		} else {
			// gra nie może załadować listy haseł
			// można coś z tym zrobić
		}
		
		// losujemy nową nagrodę
		prize = getNewPrize();

		do {
			System.out.println("\nWybierz opcję:");
			System.out.println("1. Zgaduję spółgłoskę");
			System.out.println("2. Kupuję samogłoskę");
			System.out.println("3. Odgaduję hasło");
			System.out.println("9. Oglądam statystyki");
			
			int choice = sc.nextInt();
			switch (choice) {
				case 1:
					if (Game.guessLetter(true)) {
						System.out.println("Brawo! Wygrałeś " + prize + "!");
						currentPlayer.addPrize(prize);
						prize = getNewPrize();
//						System.out.println(currentPlayer.toString());
					} else {
						System.out.println("Niestety, ta litera nie występuje w haśle...");
						currentPlayer = nextPlayer();
						System.out.println("Kolej na gracza: " + currentPlayer.getName());
						prize = getNewPrize();
				}
					
					break;
					
				case 3:
					if (Game.guessEntry()) {
						System.out.println("Tak! To jest to hasło!!");
						System.out.println("Brawo! Wygrałeś " + prize + "!\n");
						currentPlayer.addPrize(prize);
						
						// losujemy nowe hasło
						System.out.println("Oto nasze nowe hasło:");
						Game.displayEntry('1');     // "1" określa nowe hasło
						
						// losujemy nową nagrodę
						prize = getNewPrize();
					} else {
						System.out.println("Przykro mi, to nie jest to hasło.");
						System.out.println("Przechodzimy do następnego gracza:");
						currentPlayer = nextPlayer();
						System.out.println(currentPlayer.getName());
					}
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
	
	public static Player nextPlayer() {
		int playerNo = currentPlayer.getPlayerNo();
		playerNo++;
		if (playerNo > Player.MAX_NO_OF_PLAYERS) {
			playerNo = 1;
		}
		return Player.getPlayersList()[playerNo - 1];
	}
	
	public static Object getNewPrize() {
		Object prize = Game.getPrize();
		
		String ending = "";  // fragment komunikatu
		
		if (prize instanceof String) {
			if (prize.equals("Bankrut")) {
				if (!firstDraw) {
					System.out.println("Gratulacje! Zostałeś bankrutem!");
					// Player.bankruptPlayer(currentPlayer);
					nextPlayer();
				} else {
					getNewPrize();
				}
			} else {
				ending += "!";
			}
		} else {
			ending += " punktów.";
		}
		
		System.out.println("\nLosujemy nową nagrodę.");
		System.out.println("W tej rundzie gramy o: " + prize + ending);
		return prize;
	}
	
}
