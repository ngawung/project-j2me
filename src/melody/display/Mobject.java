package melody.display;

import javax.microedition.lcdui.Graphics;

public abstract class Mobject {
	
	public int[] data;
	
	public abstract void initialize();
	public abstract void update(long dt);
	public abstract void render(Graphics g);
	public abstract void destroy();
	
}
