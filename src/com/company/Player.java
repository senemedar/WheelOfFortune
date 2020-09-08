package com.company;

import java.util.ArrayList;

public class Player {
	// ustalamy liczbę graczy
	private static final int MAX_NO_OF_PLAYERS = 2;
	private static int noOfPlayers = 0;
	
	private static Player[] playersList = new Player[MAX_NO_OF_PLAYERS];
	private String name;
	private int points;     // ilość punktów na koncie zawodnika
	private ArrayList<String> prizesWon = new ArrayList<>();    // nagrody rzeczowe wygrane przez zawodnika
	
	// konstruktor
	public Player(String name) {
		if (noOfPlayers < MAX_NO_OF_PLAYERS) {
			this.name = name;
			this.points = 0;
			
			playersList[noOfPlayers] = this;
			noOfPlayers++;
			
		} else {
			System.err.println("Jest już maksymalna liczba graczy.");
		}
		
	}
	
	@Override
	public String toString() {
		return "Gracz: " + name + ". Liczba punktów: " + points;
	}
}
