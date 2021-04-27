package com.melody.display;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.melody.enums.TransformEnum;

public class Mimage extends Mobject {
	
	public int x = 0;
	public int y = 0;
	public int layer = 0;
	public boolean visible = true;
	public int transform = TransformEnum.NONE.getValue();
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public Image buffer = null;

	public Mimage(String name) {
		super(name);
		
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
		if (visible && buffer != null) g.drawImage(buffer, x, y, anchor);
	}
	
	// GET & SET
	
	public Image get_buffer() {
		return buffer;
	}
	
	public void set_buffer(String path) {
		try {
			buffer = Image.createImage(path);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void set_buffer(Image src) {
		buffer = src;
	}
	
	public void set_buffer(Image src, int x, int y, int width, int height, int transform) {
		buffer = Image.createImage(src, x, y, width, height, transform);
	}
	
	public void set_buffer(String path, int x, int y, int width, int height, int transform) {
		try {
			Image temp_buffer = Image.createImage(path);
			buffer = Image.createImage(temp_buffer, x, y, width, height, transform);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
