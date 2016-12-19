package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DoorManager {
private ArrayList<Door> doorList = null;
	
	public DoorManager() {
		doorList = new ArrayList<Door>();
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		drawDoor(g);
	}
	
	public void setDoorList(ArrayList<Door> doorList) {
		this.doorList = doorList;
	}
	
	public ArrayList<Door> getDoorList() {
		return this.doorList;
	}
	
	public void drawDoor(Graphics g) {
		g.setColor(Color.WHITE);
		
		for(int i=0; i<doorList.size(); i++) {
			Door door = doorList.get(i);
			door.render(g);
		}
	}
	
	public void rotateDoor(int i) {
		
	}
}
