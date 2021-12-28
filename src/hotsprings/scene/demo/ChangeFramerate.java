package hotsprings.scene.demo;

import javax.microedition.lcdui.Graphics;

import hotsprings.scene.MenuSelector;
import melody.core.Scene;
import melody.display.MText;
import melody.enums.KeyCodeEnum;
import melody.enums.TouchPhase;
import melody.utils.CoordUtils;

public class ChangeFramerate extends Scene {
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	
	private int selected = 0;
	
	private MText[] list = new MText[]{
			new MText("12 Fps", 0x0),
			new MText("20 Fps (default)", 0x0),
			new MText("30 Fps", 0x0),
			new MText("60 Fps", 0x0),
			new MText("Unlimited Fps (experimental)", 0x0),
	};

	public ChangeFramerate() {
		// TODO Auto-generated constructor stub
	}

	public void initialize() {
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
		
		for (int i=0; i<list.length; i++) {
			list[i].x = 20;
			list[i].y = 20 + 20 * i; 
		}
		
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
		
		if (get_input().isDown(KeyCodeEnum.UP) || get_input().isDown(KeyCodeEnum.KEY_2)) selectFramerate(selected + 1);
		if (get_input().isDown(KeyCodeEnum.DOWN) || get_input().isDown(KeyCodeEnum.KEY_8)) selectFramerate(selected - 1);
	}

	private void selectFramerate(int newSelected) {
		
		_e.set_fps(selected);
	}

	public void render(Graphics g) {
		
		for (int i=0; i<list.length; i++) {
			list[i].render(g);
		}
		
		restart.render(g);
		back.render(g);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
