package com.hotsprings.scene.demo;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.hotsprings.scene.MenuSelector;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.display.Movieclip;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;
import com.melody.utils.RandomUtils;

public class MovieclipStress extends Scene {
	
	private Image buffer;
	private int frameData[] = new int[] {
			0,0,    64,0,   128,0,
			192,0,  256,0,  320,0,
	};
	
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	private MText total = new MText("0", 0xFF0000);
	
	private Vector movList = new Vector();
	private int timePassed = 0;

	public MovieclipStress() {
		try {
			buffer = Image.createImage("/demo/img/character.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MovieclipStress());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new MovieclipStress());
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
		if (timePassed >= 200) {
			Movieclip mov = new Movieclip(buffer, 64, 64, frameData);
			mov.play(RandomUtils.range(100, 694) + 100, new int[]{0, 1, 2, 3, 4, 5}, true);
			mov.data = new int[] {
				RandomUtils.range(360, 6322),
				RandomUtils.range(80, 1285) + 80,
				RandomUtils.range(10, 267),
				
			};
			movList.addElement(mov);
			timePassed = 0;
		}
		
		for (int i=0; i<movList.size(); i++) {
			Movieclip temp = (Movieclip) (movList.elementAt(i));
			temp.x = (get_width() / 2) - 32 + ((float)Math.cos(temp.data[0] * (Math.PI / 180)) * temp.data[1]) * (float)Math.sin(System.currentTimeMillis() * (0.0001 * temp.data[2]));
			temp.y = (get_height() / 2) - 32 + ((float)Math.sin(temp.data[0] * (Math.PI / 180)) * temp.data[1]) * (float)Math.sin(System.currentTimeMillis() * (0.0001 * temp.data[2]));
			temp.update(dt);
		}
		
		total.set_text("" + movList.size());
		total.x = get_width() / 2 - total.get_width() / 2;
		total.y = get_height() / 2 - total.get_height() / 2;
		
		timePassed += dt;
		requestRender();
	}

	public void render(Graphics g) {
		for (int i=0; i<movList.size(); i++) {
			((Movieclip)movList.elementAt(i)).render(g);
		}
		total.render(g);
		restart.render(g);
		back.render(g);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
