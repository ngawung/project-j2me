package mlbb.scene.intro;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.Scene;

public class SplashMLBB extends Scene {
	
	private Image logo1;
	private Image logo2;
	private int overlay [];
	private int alpha = 255;
	
	private int x_src;
	private int maskWidth = 30;
	private int speed = 10;
	
	private int x_src2;
	private int maskWidth2;
				
	public SplashMLBB() {
		try {
			logo1 = Image.createImage("/mlbb/intro/mlbb_logo.jpg");
			logo2 = Image.createImage("/mlbb/intro/mlbb_logo2.jpg");
			overlay = new int[logo2.getWidth() * logo2.getHeight()];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		x_src = -maskWidth;
	}
	
	private void fixCoord() {
		x_src2 = x_src;
		maskWidth2 = maskWidth;
		
		if (x_src < 0) {
			x_src2 = 0;
			maskWidth2 = maskWidth + x_src;
			if (maskWidth2 < 0) maskWidth2 = 0;
		}
		
		if (x_src > logo2.getWidth() - maskWidth) {
			maskWidth2 = maskWidth - (x_src - (logo2.getWidth() - maskWidth));
			if (maskWidth2 < 0) maskWidth2 = 0;
			if (x_src > logo2.getWidth()) x_src2 = 0;
		}
	}

	public void update(long dt) {
		if (alpha > 0) {
			for (int i=0; i<overlay.length; i++) overlay[i] = alpha << 24;
			alpha -= 80;
			if (alpha < 0) alpha = 0;
		}
		
		if (x_src < logo2.getWidth())  {
			x_src += speed;
			fixCoord();
			requestRender();
		} else {
			try {
				Thread.sleep(300);
				_e.get_gameRoot().set_scene(new LoadingScreen());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(0x0);
		g.fillRect(0, 0, 240, 320);
		
		g.drawImage(logo1, 10, 125, Graphics.TOP | Graphics.LEFT);
		g.drawRegion(logo2, x_src2, 0, maskWidth2, 72, Sprite.TRANS_NONE, x_src, 124, Graphics.LEFT | Graphics.TOP);
		
		g.drawRGB(overlay, 0, logo2.getWidth(), 5, 124, 230, 72, true);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
