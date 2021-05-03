package com.melody.display;

import javax.microedition.lcdui.Graphics;

import com.melody.core.MainEngine;
import com.melody.utils.CoordUtils;

public class Quad extends Mobject {
	public float x;
	public float y;
	public int width;
	public int height;
	public int color;
	public boolean visible = true;
	public boolean fill = true;
	public boolean followCamera = true;
	public int pivotX = 0;
	public int pivotY = 0;

	public Quad(int x, int y, int width, int height, int color) {
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
			
			// if object inside camera
			if (CoordUtils.rectInRect((int)x + pivotX, (int)y + pivotY, (int)x+width + pivotX, (int)y+height + pivotY, (int)MainEngine.get_instance().get_scene().cameraX, (int)MainEngine.get_instance().get_scene().cameraY, (int)MainEngine.get_instance().get_scene().cameraX + MainEngine.get_instance().get_scene().get_width(), (int)MainEngine.get_instance().get_scene().cameraY + MainEngine.get_instance().get_scene().get_height())) {
				
				g.setColor(color);
				if (followCamera) {
					if (fill) g.fillRect((int)x - (int)MainEngine.get_instance().get_scene().cameraX + pivotX, (int)y - (int)MainEngine.get_instance().get_scene().cameraY + pivotY, width, height);
					else g.drawRect((int)x - 1 - (int)MainEngine.get_instance().get_scene().cameraX + pivotX, (int)y - 1 - (int)MainEngine.get_instance().get_scene().cameraY + pivotY, width, height);
				} else {
					if (fill) g.fillRect((int)x + pivotX, (int)y + pivotY, width, height);
					else g.drawRect((int)x - 1 + pivotX, (int)y - 1 + pivotY, width, height);
				}
				
			}
			
			
		}

	}

}
