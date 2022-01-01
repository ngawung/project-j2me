package mlbb.display.shop;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.core.MainEngine;
import melody.display.Mobject;
import melody.enums.KeyCodeEnum;

public class Recommended extends Mobject {
	private Image[] banner;
	private char banerState = 0;
	private int bannerCounter = 0;
	private final int bannerDuration = 3000;
	
	private Image hero1;
	private Image hero2;
	private Image hero3;
	
	private Image itemFrame;
	private Image[] item;
	
	private Image pricetag;
	private Image purchase;
	
	private Image bullet1;
	private Image bullet2;
	
	private char page = 0;
	private final char pageMax = 3;

	public Recommended() {
		data = new int[2];
		data[0] = 0;
		data[1] = 1;
		
		String[] bannerList = {
			"/mlbb/shop/banner1.png",
			"/mlbb/shop/banner2.png",
			"/mlbb/shop/banner3.png"
		};
		
		String[] itemList = {
				"/mlbb/shop/recommended/1.jpg",
				"/mlbb/shop/recommended/2.jpg",
				"/mlbb/shop/recommended/3.jpg",
				"/mlbb/shop/recommended/4.jpg",
				"/mlbb/shop/recommended/4.jpg",
				"/mlbb/shop/recommended/4.jpg",
				
				"/mlbb/shop/recommended/4.jpg",
				"/mlbb/shop/recommended/4.jpg",
			};
		
		try {
			pricetag = Image.createImage("/mlbb/shop/pricetag.png");
			purchase = Image.createImage("/mlbb/shop/purchase.png");
			
			hero1 = Image.createImage("/mlbb/heroes/edith2.png");
			hero2 = Image.createImage("/mlbb/heroes/phoveus.png");
			hero3 = Image.createImage("/mlbb/heroes/floryn.png");
			
			itemFrame = Image.createImage("/mlbb/shop/item_frame.png");
			bullet1 = Image.createImage("/mlbb/shop/bullet1.png");
			bullet2 = Image.createImage("/mlbb/shop/bullet2.png");
			
			banner = new Image[bannerList.length];
			for(int i=0; i<bannerList.length; i++) {
				banner[i] = Image.createImage(bannerList[i]);
			}
			
			item = new Image[itemList.length];
			for (int i=0; i<itemList.length; i++) {
				item[i] = Image.createImage(itemList[i]);
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
		
		updateInput();
	}

	private void updateInput() {
		if (data[1] == 1) {
			
			if (MainEngine.get_instance().get_input().isDown(KeyCodeEnum.KEY_3)) nextPage();
			else if (MainEngine.get_instance().get_input().isDown(KeyCodeEnum.KEY_1)) prevPage();
			
		}
	}

	public void render(Graphics g) {
		switch(page) {
			case 0: page0(g); break;
			case 1: page1(g); break;
			case 2: page2(g); break;
		}
		
		for (char i=0; i<pageMax; i++) {
			if (page == i) g.drawImage(bullet2, (240/2 - (20*pageMax)/2) + data[0] + 20*i, 266, Graphics.LEFT | Graphics.TOP);
			else g.drawImage(bullet1, (240/2 - (20*pageMax)/2) + data[0] + 20*i, 266, Graphics.LEFT | Graphics.TOP);
		}
	}
	
	public void nextPage() {
		page++;
		if (page > pageMax-1) page = pageMax-1;
	}
	
	public void prevPage() {
		if (page == 0) return;
		page--;
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
	
	private void page1(Graphics g) {
		g.drawImage(itemFrame, 11, 57, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 86, 57, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 162, 57, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 11, 156, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 86, 156, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 162, 156, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(pricetag, 11, 131, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 86, 131, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 162, 131, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 11, 230, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 86, 230, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 162, 230, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(item[0], 11+34, 57+37, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[1], 86+34, 57+37, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[2], 162+34, 57+37, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[3], 11+34, 156+37, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[4], 86+34, 156+37, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[5], 162+34, 156+37, Graphics.HCENTER | Graphics.VCENTER);
	}
	
	private void page2(Graphics g) {
		g.drawImage(itemFrame, 11, 57, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 86, 57, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(pricetag, 11, 131, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 86, 131, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(item[6], 11+34, 57+37, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[7], 86+34, 57+37, Graphics.HCENTER | Graphics.VCENTER);
	}

	public void destroy() {

	}

}
