package mlbb.display;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.display.Mobject;
import melody.enums.BMFAlign;

public class ShopList extends Mobject {
	public int x = 0;
	public int state = 0;
	
	private String[] shops = {
			"Recommended", //0
			"Discount Shop", //1
			"Skin Scratch", //2
			"Heroes", //3
			"Skins", //4
			"Draw", //5
			"Arcade", //6
			"Preparation", //7
			"Emote", //8
	};
	
	private Image frame1;
	private Image frame2;
	private int temp;
	
	// implement later
//	private boolean isMoving = false;
//	private long startTime = 0;
//	private int duration = 200;

	public ShopList() {
		try {
			frame1 = Image.createImage("/mlbb/shop/menu_frame.png");
			frame2 = Image.createImage("/mlbb/shop/menu_frame2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void next() {
		state++;
		if (state > shops.length - 1) state = 0;
	}
	
	public void prev() {
		state--;
		if (state < 0) state = shops.length - 1;
	}

	public void initialize() {
		
	}

	public void update(long dt) {
		
	}

	public void render(Graphics g) {
		g.drawImage(frame2, -60+x, 61, Graphics.LEFT | Graphics.TOP);
		g.drawImage(frame2, -30+x, 103, Graphics.LEFT | Graphics.TOP);
		g.drawImage(frame1, x, 145, Graphics.LEFT | Graphics.TOP);
		g.drawImage(frame2, -30+x, 187, Graphics.LEFT | Graphics.TOP);
		g.drawImage(frame2, -60+x, 229, Graphics.LEFT | Graphics.TOP);
		
		temp = state - 2;
		if (temp < 0) temp = shops.length + temp;
		Font.font2.render(shops[temp], -60+62+x, 61+14, BMFAlign.CENTER, g);
		
		temp = state - 1;
		if (temp < 0) temp = shops.length + temp;
		Font.font2.render(shops[temp], -30+62+x, 103+14, BMFAlign.CENTER, g);
		
		Font.font2.render(shops[state], 62+x, 145+14, BMFAlign.CENTER, g);
		
		temp = state + 1;
		if (temp > shops.length - 1) temp = temp - shops.length;
		Font.font2.render(shops[temp], -30+62+x, 187+14, BMFAlign.CENTER, g);
		
		temp = state + 2;
		if (temp > shops.length - 1) temp = temp - shops.length;
		Font.font2.render(shops[temp], -60+62+x, 229+14, BMFAlign.CENTER, g);
	}

	public void destroy() {

	}

}
