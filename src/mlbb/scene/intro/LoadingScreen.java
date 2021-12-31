package mlbb.scene.intro;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.core.Scene;
import mlbb.display.Font;
import mlbb.scene.MainMenu;

public class LoadingScreen extends Scene {
	
	private Image bg;
	private Image bar1;
	private Image bar2;
	private Image check1;
	private Image check2;
	
	private Image buffer;
	private float scale = 0f;
	private float maxScale = 0f;
	private int index;
	
	private String[] msg = {
		"Game Loading...",
		"Game Loading...",
		"Loading(No data):Config",
		"Loading(No data):Atlas Gui",
		"Loading(No data):Atlas Menu",
		"Loading(No data):Atlas Hero",
		"Loading(No data):Atlas Gameplay",
		"Loading(No data):Atlas Extra",
		"Loading(No data):Soundpack",
		"Game Loading...",
		"Game Loading...",
		"Game Loading..."
	};
	
	private Random r = new Random();

	public LoadingScreen() {
		try {
			buffer = Image.createImage(240, 320);
			bg = Image.createImage("/mlbb/intro/loading/bg_crop.png");
			bar1 = Image.createImage("/mlbb/intro/loading/bar1.png");
			bar2 = Image.createImage("/mlbb/intro/loading/bar2.png");
			check1 = Image.createImage("/mlbb/intro/loading/check1.png");
			check2 = Image.createImage("/mlbb/intro/loading/check2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		Graphics g = buffer.getGraphics();
		g.drawImage(bg, 0, 0, Graphics.TOP | Graphics.LEFT);
		
		// stupid way to draw text...
		// gonna fix later
		Font.font.render("This is work", 240 - Font.font.calculateWidth("This is work") - 5, 3, g);
		Font.font.render("of fiction, any", 240 - Font.font.calculateWidth("of fiction, any") - 5, 13, g);
		Font.font.render("similarity are", 240 - Font.font.calculateWidth("similarity are") - 5, 25, g);
		Font.font.render("fictional", 240 - Font.font.calculateWidth("fictional") - 5, 36, g);
		Font.font2.render("v1.1.2 - DevBuild", 240 - Font.font.calculateWidth("v1.1.2 - DevBuild") - 5, 49, g);
		Font.font.render("I agree ToS", 89, 301, g);
		
		g.drawImage(bar1, 5, 280, Graphics.TOP | Graphics.LEFT);
		g.drawImage(bar2, 204, 280, Graphics.TOP | Graphics.LEFT);
		g.drawImage(check1, 50, 292, Graphics.TOP | Graphics.LEFT);
		g.drawImage(check2, 55, 294, Graphics.TOP | Graphics.LEFT);
		g.setColor(0x5E6462);
		g.fillRect(27, 286, 186, 4);
		
		// save memmory.. i guess
		bg = null;
		bar1 = null;
		bar2 = null;
		check1 = null;
		check2 = null;
		
		maxScale = r.nextFloat() * 0.2f + 0.1f;
		
		requestRender();
	}
	
	public void update(long dt) {
		scale += 0.01f;
		if (scale > 1f) {
			scale = 1f;
			try {
				Thread.sleep(400);
				_e.get_gameRoot().set_scene(new MainMenu(true));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
		if (scale > maxScale) {
			try {
				Thread.sleep((int)r.nextFloat() * 500 + 1000);
				maxScale += r.nextFloat() * 0.2f + 0.1f;
				if (maxScale > 1f) maxScale = 1f;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		requestRender();
	}

	public void render(Graphics g) {
		g.drawImage(buffer, 0, 0, Graphics.TOP | Graphics.LEFT);
		
		g.setColor(0xFED267);
		g.fillRect(27, 286, (int)(186 * scale), 4);
		
		index = (int)Math.floor((msg.length - 1) * scale);
		Font.font.render(msg[index], 240 / 2 - Font.font.calculateWidth(msg[index]) / 2, 272, g);
	}

	public void destroy() {

	}

}
