package com.melody.display;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.melody.core.MainEngine;
import com.melody.enums.TransformEnum;
import com.melody.utils.ImageUtils;

public class Mimage extends Mobject {
	
	public float x = 0;
	public float y = 0;
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	
	private Image _buffer = null;
	private int _textureId;
	private TransformEnum _transform = TransformEnum.NONE;
	private boolean _validated = true;

	public Mimage(String name, String textureName) {
		super(name);
		set_texture(textureName);
	}
	
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void update(long dt) {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		_buffer = null;
	}
	
	public void render(Graphics g) {
		if (visible) {
			
			
			
			if (!_validated) validate();
			g.drawImage(_buffer, (int)x, (int)y, anchor);
		}
	}
	
//	public void validate() {
//		if (_buffer != null) {
//			if (TransformEnum.FLIP_X.getValue() == _transform.getValue()) _buffer = ImageUtils.flipX(_buffer);
//			else if (TransformEnum.FLIP_Y.getValue() == _transform.getValue()) _buffer = ImageUtils.flipY(_buffer);
//			
//			else if (TransformEnum.ROTATE_90.getValue() == _transform.getValue()) _buffer = ImageUtils.rotate90(_buffer);
//			else if (TransformEnum.ROTATE_180.getValue() == _transform.getValue()) _buffer = ImageUtils.rotate180(_buffer);
//			else if (TransformEnum.ROTATE_270.getValue() == _transform.getValue()) _buffer = ImageUtils.rotate270(_buffer);
//			
//			// flip rotate
//		}
//		
//		_validated = true;
//	}
	
	// GET & SET
	
	public Texture get_texture() { 
		return MainEngine.get_instance().get_assetManager().getTextureFromId(_textureId);
	}
	
	public void set_texture(String textureName) {
		_textureId = MainEngine.get_instance().get_assetManager().getTextureId(textureName);
	}
	
//	public void set_transform(TransformEnum transform, boolean runValidate) {
//		_transform = transform;
//		_validated = false;
//		if (runValidate) validate();
//	}
	
	public int get_width() {
		return _buffer.getWidth();
	}
	
	public int get_height() {
		return _buffer.getHeight();
	}
	
//	public void set_buffer(Image src, int x, int y, int width, int height, int transform) {
//		_buffer = Image.createImage(src, x, y, width, height, transform);
//	}
//	
//	public void set_buffer(String path, int x, int y, int width, int height, int transform) {
//		try {
//			Image temp_buffer = Image.createImage(path);
//			_buffer = Image.createImage(temp_buffer, x, y, width, height, transform);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	

}
