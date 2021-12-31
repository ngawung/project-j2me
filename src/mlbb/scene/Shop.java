package mlbb.scene;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.Scene;
import mlbb.display.Font;

public class Shop extends Scene {
	private Image bg;

	public Shop() {
		initBg();
	}
	
	private void initBg() {
		try {
			bg = Image.createImage(240, 320);
			Graphics g = bg.getGraphics();
			
			Image currencyFrame = Image.createImage("/mlbb/mainmenu/currency_frame.png");
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/bg_final_op.jpg"), 0, 0, Graphics.LEFT | Graphics.TOP);
			g.drawImage(currencyFrame, 17, 30, Graphics.LEFT | Graphics.TOP);
			g.drawImage(currencyFrame, 93, 30, Graphics.LEFT | Graphics.TOP);
			g.drawImage(currencyFrame, 169, 30, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/ticket.png"), 12, 29, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/bp.png"), 87, 29, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/diamond.png"), 192, 29, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/signal.png"), 195, 11, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/battery.png"), 211, 12, Graphics.LEFT | Graphics.TOP);
			g.setColor(0x00FF00);
			g.fillRect(213, 14, 4, 3);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 5, 320-5, Graphics.LEFT | Graphics.BOTTOM);
			g.drawRegion(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 0, 0, 85, 29, Sprite.TRANS_MIRROR, 240-5, 320-5, Graphics.RIGHT | Graphics.BOTTOM);
//			g.drawImage(Image.createImage("/mlbb/mainmenu/info.png"), 12, 320-11, Graphics.LEFT | Graphics.BOTTOM);
//			g.drawImage(Image.createImage("/mlbb/mainmenu/menu.png"), 240-12, 320-11, Graphics.RIGHT | Graphics.BOTTOM);
			
//			Font.font.render("100", 116, 11, g);
//			Font.font.render("32000", 116, 28, g);
//			Font.font.render("0", 185, 28, g);
			
			Font.font.render("Menu List", 47, 296, g);
			Font.font.render("Return", 164, 296, g);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {

	}

	public void update(long dt) {

	}

	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, Graphics.LEFT | Graphics.TOP);
	}

	public void destroy() {

	}

}
