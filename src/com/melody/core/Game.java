package com.melody.core;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import com.melody.enums.KeyCode;
import com.melody.input.Input;
import com.melody.input.KeyCodeAdapter;

public class Game extends Canvas implements Runnable {
	
	public MainEngine _e;
	public Thread mainThread;
	public Input input;
	
//	public Graphics g;

	public Game() {
		_e = MainEngine.getInstance();
	}

	private int x = 0;
	private int y = 0;
	
	protected void paint(Graphics g) {
		// clear canvas
		g.setColor(0xFFFFFF);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(0xFF00FF);
		g.drawRect(x, y, 20, 20);
	}
	
	// called by Game Engine
	public void start() {
		// setup input
		input = new Input();
		
		// main thread;
		mainThread = new Thread(this);
		mainThread.setPriority(Thread.MAX_PRIORITY);
		mainThread.start();
	}
	
	public void onEnterFrame(long dt) {
		// scene preUpdate
//		y++;
		
//		if (input.isReleased(KeyCode.DOWN)) {
//			y += 20;
////			System.out.println("click down " + y);
//		}
		
		if (input.isDown(KeyCode.UP)) {
//			y -= 20;
			System.out.println("click up ");
		}
		
		if (input.isDown(KeyCode.DOWN)) {
//			y -= 20;
			System.out.println("click DOWN ");
		}
		
		// input update
		input.update();
		
		// render update?
		repaint();
	}

	public void run() {
		long deltaTime = 0;
		long beginTime = System.currentTimeMillis();
		
		while(true) {
			try {
				beginTime = System.currentTimeMillis();
				onEnterFrame(deltaTime);
				deltaTime = System.currentTimeMillis() - beginTime;
				
				// fixed framerate
				Thread.sleep(1000 / 20);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// input handler
	public void keyPressed(int keyCode) {
		input.onKeyDown(getKeyCodeEnum(keyCode));
		System.out.println("press " + getKeyName(keyCode) + keyCode);
		System.out.println("internal " + input.adapter.adoptKeyCode(keyCode));
		
		if (keyCode == UP) {
			System.out.println("atas ");
		}
	}
	
	public void keyReleased(int keyCode) {
		input.onKeyUp(getKeyCodeEnum(keyCode));
		System.out.println("release " + getKeyName(keyCode) + keyCode);
	}
	
	public KeyCode getKeyCodeEnum(int keyCode) {
//		System.out.println("enum " + getKeyName(keyCode) +","+ (keyCode == UP));
		switch (keyCode) {
			case UP: return KeyCode.UP;
			case DOWN: return KeyCode.DOWN;
			case LEFT: return KeyCode.LEFT;
			case RIGHT: return KeyCode.RIGHT;
			
			default:
				System.out.println("invalid");
				return new KeyCode("INVALID", -1);
		}
	}

}
