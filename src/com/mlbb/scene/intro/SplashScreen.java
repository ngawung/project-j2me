package com.mlbb.scene.intro;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import penner.easing.Linear;

import com.melody.core.Scene;

public class SplashScreen extends Scene {
	
	private Image moontoon;
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
			moontoon = Image.createImage("/mlbb/intro/moontoon.gif");
			bg = Image.createImage("/mlbb/intro/moontoon_bg.png");
			argb = new int[moontoon.getWidth() * moontoon.getHeight()];
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		moontoon.getRGB(argb, 0, moontoon.getWidth(), 0, 0, moontoon.getWidth(), moontoon.getHeight());
		startTime = System.currentTimeMillis();
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
		
		requestRender();
	}

	public void render(Graphics g) {
		g.setColor(0x0);
		g.fillRect(0, 0, 240, 160);
		g.drawImage(bg, 0, 160, Graphics.TOP | Graphics.LEFT);
		
		if (!isDone) g.drawRGB(argb, 0, moontoon.getWidth(), 0, 40, moontoon.getWidth(), moontoon.getHeight(), true);
	}

	public void destroy() {

	}

}
