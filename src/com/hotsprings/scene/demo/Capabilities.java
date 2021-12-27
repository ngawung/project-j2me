package com.hotsprings.scene.demo;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;

import com.hotsprings.scene.MenuSelector;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;

public class Capabilities extends Scene {
	
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	
	private MText isColor = new MText("Color Support: ", 0x0);;
	private MText numColors = new MText("Num Colors: ", 0x0);;
	private MText numAlphaLevels = new MText("Alpha Levels: ", 0x0);;

	public Capabilities() {
		Display display = Display.getDisplay(_e);
		
		if (display.isColor()) isColor.set_text(isColor.get_text() + "true");
		else isColor.set_text(isColor.get_text() + "false");
		numColors.set_text(numColors.get_text() + display.numColors());
		numAlphaLevels.set_text(numAlphaLevels.get_text() + display.numAlphaLevels());
	}

	public void initialize() {
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
		
		requestRender();
		
		isColor.x = 5;
		isColor.y = 30;
		
		numColors.x = 5;
		numColors.y = isColor.y + isColor.get_height() + 5;
		
		numAlphaLevels.x = 5;
		numAlphaLevels.y = numColors.y + numColors.get_height() + 5;
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new ImageDemo());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new Capabilities());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
	}

	public void render(Graphics g) {
		restart.render(g);
		back.render(g);
		
		isColor.render(g);
		numColors.render(g);
		numAlphaLevels.render(g);
	}

	public void destroy() {

	}

}
