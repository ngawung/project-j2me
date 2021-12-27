package com.mlbb.scene;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.melody.core.Scene;

public class MainMenu extends Scene {
	
	private Image bg;
	private Image menu;

	public MainMenu() {
		try {
			bg = Image.createImage("/mlbb/mainmenu/bg_final_op.jpg");
			menu = Image.createImage("/mlbb/mainmenu/menu-frame.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		requestRender();
	}

	public void update(long dt) {

	}

	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, Graphics.LEFT | Graphics.TOP);
		g.drawImage(menu, 20, 100, Graphics.LEFT | Graphics.TOP);
	}

	public void destroy() {

	}

}
