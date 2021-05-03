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
			
			if (followCamera) {
				// if object inside camera
				if (CoordUtils.rectInRect(
						(int)x, (int)y, (int)x+width, (int)y+height,
						(int)MainEngine.get_instance().get_scene().cameraX, (int)MainEngine.get_instance().get_scene().cameraY, (int)MainEngine.get_instance().get_scene().cameraX + MainEngine.get_instance().get_scene().get_width(), (int)MainEngine.get_instance().get_scene().cameraY + MainEngine.get_instance().get_scene().get_height())) {
					
					
					g.setColor(color);
					if (fill) g.fillRect((int)x - (int)MainEngine.get_instance().get_scene().cameraX, (int)y - (int)MainEngine.get_instance().get_scene().cameraY, width, height);
					else g.drawRect((int)x - 1 - (int)MainEngine.get_instance().get_scene().cameraX, (int)y - 1 - (int)MainEngine.get_instance().get_scene().cameraY, width, height);
					
				}
			} else {
				g.setColor(color);
				if (fill) g.fillRect((int)x, (int)y, width, height);
				else g.drawRect((int)x - 1, (int)y - 1, width, height);
			}
			
			
		}

	}

}
