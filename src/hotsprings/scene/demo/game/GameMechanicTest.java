package hotsprings.scene.demo.game;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.core.Scene;
import melody.display.MText;
import melody.display.Quad;
import melody.enums.KeyCodeEnum;
import melody.enums.TouchPhase;
import melody.utils.CoordUtils;
import melody.utils.RandomUtils;

public class GameMechanicTest extends Scene {
	
	private Image grid;
	
	private Quad player = new Quad(0, 0, 64, 80, 0x0000FF);
	private Quad enemy = new Quad(0, 0, 64, 80, 0xFF0000);
	private Quad follow = new Quad(0, 0, 20, 20, 0x00FF00);
	
	private Quad leftBound;
	private Quad rightBound;
	private Quad centerBound;
	
	private float speed = 280.f;
	private float velocity = 0;
	private final float friction = 560f;
	
	private final int LEAP_DISTANCE_LIMIT = 100;
	private final int HOLD_ATTACK_DELAY = 300;
	private final int WAIT_COMBO_TIME = 500;
	private final int COMBO_MAX = 3;
	
	private final int STATUS_IDLE = 0;
	private final int STATUS_LEAP = 1;
	private final int STATUS_ATTACK = 2;
	private final int STATUS_COMBO = 3;
	
	private int status = STATUS_IDLE;
	
	private int holdCounter = 0;
	private int waitComboCounter = 0;
	private int comboCooldownCounter = 0;
	
	private int comboTotal = 0;
	private double leapRotation;

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
		
		////////////// LOGIC /////////////////
		
		// floating effect
		if (status == STATUS_LEAP) {
			player.pivotY = (int)(-player.height - velocity * 0.1);
		}
		
		// switch back to idle
		if (status == STATUS_LEAP && velocity == 0) status = STATUS_IDLE;
		if ((status == STATUS_ATTACK || status == STATUS_COMBO) && holdCounter == 0) status = STATUS_IDLE;
		
		/////// INPUT HANDLER //////
		
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		int distance = (int)((enemy.x - player.x)*(enemy.x - player.x) + (enemy.y - player.y)*(enemy.y - player.y));
		
		// leap forward
		if (get_input().isDown(KeyCodeEnum.KEY_6) || coord != null && CoordUtils.pointInRect(coord[0], coord[1], rightBound.x, rightBound.y, rightBound.x + rightBound.width, rightBound.y + rightBound.height)) {
			if (status == STATUS_IDLE) {
				
				if (distance < LEAP_DISTANCE_LIMIT*LEAP_DISTANCE_LIMIT) leapRotation = (RandomUtils.range(360, 0)) * (Math.PI/180);
				else leapRotation = CoordUtils.aTan2(enemy.y - player.y, enemy.x -  player.x);
				
				velocity += speed;
				status = STATUS_LEAP;
			}
		}
		
		// leap backward
		if (get_input().isDown(KeyCodeEnum.KEY_4) || coord != null && CoordUtils.pointInRect(coord[0], coord[1], leftBound.x, leftBound.y, leftBound.x + leftBound.width, leftBound.y + leftBound.height)) {
			if (status == STATUS_IDLE) {

				if (player.x > enemy.x) leapRotation = (RandomUtils.range(90, 0) - 45) * (Math.PI/180);
				else leapRotation = (RandomUtils.range(90, 0) + 135) * (Math.PI/180);
				
				velocity += speed;
				status = STATUS_LEAP;
			}
		}
		
