package melody.display;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.MainEngine;
import melody.utils.CoordUtils;

public class Mimage extends Mobject {
	
	public float x = 0;
	public float y = 0;
	public boolean visible = true;
	public int anchor = Graphics.TOP | Graphics.LEFT;
	public Image buffer;
	public boolean followCamera = true;
	
	public int transform = Sprite.TRANS_NONE;

	public Mimage(Image buffer) {
		this.buffer = buffer;
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
			
			// inside camera
			if (CoordUtils.rectInRect((int)x, (int)y, (int)x + get_width(), (int)y + get_height(), (int)MainEngine.get_instance().get_scene().cameraX, (int)MainEngine.get_instance().get_scene().cameraY, (int)MainEngine.get_instance().get_scene().cameraX + (int)MainEngine.get_instance().get_scene().get_width(), (int)MainEngine.get_instance().get_scene().cameraY + (int)MainEngine.get_instance().get_scene().get_height())) {
				
				if (followCamera) g.drawRegion(buffer,-(int)MainEngine.get_instance().get_scene().cameraX, -(int)MainEngine.get_instance().get_scene().cameraY, get_width(), get_height(), transform, (int)x, (int)y, anchor);
				else g.drawRegion(buffer,0, 0, get_width(), get_height(), transform, (int)x, (int)y, anchor);
				
			}
			
		}
	}
	
	public int get_width() {
		return buffer.getWidth();
	}
	
	public int get_height() {
		return buffer.getHeight();
	}
}
