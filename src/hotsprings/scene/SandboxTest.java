package hotsprings.scene;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import com.tinyline.util.GZIPInputStream;

import melody.core.Scene;
import melody.enums.BMFAlign;
import melody.enums.KeyCodeEnum;
import mlbb.display.Font;

public class SandboxTest extends Scene {
	
	private Image test;
	private Image bg;
	GZIPInputStream gzip;
	InputStream in;
	long counter = 0;
    
    char headerState = 0;
    int[] header = {
    	0,
		12745,
		26806,
		40762,
		54033,
		67718,
//		81430,
    };
    
	public SandboxTest() {
		try {
			bg = Image.createImage(240, 320);
			Graphics g = bg.getGraphics();
			
			g.drawImage(Image.createImage("/mlbb/heroes/bg_crop.jpg"), 0, 0, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 5, 320-5, Graphics.LEFT | Graphics.BOTTOM);
			g.drawRegion(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 0, 0, 85, 29, Sprite.TRANS_MIRROR, 240-5, 320-5, Graphics.RIGHT | Graphics.BOTTOM);
			
			Font.font.render("Details", 41, 296, g);
			Font.font.render("Skins", 200, 296, BMFAlign.RIGHT, g);
			
			in = getClass().getResourceAsStream("/mlbb/heroes/layla");
			
			test = Image.createImage(in);
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void next() {
		try {
			headerState++;
			if (headerState > header.length-1) headerState = 0;
			
			in = getClass().getResourceAsStream("/mlbb/heroes/layla");
			
			if (headerState != 0) in.skip(header[headerState]);
			
			test = Image.createImage(in);
			
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		
	}

	public void update(long dt) {
		counter += dt;
		if (counter >= 1000/8) {
			counter = 0;
			next();
		}
		
		if (get_input().isDown(KeyCodeEnum.CENTER)) next();
		
		requestRender();
	}

	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, Graphics.LEFT | Graphics.TOP);
		g.drawImage(test, 47, 30, Graphics.LEFT | Graphics.TOP);
//		g.setColor(0x0);
//		g.drawString("time: " + start, 10, 40, Graphics.LEFT | Graphics.TOP);
	}

	public void destroy() {

	}

}
