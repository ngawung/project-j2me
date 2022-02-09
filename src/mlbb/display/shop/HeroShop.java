package mlbb.display.shop;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.MainEngine;
import melody.display.Mobject;
import melody.enums.BMFAlign;
import melody.enums.KeyCode;
import mlbb.display.Font;

public class HeroShop extends Mobject {
	
	private char listState = 0;
	private final char listStateMax = 7;
	private char page = 0;
	private char pageMax = 0;
	
	private Image hero1;
	private Image hero2;
	private Image hero3;
	
	private int price1;
	private int price2;
	private int price3;
	
	private Image pricetag;
	private Image purchase;
	
	private Image bullet1;
	private Image bullet2;
	
	private Image bp;
	private Image arrow;
	
	private Image sort;
	
	private String[] list_name = {
			"All Heroes",
			"Tank",
			"Fighter",
			"Assasin",
			"Mage",
			"Marksman",
			"Support",
	};
	
	private HeroData[] list_all = {
			new HeroData("miya", 10800),
			new HeroData("balmond", 6500),
			new HeroData("saber", 6500),
			new HeroData("nana", 6500),
			new HeroData("tigreal", 6500),
			new HeroData("eudora", 2000),
			new HeroData("zilong", 15000),
			new HeroData("layla", 2000),
	};
	
	private HeroData[] list_tank = {
			new HeroData("tigreal", 6500),
	};
	
	private HeroData[] list_fighter = {
			new HeroData("balmond", 6500),
			new HeroData("zilong", 15000),
	};
	
	private HeroData[] list_assasin = {
			new HeroData("saber", 6500),
			new HeroData("zilong", 15000),
	};
	
	private HeroData[] list_mage = {
			new HeroData("nana", 6500),
			new HeroData("eudora", 2000),
	};
	
	private HeroData[] list_marksman = {
			new HeroData("miya", 10800),
			new HeroData("layla", 2000),
	};
	
	private HeroData[] list_support = {
			new HeroData("nana", 6500),
			new HeroData("tigreal", 6500),
	};

