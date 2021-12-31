package mlbb.display.mainmenu;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.display.Mobject;
import melody.enums.BMFAlign;
import mlbb.display.Font;

public class Ads extends Mobject {
	
	private Image overlay;
	private Image[] ads;
	private char state = 0;

	public Ads() {
		String[] ads_list = {
			"/mlbb/mainmenu/ads/1.jpg",
			"/mlbb/mainmenu/ads/2.jpg",
			"/mlbb/mainmenu/ads/3.jpg",
			"/mlbb/mainmenu/ads/4.png",
		};
		
		try {
			overlay = Image.createImage("/mlbb/mainmenu/ads/overlay.png");
			ads = new Image[ads_list.length];
			for (int i=0; i<ads_list.length; i++) {
				ads[i] = Image.createImage(ads_list[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {

	}

	public void update(long dt) {
		
	}
	
	/**
	 * @return true if there is next
	 */
	public boolean next() {
		state++;
		if (state > ads.length-1) {
			state = (char)(ads.length-1);
			return false;
		} else {
			return true;
		}
	}

	public void render(Graphics g) {
		g.drawImage(overlay, 0, 0, Graphics.LEFT | Graphics.TOP);
		g.drawImage(ads[state], 10, 35, Graphics.LEFT | Graphics.TOP);
		Font.font.render("Close", 240-5, 320 - 15, BMFAlign.RIGHT, g);
	}

	public void destroy() {
		ads = null;
		overlay = null;
	}

}
