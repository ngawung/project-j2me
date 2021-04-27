package com.hotsprings.scene;

import com.hotsprings.object.MyObject;
import com.melody.core.Scene;

public class TestScene extends Scene {

	public TestScene() {

	}
	
	public void initialize() {
		System.out.println("Test Scene Initialized!");
		
		MyObject obj = new MyObject("My Object");
		addChild(obj);
	}

}
