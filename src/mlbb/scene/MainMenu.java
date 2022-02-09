package mlbb.scene;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import penner.easing.Linear;

import melody.core.Scene;
import melody.enums.BMFAlign;
import melody.enums.KeyCode;
import mlbb.display.Font;
import mlbb.display.mainmenu.Ads;
import mlbb.display.mainmenu.EventBanner;
import mlbb.display.mainmenu.FriendList;
import mlbb.display.mainmenu.InfoList;
import mlbb.display.mainmenu.MenuList;
import mlbb.display.mainmenu.ModeSelector;

public class MainMenu extends Scene {
	
	private Image bg;
	private FriendList friendList;
	private EventBanner eventBanner;
	private ModeSelector modeSelector;
	private InfoList infoList;
	private MenuList menuList;
	private Ads ads;
	
	private Image menu2;
	private Image info2;
	private char menuState = 0;
	private char infoState = 0;
	private Image arrow;
	
	private char buttonState = 0;
	
	private int startOffset = 0;
	private int endOffset = 0;
	private long startTime = 0;
	private boolean isMoving = false;
	private final int duration = 400;
	
	private boolean isAds = true;
	private int adsDelay = 1000;
	
	// 0 - mode selector
	// 1 - info
	// 2 - menu
	private int state = 0;
	private boolean enableInput = true;
	
