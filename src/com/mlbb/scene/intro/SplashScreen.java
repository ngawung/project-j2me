package com.mlbb.scene.intro;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.melody.core.Scene;

public class SplashScreen extends Scene {
	
	private Image moontoon;
	private Image bg;
	private int argb [];
	private int alpha = 1;
	private int alphaUpdate = 50;
	
	private boolean isDone = false;
	private boolean sleepNextFrame = false;
	private boolean updateAlpha = true;
	private boolean isFadeOut = false;
	private int waitTime = 1600;

	public SplashScreen() {
		try {
			moontoon = Image.createImage("/mlbb/intro/moontoon.gif");
			bg = Image.createImage("/mlbb/intro/moontoon_bg.png");
			argb = new int[moontoon.getWidth() * moontoon.getHeight()];
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		moontoon.getRGB(argb, 0, moontoon.getWidth(), 0, 0, moontoon.getWidth(), moontoon.getHeight());
		requestRender();
	}

	public void update(long dt) {
		if (sleepNextFrame) {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sleepNextFrame = false;
		}
		
		if (isDone) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			_e.get_gameRoot().set_scene(new SplashMLBB());
		}
		
		if (updateAlpha) {
			for (int i = 0; i <argb.length; i ++) {
				if (((argb[i] >> 24)) != 0)
					argb[i] = (alpha << 24) | (argb[i] & 0x00FFFFFF);
			}
			if (!isFadeOut) alpha += alphaUpdate;
			else alpha -= alphaUpdate;
			
			if (alpha > 255) alpha = 255;
			if (alpha < 0) alpha = 0;
			
			requestRender();
			System.out.println(alpha);
		}
		
		if (alpha >=255 && !isFadeOut) {
			isFadeOut = true;
			sleepNextFrame = true; // quick hack
			alpha = 255;
		}
		
		if (isFadeOut && alpha <= 0) {
			updateAlpha = false;
			isDone = true;
		}
	}

	public void render(Graphics g) {
		// draw bg
		g.setColor(0x0);
		g.fillRect(0, 0, 240, 160);
		g.drawImage(bg, 0, 160, Graphics.TOP | Graphics.LEFT);
		
		// draw logo
		if (!isDone) g.drawRGB(argb, 0, moontoon.getWidth(), 0, 40, moontoon.getWidth(), moontoon.getHeight(), true);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
