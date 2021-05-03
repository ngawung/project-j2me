package com.hotsprings.scene.demo;

import javax.microedition.lcdui.Graphics;

import com.hotsprings.scene.MenuSelector;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;

public class TouchDemo extends Scene {
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	
	private MText text = new MText("Idle", 0x0);

	public TouchDemo() {
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
		int[] coord_begin = get_input().getTouchCoord(TouchPhase.BEGIN);
		int[] coord_drag = get_input().getTouchCoord(TouchPhase.DRAG);
		int[] coord_end = get_input().getTouchCoord(TouchPhase.END);
		
		if (coord_begin != null) {
			if (CoordUtils.pointInRect(coord_begin[0], coord_begin[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new TouchDemo());
			else if (CoordUtils.pointInRect(coord_begin[0], coord_begin[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
			
			text.set_text("Begin (" + coord_begin[0] + ", " + coord_begin[1] + ")");
			reCenterText();
		}
		
		if (coord_drag != null) {
			text.set_text("Drag (" + coord_drag[0] + ", " + coord_drag[1] + ")");
			reCenterText();
		}
		
		if (coord_end != null) {
			text.set_text("Ended (" + coord_end[0] + ", " + coord_end[1] + ")");
			reCenterText();
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new TouchDemo());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
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