	public MainMenu(boolean isAds) {
		this.isAds = isAds;
		if (isAds) ads = new Ads();
		
		friendList = new FriendList();
		eventBanner = new EventBanner();
		modeSelector = new ModeSelector();
		infoList = new InfoList();
		menuList = new MenuList();
		initBg();
		
		try {
			menu2 = Image.createImage("/mlbb/mainmenu/menu2.png");
			info2 = Image.createImage("/mlbb/mainmenu/info2.png");
			arrow = Image.createImage("/mlbb/mainmenu/arrow2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initBg() {
		try {
			bg = Image.createImage(240, 320);
			Graphics g = bg.getGraphics();
			
			Image currencyFrame = Image.createImage("/mlbb/mainmenu/currency_frame.png");
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/bg_final_op.jpg"), 0, 0, Graphics.LEFT | Graphics.TOP);
			g.drawImage(currencyFrame, 106, 9, Graphics.LEFT | Graphics.TOP);
			g.drawImage(currencyFrame, 106, 26, Graphics.LEFT | Graphics.TOP);
			g.drawImage(currencyFrame, 174, 26, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/level_frame.png"), 37, 22, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/avatar.png"), 5, 5, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/ticket.png"), 101, 8, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/bp.png"), 100, 25, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/diamond.png"), 167, 25, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/signal.png"), 195, 11, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/battery.png"), 211, 12, Graphics.LEFT | Graphics.TOP);
			g.setColor(0x00FF00);
			g.fillRect(213, 14, 4, 3);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 5, 320-5, Graphics.LEFT | Graphics.BOTTOM);
			g.drawRegion(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 0, 0, 85, 29, Sprite.TRANS_MIRROR, 240-5, 320-5, Graphics.RIGHT | Graphics.BOTTOM);
			g.drawImage(Image.createImage("/mlbb/mainmenu/info.png"), 12, 320-11, Graphics.LEFT | Graphics.BOTTOM);
			g.drawImage(Image.createImage("/mlbb/mainmenu/menu.png"), 240-12, 320-11, Graphics.RIGHT | Graphics.BOTTOM);
			
			Font.font2.render("Scarlet", 40, 8, g);
			Font.font.render("30", 38, 26, g);
			
			Font.font.render("100", 116, 11, g);
			Font.font.render("32000", 116, 28, g);
			Font.font.render("0", 185, 28, g);
			
			Font.font.render("Info", 41, 296, g);
			Font.font.render("Menu", 200, 296, BMFAlign.RIGHT, g);
			
			// exp
			g.setColor(0x3067B3);
			g.fillRect(56, 30, 40, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void initialize() {
		friendList.x = 240 - friendList.width - 10;
		friendList.y = 51;
		
		eventBanner.x = 10;
		eventBanner.y = 48;
		
		modeSelector.y = 195;
		infoList.x = -60;
		menuList.x = 240 + 27;
	}

	public void update(long dt) {
//		int[] touch = get_input().getTouchCoord(TouchPhase.END);
//		if (touch != null) {
//			tempX = touch[0];
//			tempY = touch[1];
//			System.out.println(tempX + " " + tempY);
//		}
		if (isAds) {
			startTime += dt;
			if (startTime >= adsDelay) {
				startTime = adsDelay; // prevent overflow
				
				if (get_input().isReleased2(KeyCode.RIGHT_SOFT_KEY)) {
					if(!ads.next()) {
						isAds = false;
						ads.destroy();
						ads = null;
					}
				}
			}
		} else {
			updateInput();
			updateEasing();
		}
		
		requestRender();
		
		eventBanner.update(dt);
		modeSelector.update(dt);
	}
	
	private void updateInput() {
		buttonState = 0;
		
		if (!isMoving) {
			if (get_input().isDown2(KeyCode.LEFT_SOFT_KEY)) {
				startTime = System.currentTimeMillis();
				isMoving = true;
				enableInput = false;
				buttonState = 1;
				
				switch(state) {
					case 0:
						endOffset = 80;
						startOffset = 0;
						state = 1;
						break;
					case 1:
						endOffset = 0;
						startOffset = 80;
						state = 0;
						break;
					case 2:
						endOffset = 80;
						startOffset = -80;
						state = 1;
						break;
				}
				
			} else if (get_input().isDown2(KeyCode.RIGHT_SOFT_KEY)) {
				startTime = System.currentTimeMillis();
				isMoving = true;
				enableInput = false;
				buttonState = 2;
				
				switch(state) {
				case 0:
					endOffset = -80;
					startOffset = 0;
					state = 2;
					break;
				case 1:
					endOffset = -80;
					startOffset = 80;
					state = 2;
					break;
				case 2:
					endOffset = 0;
					startOffset = -80;
					state = 0;
					break;
				}
			}
			
			if (get_input().isDown2(new int[]{KeyCode.DOWN, KeyCode.KEY_8})) {
				if (state == 1) infoState++;
				else if (state == 2) menuState++;
			}
			if (get_input().isDown2(new int[]{KeyCode.UP, KeyCode.KEY_2})) {
				if (state == 1) infoState--;
				else if (state == 2) menuState--;
			}
			
			if (infoState > 3) infoState = 3;
			if (menuState > 3) menuState = 3;
			
			if (get_input().isDown2(new int[]{KeyCode.CENTER, KeyCode.KEY_5})) {
				switch(state) {
					case 1:
						switch(infoState) {
							case 0: _e.get_gameRoot().set_scene(new Shop()); break;
						}
						break;
				}
			}
		}
	}
	
	private void updateEasing() {
		if (isMoving) {
			int temp = 0;
			if (System.currentTimeMillis() - startTime < duration) {
				temp = (int)Linear.easeInOut(System.currentTimeMillis() - startTime, startOffset, endOffset - startOffset, duration);
			} else {
				temp = endOffset;
				isMoving = false;
				enableInput = true;
			}
			
			modeSelector.x = temp;
			friendList.x = 240 - friendList.width - 10 + temp;
			eventBanner.x = 10 + temp;
			infoList.x = -60 + temp;
			menuList.x = 240 + 27 + temp;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, Graphics.LEFT | Graphics.TOP);
		
		// exp bar
		g.setColor(0xD0D1FF);
		g.fillRect(56, 30, (int)(40*0.3), 3);
		
		modeSelector.render(g);
		friendList.render(g);
		eventBanner.render(g);
		
		if (infoList.x + 35 > 0) {
			infoList.render(g);
			g.drawImage(arrow,
					infoList.x + 43 + (int)(Math.sin(System.currentTimeMillis() * 0.008) * 4),
					61 + infoState*55 + 8,
					Graphics.LEFT | Graphics.TOP);
		}
		if (menuList.x < 240) {
			menuList.render(g);
			g.drawRegion(arrow,
					0, 0,
					arrow.getWidth(), arrow.getHeight(),
					Sprite.TRANS_MIRROR,
					menuList.x - 20 + (int)(Math.sin(System.currentTimeMillis() * 0.008) * 4),
					61 + menuState*55 + 8,
					Graphics.LEFT | Graphics.TOP);
		}
		
		if (buttonState == 1) g.drawImage(info2, 12, 320-11, Graphics.LEFT | Graphics.BOTTOM);
		if (buttonState == 2) g.drawImage(menu2, 240-12, 320-11, Graphics.RIGHT | Graphics.BOTTOM);
		
		if (ads != null && startTime >= adsDelay) ads.render(g);
		
		if (state > 0) Font.font.render("Ok", 240/2, 300, BMFAlign.CENTER, g);
	}

	public void destroy() {
		friendList.destroy();
		eventBanner.destroy();
		modeSelector.destroy();
		infoList.destroy();
	}

}
