package mlbb.display.mainmenu;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import melody.display.Mobject;
import mlbb.display.Font;

public class FriendList extends Mobject {
	public int x = 0;
	public int y = 0;
	public final int width = 100;
	public final int height = 70;
	
	private Image friend1;
	private Image friend2;
	private Image group;
	private Image add;

	public FriendList() {
		try {
			friend1 = Image.createImage("/mlbb/mainmenu/friend1.png");
			friend2 = Image.createImage("/mlbb/mainmenu/friend2.png");
			group = Image.createImage("/mlbb/mainmenu/group.png");
			add = Image.createImage("/mlbb/mainmenu/add.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {

	}

	public void update(long dt) {

	}

	public void render(Graphics g) {
		Font.font.render("Online 0/0", 8+x, 0+y, g);
		g.setColor(0x3067B3);
		g.drawLine(5+x, 12+y, 5+90+x, 12+y);
		
		g.drawImage(friend1, 20+x, 23+y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(friend2, 56+x, 23+y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(group, 59+x, 54+y, Graphics.LEFT | Graphics.TOP);
		g.drawImage(add, 79+x, 54+y, Graphics.LEFT | Graphics.TOP);
	}

	public void destroy() {
		friend1 = null;
		friend2 = null;
		group = null;
		add = null;
	}

}
