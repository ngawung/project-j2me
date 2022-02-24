package mlbb.scene.intro;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.Scene;
import penner.easing.Linear;

public class SplashMLBB extends Scene {
	
	private Image logo1;
	private Image logo2;
	private Image logo3;
	private int[] buffer;
	private int alpha = 255;
	private int newAlpha = 0;
	
	private int x_src;
	private int maskWidth = 30;
	private int speed = 10;
	
	private int x_src2;
	private int x_src3;
	private int maskWidth2;
	
	private long startTime;
	private int duration = 1800;
	private int duration2 = 1000;
	private boolean sleepNextFrame = false;
	private boolean isDone = false;
				
	public SplashMLBB() {
		try {
			logo1 = Image.createImage("/mlbb/intro/mlbb_logo.jpg");
			logo2 = Image.createImage("/mlbb/intro/mlbb_logo2.jpg");
			logo3 = Image.createImage(logo2.getWidth(), logo2.getHeight());
			buffer = new int[logo2.getWidth() * logo2.getHeight()];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		x_src = -maskWidth;
		startTime = System.currentTimeMillis();
		
		set_backgroundColor(0);
	}

	public void update(long dt) {
		if (sleepNextFrame) {
			try {
				Thread.sleep((int)(duration * 0.6f));
				_e.get_gameRoot().set_scene(new LoadingScreen());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (System.currentTimeMillis() - startTime < duration) {
			x_src2 = (int)Linear.easeInOut(System.currentTimeMillis() - startTime, x_src, logo2.getWidth() - x_src, duration);
		} else if(System.currentTimeMillis() - startTime > duration) {
			x_src2 = logo2.getWidth();
			sleepNextFrame = true;
		}
		
		// fix cords
		if (x_src2 < 0) x_src3 = 0;
		else x_src3 = x_src2;
		
		maskWidth2 = x_src2 + maskWidth;
		if (maskWidth2 < 0) maskWidth2 = 0;
		else if (x_src2 < 0) maskWidth2 = x_src2 + maskWidth;
		else if (maskWidth2 > logo2.getWidth()) maskWidth2 = logo2.getWidth() - x_src2;
		else maskWidth2 = maskWidth2 - x_src2;
		
		// overlay
		if (System.currentTimeMillis() - startTime < duration2) {
			newAlpha = (int)Linear.easeInOut(System.currentTimeMillis() - startTime, alpha, 0 - alpha, duration2);
		} else if(System.currentTimeMillis() - startTime > duration2) {
			newAlpha = 0;
		}
		
		for (int i = 0; i <buffer.length; i ++) {
				buffer[i] = (newAlpha << 24);
		}
		
		updateLogo();
		requestRender();
	}

	private void updateLogo() {
		Graphics gp = logo3.getGraphics();
		
		gp.setColor(0);
		gp.fillRect(0, 0, logo3.getWidth(), logo3.getHeight());
		
		gp.drawImage(logo1, logo3.getWidth()/2, logo3.getHeight()/2, Graphics.VCENTER | Graphics.HCENTER);
		gp.drawRegion(logo2, x_src3, 0, maskWidth2, logo2.getHeight(), Sprite.TRANS_NONE, x_src3, 0, Graphics.LEFT | Graphics.TOP);
		
		Image temp = Image.createRGBImage(buffer, logo2.getWidth(), logo2.getHeight(), true);
		gp.drawImage(temp, 0, 0, Graphics.LEFT | Graphics.TOP);
	}

	public void render(Graphics g) {
		g.drawImage(logo3, get_width()/2, get_height()/2, Graphics.VCENTER | Graphics.HCENTER);
//		g.drawRegion(logo2, x_src2, 0, maskWidth2, 72, Sprite.TRANS_NONE, x_src, 124, Graphics.LEFT | Graphics.TOP);
		
//		g.drawRGB(overlay, 0, logo2.getWidth(), 5, 124, 230, 72, true);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
