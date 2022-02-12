package mlbb.scene.demo;

import javax.microedition.lcdui.Graphics;

import melody.core.Scene;
import melody.enums.KeyCode;
import mlbb.utils.QuickSort;
import mlbb.utils.Samusort;

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
		
		int[] array1 = new int[] {352, 124, 12, 245, 123143, 2421, 2};
		int[] array2 = new int[] {1, 0, 3, 4, 5, 8, 6, 7, 9};
		int[] array3 = new int[] {1, 1, 1, 1, 2, 2, 2, 3, 4};
		int[] array4 = new int[] {9, 0, 7, 6, 5, 4, 3, 2, 1};
		
		QuickSort.sort(array1, 0, array1.length - 1);
		QuickSort.sort(array2, 0, array2.length - 1);
		QuickSort.sort(array3, 0, array3.length - 1);
		QuickSort.sort(array4, 0, array4.length - 1);

		toStringArray(array1);
		toStringArray(array2);
		toStringArray(array3);
		toStringArray(array4);
	}
	
	public void toStringArray(int[] array) {
		String out = "";
		
		for (int i=0; i<array.length; i++) {
			out += array[i] + ", ";
		}
		
		System.out.println(out);
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
	
	public void stalinSort(int[] array) {
        int i = 0;
        for (int j = 1; j < array.length; i++, j++) {
            if (array[i] > array[j]) {
                i--;
            } else {
                if (j - i > 1) {
                    array[i + 1] = array[j];
                }
            }
        }
    }

}
