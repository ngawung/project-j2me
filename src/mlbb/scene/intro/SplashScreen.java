package mlbb.scene.intro;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import penner.easing.Linear;

import melody.core.Scene;

public class SplashScreen extends Scene {
	
	private Image moontoon;
	private Image moontoon2;
	private Image bg;
	private int argb [];
	
	private int alpha = 1;
	private int newAlpha = 0;
	private int duration = 1000;
	private boolean isFadeOut = false;
	private boolean sleepNextFrame = false;
	private boolean isDone = false;
	
	private long startTime;

	public SplashScreen() {
		try {
			moontoon = Image.createImage("/mlbb/intro/moontoon.png");
			bg = Image.createImage(get_width(), get_height() / 2);
			argb = new int[moontoon.getWidth() * moontoon.getHeight()];
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		moontoon.getRGB(argb, 0, moontoon.getWidth(), 0, 0, moontoon.getWidth(), moontoon.getHeight());
		prepareBG();
		startTime = System.currentTimeMillis();
		
		set_backgroundColor(0);
	}

	private void prepareBG() {
		Graphics gp = bg.getGraphics();
		
//		gp.setColor(0);
//		gp.fillRect(0, 0, bg.getWidth(), bg.getHeight());
		
		int r = 14;
		int g = 14;
		int b = 41;
		
		for (int i=0; i<bg.getHeight(); i++) {
			float percentage = (float)i / bg.getHeight();
			
			gp.setColor(r - (int)(r*percentage), g - (int)(g*percentage), b - (int)(b*percentage));
			gp.drawLine(0, bg.getHeight() - i - 1, bg.getWidth(), bg.getHeight() - i - 1);
		}
	}

	public void update(long dt) {
		if (sleepNextFrame) {
			try {
				Thread.sleep((int)(duration * 2.2f));
				startTime = System.currentTimeMillis();
				isFadeOut = true;
				sleepNextFrame = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (isDone) {
			try {
				Thread.sleep((int)(duration * 0.6));
				_e.get_gameRoot().set_scene(new SplashMLBB());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// fade in
		if (!isFadeOut && System.currentTimeMillis() - startTime < duration) {
			newAlpha = (int)Linear.easeInOut(System.currentTimeMillis() - startTime, alpha, 255 - alpha, duration);
		} else if(!isFadeOut && System.currentTimeMillis() - startTime > duration) {
			alpha = newAlpha = 255;
			sleepNextFrame = true;
		}
		
		// fade out
		else if (isFadeOut && System.currentTimeMillis() - startTime < duration) {
			newAlpha = (int)Linear.easeInOut(System.currentTimeMillis() - startTime, alpha, 0 - alpha, duration);
		} else if(isFadeOut && System.currentTimeMillis() - startTime > duration) {
			alpha = newAlpha = 0;
			isDone = true;
		}
		
		for (int i = 0; i <argb.length; i ++) {
			if (((argb[i] >> 24)) != 0)
				argb[i] = (newAlpha << 24) | (argb[i] & 0x00FFFFFF);
		}
		
		moontoon2 = Image.createRGBImage(argb, moontoon.getWidth(), moontoon.getHeight(), true);
		
		requestRender();
	}

	public void render(Graphics g) {
		g.drawImage(bg, 0, get_height(), Graphics.BOTTOM | Graphics.LEFT);
		
		if (!isDone) g.drawImage(moontoon2, get_width()/2, get_height()/2, Graphics.VCENTER | Graphics.HCENTER);
	}

	public void destroy() {

	}

}
