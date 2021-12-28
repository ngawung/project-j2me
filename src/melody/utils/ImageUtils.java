package melody.utils;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ImageUtils {
	
	public final static Image flipX(Image src) {
		int width = src.getWidth();
		int height = src.getHeight();
		Image result = Image.createImage(width, height);
		Graphics g = result.getGraphics();
		
		for (int x=0; x<width; x++) { 
			g.setClip(x, 0, 1, height); 
			g.drawImage(src, x * 2 - width, 0, Graphics.TOP | Graphics.LEFT); 
		}
		
		return result;
	}
	
	public final static Image flipY(Image src) {
		int width = src.getWidth();
		int height = src.getHeight();
		Image result = Image.createImage(width, height);
		Graphics g = result.getGraphics();
		
		for (int y=0; y<width; y++) { 
			g.setClip(0, y, width, 1); 
			g.drawImage(src, 0, y * 2 - height, Graphics.TOP | Graphics.LEFT); 
		}
		
		return result;
	}
	
	public final static Image rotate90(Image src) {
		int width = src.getWidth();
		int height = src.getHeight();
		Image result = Image.createImage(width, height);
		Graphics g = result.getGraphics();
		
		for (int y=0; y<height; y++) { 
			for (int x=0; x<width; x++) {
				g.setClip(x, y, 1, 1);
				g.drawImage(src, x - y, y + x - height, Graphics.TOP | Graphics.LEFT);
				
			}
		}
		
		return result;
	}
	
	public final static Image rotate180(Image src) {
		int width = src.getWidth();
		int height = src.getHeight();
		Image result = Image.createImage(width, height);
		Graphics g = result.getGraphics();
		
		for (int y=0; y<height; y++) { 
			for (int x=0; x<width; x++) {
				g.setClip(x, y, 1, 1);
				g.drawImage(src, x * 2 - width, y * 2 - height, Graphics.TOP | Graphics.LEFT);
			}
		}

		return result;
	}
	
	public final static Image rotate270(Image src) {
		int width = src.getWidth();
		int height = src.getHeight();
		Image result = Image.createImage(width, height);
		Graphics g = result.getGraphics();
		
		for (int y=0; y<height; y++) { 
			for (int x=0; x<width; x++) {
				g.setClip(x, y, 1, 1);
				g.drawImage(src, x + y - width, y - x, Graphics.TOP | Graphics.LEFT);
			}
		}
		
		return result;
	}
	
	// mirror rotation comming soon
	// need break... drawing pixel per pixel really annoying... lol

}
