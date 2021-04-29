package com.melody.core;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.melody.utils.SaveManager;

public abstract class MainEngine extends MIDlet {
	
	private static MainEngine instance = null;
	public String projectName;
	
	public boolean isPaused = false;
	
	private Game gameRoot;
	public Thread mainThread;
	public SaveManager saveManager;

	public MainEngine(String projectName) {
		// singleton init
		instance = this;
		this.projectName = projectName;
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		initialize();

	}
	
	public final void setupGame() {
		gameRoot = new Game();
		gameRoot.setFullScreenMode(true);
		gameRoot.start();
		
		Display.getDisplay(this).setCurrent(gameRoot);
		
		// init assetmanager, sound, etc here...
		saveManager = new SaveManager();
		
		handleGameReady();
	}
	
	// override this
	public abstract void initialize();
	public abstract void update();
	public abstract void handleGameReady();
	
	// GET & SET
	
	public final static MainEngine getInstance() {
		return instance;
	}
	
	public final Game get_gameRoot() {
		return gameRoot;
	}

}
