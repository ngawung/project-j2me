package melody.core;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class StatDisplay {
	public int color = 0x0;
	
	private int frameCount = 0;
	private long timePassed = 0;
	
	private float framerate;
	private long memUsage;
	
	private Runtime runtime;
	private final int UPDATE_DELAY = 3000;
	
	private Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
	
	public StatDisplay() {
		runtime = Runtime.getRuntime();
	}
	
	public void update(long dt) {
		frameCount = (frameCount == Integer.MAX_VALUE)?1:frameCount+1;
		timePassed += dt;
		
		if (timePassed >= UPDATE_DELAY) {
			framerate = ((float)frameCount / ((float)timePassed / 1000));
			
			// fixed decimal
			int factor = 100;
			int scaled_and_rounded = (int)(framerate * factor + 0.5);
			framerate = (float)scaled_and_rounded / factor;

			memUsage = ((runtime.totalMemory() - runtime.freeMemory()));
			frameCount = 0;
			timePassed = 0;
			
			MainEngine.get_instance().requestRender();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		
		// draw fps
		g.drawString("fps: " + framerate, 0, 0, Graphics.TOP | Graphics.LEFT);
		
		// draw mem usage
		g.drawString("mem: " + (memUsage / 1000) + "kb", 0, (int)(font.getHeight() * 0.8), Graphics.TOP | Graphics.LEFT);
	}
	
}
