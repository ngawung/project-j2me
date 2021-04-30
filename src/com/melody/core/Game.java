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
	
	private long _deltaTime;
	private long _beginTime;
	
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
		_input.update(dt);
		_e.get_soundManager().update(dt);
		
		
		// dont update & render if paused
		if (_e.pause) return;
		
		// scene preUpdate
		if (_currentScene != null) {
			if (!_currentScene.get_initialized()) _currentScene.preInit();
			_currentScene.preUpdate(dt);
		}

		// render update?
		repaint();
	}

	public final void run() {
		_deltaTime = 0;
		_beginTime = System.currentTimeMillis();
		
		while(true) {
			_beginTime = System.currentTimeMillis();
			onEnterFrame(_deltaTime);
				
			// update stats
			
			// fixed framerate
			try {
				Thread.sleep(1000 / _e.get_fps());
			} catch (InterruptedException e) {}
			
			_deltaTime = System.currentTimeMillis() - _beginTime;
				
		}
	}
	
	// input handler
	public final void keyPressed(int rawKeyCode) {
		_input.onKeyDown(rawKeyCode);
	}
	
	public final void keyReleased(int rawKeyCode) {
		_input.onKeyUp(rawKeyCode);
	}
	
	public final void pointerPressed(int x, int y) {
		_input.onTouch(TouchPhase.BEGIN, x, y);
	}
	
	public final void pointerReleased(int x, int y) {
		_input.onTouch(TouchPhase.END, x, y);
	}
	
	public final void pointerDragged(int x, int y) {
		_input.onTouch(TouchPhase.DRAG, x, y);
	}
	
	// GET & SET
	
	public final void set_scene(Scene scene) {
		if (_currentScene != null) {
			_currentScene.destroy();
			_currentScene = null;
		}
		
		_currentScene = scene;
	}
	
	public final Scene get_scene() {
		return _currentScene;
	}
	
	public final Input get_input() {
		return _input;
	}

}
