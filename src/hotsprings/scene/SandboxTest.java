package hotsprings.scene;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.tinyline.util.GZIPInputStream;

import melody.core.Scene;

public class SandboxTest extends Scene {
	
	private Image test;
	int len;
    int counter = 0;
    int total = 0;
    long start = 0;
	public SandboxTest() {
		try {
//			InputStream in = getClass().getResourceAsStream("/mlbb/heroes/edith2.png");
//			int length = in.available();
//			byte[] temp = new byte[length];
//			in.read(temp, 0, length);
//			test = Image.createImage(temp, 0, length);
			
			InputStream in = getClass().getResourceAsStream("/demo/compress");
			GZIPInputStream gzip = new GZIPInputStream(in);
			
			System.out.println(in.available());
			System.out.println(gzip.available());
			
//			gzip.skip(11046);
			start = System.currentTimeMillis();
			test = Image.createImage(gzip);
			System.out.println(System.currentTimeMillis() - start);
			
//			byte[] buffer = new byte[1024];
//			
//            start = System.currentTimeMillis();
//            while ((len = gzip.read(buffer, 0 ,1024)) > 0) {
//            	counter++;
//            	total += len;
//            	System.out.println(total);
//            }
//            start = System.currentTimeMillis() - start;
//            System.out.println("counter: ");
//            System.out.println(counter);
//            System.out.println(total);
//            System.out.println(start);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		requestRender();
	}

	public void update(long dt) {

	}

	public void render(Graphics g) {
		g.drawImage(test, 0, 0, Graphics.LEFT | Graphics.TOP);
		g.setColor(0x0);
		g.drawString("time: " + start, 10, 40, Graphics.LEFT | Graphics.TOP);
	}

	public void destroy() {

	}

}
