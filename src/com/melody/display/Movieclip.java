package com.melody.display;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import com.melody.enums.TransformEnum;

public class Movieclip extends Mobject {
	
	public int x = 0;
	public int y = 0;
	public int layer = 0;
	public boolean visible = true;
	public int transform = TransformEnum.NONE.getValue();
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public Image buffer = null;
	
	public int width;
	public int height;
	
	public int delay;
	public int frame;
	public int currentDelay;
	
	public int[] frameData;
	public int[] animationSequence;
	
	public boolean loop = false;
	public boolean paused = false;

	public Movieclip(String name) {
		super(name);
		
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		if (!paused && animationSequence != null) {
			
			if (currentDelay < delay) {
				currentDelay++;
				return;
			}
			
			frame++;
			currentDelay = 0;
			
			if (frame >= animationSequence.length) {
				if (!loop) frame = animationSequence.length - 1;
				else frame = 0;
			}
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		if (visible && buffer != null && animationSequence != null) {
			int pointerX = frameData[frame * 2];
			int pointerY = frameData[frame * 2 + 1];
			g.drawRegion(buffer, pointerX, pointerY, width, height, Sprite.TRANS_NONE, x, y, anchor);
		}
	}
	
	public void play(int delay, int[] animationSequence, boolean loop) {
		this.delay = delay;
		this.currentDelay = 0;
		this.frame = 0;
		this.loop = loop;
		this.animationSequence = animationSequence;
		this.paused = false;
	}
	
	// GET & SET
	
	public Image get_buffer() {
		return buffer;
	}
	
	public void set_buffer(String path, int width, int height) {
		try {
			buffer = Image.createImage(path);
			this.width = width;
			this.height = height;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// too lazy to implement all lol...
//	public void set_buffer(Image src) {
//		buffer = src;
//	}
//	
//	public void set_buffer(Image src, int x, int y, int width, int height, int transform) {
//		buffer = Image.createImage(src, x, y, width, height, transform);
//	}
//	
//	public void set_buffer(String path, int x, int y, int width, int height, int transform) {
//		try {
//			Image temp_buffer = Image.createImage(path);
//			buffer = Image.createImage(temp_buffer, x, y, width, height, transform);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
