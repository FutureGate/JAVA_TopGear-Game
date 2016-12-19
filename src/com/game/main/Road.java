package com.game.main;

import java.awt.Graphics;

public class Road {
	private int startCol;
	private int startRow;
	private int finishCol;
	private int finishRow;
	
	public Road(int startCol, int startRow, int finishCol, int finishRow) {
		this.startCol = startCol;
		this.startRow = startRow;
		this.finishCol = finishCol;
		this.finishRow = finishRow;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		int margin = Game.CANVAS_START;
		int length = Game.REAL_WIDTH / Game.block_size;
		
		g.drawLine(margin + ((length/2) + (length)*(startRow)),
				margin + ((length/2) + (length)*(startCol)),
				margin + ((length/2) + (length)*(finishRow)),
				margin + ((length/2) + (length)*(finishCol)));
	}
	
	public int getAngle() {
		int angle = (int) Math.toDegrees(Math.atan2(finishCol - startCol, finishRow - startRow));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	public int getStartCol() {
		return this.startCol;
	}
	
	public int getStartRow() {
		return this.startRow;
	}
	
	public int getFinishCol() {
		return this.finishCol;
	}
	
	public int getFinishRow() {
		return this.finishRow;
	}
}
