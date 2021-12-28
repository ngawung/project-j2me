package hotsprings.scene.demo;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import hotsprings.scene.MenuSelector;
import melody.core.Scene;
import melody.display.MText;
import melody.display.Mimage;
import melody.enums.KeyCodeEnum;
import melody.enums.TouchPhase;
import melody.utils.CoordUtils;

public class ImageDemo extends Scene {
	
	private Mimage bg_img;
	private Mimage png_img;
	private Mimage gif_img;
	private Mimage jpg_img;
	
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	
	private int timePassed = 0;

	public ImageDemo() {
		try {
			bg_img = new Mimage(Image.createImage("/demo/img/bg.png"));
			png_img = new Mimage(Image.createImage("/demo/img/melody.png"));
			gif_img = new Mimage(Image.createImage("/demo/img/melody.gif"));
			jpg_img = new Mimage(Image.createImage("/demo/img/melody.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
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
		
		requestRender();
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new ImageDemo());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new ImageDemo());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
		if (timePassed >= 1000) {
			
			png_img.transform += 1;
			if (png_img.transform > 7) png_img.transform = 0;
			
			gif_img.transform += 1;
			if (gif_img.transform > 7) gif_img.transform = 0;
			
			jpg_img.transform += 1;
			if (jpg_img.transform > 7) jpg_img.transform = 0;
			
			timePassed = 0;
			
			requestRender();
		}
		
		timePassed += dt;
	}
	
	public void render(Graphics g) {
		bg_img.render(g);
		png_img.render(g);
		gif_img.render(g);
		jpg_img.render(g);
		
		restart.render(g);
		back.render(g);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
