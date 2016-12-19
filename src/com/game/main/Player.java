package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.game.main.Game.STATE;

public class Player {
	private double x;
	private double y;
	private double speed = 1;
	
	private Game game;
	private ArrayList<Road> roadList = null;
	private ArrayList<Door> doorList = null;
	
	private int roadIndex = 0;
	private int doorIndex = 0;
	private boolean clear = false;
	private boolean inDoor = false;
	
	private static final int UP = -90;
	private static final int DOWN = 90;
	private static final int LEFT = 180;
	private static final int RIGHT = 0;
	
	public Player(double x, double y, double speed) {
		this.speed = speed;
		this.x = x;
		this.y = y;
	}
	
	public void reset() {
		clear = false;
		roadIndex = 0;
		doorIndex = 0;
		
		this.x = Game.CANVAS_START + Game.block_length/2 + (Game.block_length)*(roadList.get(0).getStartRow());
		this.y = Game.CANVAS_START + Game.block_length/2 + (Game.block_length)*(roadList.get(0).getStartCol());
	}
	
	public void tick() {
		follow();
	}
	
	public void render(Graphics g) {
		int length = Game.REAL_WIDTH/Game.block_size/10*4;
		
		g.setColor(Color.YELLOW);
		g.fillOval((int)x-length/2, (int)y-length/2, length, length);
		
		if(clear) {
			Game.state = Game.STATE.CLEAR;
		}
	}
	
	public void setRoads(ArrayList<Road> roadList) {
		this.roadList = roadList;
	}
	
	public void setDoors(ArrayList<Door> doorList) {
		this.doorList = doorList;
	}
	
	public void follow() {
		if(!clear) {
			if(roadIndex >= roadList.size()) {
				clear = true;
				return;
			}
			
			Road targetRoad = roadList.get(roadIndex);
			
			int angle = targetRoad.getAngle();
			//System.out.println(angle);
			
			switch(angle) {
			case UP:
				goUp(targetRoad);
				break;
			case DOWN:
				goDown(targetRoad);
				break;
			case LEFT:
				goLeft(targetRoad);
				break;
			case RIGHT:
				goRight(targetRoad);
				break;
			default:
				break;
			}
			
			isGameOver(angle);
		}
	}
	
	public void goUp(Road targetRoad) {
		y -= speed;
		
		int length = Game.block_length;
		int point = Game.CANVAS_START + length/2 + (length)*(targetRoad.getFinishCol());
		
		if(y <= point) {
			y = point;
			roadIndex++;
		}
	}
	
	public void goDown(Road targetRoad) {
		y += speed;
		
		int length = Game.block_length;
		int point = Game.CANVAS_START + length/2 + (length)*(targetRoad.getFinishCol());
		
		if(y >= point) {
			y = point;
			roadIndex++;
		}
	}
	
	public void goLeft(Road targetRoad) {
		x -= speed;
		
		int length = Game.block_length;
		int point = Game.CANVAS_START + length/2 + (length)*(targetRoad.getFinishRow());
		
		if(x <= point) {
			x = point;
			roadIndex++;
		}
	}
	
	public void goRight(Road targetRoad) {
		x += speed;
		
		int length = Game.block_length;
		int point = Game.CANVAS_START + length/2 + (length)*(targetRoad.getFinishRow());
		
		if(x >= point) {
			x = point;
			roadIndex++;
		}
	}
	
	public void isGameOver(int angle) {
		if(doorIndex >= doorList.size()) {
			return;
		}
		
		Door door = doorList.get(doorIndex);
		
		boolean isHorizontal = door.getRotation();
		
		int col = door.getCol()-1;
		int row = door.getRow()-1;
		
		int length = game.block_length;
		int margin = game.CANVAS_START;
		
		if(!inDoor) {
			if(margin + (length)*(row) < x && x < margin + (length)*(row) + length) {
				if(margin + (length) +(length)*(col) < y && y < margin + (length)*(col) + length*2) {
					inDoor = true;
					
					switch(angle) {
					case UP:
					case DOWN:
						if(isHorizontal)
							Game.state = Game.STATE.GAME_OVER;
						break;
						
					case LEFT:
					case RIGHT:
						if(!isHorizontal)
							Game.state = Game.STATE.GAME_OVER;
					}
				}
			}
		} else {
			if(margin + (length)*(row) < x && x < margin + (length)*(row) + length) {
				if(margin + length + (length)*(col) < y && y < margin + (length)*(col) + length*2) {
					switch(angle) {
					case UP:
					case DOWN:
						if(isHorizontal)
							Game.state = Game.STATE.GAME_OVER;
						break;
						
					case LEFT:
					case RIGHT:
						if(!isHorizontal)
							Game.state = Game.STATE.GAME_OVER;
					}
				}
			}
			
			if(margin + (length)*(row) > x || x > margin + (length)*(row) + length) {
				if(margin + (length)*(col) > y || y > margin + (length)*(col) + length) {
					inDoor = false;
					doorIndex++;
				}
			}
		}
	}
}
