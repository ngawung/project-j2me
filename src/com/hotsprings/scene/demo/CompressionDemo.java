package com.hotsprings.scene.demo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.microedition.lcdui.Graphics;

import com.melody.core.Scene;
import com.melody.enums.KeyCodeEnum;
import com.tinyline.util.GZIPInputStream;

public class CompressionDemo extends Scene {
	
	private char[] frame = new char[43200];
	private int timePassed = 0;
	private int fps = 10;
	private InputStreamReader out;
	private GZIPInputStream gzip2;
	private boolean isDone = false;
	private int frame2;
	private int pixel;
	private boolean play = false;

	public CompressionDemo() {
		// TODO Auto-generated constructor stub
		get_soundManager().load("/demo/sound/bad.amr");
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
		try {
			gzip2 = new GZIPInputStream(getClass().getResourceAsStream("/demo/file/compress"));
			out = new InputStreamReader(gzip2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void update(long dt) {
		timePassed += dt;
		
		if (get_input().isReleased(KeyCodeEnum.CENTER)) {
			System.out.println("play");
			timePassed = 0;
			get_soundManager().play();
			play = true;
		}
		
		if (timePassed > (1000 / fps) && !isDone && play  ) {
			try {
				
				if (out.read(frame, 0, frame.length) == -1) {
					System.out.println("done");
					isDone = true;
					out.close();
					gzip2.close();
				}
				
				System.out.println("render");
				requestRender();
				timePassed -= (1000 / fps);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void render(Graphics g) {
		if (play) {
			int x = 0;
			int y = 0;
			for (int i=0; i<frame.length; i++) {
				if (frame[i] == 0x00) g.setColor(0x0);
				else g.setColor(0xFFFFFF);
				
				g.fillRect(x, y, 1, 1);
				x++;
				if (x > get_width() - 1) {
					x = 0;
					y++;
				}
			}
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
