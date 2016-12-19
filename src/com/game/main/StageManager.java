package com.game.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StageManager {
	ArrayList<ArrayList<Road>> roadList = null;
	ArrayList<Door> doorList = null;
	ArrayList<Player> playerList = null;
	
	public StageManager() {
		roadList = new ArrayList<ArrayList<Road>>();
		doorList = new ArrayList<>();
		playerList = new ArrayList<>();
	}
	
	public void loadStage(int stage) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("./stage/stage_" + stage + ".game"));
		String line;
		ArrayList<String> tokens = null;
		StringTokenizer tokenizer = null;
		int currentIndex = 0;
		
		while((line = br.readLine()) != null) {
			String param[] = line.split(":");
			
			if(param[0].equals("ROAD")) {
				tokenizer = new StringTokenizer(param[1], ",");
				tokens = new ArrayList<>();
				
				while(tokenizer.hasMoreTokens()) {
					tokens.add(tokenizer.nextToken());
				}
				
				int startCol = Integer.parseInt(tokens.get(0));
				int startRow = Integer.parseInt(tokens.get(1));
				int finishCol = Integer.parseInt(tokens.get(2));
				int finishRow = Integer.parseInt(tokens.get(3));
				
				if(roadList.size() <= currentIndex) {
					roadList.add(new ArrayList<Road>());
				}
				
				roadList.get(currentIndex).add(new Road(startCol, startRow, finishCol, finishRow));
				
				if(tokens.size() == 5) {
					currentIndex++;
				}
				
			} else if (param[0].equals("DOOR")) {
				tokenizer = new StringTokenizer(param[1], ",");
				tokens = new ArrayList<>();
				
				System.out.println(param[1]);
				
				while(tokenizer.hasMoreTokens()) {
					tokens.add(tokenizer.nextToken());
				}
				
				int col = Integer.parseInt(tokens.get(0));
				int row = Integer.parseInt(tokens.get(1));
				System.out.println(tokens.get(2));
				boolean isHorizontal = Boolean.parseBoolean(tokens.get(2));
				
				doorList.add(new Door(col, row, isHorizontal));
			} else if (param[0].equals("PLAYER")) {
				tokenizer = new StringTokenizer(param[1], ",");
				tokens = new ArrayList<>();
				
				while(tokenizer.hasMoreTokens()) {
					tokens.add(tokenizer.nextToken());
				}
				
				int x = Integer.parseInt(tokens.get(0));
				int y = Integer.parseInt(tokens.get(1));
				int speed = Integer.parseInt(tokens.get(2));
				
				playerList.add(new Player(x, y, speed));
			}
		}
	}
	
	public ArrayList<ArrayList<Road>> getRoadList() {
		return this.roadList;
	}
	
	public  ArrayList<Door> getDoorList() {
		return this.doorList;
	}
	
	public ArrayList<Player> getPlayerList() {
		return this.playerList;
	}
}
