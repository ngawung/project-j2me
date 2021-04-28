package com.melody.display;

import javax.microedition.lcdui.Graphics;

public class MText extends Mobject {
	public String text;
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public int x = 0;
	public int y = 0;
	public int color;

	public MText(String name, String text, int color) {
		super(name);
		this.text = text;
		this.color = color;
	}

	public void initialize() {
		// TODO Auto-generated method stub

	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
		if (visible) {
			g.setColor(color);
			g.drawString(text, x, y, anchor);
		}
	}

}
