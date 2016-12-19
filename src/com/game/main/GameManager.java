package com.game.main;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameManager {
	public static RoadManager roadManager = null;
	public static DoorManager doorManager = null;
	public static PlayerManager playerManager = null;
	private Graphics g = null;
	
	public GameManager() {
		roadManager = new RoadManager();
		doorManager = new DoorManager();
		playerManager = new PlayerManager();
	}
	
	public void setLists(ArrayList<ArrayList<Road>> roadList, ArrayList<Door> doorList, ArrayList<Player> playerList) {
		this.roadManager.setRoadList(roadList);
		this.doorManager.setDoorList(doorList);
		this.playerManager.setPlayerList(playerList);
		this.playerManager.setLists(roadList, doorList);
		
		resetGame();
	}
	
	public void resetGame() {
		playerManager.resetPlayer();
	}
	
	public void tick() {
		if(roadManager == null || doorManager == null || playerManager == null) {
			return;
		}
		
		roadManager.tick();
		doorManager.tick();
		playerManager.tick();
	}
	
	public void render(Graphics g) {
		roadManager.render(g);
		doorManager.render(g);
		playerManager.render(g);
	}
	
	public ArrayList<Door> getDoorList() {
		return doorManager.getDoorList();
	}
	
	public void addDoor(Door door) {
		
	}
}
