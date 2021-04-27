package com.hotsprings;

import com.hotsprings.scene.TestScene;
import com.melody.core.MainEngine;


public class Main extends MainEngine {
	public Main() {
		System.out.println("Initialize");
	}
	
	public void initialize() {
		setupGame();
	}
	
	public void handleGameReady() {
		get_gameRoot().set_scene(new TestScene());
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
}
