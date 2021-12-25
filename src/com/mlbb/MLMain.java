package com.mlbb;

import com.melody.core.MainEngine;
import com.mlbb.scene.MainMenu;

public class MLMain extends MainEngine {

	public MLMain() {
		super("MLBB Simulator");
	}

	public void initialize() {
		setupGame(20, true);
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void handleGameReady() {
//		get_gameRoot().set_scene(new SplashScreen());
		get_gameRoot().set_scene(new MainMenu());
	}

}