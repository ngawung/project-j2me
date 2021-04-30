package com.melody.display;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Movieclip extends Mobject {
	
	public int x = 0;
	public int y = 0;
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	
	public int width;
	public int height;
	public boolean paused = false;

	public boolean loop = false;
	public int delay = 0;
	
	private int _frame = 0;
	private int _currentDelay = 0;
	private int[] _frameData;
	private int[] _animationSequence;
	
	private Image _buffer = null;
//	private int _transform = TransformEnum.NONE.getValue();
//	private boolean _validated = true;

	public Movieclip(String name) {
		super(name);
		
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		if (!paused && _animationSequence != null) {
			
			if (_currentDelay < delay) {
				_currentDelay++;
				return;
			}
			
			_frame++;
			_currentDelay = 0;
			
			if (_frame >= _animationSequence.length) {
				if (!loop) _frame = _animationSequence.length - 1;
				else _frame = 0;
			}
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		if (visible && _buffer != null && _animationSequence != null) {
			int pointerX = _frameData[_frame * 2];
			int pointerY = _frameData[_frame * 2 + 1];
			g.drawRegion(_buffer, pointerX, pointerY, width, height, Sprite.TRANS_NONE, x, y, anchor);
		}
	}
	
	public void play(int delay, int[] animationSequence, boolean loop) {
		_currentDelay = 0;
		_frame = 0;
		_animationSequence = animationSequence;
		
		this.loop = loop;
		this.delay = delay;
		this.paused = false;
	}
	
//	public void validate() {
//		// need to transform _frameData as well...
//		
//		_validated = true;
//	}
	
	// GET & SET
	
	public Image get_buffer() {
		return _buffer;
	}
	
	public void set_buffer(String path, int width, int height, int[] frameData) {
		try {
			_buffer = Image.createImage(path);
			this.width = width;
			this.height = height;
			_frameData = frameData;
			
		} catch (IOException e) {
			System.out.println("Failed load '" + path + "' from (" + name + ")");
			e.printStackTrace();
		}
	}
	
	public void set_buffer(Image src, int width, int height, int[] frameData) {
		_buffer = src;
		this.width = width;
		this.height = height;
		_frameData = frameData;
	}
	
//	public void set_transform(TransformEnum transform, boolean runValidate) {
//		_transform = transform.getValue();
//		_validated = false;
//		if (runValidate) validate();
//	}
	
	public int get_currentFrame() {
		return _frame;
	}
	
	public int[] get_frameData() {
		return _frameData;
	}
	
	public int[] get_animationSequence() {
		return _animationSequence;
	}
	
	// too lazy to implement all lol...
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
