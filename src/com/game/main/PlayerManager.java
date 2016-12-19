package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PlayerManager {
private ArrayList<Player> playerList = null;
	
	public PlayerManager() {
		playerList = new ArrayList<Player>();
	}
	
	public void tick() {
		for(int i=0; i<playerList.size(); i++) {
			playerList.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i<playerList.size(); i++) {
			playerList.get(i).render(g);
		}
	}
	
	public void resetPlayer() {
		for(int i=0; i<playerList.size(); i++) {
			playerList.get(i).reset();
		}
	}
	
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public void setLists(ArrayList<ArrayList<Road>> roadList, ArrayList<Door> doorList) {
		for(int i=0; i<playerList.size(); i++) {
			playerList.get(i).setRoads(roadList.get(i));
			playerList.get(i).setDoors(doorList);
		}
	}
	
	public ArrayList<Player> getPlayerList() {
		return this.playerList;
	}
}
