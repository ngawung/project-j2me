package com.melody.display;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Mimage extends Mobject {
	
	public float x = 0;
	public float y = 0;
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public Image texture;
	
	private int _transform = Sprite.TRANS_NONE;
//	private boolean _validated = true;

	public Mimage(String name, Image texture) {
		super(name);
		this.texture = texture;
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
			g.drawRegion(
				texture,
				0, 0,
				get_width(),
				get_height(),
				_transform, (int)x, (int)y, anchor
			);
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
	
//	public void set_transform(TransformEnum transform, boolean runValidate) {
//		_transform = transform;
//		_validated = false;
//		if (runValidate) validate();
//	}
	
	public int get_width() {
		return texture.getWidth();
	}
	
	public int get_height() {
		return texture.getHeight();
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
