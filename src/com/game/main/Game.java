package com.game.main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	//=============================================================================================================
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = 320;
	public static final int SCALE = 2;
	public static final int REAL_WIDTH = WIDTH * SCALE;
	public static final int REAL_HEIGHT = HEIGHT * SCALE;
	public static final int CANVAS_START = 5;
	public static final String TITLE = "Top Gear";
	public static final int block_size = 5;
	public static final int block_length = REAL_WIDTH/block_size;
	public static int currentStage;
	
	//=============================================================================================================
	
	public static boolean initiation = false;
	boolean running = false;
	private Thread thread;
	public static Graphics2D g = null;

	//=============================================================================================================
	
	private Menu menu;
	public static Stage stage;
	public static GameManager gameManager;
	public static StageManager stageManager;
	
	//=============================================================================================================
	
	public static enum STATE {
		MENU,
		GAME,
		GAME_OVER,
		CREDIT,
		STAGE,
		CLEAR
	};
	
	public static STATE state = STATE.MENU;
	
	//=============================================================================================================
	
	public void init() {
		requestFocus();
		
		menu = new Menu();
		gameManager = new GameManager();
		stageManager = new StageManager();
		stage = new Stage();
		
		this.addMouseListener(new MouseInput());
	}
	
	public void initGame() {
		initiation = true;
		
		if(gameManager != null) {
			gameManager = null;
			gameManager = new GameManager();
		}
		
		if(stageManager != null) {
			stageManager = null;
			stageManager = new StageManager();
		}
		
		try {
			stageManager.loadStage(currentStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		gameManager.setLists(stageManager.getRoadList(), stageManager.getDoorList(), stageManager.getPlayerList());
	}
	
	private synchronized void start() {
		if(running)
				return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.exit(1);
	}

	@Override
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		if(state == STATE.GAME) {
			gameManager.tick();
		} else if(state == STATE.MENU) {
			
		} else if(state == STATE.STAGE) {
			
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(2);
			return;
		}
		
		g = (Graphics2D) bs.getDrawGraphics();
		
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints(rh);
		g.setStroke(new BasicStroke(5));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, REAL_WIDTH+10, REAL_HEIGHT+10);
		
		if(state == STATE.MENU) {
			menu.render(g);
		} else if(state == STATE.GAME) {
			if(!initiation) {
				initGame();
			}
			
			gameManager.render(g);
		} else if(state == STATE.GAME_OVER) {
			Font font = new Font("arial", Font.BOLD, 100);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("GAMEOVER", Game.REAL_WIDTH/15, Game.REAL_HEIGHT/2);
			g.setFont(new Font("arial", Font.BOLD, 50));
			
			int x = Game.REAL_WIDTH/3+90;
			int y = Game.REAL_HEIGHT/2 + 150;
			int length = 100;
			
			int xPoint[] = {x, x, x + length/2};
			int yPoint[] = {y, y + length, y + length/2};
			
			g.fillPolygon(xPoint, yPoint, 3);
		} else if(state == STATE.STAGE) {
			stage.render(g);
		} else if(state == STATE.CLEAR) {
			Font font = new Font("arial", Font.BOLD, 100);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("CLEAR", Game.REAL_WIDTH/4, Game.REAL_HEIGHT/2);
			g.setFont(new Font("arial", Font.BOLD, 50));
			
			int x = Game.REAL_WIDTH/3+90;
			int y = Game.REAL_HEIGHT/2 + 150;
			int length = 100;
			
			int xPoint[] = {x, x, x + length/2};
			int yPoint[] = {y, y + length, y + length/2};
			
			g.fillPolygon(xPoint, yPoint, 3);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}
