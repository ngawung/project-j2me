package com.melody.core;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import com.melody.display.Mobject;
import com.melody.enums.TouchPhase;
import com.melody.input.Input;
import com.melody.utils.SoundManager;

public final class Game extends Canvas implements Runnable {
	
	private MainEngine _e;
	private Thread _mainThread;
	private Scene _currentScene;
	private Input _input;
	
	public int backgroundColor = 0xFFFFFF;
	
	public Game() {
		_e = MainEngine.get_instance();
	}
	
	protected void paint(Graphics g) {
		// clear canvas
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// render child
		if (_currentScene != null && _currentScene.get_initialized()) {
			for (int i=0; i<_currentScene.get_childrens().size(); i++) {
				((Mobject)_currentScene.get_childrens().elementAt(i)).render(g);
			}
		}
	}
	
	// called by Game Engine
	public final void start() {
		// setup input
		_input = new Input();
		
		// main thread;
		_mainThread = new Thread(this);
		_mainThread.setPriority(Thread.MAX_PRIORITY);
		_mainThread.start();
	}
	
	public final void onEnterFrame(long dt) {
		// input update
		input.update();
		
		if (_e.pause) return;
		
		// scene preUpdate
		if (currentScene != null) {
			if (!currentScene.initialized) currentScene.preInit();
			currentScene.preUpdate(dt);
		}

		// render update?
		repaint();
	}

	public final void run() {
		long deltaTime = 0;
		long beginTime = System.currentTimeMillis();
		
		while(true) {
			beginTime = System.currentTimeMillis();
			onEnterFrame(deltaTime);
				
			// update stats
			
			// fixed framerate
			try {
				Thread.sleep(1000 / _e.get_fps());
			} catch (InterruptedException e) {}
			
			deltaTime = System.currentTimeMillis() - beginTime;
				
		}
	}
	
	// input handler
	public final void keyPressed(int rawKeyCode) {
		input.onKeyDown(rawKeyCode);
	}
	
	public final void keyReleased(int rawKeyCode) {
		input.onKeyUp(rawKeyCode);
	}
	
	public final void pointerPressed(int x, int y) {
		input.onTouch(TouchPhase.BEGIN, x, y);
	}
	
	public final void pointerReleased(int x, int y) {
		input.onTouch(TouchPhase.END, x, y);
	}
	
	public final void pointerDragged(int x, int y) {
		input.onTouch(TouchPhase.DRAG, x, y);
	}
	
	// GET & SET
	
	public final void set_scene(Scene scene) {
		if (currentScene != null) {
			currentScene.destroy();
			currentScene = null;
		}
		
		currentScene = scene;
	}
	
	public final Scene get_scene() {
		return currentScene;
	}
	
	public final Input get_input() {
		return _input;
	}

}
