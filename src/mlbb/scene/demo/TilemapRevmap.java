package mlbb.scene.demo;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.Scene;
import melody.enums.KeyCode;
import melody.utils.RandomUtils;

public class TilemapRevmap extends Scene {
	
	private int _tileW = 16;
	private int _tileH = 16;
	private int _levelW;
	private int _levelH;
	private int visibleX = 0;
	private int visibleY = 0;
	private int tileOffsetX = 0;
	private int tileOffsetY = 0;
	
	private int camX = 0;
	private int camY = 0;
	
	private float playerX = 0f;
	private float playerY = 0f;
	private float velocityX = 0f;
	private float velocityY = 0f;
	
	private float SPEED = 0.6f;
	
	private Image tilemap;
	private byte[] _mapData;

	public TilemapRevmap() {
		try {
			
			DataInputStream dis = new DataInputStream(getClass().getResourceAsStream("/demo/map/test3"));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			int nRead;
			byte[] buffer = new byte[1000];
			
			while((nRead = dis.read(buffer, 0, buffer.length)) != -1) {
				bos.write(buffer, 0, nRead);
			}
			
			_mapData = bos.toByteArray();
			
			System.out.println(_mapData.length);
			
			tilemap = Image.createImage("/demo/img/tilemap2.png");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		
	}

	public void initialize() {
		_levelW = 100;
		_levelH = 100;
		
		visibleX = get_width() / _tileW;
		visibleY = get_height() / _tileW;
		
		System.out.println("Visible: " + visibleX + ", " + visibleY);
	}
	
	public void update(long dt) {
		velocityY = 0;
		velocityX = 0;
		
		if (get_input().isHeld(KeyCode.UP)) playerY -= SPEED;
		if (get_input().isHeld(KeyCode.DOWN)) playerY += SPEED;
		if (get_input().isHeld(KeyCode.LEFT)) playerX -= SPEED;
		if (get_input().isHeld(KeyCode.RIGHT)) playerX += SPEED;
		
		// player movement here
		playerX = playerX + velocityX;
		playerY = playerY + velocityY;
		
		// centering camera
		camX = (int)(playerX * _tileW) - (visibleX/2) * _tileW;
		camY = (int)(playerY * _tileH) - (visibleY/2) * _tileH;
		
		// clamp camera
		if (camX < 0) camX = 0;
		if (camX > (_levelW - visibleX) * _tileW) camX = (_levelW - visibleX) * _tileW;
		if (camY < 0) camY = 0;
		if (camY > (_levelH - visibleY) * _tileW) camY = (_levelH - visibleY) * _tileH;
		
		// tile offset
		tileOffsetX = camX - (int)Math.floor((camX / _tileW) * _tileW);
		tileOffsetY = camY - (int)Math.floor((camY / _tileH) * _tileH);
		
		requestRender();
	}
	
	private char getTile(int x, int y) {
		char tileTemp = 0;
		
		if (x >= 0 && x < _levelW && y >= 0 && y < _levelH) {
			for (int bitSize=0; bitSize<5; bitSize++) {
				int index = (y * _levelW + x) * 5 + bitSize;
				int mask = 0x80 >> (index%8);
				if ((_mapData[(int)(index/8)] & mask) > 0)
					tileTemp |= (0x0001 << (4-bitSize));
			}
			
			return tileTemp;
		}
		
		return ' ';
	}

	public void render(Graphics g) {
		// draw map
		g.setColor(255,0,0);
		for (int y= 0; y<visibleY + 1; y++) {
			for (int x= 0; x<visibleX + 1; x++) {
				
				char tileId = getTile((int)Math.floor((x * _tileW + camX) / _tileW), (int)Math.floor((y * _tileH + camY) / _tileH));
				
//				g.drawRect(x * _tileW - tileOffsetX, y * _tileH - tileOffsetY, _tileW, _tileH);
				
//				if (tileId == 31) g.setColor(0,0,0);
//				else if (tileId == 30) g.setColor(0,255,0);
//				else g.setColor(255,0,255);
//				
//				g.fillRect(x * _tileW - tileOffsetX, y * _tileH - tileOffsetY, _tileW, _tileH);
				
				Random r = new Random((int)Math.floor((y * _tileH + camY) / _tileH) * _levelW + (int)Math.floor((x * _tileW + camX) / _tileW));
				int tileNumY =  (int)tileId/10;
				int tileNumX =  Math.abs(tileNumY * 10 - tileId);
				
				if (tileId == 31) g.drawRegion(tilemap, 9 * _tileW, 3 * _tileH, 16, 16, Sprite.TRANS_NONE, (int)(x * _tileW - tileOffsetX), (int)(y * _tileH - tileOffsetY), Graphics.LEFT | Graphics.TOP);
				else if (tileId == 30) g.drawRegion(tilemap, RandomUtils.rand(6, 7, r) * _tileW, RandomUtils.rand(3, 6, r) * _tileH, 16, 16, Sprite.TRANS_NONE, (int)(x * _tileW - tileOffsetX), (int)(y * _tileH - tileOffsetY), Graphics.LEFT | Graphics.TOP);
				else g.drawRegion(tilemap, tileNumX * _tileW, tileNumY * _tileH, 16, 16, Sprite.TRANS_NONE, (int)(x * _tileW - tileOffsetX), (int)(y * _tileH - tileOffsetY), Graphics.LEFT | Graphics.TOP);
				
			}
		}
		
		// draw character
		g.setColor(0, 0 , 255);
		g.fillRect((int)(playerX * _tileW) - _tileW/4 - camX, (int)(playerY * _tileH) - _tileH/4 - camY, _tileW/2, _tileH/2);
	}

}
