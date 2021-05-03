package com.hotsprings.scene.demo.game;

import javax.microedition.lcdui.Graphics;

import com.melody.core.Scene;
import com.melody.display.Quad;
import com.melody.enums.KeyCodeEnum;
import com.melody.utils.CoordUtils;
import com.melody.utils.RandomUtils;

public class GameMechanicTest extends Scene {
	
	private Quad player = new Quad(0, 0, 30, 30, 0x0000FF);
	private Quad enemy = new Quad(0, 0, 30, 30, 0xFF0000);
	
	private int speed = 15;
	private float velocity = 0;
	
	private boolean leapForward = true;
	private int backLeap;

	public GameMechanicTest() {
		player.fill = true;
		enemy.x = get_width() / 2 - enemy.width / 2;
		enemy.y = get_height() / 2 - enemy.height / 2;
	}

	public void initialize() {
		System.out.println(CoordUtils.aTan2(30, 30) * (180/Math.PI));
	}

	public void update(long dt) {
		velocity -= 0.8;
		if (velocity < 0) velocity = 0;
		
		if (get_input().isReleased(KeyCodeEnum.CENTER)) {
			velocity += speed;
			leapForward = true;
		}
		
		if (get_input().isReleased(KeyCodeEnum.SOFTKEY_RIGHT)) {
			velocity += speed;
			leapForward = false;
			if (player.x > enemy.x) backLeap = RandomUtils.range(90, 0) - 45;
			else backLeap = RandomUtils.range(90, 0) + 135;
			
			System.out.println(backLeap);
		}
		
		if (get_input().isHeld(KeyCodeEnum.UP)) player.y -= speed;
		if (get_input().isHeld(KeyCodeEnum.DOWN)) player.y += speed;
		if (get_input().isHeld(KeyCodeEnum.LEFT)) player.x -= speed;
		if (get_input().isHeld(KeyCodeEnum.RIGHT)) player.x += speed;
		
		if (velocity > 0) {
			if (leapForward) {
				if ((enemy.x - player.x)*(enemy.x - player.x) + (enemy.y - player.y)*(enemy.y - player.y) < 30*30) velocity = 0;
				double rot = CoordUtils.aTan2(enemy.y - player.y, enemy.x -  player.x);
				player.x += Math.cos(rot) * velocity;
				player.y += Math.sin(rot) * velocity;
			} else {
				player.x += Math.cos(backLeap * (Math.PI/180)) * velocity;
				player.y += Math.sin(backLeap * (Math.PI/180)) * velocity;
			}
		}
		
		if (player.x < 0) player.x = 0;
		if (player.x + player.width > get_width()) player.x = get_width() - player.width;
		if (player.y < 0) player.y = 0;
		if (player.y + player.height > get_height()) player.y = get_height() - player.height;
		
		
//		cameraX = player.x - get_width() / 2 + player.width / 2;
//		cameraY = player.y - get_height() / 2 + player.height / 2;
		
		requestRender();
	}

	public void render(Graphics g) {
		enemy.render(g);
		player.render(g);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
