package com.melody.display;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Mimage extends Mobject {
	
	public float x = 0;
	public float y = 0;
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public Image buffer;
	
	public int transform = Sprite.TRANS_NONE;

	public Mimage(Image buffer) {
		this.buffer = buffer;
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
			g.drawRegion(
				buffer,
				0, 0,
				get_width(),
				get_height(),
				transform, (int)x, (int)y, anchor
			);
		}
	}
	
	public int get_width() {
		return buffer.getWidth();
	}
	
	public int get_height() {
		return buffer.getHeight();
	}
}
