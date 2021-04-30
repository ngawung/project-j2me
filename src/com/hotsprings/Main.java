package com.hotsprings;

import com.hotsprings.scene.TestScene;
import com.melody.core.MainEngine;


public class Main extends MainEngine {
	public Main() {
		super("hotsprings");
		
		System.out.println("Initialize");
	}
	
	public void initialize() {
		setupGame(20);
	}
	
	public void handleGameReady() {
		get_gameRoot().set_scene(new TestScene());
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
}
