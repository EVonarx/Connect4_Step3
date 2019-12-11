package fr.evonarx.connect4;

import java.util.Scanner;

import javax.swing.JPanel;

import java.lang.Integer;

public class GameGraphical extends Game {
	
	public static GridGraphical gridG; //global variable graphical object
	
	
	public GameGraphical() {
	
		gridG = new GridGraphical(); //create an instance of the graphical game
		
	}

	public static void main(String[] args) {
		GameGraphical j = new GameGraphical();
	}
	
}
