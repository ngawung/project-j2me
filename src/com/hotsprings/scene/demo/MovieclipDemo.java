package com.hotsprings.scene.demo;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.melody.core.Scene;
import com.melody.display.Movieclip;

public class MovieclipDemo extends Scene {
	
	private Image buffer;
	private int frameData[] = new int[] {
			0,0,   16,0,   32,0,
			0,16,  16,16,  32,16,
			0,32,  16,32,  32,32,
			0,48,  16,48,  32,48,
	};
	
	private Movieclip[] movList = new Movieclip[4];
	
	public MovieclipDemo() {
		try {
			buffer = Image.createImage("/demo/img/character.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		for (int i=0; i<movList.length; i++) {
			movList[i] = new Movieclip(buffer, 16, 16, frameData);
			movList[i].x = 20;
			movList[i].y = 20 + (30 * i);
			movList[i].play(100, new int[]{ i*3, i*3+1, i*3+2  }, true);
		}
	}

	public void update(long dt) {
		for (int i=0; i<movList.length; i++) {
			movList[i].update(dt);
		}

	}

	public void render(Graphics g) {
		for (int i=0; i<movList.length; i++) {
			movList[i].render(g);
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
