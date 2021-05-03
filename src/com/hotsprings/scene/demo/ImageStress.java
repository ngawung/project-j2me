package com.hotsprings.scene.demo;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.hotsprings.scene.MenuSelector;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.display.Mimage;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;
import com.melody.utils.RandomUtils;

public class ImageStress extends Scene {
	
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	private MText total = new MText("0", 0xFF0000);
	
	private Vector imageList = new Vector();
	private Image melody;
	
	private int timePassed = 0;

	public ImageStress() {
		try {
			melody = Image.createImage("/demo/img/melody.png");
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
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new ImageStress());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new ImageStress());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
		if (timePassed >= 200) {
			Mimage img = new Mimage(melody);
			img.data = new int[] {
				RandomUtils.range(360, 6322),
				RandomUtils.range(80, 267) + 80,
				RandomUtils.range(10, 267),
			};
			imageList.addElement(img);
			
			
			
			timePassed = 0;
		}
		
		for (int i=0; i<imageList.size(); i++) {
			Mimage temp = (Mimage) (imageList.elementAt(i));
			temp.x = (get_width() / 2) - 44 + ((float)Math.cos(temp.data[0] * (Math.PI / 180)) * temp.data[1]) * (float)Math.sin(System.currentTimeMillis() * (0.0001 * temp.data[2]));
			temp.y = (get_height() / 2) - 50 + ((float)Math.sin(temp.data[0] * (Math.PI / 180)) * temp.data[1]) * (float)Math.sin(System.currentTimeMillis() * (0.0001 * temp.data[2]));
		}
		
		total.set_text("" + imageList.size());
		total.x = get_width() / 2 - total.get_width() / 2;
		total.y = get_height() / 2 - total.get_height() / 2;
		
		timePassed += dt;
		requestRender();
	}
	
	public void render(Graphics g) {
		for (int i=0; i<imageList.size(); i++) {
			( (Mimage) (imageList.elementAt(i)) ).render(g);
		}
		total.render(g);
		restart.render(g);
		back.render(g);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
