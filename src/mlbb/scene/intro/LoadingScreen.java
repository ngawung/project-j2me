package mlbb.scene.intro;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.Scene;
import melody.enums.BMFAlign;
import mlbb.display.Font;
import mlbb.scene.MainMenu;

public class LoadingScreen extends Scene {
	
	private Image bg;
	private Image arrow;
	private Image logo;
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
			buffer = Image.createImage(get_width(), get_height());
			bg = Image.createImage("/mlbb/intro/loading/bg_crop.jpg");
			logo = Image.createImage("/mlbb/intro/loading/logo.png");
			arrow = Image.createImage("/mlbb/intro/loading/arrow.png");
			check1 = Image.createImage("/mlbb/intro/loading/check1.png");
			check2 = Image.createImage("/mlbb/intro/loading/check2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		Graphics g = buffer.getGraphics();
		g.setColor(0);
		g.fillRect(0, 0, get_width(), get_height());
		
		int visibleX = get_width() / bg.getWidth();
		int visibleY = get_height() / bg.getHeight();
		int overflowX = 0;
		int overflowY = 0;
		
		boolean isFlipX = true;
		boolean isFlipY = true;
		
		if (visibleX != 0) overflowX = get_width()/2 - bg.getWidth()/2;
		if (visibleY != 0) overflowY = get_height()/2 - bg.getHeight()/2;
		
		if (visibleX > 0 && visibleY > 0) {
			isFlipX = false;
			
			for (int y=0; y<visibleY+2; y++) {
				for (int x=0; x<visibleX+2; x++) {
					if (!isFlipX && !isFlipY) g.drawImage(bg, bg.getWidth() * (x-1) + overflowX, bg.getHeight() * (y-1) + overflowY, Graphics.LEFT | Graphics.TOP);
					else if (isFlipX && isFlipY) g.drawRegion(bg, 0, 0, bg.getWidth(), bg.getHeight(), Sprite.TRANS_MIRROR_ROT180, bg.getWidth() * (x-1) + overflowX, bg.getHeight() * (y-1) + overflowY, Graphics.LEFT | Graphics.TOP);
					else if (isFlipX && !isFlipY) g.drawRegion(bg, 0, 0, bg.getWidth(), bg.getHeight(), Sprite.TRANS_MIRROR, bg.getWidth() * (x-1) + overflowX, bg.getHeight() * (y-1) + overflowY, Graphics.LEFT | Graphics.TOP);
					else if (!isFlipX && isFlipY) g.drawRegion(bg, 0, 0, bg.getWidth(), bg.getHeight(), Sprite.TRANS_ROT180, bg.getWidth() * (x-1) + overflowX, bg.getHeight() * (y-1) + overflowY, Graphics.LEFT | Graphics.TOP);
					isFlipX = !isFlipX;
				}
				isFlipY = !isFlipY;
			}
		} else if (visibleX > 0) {
			for (int x=0; x<visibleX+2; x++) {
				if (isFlipX) g.drawRegion(bg, 0, 0, bg.getWidth(), bg.getHeight(), Sprite.TRANS_MIRROR, bg.getWidth() * (x-1) + overflowX, 0, Graphics.LEFT | Graphics.TOP);
				else g.drawImage(bg, bg.getWidth() * (x-1) + overflowX, 0, Graphics.LEFT | Graphics.TOP);
				isFlipX = !isFlipX;
			}
		} else if (visibleY > 0) {
			for (int y=0; y<visibleY+2; y++) {
				if (isFlipY) g.drawRegion(bg, 0, 0, bg.getWidth(), bg.getHeight(), Sprite.TRANS_MIRROR_ROT180, get_width()/2, bg.getHeight() * (y-1) + overflowY, Graphics.HCENTER | Graphics.TOP);
				else g.drawImage(bg, get_width()/2, bg.getHeight() * (y-1) + overflowY, Graphics.HCENTER | Graphics.TOP);
				isFlipY = !isFlipY;
			}
		} else {
			g.drawImage(bg, get_width()/2, get_height()/2, Graphics.VCENTER | Graphics.HCENTER);
		}
		
		// stupid way to draw text...
		// gonna fix later
		Font.font.render("This is work", get_width() - 5, 3, BMFAlign.RIGHT, g);
		Font.font.render("of fiction, any", get_width() - 5, 13, BMFAlign.RIGHT, g);
		Font.font.render("similarity are", get_width() - 5, 25, BMFAlign.RIGHT, g);
		Font.font.render("fictional", get_width() - 5, 36, BMFAlign.RIGHT, g);
		Font.font2.render("v1.2.0 - DevBuild", get_width() - 5, 49, BMFAlign.RIGHT, g);
		Font.font.render("I agree ToS", get_width()/2, get_height() - 20, BMFAlign.CENTER, g);
		
		
		g.drawImage(arrow, get_width() - 15, get_height() - 38, Graphics.VCENTER | Graphics.RIGHT);
		g.drawRegion(arrow, 0, 0, arrow.getWidth(), arrow.getHeight(), Sprite.TRANS_MIRROR, 15, get_height() - 38, Graphics.VCENTER | Graphics.LEFT);
		
		g.setColor(0x5E6462);
		g.fillRect(15 + 22, get_height() - 38 - 2, (get_width() - 15 - 25) - (15 + 22), 4);

		g.drawImage(check1, get_width()/2 - Font.font.calculateWidth("I agree ToS")/2 - check1.getWidth() - 8, get_height() - check1.getHeight() - 3, Graphics.TOP | Graphics.LEFT);
		g.drawImage(check2, get_width()/2 - Font.font.calculateWidth("I agree ToS")/2 - check1.getWidth() - 8 + 5, get_height() - check1.getHeight() - 3 + 2, Graphics.TOP | Graphics.LEFT);
		
		g.drawImage(logo, 5, 5, Graphics.LEFT | Graphics.TOP);
		
		
		// save memmory.. i guess
		bg = null;
		arrow = null;
		check1 = null;
		check2 = null;
		
		maxScale = r.nextFloat() * 0.2f + 0.1f;
		
		set_backgroundColor(0);
		requestRender();
	}
	
	public void update(long dt) {
		scale += 0.01f;
		if (scale > 1f) {
			scale = 1f;
			try {
				Thread.sleep(400);
//				_e.get_gameRoot().set_scene(new MainMenu(true));
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
		g.drawImage(buffer, get_width()/2, get_height()/2, Graphics.VCENTER| Graphics.HCENTER);
		
		g.setColor(0xFED267);
		g.fillRect(15 + 22, get_height() - 38 - 2, (int)(((get_width() - 15 - 25) - (15 + 22)) * scale), 4);
		
		index = (int)Math.floor((msg.length - 1) * scale);
		Font.font.render(msg[index], get_width()/2, get_height() - 38 - 18, BMFAlign.CENTER, g);
	}

	public void destroy() {

	}

}
