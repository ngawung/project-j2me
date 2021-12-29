package mlbb.display.mainmenu;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.display.Mobject;
import melody.enums.BMFAlign;
import mlbb.display.Font;

public class MenuList extends Mobject {
	public int x = 0;
	public int y = 0;
	
	private Image menuFrame;
	
	private Image preparation;
	private Image heroes;
	private Image achievement;
	private Image inventory;

	public MenuList() {
		try {
			menuFrame = Image.createImage("/mlbb/mainmenu/info/menu_frame2.png");
			
			preparation = Image.createImage("/mlbb/mainmenu/info/preparation.png");
			heroes = Image.createImage("/mlbb/mainmenu/info/heroes.png");
			achievement = Image.createImage("/mlbb/mainmenu/info/achievement.png");
			inventory = Image.createImage("/mlbb/mainmenu/info/inventory.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {

	}

	public void update(long dt) {

	}

	public void render(Graphics g) {
		g.drawImage(menuFrame, x, 61 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(menuFrame, x, 116 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(menuFrame, x, 170 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(menuFrame, x, 225 + y, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(preparation, 8 + x, 70 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(heroes, 9 + x, 125 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(achievement, 6 + x, 179 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(inventory, 7 + x, 234 + y, Graphics.LEFT | Graphics.TOP);
		
		Font.font.render("Preparation", 16 + x, 95 + y, BMFAlign.CENTER, g);
		Font.font.render("Heroes", 16 + x, 150 + y, BMFAlign.CENTER, g);
		Font.font.render("Achievment", 16 + x, 204 + y, BMFAlign.CENTER, g);
		Font.font.render("Inventory", 16 + x, 259 + y, BMFAlign.CENTER, g);
	}

	public void destroy() {

	}

}
