package mlbb.display.shop;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.core.MainEngine;
import melody.display.Mobject;
import melody.enums.BMFAlign;
import melody.enums.KeyCode;
import mlbb.display.Font;

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
	
	private Image bp;
	private Image diamond;
	
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
			
			bp = Image.createImage("/mlbb/mainmenu/bp.png");
			diamond = Image.createImage("/mlbb/mainmenu/diamond.png");
			
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
		if (MainEngine.get_instance().get_input().isDown2(new int[]{KeyCode.KEY_3, KeyCode.KEY_9})) nextPage();
		else if (MainEngine.get_instance().get_input().isDown2(new int[]{KeyCode.KEY_1, KeyCode.KEY_7})) prevPage();
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
		
		g.drawImage(diamond, 5+5+data[0], 208+2, Graphics.LEFT | Graphics.TOP);
		g.drawImage(bp, 84+5+data[0], 208+2, Graphics.LEFT | Graphics.TOP);
		g.drawImage(bp, 162+5+data[0], 208+2, Graphics.LEFT | Graphics.TOP);
		
		Font.font.render("51", 5+16+27+data[0], 208+5, BMFAlign.CENTER, g);
		Font.font.render("32000", 84+16+27+data[0], 208+5, BMFAlign.CENTER, g);
		Font.font.render("32000", 162+16+27+data[0], 208+5, BMFAlign.CENTER, g);
		
		Font.font.render("Purchase", 5+36+data[0], 231+5, BMFAlign.CENTER, g);
		Font.font.render("Purchase", 84+36+data[0], 231+5, BMFAlign.CENTER, g);
		Font.font.render("Purchase", 162+36+data[0], 231+5, BMFAlign.CENTER, g);
	}
	
	private void page1(Graphics g) {
		g.drawImage(itemFrame, 5+data[0], 57, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 84+data[0], 57, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 162+data[0], 57, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 5+data[0], 156, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 84+data[0], 156, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 162+data[0], 156, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(pricetag, 5+data[0], 130, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 84+data[0], 130, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 162+data[0], 130, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 5+data[0], 229, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 84+data[0], 229, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 162+data[0], 229, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(item[0], 5+36+data[0], 57+36, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[1], 84+36+data[0], 57+36, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[2], 162+36+data[0], 57+36, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[3], 5+36+data[0], 156+36, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[4], 84+36+data[0], 156+36, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[5], 162+36+data[0], 156+36, Graphics.HCENTER | Graphics.VCENTER);
		
		g.drawImage(diamond, 84+5+data[0], 130+2, Graphics.LEFT | Graphics.TOP);
		g.drawImage(diamond, 162+5+data[0], 130+2, Graphics.LEFT | Graphics.TOP);
		g.drawImage(diamond, 5+5+data[0], 229+2, Graphics.LEFT | Graphics.TOP);
		g.drawImage(diamond, 84+5+data[0], 229+2, Graphics.LEFT | Graphics.TOP);
		g.drawImage(diamond, 162+5+data[0], 229+2, Graphics.LEFT | Graphics.TOP);
		
		Font.font.render("Free", 5+36+data[0], 130+5, BMFAlign.CENTER, g);
		Font.font.render("10", 84+16+27+data[0], 130+5, BMFAlign.CENTER, g);
		Font.font.render("149", 162+16+27+data[0], 130+5, BMFAlign.CENTER, g);
		Font.font.render("20", 5+16+27+data[0], 229+5, BMFAlign.CENTER, g);
		Font.font.render("10", 84+16+27+data[0], 229+5, BMFAlign.CENTER, g);
		Font.font.render("55", 162+16+27+data[0], 229+5, BMFAlign.CENTER, g);
	}
	
	private void page2(Graphics g) {
		g.drawImage(itemFrame, 5+data[0], 57, Graphics.LEFT | Graphics.TOP);
		g.drawImage(itemFrame, 84+data[0], 57, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(pricetag, 5+data[0], 130, Graphics.LEFT | Graphics.TOP);
		g.drawImage(pricetag, 84+data[0], 130, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(item[6], 5+36+data[0], 57+36, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(item[7], 84+36+data[0], 57+36, Graphics.HCENTER | Graphics.VCENTER);
		
		g.drawImage(diamond, 5+5+data[0], 130+2, Graphics.LEFT | Graphics.TOP);
		g.drawImage(diamond, 84+5+data[0], 130+2, Graphics.LEFT | Graphics.TOP);
		
		Font.font.render("238", 5+16+27+data[0], 130+5, BMFAlign.CENTER, g);
		Font.font.render("268", 84+16+27+data[0], 130+5, BMFAlign.CENTER, g);
	}

	public void destroy() {

	}

}
