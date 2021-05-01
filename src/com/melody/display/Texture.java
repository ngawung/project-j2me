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

}
