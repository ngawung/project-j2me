package com.hotsprings.object;

import com.melody.display.Mobject;

public class MyObject extends Mobject {

	public MyObject(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void initialize() {
		System.out.println("MyObject Initialized");
		
	}

	public void update() {
		System.out.println("MyObject updated!");

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
