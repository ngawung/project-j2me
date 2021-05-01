package com.hotsprings.scene.demo;

import com.hotsprings.scene.MenuSelector;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.display.Mimage;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;

public class ImageDemo extends Scene {
	
	private Mimage bg_img = new Mimage("bg_img");
	private Mimage png_img = new Mimage("png_img");
	private Mimage gif_img = new Mimage("gif_img");
	private Mimage jpg_img = new Mimage("jpg_img");
	
	private MText restart = new MText("restart", "Restart", 0x0);
	private MText back = new MText("back", "Back", 0x0);

	public ImageDemo() {
		// TODO Auto-generated constructor stub
	}

	public void initialize() {
		bg_img.set_buffer("/demo/img/bg.png");
		png_img.set_buffer("/demo/img/melody.png");
		gif_img.set_buffer("/demo/img/melody.gif");
		jpg_img.set_buffer("/demo/img/melody.jpg");
		
		png_img.x = 30;
		png_img.y = 30;
		
		gif_img.x = 30 + 100;
		gif_img.y = 40;
		
		jpg_img.x = 40;
		jpg_img.y = 40 + 100 + 10;
		
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
		
		addChild(bg_img);
		addChild(png_img);
		addChild(gif_img);
		addChild(jpg_img);

		addChild(restart);
		addChild(back);
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new ImageDemo());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new ImageDemo());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
//		int rot = 20;
//		int dis = 1;
////		png_img.x = (float) ((Math.cos(rot * (Math.PI / 180)) * dis) * Math.sin(System.currentTimeMillis()));
////		png_img.y = (float) ((Math.sin(rot * (Math.PI / 180)) * dis) * Math.sin(System.currentTimeMillis()));
//		
//		png_img.y = 30 + (float)( Math.sin(System.currentTimeMillis() * 0.005) * 10);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
