package mlbb.display.shop;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.display.Mobject;

public class CoominSoon extends Mobject {
	
	private Image soon;
	
	public CoominSoon() {
		data = new int[2];
		data[0] = 0;
		data[1] = 0;
		
		try {
			soon = Image.createImage("/mlbb/shop/soon.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		
	}

	public void update(long dt) {

	}

	public void render(Graphics g) {
		g.drawImage(soon, 240/2+data[0], 320/2, Graphics.VCENTER | Graphics.HCENTER);
	}

	public void destroy() {
		data = null;
	}

}
