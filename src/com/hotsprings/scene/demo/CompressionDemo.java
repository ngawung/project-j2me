package com.hotsprings.scene.demo;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.microedition.lcdui.Graphics;
import com.melody.core.Scene;
import com.tinyline.util.GZIPInputStream;

public class CompressionDemo extends Scene {
	
	private char[] frame = new char[5400];
	private int timePassed = 0;
	private int fps = 10;
	private InputStreamReader out;
	private GZIPInputStream gzip2;
	private DataInputStream in;
	private boolean isDone = false;

	public CompressionDemo() {
		// TODO Auto-generated constructor stub
		get_soundManager().load("/demo/sound/bad.amr");
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
		try {
			gzip2 = new GZIPInputStream(getClass().getResourceAsStream("/demo/file/compress"));
			out = new InputStreamReader(gzip2, "hex");
//			in = new DataInputStream(gzip2);
			
			int pixel = 0;
			while (true) {
				if (out.read(frame, 0, frame.length) == -1) break;
				pixel++;
				System.out.println(pixel);
				if (pixel > 5400) {
					timePassed++;
					pixel = 0;
					System.out.println(timePassed);
				}
			}
			
			get_soundManager().play();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void update(long dt) {
//		timePassed += dt;
//		
//		while (timePassed > (1000 / fps) && !isDone) {
//			try {
//				
//				if (out.read(frame, 0, frame.length) == -1) {
//					System.out.println("done");
//					isDone = true;
//					out.close();
//					gzip2.close();
//				}
//				
//				System.out.println("render");
//				requestRender();
//				timePassed -= (1000 / fps);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public void render(Graphics g) {
//		int x = 0;
//		int y = 0;
//		
//		for (int i=0; i<frame.length; i++) {
//			for (int k=0; k<8; k++) {
//				if (bitStatus(frame[i], k) > 0) g.setColor(0xFFFFFF);
//				else g.setColor(0x0);
//				
//				g.fillRect(x, y, 1, 1);
//				x++;
//				if (x > get_width() - 1) {
//					x = 0;
//					y++;
//				}
//			}
//		}
	}
	
	private int bitStatus(byte c, int bit) {
	    return (c >> bit) & 1;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
