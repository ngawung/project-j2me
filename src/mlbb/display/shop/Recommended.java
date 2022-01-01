package mlbb.display.shop;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.display.Mobject;

public class Recommended extends Mobject {
	private Image[] banner;
	private char banerState = 0;
	private int bannerCounter = 0;
	private final int bannerDuration = 3000;
	
	private Image hero1;
	private Image hero2;
	private Image hero3;
	
	private Image pricetag;
	private Image purchase;
	
	private char page = 0;

	public Recommended() {
		data = new int[1];
		data[0] = 0;
		
		String[] bannerList = {
			"/mlbb/shop/banner1.png",
			"/mlbb/shop/banner2.png",
			"/mlbb/shop/banner3.png"
		};
		
		try {
			pricetag = Image.createImage("/mlbb/shop/pricetag.png");
			purchase = Image.createImage("/mlbb/shop/purchase.png");
			
			hero1 = Image.createImage("/mlbb/heroes/edith2.png");
			hero2 = Image.createImage("/mlbb/heroes/phoveus.png");
			hero3 = Image.createImage("/mlbb/heroes/floryn.png");
			
			banner = new Image[bannerList.length];
			for(int i=0; i<bannerList.length; i++) {
				banner[i] = Image.createImage(bannerList[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {

	}

	public void update(long dt) {
		switch(page) {
			case 0:
				bannerCounter += dt;
				if (bannerCounter >= bannerDuration) {
					bannerCounter = 0;
					banerState++;
					if (banerState >  banner.length - 1) banerState = 0;
					
				}
				break;
		}
	}

	public void render(Graphics g) {
		switch(page) {
			case 0: page0(g); break;
		}
	}

	private void page0(Graphics g) {
		g.drawImage(banner[banerState], 240/2+data[0], 57, Graphics.HCENTER | Graphics.TOP);
		
		g.drawImage(hero1, 5+data[0], 123, Graphics.LEFT | Graphics.TOP);
		g.drawImage(hero2, 84+data[0], 123, Graphics.LEFT | Graphics.TOP);
		g.drawImage(hero3, 162+data[0], 123, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(pricetag, 5+data[0], 208, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 84+data[0], 208, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 162+data[0], 208, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(purchase, 5+data[0], 231, Graphics.LEFT | Graphics.TOP);
		g.drawImage(purchase, 84+data[0], 231, Graphics.LEFT | Graphics.TOP);
		g.drawImage(purchase, 162+data[0], 231, Graphics.LEFT | Graphics.TOP);
	}

	public void destroy() {

	}

}
