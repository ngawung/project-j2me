package com.hotsprings.scene.demo;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.hotsprings.scene.MenuSelector;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.display.Mimage;
import com.melody.display.Movieclip;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;

public class MovieclipDemo extends Scene {
	
	private Image buffer;
	private Mimage bg_img;
	
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	
	private int frameData[] = new int[] {
			0,0,    64,0,   128,0,
			192,0,  256,0,  320,0,
	};
	
	private Movieclip[] movList = new Movieclip[3];
	private int timePassed = 0;
	
	public MovieclipDemo() {
		try {
			buffer = Image.createImage("/demo/img/character.png");
			bg_img = new Mimage(Image.createImage("/demo/img/bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		for (int i=0; i<movList.length; i++) {
			movList[i] = new Movieclip(buffer, 64, 64, frameData);
			movList[i].x = 20 + 32 * i;
			movList[i].y = 35 + (70 * i);
			movList[i].play(100, new int[]{0, 1, 2, 3, 4, 5}, true);
		}
		
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
		
		requestRender();
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MovieclipDemo());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new MovieclipDemo());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
		for (int i=0; i<movList.length; i++) {
			movList[i].update(dt);
		}
		
		if (timePassed  >= 1000) {
			
			for (int i=0; i<movList.length; i++) {
				movList[i].transform += 1;
				if (movList[i].transform > 7) movList[i].transform = 0;
			}
			
			timePassed = 0;
			
			requestRender();
		}
		
		timePassed += dt;

	}

	public void render(Graphics g) {
		bg_img.render(g);
		
		for (int i=0; i<movList.length; i++) {
			movList[i].render(g);
		}
		
		restart.render(g);
		back.render(g);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
