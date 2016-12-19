package com.game.temp.stage_loader;

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
}
