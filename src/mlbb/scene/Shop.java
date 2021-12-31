package mlbb.scene;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.Scene;
import melody.enums.BMFAlign;
import melody.enums.KeyCodeEnum;
import mlbb.display.Font;
import mlbb.display.ShopList;

public class Shop extends Scene {
	private Image bg;
	private ShopList shopList;

	public Shop() {
		shopList = new ShopList();
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
			g.drawImage(Image.createImage("/mlbb/mainmenu/diamond.png"), 162, 29, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/signal.png"), 195, 11, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/battery.png"), 211, 12, Graphics.LEFT | Graphics.TOP);
			g.setColor(0x00FF00);
			g.fillRect(213, 14, 4, 3);
			
			g.drawImage(Image.createImage("/mlbb/shop/wish.png"), 22, 5, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/shop/gallery.png"), 52, 5, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/shop/gift.png"), 82, 5, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 5, 320-5, Graphics.LEFT | Graphics.BOTTOM);
			g.drawRegion(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 0, 0, 85, 29, Sprite.TRANS_MIRROR, 240-5, 320-5, Graphics.RIGHT | Graphics.BOTTOM);
//			g.drawImage(Image.createImage("/mlbb/mainmenu/info.png"), 12, 320-11, Graphics.LEFT | Graphics.BOTTOM);
			g.drawImage(Image.createImage("/mlbb/shop/back.png"), 240-12, 320-11, Graphics.RIGHT | Graphics.BOTTOM);
			
//			Font.font.render("100", 116, 11, g);
//			Font.font.render("32000", 116, 28, g);
//			Font.font.render("0", 185, 28, g);
			
			Font.font.render("ShopList", 41, 296, g);
			Font.font.render("Return", 200, 296, BMFAlign.RIGHT, g);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		shopList.x = -145;
	}

	public void update(long dt) {
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MainMenu(false));
		
		if (get_input().isDown(KeyCodeEnum.DOWN)) shopList.next();
		if (get_input().isDown(KeyCodeEnum.UP)) shopList.prev();
		
		requestRender();
	}

	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, Graphics.LEFT | Graphics.TOP);
		
		if (shopList.x + 145 > 0) shopList.render(g);
	}

	public void destroy() {

	}

}
