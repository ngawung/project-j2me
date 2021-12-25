package com.melody.display;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;


public class BMFont extends Mobject {
	
	public String text = "";
	public float x = 0;
	public float y = 0;
	
	public Image buffer;
	public int[][] format;
	
	private int _cursor = 0;
	
//	100% bakal lupa
//	0 - id			0 - fontSize
//	1 - x			1 - lineHeight
//	2 - y			2 - base
//	3 - width
//	4 - height
//	5 - xoffset
//	6 - yoffset
//	7 - xadvance
	
	public BMFont(Image buffer, int[][] format) {
		this.buffer = buffer;
		this.format = format;
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
	
	public int calculateWidth() {
		if (text.length() == 0) return 0;
		
		_cursor = 0;
		
		for (int i=0; i<text.length(); i++) {
			
			int cIndex = (int) text.charAt(i);
			if (cIndex < 32 || cIndex > 126) continue;
			
			_cursor += format[cIndex - 31][7];
		}
		
		return _cursor;
	}
	
	/**
	 * Caution this function also set current variable
	 */
	public int calculateWidth(String text) {
		this.text = text;
		return calculateWidth();
	}

	public void render(Graphics g) {
		if (text.length() == 0) return;
		
		_cursor = 0;
		
		for (int i=0; i<text.length(); i++) {
			
			int cIndex = (int) text.charAt(i);
			if (cIndex < 32 || cIndex > 126) continue;
			
			
			g.drawRegion(
				buffer,
				format[cIndex - 31][1],
				format[cIndex - 31][2],
				format[cIndex - 31][3],
				format[cIndex - 31][4],
				Sprite.TRANS_NONE,
				(int)x + format[cIndex - 31][5] + _cursor,
				(int)y + format[cIndex - 31][6],
				Graphics.TOP | Graphics.LEFT
			);
			
			_cursor += format[cIndex - 31][7];
		}
		
	}
	
	/**
	 * Caution this function also set current variable
	 */
	public void render(String text, int x, int y, Graphics g) {
		this.text = text;
		this.x = x;
		this.y = y;
		render(g);
	}

}
