package com.hotsprings.scene.demo;

import java.io.IOException;
import java.io.InputStreamReader;
import javax.microedition.lcdui.Graphics;
import com.melody.core.Scene;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.tinyline.util.GZIPInputStream;

public class CompressionDemo extends Scene {
	
	private char[] frame = new char[120*90];
	private int timePassed = 0;
	private int fps = 10;
	private InputStreamReader out;
	private GZIPInputStream gzip2;
	private boolean isDone = false;
	
	private int lineWidth;
	private int x = 0;
	private int y = 0;
	private int i;
	private final int PIXEL_SIZE = 2;
	private boolean play = false;

	public CompressionDemo() {
		// TODO Auto-generated constructor stub
		get_soundManager().load("/demo/sound/bad.amr");
		set_backgroundColor(0x0);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
		try {
			gzip2 = new GZIPInputStream(getClass().getResourceAsStream("/demo/file/compress"));
			out = new InputStreamReader(gzip2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		requestRender();
	}

	public void update(long dt) {
		if (play) timePassed += dt;
		else {
			if (get_input().isDown(KeyCodeEnum.KEY_5) || get_input().getTouch(TouchPhase.END)) playing();
		}
		
		
		while (timePassed > (1000 / fps) && !isDone && play ) {
			try {
				
				if (out.read(frame, 0, frame.length) == -1) {
					isDone = true;
					out.close();
					gzip2.close();
				}
				
				requestRender();
				timePassed -= (1000 / fps);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void playing() {
		play = true;
		get_soundManager().play();
	}

	public void render(Graphics g) {
		if (!play) {
			g.setColor(0xFFFFFF);
			g.drawString("PRESS KEY 5 TO START", 10, 10, Graphics.TOP | Graphics.LEFT);
			return;
		}
		
		lineWidth = 1;
		x = 0;
		y = 0;
		
		for (i=0; i<frame.length; i++) {
			if (frame[i] == 1) g.setColor(0xFFFFFF);
			else g.setColor(0x0);
			
			if (i+1 < 120 - 1 && frame[i+1] == frame[i]) {
				lineWidth++ ;
				continue;
			}
			
			g.fillRect(240 - y*PIXEL_SIZE - 30, x*PIXEL_SIZE + 40, PIXEL_SIZE, lineWidth*PIXEL_SIZE);
			x += lineWidth;
			if (x > 120 - 1) {
				x = 0;
				y++;
			}
			lineWidth = 1;
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
