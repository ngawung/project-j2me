package mlbb.scene.demo;

import javax.microedition.lcdui.Graphics;

import melody.core.Scene;
import melody.enums.KeyCode;

public class TilemapRevmap extends Scene {
	
	private int _tileW = 40;
	private int _tileH = 40;
	private int _levelW;
	private int _levelH;
	private int visibleX = 0;
	private int visibleY = 0;
	private int tileOffsetX = 0;
	private int tileOffsetY = 0;
	
	private int offsetX = 0;
	private int offsetY = 0;
	
	private int camX = 0;
	private int camY = 0;
	
	private float playerX = 0f;
	private float playerY = 0f;
	
	private float SPEED = 0.6f;

	public TilemapRevmap() {
		
	}

	public void destroy() {
		
	}

	public void initialize() {
		_levelW = 20;
		_levelH = 20;
		
		visibleX = get_width() / _tileW;
		visibleY = get_height() / _tileW;
		
		System.out.println("Visible: " + visibleX + ", " + visibleY);
	}
	
	public void update(long dt) {
		if (get_input().isHeld(KeyCode.UP)) playerY -= SPEED;
		if (get_input().isHeld(KeyCode.DOWN)) playerY += SPEED;
		if (get_input().isHeld(KeyCode.LEFT)) playerX -= SPEED;
		if (get_input().isHeld(KeyCode.RIGHT)) playerX += SPEED;
		
		if (get_input().isHeld(KeyCode.KEY_2)) playerY -= SPEED;
		if (get_input().isHeld(KeyCode.KEY_8)) playerY += SPEED;
		if (get_input().isHeld(KeyCode.KEY_4)) playerX -= SPEED;
		if (get_input().isHeld(KeyCode.KEY_6)) playerX += SPEED;
		
		camX = (int)(playerX * _tileW) - (visibleX/2) * _tileW;
		camY = (int)(playerY * _tileH) - (visibleY/2) * _tileH;
		
		// clamp camera
		if (camX < 0) camX = 0;
		if (camX > (_levelW - visibleX) * _tileW) camX = (_levelW - visibleX) * _tileW;
		if (camY < 0) camY = 0;
		if (camY > (_levelH - visibleY) * _tileW) camY = (_levelH - visibleY) * _tileH;
		
		// tile offset
		tileOffsetX = camX - (int)(camX / _tileW) * _tileW;
		tileOffsetY = camY - (int)(camY / _tileH) * _tileH;
		
		requestRender();
	}

	public void render(Graphics g) {
		// draw map
		g.setColor(255,0,0);
		for (int y= 0; y<visibleY + 1; y++) {
			for (int x= 0; x<visibleX + 1; x++) {
				
				g.drawRect(x * _tileW - tileOffsetX, y * _tileH - tileOffsetY, _tileW, _tileH);
				
			}
		}
		
		// draw character
		g.setColor(0, 0 , 255);
		g.fillRect((int)(playerX * _tileW) - _tileW/4 - camX, (int)(playerY * _tileH) - _tileH/4 - camY, _tileW/2, _tileH/2);
	}

}
