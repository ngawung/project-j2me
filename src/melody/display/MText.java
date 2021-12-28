package melody.display;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import melody.core.MainEngine;
import melody.utils.CoordUtils;

public class MText extends Mobject {
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public float x = 0;
	public float y = 0;
	public int color;
	public Font font;
	public boolean followCamera = true;

	private String _text;
	private int _width = 0;
	private int _height = 0;

	public MText(String text, int color) {
		this.color = color;
		this.font = Font.getDefaultFont();
		
		_text = text;
		_width = font.stringWidth(text);
		_height = font.getHeight();
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
		if (visible && _text.length() > 0) {
			
			g.setColor(color);
			g.setFont(font);
			
			if (followCamera) {
				// inside camera
				if (CoordUtils.rectInRect((int)x, (int)y, (int)x + get_width(), (int)y + get_height(), (int)MainEngine.get_instance().get_scene().cameraX, (int)MainEngine.get_instance().get_scene().cameraY, (int)MainEngine.get_instance().get_scene().cameraX + (int)MainEngine.get_instance().get_scene().get_width(), (int)MainEngine.get_instance().get_scene().cameraY + (int)MainEngine.get_instance().get_scene().get_height()))
					g.drawString(_text, (int)x - (int)MainEngine.get_instance().get_scene().cameraX, (int)y - (int)MainEngine.get_instance().get_scene().cameraY, anchor);
			
			} else g.drawString(_text, (int)x, (int)y, anchor);
		}
	}
	
	public void set_text(String text) {
		this._text = text;
		_width = font.stringWidth(text);
		_height = font.getHeight();
	}
	
	public String get_text() {
		return _text;
	}
	
	public int get_width() {
		return _width;
	}
	
	public int get_height() {
		return _height;
	}

}
