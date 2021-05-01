package com.hotsprings.scene.demo;

import java.io.IOException;
import java.util.Vector;

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
	
	private MText restart = new MText("restart", "Restart", 0x0);
	private MText back = new MText("back", "Back", 0x0);
	
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
		
		addChild(restart);
		addChild(back);
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new ImageStress());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new ImageStress());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
		if (timePassed >= 300) {
			Mimage img = new Mimage("img_" + imageList.size(), melody);
			img.data = RandomUtils.range(360, 0);
			addChild(img);
			imageList.addElement(img);
			
			timePassed = 0;
		}
		
		for (int i=0; i<imageList.size(); i++) {
			Mimage temp = (Mimage) (imageList.elementAt(i));
			temp.x = (get_width() / 2) - 44 + (float)( Math.cos(temp.data * (Math.PI / 180)) * 100 ) * (float)( Math.sin(System.currentTimeMillis() * 0.005) );
			temp.y = (get_height() / 2) - 50 + (float)( Math.sin(temp.data * (Math.PI / 180)) * 100 ) * (float)( Math.sin(System.currentTimeMillis() * 0.005) );
		}
		
		
		timePassed += dt;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
