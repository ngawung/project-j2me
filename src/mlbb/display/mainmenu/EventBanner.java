package mlbb.display.mainmenu;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.display.Mobject;

public class EventBanner extends Mobject {
	public Image buffer;
	public int x = 0;
	public int y = 0;
	
	private Image[] events;
	private boolean redraw = false;
	private int cursor = 0;
	private int state = 0;
	private int waitCounter = 0;
	
	private Image icon1;
	private Image icon2;

	public EventBanner() {
		try {
		String[] event_name = {
			"/mlbb/mainmenu/event/1_op.jpg",
			"/mlbb/mainmenu/event/2_op.jpg",
			"/mlbb/mainmenu/event/3_op.jpg",
			"/mlbb/mainmenu/event/4_op.jpg",
			"/mlbb/mainmenu/event/5_op.jpg",
			"/mlbb/mainmenu/event/6_op.jpg"
		};
		
		events = new Image[6];
		for (int i=0; i<event_name.length; i++) {
			events[i] = Image.createImage(event_name[i]);
		}
		
		buffer = Image.createImage(events[0].getWidth(), events[0].getHeight());
		buffer.getGraphics().drawImage(events[state], 0, 0, Graphics.LEFT | Graphics.TOP);
		
		icon1 = Image.createImage("/mlbb/mainmenu/event/f2.png");
		icon2 = Image.createImage("/mlbb/mainmenu/event/f1.png");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {

	}

	public void update(long dt) {
		waitCounter += dt;
		if (waitCounter > 4000) {
			redraw = true;
			cursor = 0;
			waitCounter = 0;
			state++;
			if (state > events.length-1) state = 0;
		}
		
		if (redraw && cursor < buffer.getWidth()) {
			int tempWidth = 5;
			if ((tempWidth + cursor) > buffer.getWidth()) 
				tempWidth = buffer.getWidth() - cursor;
			
			buffer.getGraphics().drawRegion(
					events[state],
					cursor, 0,
					tempWidth, buffer.getHeight(),
					Sprite.TRANS_NONE,
					cursor, 0,
					Graphics.TOP | Graphics.LEFT);
			
			cursor += tempWidth;
		} else {
			redraw = false;
			cursor = 0;
		}
	}

	public void render(Graphics g) {
		g.drawImage(icon1, x, y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(icon2, 40+x, y, Graphics.LEFT | Graphics.TOP);
		g.fillRect(5+x, 32+y, 20, 3);
		
		g.drawImage(buffer, x, 40+y, Graphics.LEFT | Graphics.TOP);
	}

	public void destroy() {
		buffer = null;
		events = null;
	}

}
