package com.hotsprings;

import com.hotsprings.scene.demo.CompressionDemo;
import com.melody.core.MainEngine;


public class Main extends MainEngine {
	public Main() {
		super("HotspringsDemo");
		
		System.out.println("Initialize");
	}
	
	public void initialize() {
		setupGame(20, true);
	}
	
	public void handleGameReady() {
		get_gameRoot().set_scene(new CompressionDemo());
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
}
