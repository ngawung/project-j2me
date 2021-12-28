package hotsprings.scene.demo;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import hotsprings.scene.MenuSelector;
import melody.core.Scene;
import melody.display.BMFont;
import melody.display.MText;
import melody.enums.KeyCodeEnum;
import melody.enums.TouchPhase;
import melody.utils.CoordUtils;

public class BitmapFontDemo extends Scene {
	
	public BMFont bmf;
	public int[][] bmf_format = new int[][]{
//			{ 13,  53, 80,  2,  9,  1, 4, 4 },
			{ 14, 16, 13 },
			{ 32,  24, 107, 12, 1, -4, 15, 4 },
			{ 33,  27, 80,  2,  10, 1, 3, 4 },
			{ 34,  56, 98,  6,  3,  0, 3, 6 },
			{ 35,  19, 25,  8,  10, 0, 3, 8 },
			{ 36,  0 , 0 ,  6,  13, 1, 2, 8 },
			{ 37,  15, 14,  11, 10, 0, 3, 11 },
			{ 38,  44, 47,  7,  10, 1, 3, 9 },
			{ 39,  61, 88,  2,  3,  1, 3, 4 },
			{ 40,  25, 0 ,  3,  13, 1, 3, 4 },
			{ 41,  29, 0 ,  3,  13, 0, 3, 4 },
			{ 42,  42, 98,  6,  6,  0, 3, 6 },
			{ 43,  47, 90,  6,  7,  1, 5, 8 },
			{ 44,  11, 107, 3,  3,  0, 11, 4 },
			{ 45,  46, 105, 4,  1,  0, 8, 4 },
			{ 46,  21, 107, 2,  2,  1, 11, 4 },
			{ 47,  53, 0 ,  4,  12, 0, 3, 4 },
			{ 48,  38, 58,  6,  10, 1, 3, 8 },
			{ 49,  59, 13,  4,  10, 1, 3, 8 },
			{ 50,  52, 58,  6,  10, 1, 3, 8 },
			{ 51,  0 , 69,  6,  10, 1, 3, 8 },
			{ 52,  8 , 58,  7,  10, 1, 3, 8 },
			{ 53,  7 , 69,  6,  10, 1, 3, 8 },
			{ 54,  14, 69,  6,  10, 1, 3, 8 },
			{ 55,  31, 58,  6,  10, 1, 3, 8 },
			{ 56,  21, 69,  6,  10, 1, 3, 8 },
			{ 57,  7 , 80,  6,  10, 1, 3, 8 },
			{ 58,  56, 80,  2,  8,  1, 5, 4 },
			{ 59,  49, 80,  3,  9,  0, 5, 4 },
			{ 60,  30, 80,  6,  9,  1, 4, 8 },
			{ 61,  49, 98,  6,  4,  1, 6, 8 },
			{ 62,  37, 80,  6,  9,  1, 4, 8 },
			{ 63,  28, 69,  6,  10, 1, 3, 8 },
			{ 64,  40, 0 ,  12, 12, 1, 2, 14 },
			{ 65,  36, 36,  8,  10, 0, 3, 8 },
			{ 66,  28, 25,  8,  10, 1, 3, 10 },
			{ 67,  46, 25,  8,  10, 1, 3, 9 },
			{ 68,  18, 36,  8,  10, 1, 3, 10 },
			{ 69,  36, 47,  7,  10, 1, 3, 9 },
			{ 70,  16, 58,  7,  10, 1, 3, 8 },
			{ 71,  0 , 47,  8,  10, 1, 3, 10 },
			{ 72,  45, 36,  8,  10, 1, 3, 10 },
			{ 73,  24, 80,  2,  10, 1, 3, 4 },
			{ 74,  24, 58,  6,  10, 0, 3, 7 },
			{ 75,  54, 36,  8,  10, 1, 3, 9 },
			{ 76,  0 , 58,  7,  10, 1, 3, 8 },
			{ 77,  38, 14,  10, 10, 1, 3, 12 },
			{ 78,  9 , 47,  8,  10, 1, 3, 10 },
			{ 79,  18, 47,  8,  10, 1, 3, 10 },
			{ 80,  10, 25,  8,  10, 1, 3, 9 },
			{ 81,  27, 36,  8,  10, 1, 3, 10 },
			{ 82,  27, 47,  8,  10, 1, 3, 10 },
			{ 83,  52, 47,  7,  10, 1, 3, 9 },
			{ 84,  0 , 36,  8,  10, 0, 3, 8 },
			{ 85,  55, 24,  8,  10, 1, 3, 10 },
			{ 86,  37, 25,  8,  10, 0, 3, 8 },
			{ 87,  0 , 14,  14, 10, 0, 3, 14 },
			{ 88,  49, 13,  9,  10, 0, 3, 9 },
			{ 89,  27, 14,  10, 10, 0, 3, 10 },
			{ 90,  0 , 25,  9,  10, 0, 3, 9 },
			{ 91,  33, 0 ,  3,  13, 1, 3, 4 },
			{ 92,  58, 0 ,  4,  12, 0, 3, 4 },
			{ 93,  21, 0 ,  3,  13, 0, 3, 4 },
			{ 94,  0 , 107, 5,  3,  0, 3, 5 },
			{ 95,  37, 106, 8,  1,  0, 15, 8 },
			{ 96,  6 , 107, 4,  3,  1, 2, 5 },
			{ 97,  28, 99,  6,  7,  1, 6, 8 },
			{ 98,  42, 69,  6,  10, 1, 3, 8 },
			{ 99,  35, 98,  6,  7,  1, 6, 7 },
			{ 100, 49, 69,  6,  10, 1, 3, 8 },
			{ 101, 40, 90,  6,  7,  1, 6, 8 },
			{ 102, 59, 58,  4,  10, 0, 3, 4 },
			{ 103, 56, 69,  6,  10, 1, 6, 8 },
			{ 104, 0 , 80,  6,  10, 1, 3, 8 },
			{ 105, 21, 80,  2,  10, 1, 3, 4 },
			{ 106, 17, 0 ,  3,  13, 0, 3, 4 },
			{ 107, 35, 69,  6,  10, 1, 3, 7 },
			{ 108, 60, 47,  2,  10, 1, 3, 4 },
			{ 109, 0 , 91,  10, 7,  1, 6, 12 },
			{ 110, 54, 90,  6,  7,  1, 6, 8 },
			{ 111, 0 , 99,  6,  7,  1, 6, 8 },
			{ 112, 14, 80,  6,  10, 1, 6, 8 },
			{ 113, 45, 58,  6,  10, 1, 6, 8 },
			{ 114, 59, 80,  4,  7,  1, 6, 5 },
			{ 115, 7 , 99,  6,  7,  1, 6, 8 },
			{ 116, 44, 80,  4,  9,  0, 4, 4 },
			{ 117, 14, 99,  6,  7,  1, 6, 8 },
			{ 118, 22, 91,  8,  7,  0, 6, 8 },
			{ 119, 11, 91,  10, 7,  0, 6, 10 },
			{ 120, 31, 90,  8,  7,  0, 6, 8 },
			{ 121, 9 , 36,  8,  10, 0, 6, 8 },
			{ 122, 21, 99,  6,  7,  1, 6, 8 },
			{ 123, 7 , 0 ,  4,  13, 1, 3, 5 },
			{ 124, 37, 0 ,  2,  13, 1, 3, 4 },
			{ 125, 12, 0 ,  4,  13, 0, 3, 5 },
			{ 126, 15, 107, 5,  2,  0, 3, 5 }
	};
	
	private MText restart = new MText("Restart", 0x0);
	private MText back = new MText("Back", 0x0);
	

	public BitmapFontDemo() {
		try {
			bmf = new BMFont(Image.createImage("/demo/img/bmf.png"), bmf_format);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		bmf.text = "Hello World!";
		
		restart.y = get_height() - restart.get_height() - 5;
		restart.x = 5;
		back.y = get_height() - back.get_height() - 5;
		back.x = get_width() - back.get_width() - 5;
		
		requestRender();
		
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new BitmapFontDemo());
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) _e.get_gameRoot().set_scene(new MenuSelector());
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) _e.get_gameRoot().set_scene(new BitmapFontDemo());;
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) _e.get_gameRoot().set_scene(new MenuSelector());
		
		bmf.x = (get_width() / 2) + (float)(Math.sin(System.currentTimeMillis() * 0.005) * 100);
		bmf.y = (get_height() / 2) + (float)(Math.cos(System.currentTimeMillis() * 0.001) * 100);
		
		requestRender();
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
		bmf.render(g);
		restart.render(g);
		back.render(g);
	}

}
