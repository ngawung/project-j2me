package com.hotsprings.scene.demo;

import javax.microedition.lcdui.Graphics;

import com.hotsprings.scene.MenuSelector;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;

public class Player1Demo extends Scene {
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	private MText play = new MText("Play", 0x0);

	public Player1Demo() {
		get_soundManager().load("/demo/sound/music.amr");
	}

	public void initialize() {
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
		play.y = get_height() - play.get_height() - 5;
		play.x = get_width() / 2 - play.get_width() / 2;
		
		requestRender();
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new Player1Demo());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 80, get_height() - 40, 80, 40)) playMusic();
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new Player1Demo());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		if (get_input().isDown(KeyCodeEnum.CENTER)) playMusic();

	}

	private void playMusic() {
		get_soundManager().play();
	}

	public void render(Graphics g) {
		play.render(g);
		restart.render(g);
		back.render(g);
	}

	public void destroy() {
		get_soundManager().unload();
	}

}
