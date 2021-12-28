package com.mlbb;

import com.melody.core.MainEngine;
//import com.mlbb.scene.MainMenu;
import com.mlbb.scene.intro.SplashScreen;

public class MLMain extends MainEngine {

	public MLMain() {
		super("MLBB Simulator");
	}

	public void initialize() {
		setupGame(20, true);
		get_gameRoot().get_stat().color = 0xFFFF00;
		
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void handleGameReady() {
		get_gameRoot().set_scene(new SplashScreen());
//		get_gameRoot().set_scene(new MainMenu());
	}

}
