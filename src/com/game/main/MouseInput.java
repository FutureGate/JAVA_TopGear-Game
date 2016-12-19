package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class MouseInput implements java.awt.event.MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int mx = arg0.getX();
		int my = arg0.getY();
		
		if(Game.state == Game.state.MENU) {
			if((mx >= (Game.WIDTH / 2 + 120)) && mx <= (Game.WIDTH /2 +220)) {
				if(my >= 150 && my <= 200) {
					Game.state = Game.state.STAGE;
				} else if(my >= 250 && my <= 300) {
					System.out.println("!!");
				} else if(my >= 350 && my <= 400) {
					System.exit(0);
				}
			}
		} else if(Game.state == Game.state.GAME) {
			for(int i=0; i<Game.gameManager.getDoorList().size(); i++) {
				Door door = Game.gameManager.getDoorList().get(i);
				
				int col = door.getCol()-1;
				int row = door.getRow()-1;
				
				int length = Game.block_length;
				int margin = Game.CANVAS_START;
				
				if(margin + (length)*(row) < mx && mx < margin + (length)*(row) + length) {
					if(margin + length + (length)*(col) < my && my < margin + (length)*(col) + length*2) {
						Game.gameManager.getDoorList().get(i).rotate();
					}
				}
			}
		} else if(Game.state == Game.state.STAGE) {
			int currIndex = 0;
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
			
			for(int i=0; i<col; i++) {
				for(int j=0; j<row; j++) {
					if( (currX <= mx && mx <= currX+blockSize) && (currY <= my && my <= currY+blockSize) ) {
						currIndex = index;
					}
					index++;
					currX += blockSize+between;
				}
				currX = margin*2;
				currY += blockSize+between;
			}
			
			Game.currentStage = currIndex;
			Game.state = Game.STATE.GAME;
			Game.initiation = false;
		} else if(Game.state == Game.STATE.CLEAR) {
			int x = Game.REAL_WIDTH/3+90;
			int y = Game.REAL_HEIGHT/2 + 150;
			int length = 100;
			
			int xPoint[] = {x, x, x + length/2};
			int yPoint[] = {y, y + length, y + length/2};
			if( x <= mx && mx <= x + length/2) {
				if( y <= my && my <= y+length ) {
					Game.state = Game.STATE.GAME;
					Game.currentStage++;
					Game.initiation = false;
					
					System.out.println("currS: " + Game.currentStage);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
