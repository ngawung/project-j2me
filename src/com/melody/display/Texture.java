package com.melody.display;

import javax.microedition.lcdui.Image;

public class Texture {
	
	private String _name;
	private Image _buffer;

	public Texture(String name, Image buffer) {
		_name = name;
		_buffer = buffer;
	}
	
	public void destroy() {
		_buffer = null;
		_name = null;
	}
	
	public String get_name() { return _name; }
	public Image get_buffer() { return _buffer; }

}
