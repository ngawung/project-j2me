package com.hotsprings.scene.demo;

import javax.microedition.lcdui.Graphics;

import com.hotsprings.scene.MenuSelector;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.input.Input;
import com.melody.utils.CoordUtils;

public class KeypadDemo extends Scene {
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	
	private MText text = new MText("Idle", 0x0);

	public KeypadDemo() {
		// TODO Auto-generated constructor stub
	}

	public void initialize() {
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
		
		reCenterText();
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new KeypadDemo());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new KeypadDemo());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
		for (int i=0; i<Input.KEY_COUNT; i++) {
			if (get_input().getKeyStatus(i) == -1) {
				text.set_text("Release " + KeyCodeEnum.getEnumById(i).getKey());
				reCenterText();
			}
			
			if (get_input().getKeyStatus(i) == 1) {
				text.set_text("Press " + KeyCodeEnum.getEnumById(i).getKey());
				reCenterText();
			}
			
			if (get_input().getKeyStatus(i) > 1) {
				text.set_text("Held " + KeyCodeEnum.getEnumById(i).getKey());
				reCenterText();
			}
		}
		
	}

	private void reCenterText() {
		text.x = (get_width() / 2) - text.get_width() / 2;
		text.y = (get_height() / 2) - text.get_height() / 2;
		requestRender();
	}

	public void render(Graphics g) {
		text.render(g);
		restart.render(g);
		back.render(g);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
