package com.hotsprings.scene.demo.game;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.display.Quad;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;
import com.melody.utils.RandomUtils;

public class GameMechanicTest extends Scene {
	
	private Image grid;
	
	private Quad player = new Quad(0, 0, 64, 80, 0x0000FF);
	private Quad enemy = new Quad(0, 0, 64, 80, 0xFF0000);
	private Quad follow = new Quad(0, 0, 20, 20, 0x00FF00);
	
	private Quad leftBound;
	private Quad rightBound;
	private Quad centerBound;
	
	private float speed = 20f;
	private float velocity = 0;
	private final float friction = 0.8f;
	
	private final int leapLimit = 100;
	private final int comboDelay = 500;
	private final int comboMax = 3;
	private int comboTimeCounter = 0;
	private int comboCounter = 0;
	private int attackDelay = 0;
	private double leapRotation;

	private boolean attacking = false;
	private boolean leaping = false;
	private boolean levitate = false;

	private MText text = new MText("", 0x0);

	private double midX;
	private double midY;
	
	private double midX2;
	private double midY2;

	public GameMechanicTest() {
		try {
			grid = Image.createImage("/demo/img/grid.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		leftBound = new Quad(0, 0, 80, get_height() - 60, 0xFF0000);
		rightBound = new Quad(0, 0, 80, get_height() - 60, 0xFF0000);
		centerBound = new Quad(0, 0, get_width() - 40, 60, 0xFF0000);
		
		leftBound.fill = rightBound.fill = centerBound.fill = false;
		leftBound.followCamera = rightBound.followCamera = centerBound.followCamera = false;
		rightBound.x = get_width() - rightBound.width;
		centerBound.y = get_height() - centerBound.height;
		centerBound.x = get_width() / 2 - centerBound.width / 2;
	}

	public void initialize() {
		text.y = get_height() - text.get_height() - 5;
		text.followCamera = false;
		
		player.fill = true;
		enemy.x = get_width() / 2 - enemy.width / 2;
		enemy.y = get_height() / 2 - enemy.height / 2;
		
		player.pivotX = -player.width / 2;
		player.pivotY = -player.height;
		enemy.pivotX = -enemy.width / 2;
		enemy.pivotY = -enemy.height;
	}

	public void update(long dt) {
		velocity *= friction;
		if (velocity < friction) {
			velocity = 0;
			leaping = levitate = attacking = false;
			comboTimeCounter = 0;
		}
		
		attackDelay -= dt;
		if (attackDelay < 0) attackDelay = 0;
		
		int distance = (int)((enemy.x - player.x)*(enemy.x - player.x) + (enemy.y - player.y)*(enemy.y - player.y));
		
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		// leap forward
		if (!leaping && get_input().isDown(KeyCodeEnum.KEY_6) || !leaping && coord != null && CoordUtils.pointInRect(coord[0], coord[1], rightBound.x, rightBound.y, rightBound.x + rightBound.width, rightBound.y + rightBound.height)) {
			if (distance < leapLimit*leapLimit) return;
			
			leapRotation = CoordUtils.aTan2(enemy.y - player.y, enemy.x -  player.x);
			velocity += speed;
			leaping = true;
			levitate = true;
		}
		
		if (!leaping && get_input().isDown(KeyCodeEnum.KEY_4) || !leaping && coord != null && CoordUtils.pointInRect(coord[0], coord[1], leftBound.x, leftBound.y, leftBound.x + leftBound.width, leftBound.y + leftBound.height)) {
			if (player.x > enemy.x) leapRotation = (RandomUtils.range(90, 0) - 45) * (Math.PI/180);
			else leapRotation = (RandomUtils.range(90, 0) + 135) * (Math.PI/180);
			
			velocity += speed;
			leaping = true;
			levitate = true;
		}
		
		if (!leaping && attackDelay == 0 && get_input().isDown(KeyCodeEnum.KEY_5) || !leaping && attackDelay == 0 && coord != null && CoordUtils.pointInRect(coord[0], coord[1], centerBound.x, centerBound.y, centerBound.x + centerBound.width, centerBound.y + centerBound.height)) {
			if (player.x > enemy.x) leapRotation = 180 * (Math.PI/180);
			else leapRotation = 0;
			velocity += speed/2;
			leaping = true;
			attacking = true;
			System.out.println("Attack");
			
			text.set_text("attack");
		}
		
		if (attacking && comboTimeCounter >= comboDelay && get_input().isDown(KeyCodeEnum.KEY_5) || attacking && comboTimeCounter >= comboDelay && coord != null && CoordUtils.pointInRect(coord[0], coord[1], centerBound.x, centerBound.y, centerBound.x + centerBound.width, centerBound.y + centerBound.height)) {
			if (comboCounter >= comboMax) {
				comboCounter = 0;
				attackDelay = 400;
				attacking = false;
				return;
			}
			if (player.x > enemy.x) leapRotation = 180 * (Math.PI/180);
			else leapRotation = 0;
			velocity += speed;
			leaping = true;
			attacking = true;
			comboTimeCounter = 0;
			comboCounter++;
			
			text.set_text("Combo " + comboCounter);
		}
		
		if (attacking) comboTimeCounter += dt;
		
		if (velocity > 0) {
			player.x += Math.cos(leapRotation) * velocity;
			player.y += Math.sin(leapRotation) * velocity;
		}
		
		if (levitate) player.pivotY = (int)(-player.height - velocity * 1.2);
		
//		old
		midX = ((player.x + enemy.x) / 2);
		midY = ((player.y + enemy.y) / 2);
		
		midX2 = ((player.x + midX) / 2);
		midY2 = ((player.y + midY) / 2);
		
		if (distance > ((get_width()*0.8)*(get_width()*0.8))) {
			follow.x += (float)(midX2 - follow.x) * 0.2f;
			follow.y += (float)(midY2 - follow.y) * 0.2f;
		} else {
			follow.x += (float)(midX - follow.x) * 0.2f;
			follow.y += (float)(midY - follow.y) * 0.2f;
		}
		
		cameraX = (int)(follow.x - get_width() / 2);
		cameraY = (int)(follow.y - get_height() / 2);
		
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
		
		leftBound.render(g);
		rightBound.render(g);
		centerBound.render(g);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
