package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Stage {
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		int stage_size = 16;
		int index = 1;
		int margin = Game.CANVAS_START;
		
		int between = 20;

		int row = 4;
		int col = 4;
		int screen_size = Game.REAL_WIDTH;
		
		int currX = margin*2;
		int currY = margin*2;
		
		int blockSize = (screen_size - (margin*2) - (between*3))/4;
		
		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		
		for(int i=0; i<col; i++) {
			for(int j=0; j<row; j++) {
				g.drawRect(currX, currY, blockSize, blockSize);
				
				g.drawString(Integer.toString(index), currX + blockSize/2, currY + blockSize/2);
				
				index++;
				currX += blockSize+between;
			}
			
			currX = margin*2;
			currY += blockSize+between;
		}
	}
}
