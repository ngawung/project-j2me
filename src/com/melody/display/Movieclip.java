package com.melody.display;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import com.melody.core.MainEngine;

public class Movieclip extends Mobject {
	
	public float x = 0;
	public float y = 0;
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public int transform = Sprite.TRANS_NONE;
	
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

	public Movieclip(Image buffer, int width, int height, int[] frameData) {
		_buffer = buffer;
		_frameData = frameData;
		this.width = width;
		this.height = height;
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void update(long dt) {
		if (!paused && _animationSequence != null) {
			
			if (_currentDelay < delay) {
				_currentDelay += dt;
				return;
			}
			
			_frame++;
			_currentDelay -= delay;
			
			if (_frame >= _animationSequence.length) {
				if (!loop) _frame = _animationSequence.length - 1;
				else _frame = 0;
			}
			
			if (visible) MainEngine.get_instance().requestRender();
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		if (visible && _buffer != null && _animationSequence != null) {
			int pointerX = _frameData[_frame * 2];
			int pointerY = _frameData[_frame * 2 + 1];
			g.drawRegion(_buffer, pointerX, pointerY, width, height, transform, (int)x, (int)y, anchor);
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
	
	// GET & SET
	
	public Image get_buffer() {
		return _buffer;
	}
	
	public void set_buffer(Image buffer, int width, int height, int[] frameData) {
		_buffer = buffer;
		_frameData = frameData;
		this.width = width;
		this.height = height;
	}
	
	public int get_currentFrame() {
		return _frame;
	}
	
	public int[] get_frameData() {
		return _frameData;
	}
	
	public int[] get_animationSequence() {
		return _animationSequence;
	}
	
}
