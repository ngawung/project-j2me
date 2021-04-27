package com.melody.display;

public class Mobject {
	
	// basic transform
//	public int x;
//	public int y;
//	public int width;
//	public int height;
	
	public String name;
	
	// didnt need pre function?
//	public void preInit();
//	public void preUpdate();
	
	public Mobject(String name) {
		this.name = name;
	}
	
	public void initialize() {}
	public void update() {}
	public void destroy() {}
	
	// seperate render?
//	public void render();
	
}
