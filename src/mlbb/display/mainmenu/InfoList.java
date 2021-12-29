package mlbb.display.mainmenu;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.display.Mobject;
import melody.enums.BMFAlign;
import mlbb.display.Font;

public class InfoList extends Mobject {
	
	public int x = 0;
	public int y = 0;
	
	private Image menuFrame1;
	private Image menuFrame2;
	
	private Image shop;
	private Image event;
	private Image starlight;
	private Image task;

	public InfoList() {
		try {
			menuFrame1 = Image.createImage("/mlbb/mainmenu/info/menu_frame2.png");
			menuFrame2 = Image.createImage("/mlbb/mainmenu/info/menu_frame3.png");
			
			shop = Image.createImage("/mlbb/mainmenu/info/shop.png");
			event = Image.createImage("/mlbb/mainmenu/info/event.png");
			starlight = Image.createImage("/mlbb/mainmenu/info/starlight.png");
			task = Image.createImage("/mlbb/mainmenu/info/task.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		
	}

	public void update(long dt) {

	}

	public void render(Graphics g) {
		g.drawImage(menuFrame2, x, 61 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(menuFrame1, x, 116 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(menuFrame1, x, 170 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(menuFrame1, x, 225 + y, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(shop, -2 + x, 62 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(event, -1 + x, 117 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(starlight, -5 + x, 171 + y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(task, -5 + x, 226 + y, Graphics.LEFT | Graphics.TOP);
		
		Font.font.render("Shop", 16 + x, 95 + y, BMFAlign.CENTER, g);
		Font.font.render("Event", 16 + x, 150 + y, BMFAlign.CENTER, g);
		Font.font.render("Starlight", 16 + x, 204 + y, BMFAlign.CENTER, g);
		Font.font.render("Task", 16 + x, 259 + y, BMFAlign.CENTER, g);
	}

	public void destroy() {

	}

}
