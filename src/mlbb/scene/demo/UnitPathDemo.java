package mlbb.scene.demo;

import javax.microedition.lcdui.Graphics;

import melody.core.Scene;
import melody.enums.KeyCode;

public class UnitPathDemo extends Scene {
	
	private int _playerX = 0;
	private int _playerY = 0;
	private double _playerAngle = 0;
	private boolean _playerMove = false;
	
	private int _point1X = 100;
	private int _point1Y = 100;
	private int _point2X = 200;
	private int _point2Y = 100;
	
	private int SPEED = 100;

	public UnitPathDemo() {
	}

	public void destroy() {

	}

	public void initialize() {
		
	}

	public void render(Graphics g) {
		g.setColor(255, 0, 255);
		g.fillRect(_playerX, _playerY, 16, 16);
		
//		point
		g.setColor(0, 0, 255);
		g.fillRect(_point1X, _point1Y, 5, 5);
		g.fillRect(_point2X, _point2Y, 5, 5);
	}

	public void update(long dt) {
		requestRender();
		
		_playerAngle = 0;
		_playerMove = false;
		
		// input
		if (get_input().isHeld(KeyCode.LEFT)) {
			_playerAngle = 180;
			_playerMove = true;
		}
		if (get_input().isHeld(KeyCode.RIGHT)) {
			_playerAngle = 0;
			_playerMove = true;
		}
		if (get_input().isHeld(KeyCode.UP)) {
			_playerAngle = 270;
			_playerMove = true;
		}
		if (get_input().isHeld(KeyCode.DOWN)) {
			_playerAngle = 90;
			_playerMove = true;
		}
		
		if (_playerMove) {
			_playerX += (int)Math.cos(_playerAngle * (Math.PI/180)) * 5;
			_playerY += (int)Math.sin(_playerAngle * (Math.PI/180)) * 5;
		}
	}

}
