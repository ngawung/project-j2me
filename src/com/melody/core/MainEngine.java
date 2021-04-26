package com.melody.core;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MainEngine extends MIDlet {
	
	private static MainEngine instance = null;
	
	public boolean isPaused = false;
	
	private Game gameRoot;
	public Thread mainThread;

	public MainEngine() {
		// singleton init
		instance = this;
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
	
	public void setupGame() {
		gameRoot = new Game();
		gameRoot.setFullScreenMode(true);
		gameRoot.start();
		
		Display.getDisplay(this).setCurrent(gameRoot);
		
		// init assetmanager, sound, etc here...
		
		handleGameReady();
	}
	
	// override this
	
	public void initialize() {
		
	}
	
	public void update() {
		
	}
	
	public void handleGameReady() {
		
	}
	
	// GET & SET
	
	public static MainEngine getInstance() {
		return instance;
	}
	
	public Game get_gameRoot() {
		return gameRoot;
	}

}
