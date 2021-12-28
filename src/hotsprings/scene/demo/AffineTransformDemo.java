package hotsprings.scene.demo;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.Scene;
import melody.enums.KeyCodeEnum;
import melody.utils.Matrix;
import melody.utils.Point;

public class AffineTransformDemo extends Scene {
	
	private Image img;
	private int speed = 5;
	private Matrix m = new Matrix();
	private Matrix m2 = new Matrix();
	
	private float img_x = 0;
	private float img_y = 0;
	private float img_scale = 1;
	private float img_angle = 0;

	public AffineTransformDemo() {
		try {
			img = Image.createImage("/demo/img/melody.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		requestRender();
	}

	public void update(long dt) {
		if (get_input().isHeld(KeyCodeEnum.UP)) img_y -= speed;
		if (get_input().isHeld(KeyCodeEnum.DOWN)) img_y += speed;
		if (get_input().isHeld(KeyCodeEnum.LEFT)) img_x -= speed;
		if (get_input().isHeld(KeyCodeEnum.RIGHT)) img_x += speed;
		
		if (get_input().isHeld(KeyCodeEnum.KEY_2)) img_angle++;
		if (get_input().isHeld(KeyCodeEnum.KEY_8)) img_angle--;
		
		if (get_input().isDown(KeyCodeEnum.KEY_4)) img_scale -= 0.1;
		if (get_input().isDown(KeyCodeEnum.KEY_6)) img_scale += 0.1;
		
		
		requestRender();
	}
	
	private int x;
	private int y;
	private Point point = new Point();
	
	private Point start = new Point();
	private Point end = new Point();
	private Point temp = new Point();
	
	public void render(Graphics g) {
		
		m.identity();
		m.translate(-img.getWidth()/2, -img.getHeight()/2);
		m.rotate(img_angle);
		m.scale(img_scale, img_scale);
		m.translate(img_x, img_y);
		
		// top left
		m.transformPoint(0, 0, temp);
		start.x = temp.x; start.y = temp.y;
		end.x = temp.x; end.y = temp.y;
		
		// bottom right
		m.transformPoint(img.getWidth(), img.getHeight(), temp);
		start.x = Math.min(start.x, temp.x); start.y = Math.min(start.y, temp.y);
		end.x = Math.max(end.x, temp.x); end.y = Math.max(end.y, temp.y);
		
		// bottom left
		m.transformPoint(0, img.getHeight(), temp);
		start.x = Math.min(start.x, temp.x); start.y = Math.min(start.y, temp.y);
		end.x = Math.max(end.x, temp.x); end.y = Math.max(end.y, temp.y);
		
		// top right
		m.transformPoint(img.getWidth(), 0, temp);
		start.x = Math.min(start.x, temp.x); start.y = Math.min(start.y, temp.y);
		end.x = Math.max(end.x, temp.x); end.y = Math.max(end.y, temp.y);
		
		m2.setTo(m.a, m.b, m.c, m.d, m.tx, m.ty);
		m2.invert();
		
		for (x=(int)start.x; x<end.x; x++) {
			for (y=(int)start.y; y<end.y; y++) {
				
				m2.transformPoint(x, y, point);
				try {
					g.drawRegion(img, (int)point.x, (int)point.y, 1, 1, Sprite.TRANS_NONE, x, y, Graphics.TOP | Graphics.LEFT);
				} catch (Exception e) {}
				
			}
		}
		
//		g.drawImage(img, 0, 0, Graphics.TOP | Graphics.LEFT);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
