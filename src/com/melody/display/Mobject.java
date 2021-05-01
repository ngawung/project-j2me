package com.melody.display;

import javax.microedition.lcdui.Graphics;

public abstract class Mobject {
	
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
	
	public abstract void initialize();
	public abstract void update(long dt);
	public abstract void destroy();
	
	// seperate render?
	public abstract void render(Graphics g);
	
}