	public HeroShop() {
		try {
			bullet1 = Image.createImage("/mlbb/shop/bullet1.png");
			bullet2 = Image.createImage("/mlbb/shop/bullet2.png");
			arrow = Image.createImage("/mlbb/mainmenu/arrow2.png");
			
			bp = Image.createImage("/mlbb/mainmenu/bp.png");
			pricetag = Image.createImage("/mlbb/shop/pricetag.png");
			purchase = Image.createImage("/mlbb/shop/purchase.png");
			sort = Image.createImage("/mlbb/heroes/filter.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateItem();
	}
	
	private void updateItem() {
		System.out.println("update item");
		try {
			HeroData[] temp_list;
			switch(listState) {
				case 0: temp_list = list_all; break;
				case 1: temp_list = list_tank; break;
				case 2: temp_list = list_fighter; break;
				case 3: temp_list = list_assasin; break;
				case 4: temp_list = list_mage; break;
				case 5: temp_list = list_marksman; break;
				case 6: temp_list = list_support; break;
				default: temp_list = list_all; break;
			}
			
			pageMax = (char)Math.ceil((float)temp_list.length / 3);
			
			if ((page*3) < temp_list.length) {
				hero1 = Image.createImage("/mlbb/heroes/" + temp_list[(page*3)].name + ".jpg");
				price1 = list_all[(page*3)].price;
			}
			else hero1 = null;
			
			if ((page*3)+1 < temp_list.length) {
				hero2 = Image.createImage("/mlbb/heroes/" + temp_list[(page*3)+1].name + ".jpg");
				price2 = list_all[(page*3)+1].price;
			}
			else hero2 = null;
			
			if ((page*3)+2 < temp_list.length) {
				hero3 = Image.createImage("/mlbb/heroes/" + temp_list[(page*3)+2].name + ".jpg");
				price3 = list_all[(page*3)+2].price;
			}
			else hero3 = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {

	}

	public void update(long dt) {
		if (MainEngine.get_instance().get_input().isDown2(new int[]{KeyCode.KEY_3, KeyCode.KEY_9})) nextPage();
		if (MainEngine.get_instance().get_input().isDown2(new int[]{KeyCode.KEY_1, KeyCode.KEY_7})) prevPage();
	}
	
	private void nextPage() {
		page++;
		if (page > pageMax-1) page = (char)(pageMax-1);
		updateItem();
	}
	
	public void prevPage() {
		if (page == 0) return;
		page--;
		updateItem();
	}
	
	private void nextState() {
		page = 0; 
		listState++;
		if (listState > listStateMax-1) listState = (char)(listStateMax-1);
		updateItem();
	}
	
	public void prevState() {
		page = 0;
		if (listState == 0) return;
		listState--;
		updateItem();
	}
	

	public void render(Graphics g) {
		
		if (hero1 != null) {
			g.drawImage(hero1, 5+data[0], 115, Graphics.LEFT | Graphics.TOP);
			g.drawImage(pricetag, 5+data[0], 200, Graphics.LEFT | Graphics.TOP);
			g.drawImage(purchase, 5+data[0], 222, Graphics.LEFT | Graphics.TOP);
			g.drawImage(bp, 5+5+data[0], 200+2, Graphics.LEFT | Graphics.TOP);
			Font.font.render(""+price1, 5+16+27+data[0], 200+5, BMFAlign.CENTER, g);
			Font.font.render("Purchase", 5+36+data[0], 222+5, BMFAlign.CENTER, g);
		}
		
		if (hero2 != null) {
			g.drawImage(hero2, 84+data[0], 115, Graphics.LEFT | Graphics.TOP);
			g.drawImage(pricetag, 84+data[0], 200, Graphics.LEFT | Graphics.TOP);
			g.drawImage(purchase, 84+data[0], 222, Graphics.LEFT | Graphics.TOP);
			g.drawImage(bp, 84+5+data[0], 200+2, Graphics.LEFT | Graphics.TOP);
			Font.font.render(""+price2, 84+16+27+data[0], 200+5, BMFAlign.CENTER, g);
			Font.font.render("Purchase", 84+36+data[0], 222+5, BMFAlign.CENTER, g);
		}
		
		if (hero3 != null) {
			g.drawImage(hero3, 162+data[0], 115, Graphics.LEFT | Graphics.TOP);
			g.drawImage(pricetag, 162+data[0], 200, Graphics.LEFT | Graphics.TOP);
			g.drawImage(purchase, 162+data[0], 222, Graphics.LEFT | Graphics.TOP);
			g.drawImage(bp, 162+5+data[0], 200+2, Graphics.LEFT | Graphics.TOP);
			Font.font.render(""+price3, 162+16+27+data[0], 200+5, BMFAlign.CENTER, g);
			Font.font.render("Purchase", 162+36+data[0], 222+5, BMFAlign.CENTER, g);
		}
		
		for (char i=0; i<pageMax; i++) {
			if (page == i) g.drawImage(bullet2, (240/2 - (20*pageMax)/2) + data[0] + 20*i, 266, Graphics.LEFT | Graphics.TOP);
			else g.drawImage(bullet1, (240/2 - (20*pageMax)/2) + data[0] + 20*i, 266, Graphics.LEFT | Graphics.TOP);
		}
		
		Font.font2.render(list_name[listState], 240/2 +data[0], 91, BMFAlign.CENTER, g);
		if (listState != 0) g.drawImage(arrow,
				62 + (int)(Math.abs(Math.sin(System.currentTimeMillis() * 0.008) * 4)*-1) +data[0],
				86,
				Graphics.LEFT | Graphics.TOP);
		if (listState != listStateMax-1) g.drawRegion(arrow,
				0, 0,
				arrow.getWidth(), arrow.getHeight(),
				Sprite.TRANS_MIRROR,
				167 + (int)(Math.abs(Math.sin(System.currentTimeMillis() * 0.008) * 4)) +data[0],
				86,
				Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(sort, 8+data[0], 57, Graphics.LEFT | Graphics.TOP);
		Font.font.render("Sort By", 8+36 +data[0], 57+2, BMFAlign.CENTER, g);
	}

	public void destroy() {

	}

}
