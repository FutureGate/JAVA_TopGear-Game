package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class RoadManager {
	private ArrayList<ArrayList<Road>> roadList = null;
	
	public RoadManager() {
		roadList = new ArrayList<ArrayList<Road>>();
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		drawRoad(g);
	}
	
	public void setRoadList(ArrayList<ArrayList<Road>> roadList) {
		this.roadList = roadList;
	}
	
	public ArrayList<ArrayList<Road>> getRoadList() {
		return this.roadList;
	}
	
	public void drawBackground(Graphics g) {
		int margin = Game.CANVAS_START;
		int current_x = margin;
		int current_y = margin;
		
		int length = Game.block_length;
		
		g.setColor(Color.WHITE);
		
		for(int i=0; i<Game.block_size;i++) {
			for(int j=0; j<Game.block_size;j++) {
				g.drawRect(current_x, current_y, length, length);
				
				current_x += length;
			}
			
			current_x = margin;
			current_y+= length;
			
		}
	}
	
	public void drawRoad(Graphics g) {
		g.setColor(Color.WHITE);
		
		for(int i=0; i<roadList.size(); i++) {
			for(int j=0; j<roadList.get(i).size(); j++) {
				Road road = roadList.get(i).get(j);
				road.render(g);
			}
		}
	}
}