		// attack
		if (get_input().isDown(KeyCodeEnum.KEY_5) || coord != null && CoordUtils.pointInRect(coord[0], coord[1], centerBound.x, centerBound.y, centerBound.x + centerBound.width, centerBound.y + centerBound.height)) {
			if (status == STATUS_IDLE) {
				
				if (comboTotal < COMBO_MAX && comboCooldownCounter == 0) {
					if (player.x > enemy.x) leapRotation = 180 * (Math.PI/180);
					else leapRotation = 0;
					velocity += speed/2;
					
					holdCounter = HOLD_ATTACK_DELAY;
					waitComboCounter = 0;
					comboTotal++;
					status = STATUS_ATTACK;
					
					if (comboTotal == COMBO_MAX) comboCooldownCounter = (int)(HOLD_ATTACK_DELAY * 2.5);
					
					text.set_text("combo " + comboTotal);
					System.out.println("combo " + comboTotal);
				}
				
			}
		}
		
//		// combo
//		if (get_input().isDown(KeyCodeEnum.KEY_5) || coord != null && CoordUtils.pointInRect(coord[0], coord[1], centerBound.x, centerBound.y, centerBound.x + centerBound.width, centerBound.y + centerBound.height)) {
//			if (status == STATUS_IDLE && waitComboCounter > 0 && comboTotal <= (COMBO_MAX - 1)) {
//				if (player.x > enemy.x) leapRotation = 180 * (Math.PI/180);
//				else leapRotation = 0;
//				
//				velocity += speed;
//				holdCounter = HOLD_ATTACK_DELAY;
//				waitComboCounter = 0;
//				status = STATUS_COMBO;
//				comboTotal++;
//				if (comboTotal == COMBO_MAX) {
//					holdCounter = (int)(HOLD_ATTACK_DELAY * 2.5);
//				}
//				text.set_text("combo " + comboTotal);
//				System.out.println("combo " + comboTotal);
//			}
//		}
		
		////////// EXTRA LOGIC /////////////////
		
		// move player
		if (velocity > 0) {
			player.x += Math.cos(leapRotation) * (velocity) * (float)dt / 1000;
			player.y += Math.sin(leapRotation) * (velocity) * (float)dt / 1000;
		}
		
		////////// COUNTER THINGY /////////////////
		
		
		// if there is velocity
		if (velocity > 0) {
			velocity -= (float)friction * (float)dt / 1000;
			if (velocity <= (float)friction * (float)dt / 1000) {
				velocity = 0;
			}
		}
		
		// if there is holdCounter
		if (holdCounter > 0) {
			holdCounter -= dt;
			if (holdCounter <= 0) {
				holdCounter = 0;
				waitComboCounter = WAIT_COMBO_TIME;
			}
		}
		
		// if there is comboCooldownCounter
		if (comboCooldownCounter > 0) {
			comboCooldownCounter -= dt;
			if (comboCooldownCounter <= 0) {
				comboCooldownCounter = 0;
			}
		}
		
		// if there is holdCounter
		if (waitComboCounter > 0) {
			waitComboCounter -= dt;
			if (waitComboCounter <= 0) {
				waitComboCounter = 0;
				comboTotal = 0;
			}
		}
		

		////////// DEBUG THINGY /////////////////
		
		
//		old
		midX = ((player.x + enemy.x) / 2);
		midY = ((player.y + enemy.y) / 2);
		
		midX2 = ((player.x + midX) / 2);
		midY2 = ((player.y + midY) / 2);
		
		if (distance > ((get_width()*0.6)*(get_width()*0.6))) {
			follow.x += (float)(midX2 - follow.x) * 0.2f;
			follow.y += (float)(midY2 - follow.y) * 0.2f;
		} else {
			follow.x += (float)(midX - follow.x) * 0.2f;
			follow.y += (float)(midY - follow.y) * 0.2f;
		}
		
		cameraX = (int)(follow.x - get_width() / 2);
		cameraY = (int)(follow.y - get_height() / 2);
		
		text.set_text(velocity * ((float)dt/1000) + ", " + friction * ((float)dt/1000));
		
		switch(status) {
			case STATUS_IDLE: player.color = 0x0000FF; break;
			case STATUS_LEAP: player.color = 0x4287F5; break;
			case STATUS_ATTACK: player.color = 0x3DEB4B; break;
			case STATUS_COMBO: player.color = 0xD9DE49; break;
		}
		
		if (comboTotal == 1) player.color = 0x3DEB4B;
		if (comboTotal == 2) player.color = 0xD9DE49;
		if (comboTotal == 3) player.color = 0xf2a144;
		
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
