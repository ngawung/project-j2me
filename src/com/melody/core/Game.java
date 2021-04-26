package com.melody.core;

import java.util.Random;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import com.melody.enums.KeyCodeEnum;
import com.melody.input.Input;

public class Game extends Canvas implements Runnable {
	
	public MainEngine _e;
	public Thread mainThread;
	public Input input;
	
	// for testing
	private int x = 0;
	private int y = 0;
	private int size = 20;
	private int speed = 5;
	private int red = 255;
	private int green = 0;
	private int blue = 255;
	private boolean isFill = false;
	
	public Game() {
		_e = MainEngine.getInstance();
	}
	
	protected void paint(Graphics g) {
		// clear canvas
		g.setColor(0xFFFFFF);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(red, green, blue);
		
		if (!isFill) g.drawRect(x, y, size, size);
		else g.fillRect(x, y, size, size);
	}
	
	// called by Game Engine
	public void start() {
		// setup input
		input = new Input();
		
		System.out.println(input.adapter.getPlatformName());
		
		// main thread;
		mainThread = new Thread(this);
		mainThread.setPriority(Thread.MAX_PRIORITY);
		mainThread.start();
		
		// for testing
		x = getWidth() / 2 - size / 2;
		y = getHeight() / 2 - size / 2;
	}
	
	public void onEnterFrame(long dt) {
		// for testing
		if (input.isHeld(KeyCodeEnum.UP) || input.isHeld(KeyCodeEnum.KEY_2)) {
			y -= speed;
		} else if (input.isHeld(KeyCodeEnum.DOWN) || input.isHeld(KeyCodeEnum.KEY_8)) {
			y += speed;
		}
		
		if (input.isHeld(KeyCodeEnum.RIGHT) || input.isHeld(KeyCodeEnum.KEY_6)) {
			x += speed;
		} else if (input.isHeld(KeyCodeEnum.LEFT) || input.isHeld(KeyCodeEnum.KEY_4)) {
			x -= speed;
		}
		
		if (input.isHeld(KeyCodeEnum.KEY_1)) {
			y -= speed;
			x -= speed;
		} else if (input.isHeld(KeyCodeEnum.KEY_3)) {
			y -= speed;
			x += speed;
		} else if (input.isHeld(KeyCodeEnum.KEY_7)) {
			y += speed;
			x -= speed;
		} else if (input.isHeld(KeyCodeEnum.KEY_9)) {
			y += speed;
			x += speed;
		}
		
		if (input.isDown(KeyCodeEnum.KEY_STAR)) {
			size += speed;
			if (size > 80) size = 80;
		} else if (input.isDown(KeyCodeEnum.KEY_POUND)) {
			size -= speed;
			if (size < 5) size = 5;
		}
		
		if (input.isDown(KeyCodeEnum.KEY_0)) {
			size = 20;
			red = 255; green = 0; blue = 255;
			x = getWidth() / 2 - size / 2;
			y = getHeight() / 2 - size / 2;
		}
		
		if (input.isDown(KeyCodeEnum.SOFTKEY_LEFT)) {
			isFill = !isFill;
		} else if (input.isDown(KeyCodeEnum.SOFTKEY_RIGHT)) {
			x = getRandom(getWidth() - size, 9274);
			y =  getRandom(getHeight() - size, 3827487);
		} else if (input.isDown(KeyCodeEnum.CENTER)) {
			red = getRandom(255, 6421);
			green = getRandom(255, 23467);
			blue = getRandom(255, 942);
		}
		
		// input update
		input.update();
		
		// render update?
		repaint();
	}
	
	// temporary
	private static int getRandom(int range, long rand) {
		Random random = new Random(System.currentTimeMillis() + rand);
		int ran = Math.abs(random.nextInt());
		int number = 1 + ran % range;
		return Math.abs(number);
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
	public void keyPressed(int rawKeyCode) {
		input.onKeyDown(rawKeyCode);
	}
	
	public void keyReleased(int rawKeyCode) {
		input.onKeyUp(rawKeyCode);
	}

}
