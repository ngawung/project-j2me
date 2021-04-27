package com.melody.core;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public abstract class MainEngine extends MIDlet {
	
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
	
	public final void setupGame() {
		gameRoot = new Game();
		gameRoot.setFullScreenMode(true);
		gameRoot.start();
		
		Display.getDisplay(this).setCurrent(gameRoot);
		
		// init assetmanager, sound, etc here...
		
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
