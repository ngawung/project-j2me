package com.hotsprings.scene.demo.game;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.display.Quad;
import com.melody.enums.KeyCodeEnum;
import com.melody.utils.CoordUtils;
import com.melody.utils.RandomUtils;

public class GameMechanicTest extends Scene {
	
	private Image grid;
	
	private Quad player = new Quad(0, 0, 48, 64, 0x0000FF);
	private Quad enemy = new Quad(0, 0, 48, 64, 0xFF0000);
	private Quad follow = new Quad(0, 0, 20, 20, 0x00FF00);
	
	private float speed = 13.5f;
	private float velocity = 0;
	
	private boolean leapForward = true;
	private int backLeap;

	private float divider = 2;
	
	private MText text = new MText("", 0x0);

	private double midX;
	private double midY;
	
	private double midX2;
	private double midY2;

	public GameMechanicTest() {
		player.fill = true;
		enemy.x = get_width() / 2 - enemy.width / 2;
		enemy.y = get_height() / 2 - enemy.height / 2;
		
//		player.pivotX = player.width / 2;
//		player.pivotX = player.height;
//		
//		enemy.pivotX = enemy.width / 2;
//		enemy.pivotX = enemy.height;
	}

	public void initialize() {
		
		try {
			grid = Image.createImage("/demo/img/grid.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		text.y = get_height() - text.get_height() - 5;
		text.followCamera = false;
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
		
		if (get_input().isDown(KeyCodeEnum.KEY_1)) divider++;
		if (get_input().isDown(KeyCodeEnum.KEY_3)) divider--;
		
		int distance = (int)((enemy.x - player.x)*(enemy.x - player.x) + (enemy.y - player.y)*(enemy.y - player.y));
		
		if (velocity > 0) {
			if (leapForward) {
				if (distance < 30*30) velocity = 0;
				double rot = CoordUtils.aTan2(enemy.y - player.y, enemy.x -  player.x);
				player.x += Math.cos(rot) * velocity;
				player.y += Math.sin(rot) * velocity;
			} else {
				player.x += Math.cos(backLeap * (Math.PI/180)) * velocity;
				player.y += Math.sin(backLeap * (Math.PI/180)) * velocity;
			}
		}
		
//		old
		midX = ((player.x + enemy.x) / 2);
		midY = ((player.y + enemy.y) / 2);
		
		midX2 = ((player.x + midX) / divider);
		midY2 = ((player.y + midY) / divider);
		
		follow.x += (float)(midX2 - follow.x) * 0.2f;
		follow.y += (float)(midY2 - follow.y) * 0.2f;
		
		cameraX = (int)(follow.x - get_width() / 2);
		cameraY = (int)(follow.y - get_height() / 2);
		
		text.set_text("" + divider);
		
		requestRender();
	}

	public void render(Graphics g) {
		
		for (int i=0; i<6; i++) {
			for (int j=0; j<6; j++) {
				
				g.drawImage(grid,
						(int) ((Math.floor(cameraX / 100) + j) * 100) - (int) cameraX,
						(int) ((Math.floor(cameraY / 100) + i) * 100) - (int) cameraY,
						Graphics.TOP | Graphics.LEFT);
				
			}
		}
		
		enemy.render(g);
		player.render(g);
		follow.render(g);
		
		g.setColor(0xFF00FF);
		g.drawLine(
				(int)player.x - (int)cameraX,
				(int)player.y - (int)cameraY,
				(int)enemy.x - (int)cameraX,
				(int)enemy.y - (int)cameraY);
		
		g.fillRect(
				(int)(midX - cameraX),
				(int)(midY - cameraY),
				20, 20);
		
		
		g.setColor(0x0);
		g.fillRect(
				(int)(midX2 - cameraX), 
				(int)(midY2 - cameraY),
				20, 20);
		
		text.render(g);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
