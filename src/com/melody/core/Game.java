package com.melody.core;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import com.melody.enums.TouchPhase;
import com.melody.input.Input;

public final class Game extends Canvas implements Runnable {
	
	private MainEngine _e;
	private Thread _mainThread;
	private Scene _currentScene;
	private Scene _newScene;
	private Input _input;
	private StatDisplay _stat = new StatDisplay();
	
	public int backgroundColor = 0xFFFFFF;
	
	private long _deltaTime;
	private long _beginTime;
	private boolean doRender = false;
	
	public Game() {
		_e = MainEngine.get_instance();
	}
	
	protected void paint(Graphics g) {
		// force 240x320
		g.setClip(0, 0, 240, 320);
		
		// clear canvas
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// call render manual from scene
//		// render child
//		if (_currentScene != null && _currentScene.get_initialized()) {
//			for (int i=0; i<_currentScene.get_childrens().size(); i++) {
//				((Mobject)_currentScene.get_childrens().elementAt(i)).render(g);
//			}
//		}
		
		if(_currentScene != null) _currentScene.render(g);
		
		// render stat
		if (_e.enableStat) _stat.render(g);
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
		if (_newScene != null) {
			if (_currentScene != null) {
				_currentScene.preDestroy();
				_currentScene = null;
			}
			_currentScene = _newScene;
			_currentScene.preInit();
			_newScene = null;
			
			System.gc();
		}
		
		// scene preUpdate
		if (_currentScene != null) {
			_currentScene.preUpdate(dt);
		}
		
		// input update
		_input.update(dt);
		_e.get_soundManager().update(dt);
		
		// update statsDisplay
		_stat.update(dt);

		// render update?
		if (doRender) {
			repaint();
			doRender = false;
		}
	}
	
	public final void requestRender() {
		doRender = true;
	}

	public final void run() {
		_deltaTime = 0;
		_beginTime = System.currentTimeMillis();
		
		while(true) {
			_beginTime = System.currentTimeMillis();
			onEnterFrame(_deltaTime);
				
			// update stats
			
			try {
				// fixed framerate
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
		_newScene = scene;
	}
	
	public final Scene get_scene() {
		return _currentScene;
	}
	
	public final Input get_input() {
		return _input;
	}

}
