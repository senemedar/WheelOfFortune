package com.company;

import java.util.ArrayList;

public class Player {
	// ustalamy liczbę graczy
	public static final int MAX_NO_OF_PLAYERS = 2;
	private static int noOfPlayers = 0;
	
	private static Player[] playersList = new Player[MAX_NO_OF_PLAYERS];
	private String name;
	private int playerNo;
	private int points;     // ilość punktów na koncie zawodnika
	private ArrayList<String> listOfWonPrizes = new ArrayList<>();    // nagrody rzeczowe wygrane przez zawodnika
	
	// konstruktor
	public Player(String name) {
		if (noOfPlayers < MAX_NO_OF_PLAYERS) {
			this.name = name;
			this.points = 0;
//			listOfWonPrizes = {};
			
			playersList[noOfPlayers] = this;
			noOfPlayers++;
			this.playerNo = noOfPlayers;
			
		} else {
			System.err.println("Jest już maksymalna liczba graczy.");
		}
		
	}
	
	public static Player[] getPlayersList() {
		return playersList;
	}
	
	public void bankruptPlayer() {
		this.points = 0;
		this.listOfWonPrizes.clear();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPlayerNo() {
		return playerNo;
	}
	
	public void addPrize(Object prize) {
		if (prize instanceof String) {
			// nagroda jest rzeczowa, więc dodajemy do listy nagród
			listOfWonPrizes.add((String)prize);   // rzutujemy, bo Java spodziewa się Object, a my wiemy, że to String
		} else {
			this.points += (int)prize;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Gracz: ").append(name);
		sb.append("\nLiczba punktów: ").append(points);
		if (listOfWonPrizes.size() != 0) {
			sb.append("\nNagrody rzeczowe:");
			for (String prize : listOfWonPrizes) {
				sb.append(" ").append(prize);
			}
		} else {
			sb.append("\nBrak nagród rzeczowych.");
		}
		sb.append("\n");
		return sb.toString();
	}
}
