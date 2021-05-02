package com.melody.display;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class MText extends Mobject {
	public String text;
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public int x = 0;
	public int y = 0;
	public int color;
	public Font font;

	public MText(String text, int color) {
		this.text = text;
		this.color = color;
		this.font = Font.getDefaultFont();
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
			g.setFont(font);
			g.drawString(text, x, y, anchor);
		}
	}
	
	public int get_width() {
		return font.stringWidth(text);
	}
	
	public int get_height() {
		return font.getHeight();
	}

}
