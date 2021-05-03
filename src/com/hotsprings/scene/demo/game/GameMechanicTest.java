package com.hotsprings.scene.demo.game;

import javax.microedition.lcdui.Graphics;

import com.melody.core.Scene;
import com.melody.display.Quad;
import com.melody.enums.KeyCodeEnum;
import com.melody.utils.CoordUtils;

public class GameMechanicTest extends Scene {
	
	private Quad player = new Quad(0, 0, 80, 100, 0x0000FF);
	private Quad enemy = new Quad(0, 0, 80, 100, 0xFF0000);
	
	private int speed = 5;

	public GameMechanicTest() {
		player.fill = true;
		enemy.x = 100;
		enemy.y = 100;
	}

	public void initialize() {
		
	}

	public void update(long dt) {
		if (get_input().isHeld(KeyCodeEnum.UP)) cameraY -= speed;
		if (get_input().isHeld(KeyCodeEnum.DOWN)) cameraY += speed;
		if (get_input().isHeld(KeyCodeEnum.LEFT)) cameraX -= speed;
		if (get_input().isHeld(KeyCodeEnum.RIGHT)) cameraX += speed;
		
		player.fill = CoordUtils.rectInRect(
				(int)player.x, (int)player.y,
				(int)player.x + player.width, (int)player.y + player.height,
				(int)enemy.x, (int)enemy.y,
				(int)enemy.x + enemy.width, (int)enemy.y + enemy.height
			);
		
		requestRender();
	}

	public void render(Graphics g) {
//		enemy.render(g);
		player.render(g);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
