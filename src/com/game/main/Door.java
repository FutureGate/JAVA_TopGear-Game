package com.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class Door {
	private boolean isHorizontal = true;
	
	private int col;
	private int row;
	
	public Door(int col, int row, boolean isHorizontal) {
		this.col = col;
		this.row = row;
		this.isHorizontal = isHorizontal;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		int length = Game.REAL_WIDTH / Game.block_size;
		int margin = Game.CANVAS_START;
		
		int currRow = row-1;
		int currCol = col-1;
		
		g.setColor(Color.BLACK);
		g.fillOval(margin + (length)*(currRow),
				margin + (length) + (length)*(currCol),
				length,
				length);
		
		g.setColor(Color.WHITE);
		g.drawOval(margin + (length)*(currRow),
				margin + (length) + (length)*(currCol),
				length,
				length);
		
		if(isHorizontal) {
			g.drawLine(margin + (length)*(currRow), margin + (length) + (length/2) + (length)*(currCol), margin + (length)*(currRow+1), margin + (length) + (length/2) + (length)*(currCol));
		} else {
			g.drawLine(margin + (length)*(currRow) + length/2, margin + (length) + (length)*(currCol), margin + (length)*(currRow) + length/2, margin + (length) + (length)*(currCol+1));
		}
	}
	
	public int getCol() {
		return this.col;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public boolean getRotation() {
		return this.isHorizontal;
	}
	
	public void rotate() {
		this.isHorizontal = !this.isHorizontal;
	}
}
