package com.game.temp.stage_loader;

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
	
	public void print() {
		System.out.printf("%d, %d, %d, %d\n", startCol, startRow, finishCol, finishRow);
	}
}
