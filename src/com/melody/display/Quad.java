package com.melody.display;

import javax.microedition.lcdui.Graphics;

public class Quad extends Mobject {
	public int x;
	public int y;
	public int width;
	public int height;
	public int color;
	public boolean visible = true;
	public boolean fill = true;

	public Quad(String name, int x, int y, int width, int height, int color) {
		super(name);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public void initialize() {
		// TODO Auto-generated method stub

	}

	public void update(long dt) {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
		if (visible) {
			g.setColor(color);
			if (fill) g.fillRect(x, y, width, height);
			else g.drawRect(x - 1, y - 1, width, height);
		}

	}

}
