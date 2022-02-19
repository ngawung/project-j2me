package mlbb.scene.demo;

import java.util.Vector;

import javax.microedition.lcdui.Graphics;

import melody.core.Scene;
import melody.enums.KeyCode;
import melody.utils.CoordUtils;
import mlbb.utils.QuickSort;
import mlbb.utils.Samusort;

public class UnitPathDemo extends Scene {
	
	private int _cameraX = 0;
	private int _cameraY = 0;
	
	private int SPEED = 20;
	
	private Unit unit = new Unit();
	private Vector units = new Vector();
	
	public int[] path = {
			0, 0,
			100, 100,
			200, 100,
			200, 200,
			300, 300,
		};

	public UnitPathDemo() {
	}

	public void destroy() {

	}

	public void initialize() {
		
	}

	public void render(Graphics g) {
		g.setColor(0, 0, 255);
		for(int i=0; i<path.length/2; i++) {
			g.fillRect(path[i*2] - 5 - _cameraX, path[i*2+1] - 5 - _cameraY, 10, 10);
		}
		
		g.setColor(255, 0, 255);
		g.fillRect((int)unit.x - 8  - _cameraX, (int)unit.y - 8 - _cameraY, 16, 16);
	}

	public void update(long dt) {
		requestRender();
		
		unit.update(dt);
		
		// input
		if (get_input().isHeld(KeyCode.LEFT)) _cameraX -= 5;
		if (get_input().isHeld(KeyCode.RIGHT)) _cameraX += 5;
		if (get_input().isHeld(KeyCode.UP)) _cameraY -= 5;
		if (get_input().isHeld(KeyCode.DOWN)) _cameraY += 5;
		
//		if (_playerMove) {
//			_playerX += (int)Math.cos(_playerAngle * (Math.PI/180)) * 5;
//			_playerY += (int)Math.sin(_playerAngle * (Math.PI/180)) * 5;
//		}
	}
	
	////////
	
	private class Unit {
		public float x = 0;
		public float y = 0;
		public double rotation = 0;
		
		public int currentPath = 0;
		
		public Unit() {
			
		}
		
		public void update(long dt) {
			if (currentPath >= path.length/2) {
				return;
			}
			
			int distance = ((path[currentPath*2] - (int)x)*(path[currentPath*2] - (int)x)) + ((path[currentPath*2+1] - (int)y)*(path[currentPath*2+1] - (int)y));
			
			if (distance < 25) {
				currentPath++;
			} else {
				rotation = CoordUtils.aTan2(path[currentPath*2+1] - y, path[currentPath*2] - x);
				
				x += Math.cos(rotation) * SPEED * ((float)dt/1000);
				y += Math.sin(rotation) * SPEED * ((float)dt/1000);
			}
			
		}
	}

}
