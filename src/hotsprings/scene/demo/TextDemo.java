package hotsprings.scene.demo;

import javax.microedition.lcdui.Graphics;

import hotsprings.scene.MenuSelector;
import melody.core.Scene;
import melody.display.MText;
import melody.enums.KeyCodeEnum;
import melody.enums.TouchPhase;
import melody.utils.CoordUtils;
import melody.utils.RandomUtils;

public class TextDemo extends Scene {
	
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	
	private MText text = new MText("Hello World", 0x0);
	private int timePassed = 0;

	public void initialize() {
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
		
		requestRender();
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new TextDemo());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new TextDemo());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
		text.x = (get_width() / 2) + (float)(Math.sin(System.currentTimeMillis() * 0.005) * 100);
		text.y = (get_height() / 2) + (float)(Math.cos(System.currentTimeMillis() * 0.001) * 100);
		
		if (timePassed  >= 100) {
			text.color = ((char)RandomUtils.range(255, 1) << 16) + ((char)RandomUtils.range(255, 2) << 8)  + ((char)RandomUtils.range(255, 3));
			timePassed = 0;
		}
		
		timePassed += dt;
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
