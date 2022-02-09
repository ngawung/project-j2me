package mlbb.scene;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import penner.easing.Linear;

import melody.core.Scene;
import melody.display.Mobject;
import melody.enums.BMFAlign;
import melody.enums.KeyCode;
import mlbb.display.Font;
import mlbb.display.shop.CoominSoon;
import mlbb.display.shop.HeroShop;
import mlbb.display.shop.Recommended;
import mlbb.display.shop.ShopList;

public class Shop extends Scene {
	private Image bg;
	private ShopList shopList;
	private boolean isMenu = false;
	
	private char state = 0;
	private Mobject shopData;
	
	private int startPos = 0;
	private int endPos = 0;
	private long startTime = 0;
	private boolean isMoving = false;
	private final int duration = 400;

	public Shop() {
		shopList = new ShopList();
		initBg();
		prepareShopData();
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
		shopList.update(dt);
		
		if (shopData != null) {
			if (!isMenu) shopData.update(dt);
		}
		
		updateInput();
		updateEasing();
		requestRender();
	}
	
	private void updateEasing() {
		if (isMoving) {
			int temp = 0;
			if (System.currentTimeMillis() - startTime < duration) {
				temp = (int)Linear.easeInOut(System.currentTimeMillis() - startTime, startPos, endPos - startPos, duration);
			} else {
				temp = endPos;
				isMoving = false;
			}
			
			shopList.x = -145 + temp;
			if (shopData != null) shopData.data[0] = temp;
		}
	}
	
	private void updateInput() {
		if (!isMoving) {
			if (!isMenu) {
				if (get_input().isDown2(KeyCode.RIGHT_SOFT_KEY)) _e.get_gameRoot().set_scene(new MainMenu(false));
				if (get_input().isDown2(KeyCode.LEFT_SOFT_KEY)) {
					isMenu = true;
					isMoving = true;
					startPos = 0;
					endPos = 140;
					startTime = System.currentTimeMillis();
				}
			} else {
				if (get_input().isDown2(KeyCode.LEFT_SOFT_KEY) ||
						get_input().isDown2(KeyCode.RIGHT_SOFT_KEY) ||
						get_input().isDown2(new int[]{KeyCode.RIGHT, KeyCode.KEY_6})) {
					isMenu = false;
					isMoving = true;
					startPos = 140;
					endPos = 0;
					startTime = System.currentTimeMillis();
				}
				
				if (get_input().isDown2(new int[]{KeyCode.DOWN, KeyCode.KEY_8})) {
					state = shopList.next();
					prepareShopData();
					if (shopData != null ) {
						shopData.data[0] = 140;
						shopData.data[1] = 0;
					}
				}
				if (get_input().isDown2(new int[]{KeyCode.UP, KeyCode.KEY_2})) {
					state = shopList.prev();
					prepareShopData();
					if (shopData != null ) {
						shopData.data[0] = 140;
						shopData.data[1] = 0;
					}
				}
			}
		}
	}

	private void prepareShopData() {
		if (shopData != null) {
			shopData.destroy();
			shopData = null;
		}
		
		switch(state) {
		case 0: shopData = new Recommended(); break;
//		case 1: shopData = new CoominSoon(); break;
//		case 2: shopData = new CoominSoon(); break;
		case 3: shopData = new HeroShop(); break;
//		case 4: shopData = new CoominSoon(); break;
//		case 5: shopData = new CoominSoon(); break;
//		case 6: shopData = new CoominSoon(); break;
//		case 7: shopData = new CoominSoon(); break;
//		case 8: shopData = new CoominSoon(); break;
		
		default:
			shopData = null;
			break;
		}
		
		if (shopData != null) shopData.data = new int[2];
	}

	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, Graphics.LEFT | Graphics.TOP);
		
		if (shopList.x + 145 > 0) shopList.render(g);
		if (shopData != null) shopData.render(g);
//		if (isMenu) Font.font.render("Up/Down", 240/2, 300, BMFAlign.CENTER, g);
	}

	public void destroy() {

	}

}
