package com.melody.display;

import javax.microedition.lcdui.Image;

public class Texture {
	
	private String _name;
	private Image _buffer;
	
	private int _pivotX;
	private int _pivotY;
	private int _width;
	private int _height;

	public Texture(String name, Image buffer) {
		_name = name;
		_buffer = buffer;
		
		// set default
		setupTexture(0, 0, _buffer.getWidth(), _buffer.getHeight());
	}
	
	public void setupTexture(int pivotX, int pivotY, int width, int height) {
		_pivotX = pivotX;
		_pivotY = pivotY;
		_width = width;
		_height = height;
	}
	
	public void destroy() {
		_buffer = null;
		_name = null;
	}
	
	public String get_name() { return _name; }
	public Image get_buffer() { return _buffer; }
	
	public int get_pivotX() { return _pivotX; }
	public int get_pivotY() { return _pivotY; }
	public int get_width() { return _width; }
	public int get_height() { return _height; }

}
