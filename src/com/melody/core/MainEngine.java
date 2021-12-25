package com.melody.core;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public abstract class MainEngine extends MIDlet {
	
	private static MainEngine _instance = null;
	
	private int _fps;
	private String _projectName;
	private Game _gameRoot;
	
	private SaveManager _saveManager;
	private SoundManager _soundManager;
	
	private final static int MAX_FRAMERATE = 500;
	private final static int MIN_FRAMERATE = 10;
	
	public boolean pause = false;
	public boolean enableStat = false;

	public MainEngine(String projectName) {
		// singleton init
		_instance = this;
		_projectName = projectName;
	}

	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
	}

	protected void startApp() throws MIDletStateChangeException {
		initialize();
	}
	
	public final void setupGame(int fps, boolean enableStat) {
		_fps = fps;
		this.enableStat = enableStat;
		
		// init assetmanager, sound, etc here...
		_saveManager = new SaveManager(_projectName);
		_soundManager = new SoundManager();
		
		_gameRoot = new Game();
		_gameRoot.setFullScreenMode(true);
		_gameRoot.start();
		
		Display display = Display.getDisplay(this);
		display.setCurrent(_gameRoot);
		
		// Print device capability
		if (enableStat) {
			System.out.println(display.numAlphaLevels());
		}
		
		handleGameReady();
	}
	
	public final void requestRender() {
		_gameRoot.requestRender();
	}
	
	// override this
	public abstract void initialize();
	public abstract void update();
	public abstract void handleGameReady();
	
	// GET & SET
	
	public final static MainEngine get_instance() { return _instance; }
	
	public final Game get_gameRoot() { return _gameRoot; }
	public final String get_projectName() { return _projectName; }
	
	public final Scene get_scene() { return _gameRoot.get_scene(); }
	
	public final SaveManager get_saveManager() { return _saveManager; }
	public final SoundManager get_soundManager() { return _soundManager; }
	
	public final int get_fps() { return _fps; }
	
	public final void set_fps(int fps) {
		if (fps < MIN_FRAMERATE) _fps = MIN_FRAMERATE;
		else if (fps > MAX_FRAMERATE) _fps = MAX_FRAMERATE;
		else _fps = fps;
	}

}
