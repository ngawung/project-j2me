package com.melody.core;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import com.melody.input.Input;

public final class Game extends Canvas implements Runnable {
	
	public MainEngine _e;
	public Thread mainThread;
	public Input input;
	
	public Scene currentScene;
	
	public Game() {
		_e = MainEngine.getInstance();
	}
	
	protected void paint(Graphics g) {
		// clear canvas
		g.setColor(0xFFFFFF);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
	}
	
	// called by Game Engine
	public final void start() {
		// setup input
		input = new Input();
		
		System.out.println(input.adapter.getPlatformName());
		
		// main thread;
		mainThread = new Thread(this);
		mainThread.setPriority(Thread.MAX_PRIORITY);
		mainThread.start();
	}
	
	public final void onEnterFrame(long dt) {
		// scene preUpdate
		if (currentScene != null) currentScene.preUpdate(dt);
		
		// input update
		input.update();
		
		// render update?
		repaint();
	}

	public final void run() {
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
	public final void keyPressed(int rawKeyCode) {
		input.onKeyDown(rawKeyCode);
	}
	
	public final void keyReleased(int rawKeyCode) {
		input.onKeyUp(rawKeyCode);
	}
	
	// GET & SET
	
	public final void set_scene(Scene scene) {
		if (currentScene != null) {
			currentScene.destroy();
			currentScene = null;
		}
		
		currentScene = scene;
		currentScene.preInit();
	}
	
	public final Scene get_scene() {
		return currentScene;
	}

}
