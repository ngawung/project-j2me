package com.melody.core;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import com.melody.utils.SaveManager;
import com.melody.utils.SoundManager;

public abstract class MainEngine extends MIDlet {
	
	private static MainEngine _instance = null;
	
	private int _fps;
	private String _projectName;
	private Game _gameRoot;
	private SaveManager _saveManager;
	private SoundManager _soundManager;
	
	public boolean pause = false;

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
	
	public final void setupGame(int fps) {
		_gameRoot = new Game();
		_gameRoot.setFullScreenMode(true);
		_gameRoot.start();
		
		Display.getDisplay(this).setCurrent(_gameRoot);
		
		// init assetmanager, sound, etc here...
		_saveManager = new SaveManager();
		_soundManager = new SoundManager();
		
		_fps = fps;
		
		handleGameReady();
	}
	
	// override this
	public abstract void initialize();
	public abstract void update();
	public abstract void handleGameReady();
	
	// GET & SET
	
	public final static MainEngine get_instance() {
		return _instance;
	}
	
	public final Game get_gameRoot() {
		return _gameRoot;
	}
	
	public final SaveManager get_saveManager() {
		return _saveManager;
	}
	
	public final String get_projectName() {
		return _projectName;
	}
	
	public final SoundManager get_soundManager() {
		return _soundManager;
	}
	
	public final int get_fps() {
		return _fps;
	}

}
