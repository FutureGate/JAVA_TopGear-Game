package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	public Rectangle playButton = new Rectangle(Game.WIDTH / Game.SCALE + 120, 150, 100, 50);
	public Rectangle creditButton = new Rectangle(Game.WIDTH / Game.SCALE + 120, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / Game.SCALE + 120, 350, 100, 50);
	
	public void render(Graphics g_o) {
		Graphics2D g = (Graphics2D) g_o;
		
		Font font0 = new Font("arial", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("Top Gear", Game.WIDTH / Game.SCALE + 60, 100);
		
		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		
		g.drawString("Play", playButton.x + 20, playButton.y + 35);
		g.drawString("Credit", creditButton.x + 8, creditButton.y + 35);
		g.drawString("Quit", quitButton.x + 20, quitButton.y + 35);
		
		g.draw(playButton);
		g.draw(creditButton);
		g.draw(quitButton);
	}
}
