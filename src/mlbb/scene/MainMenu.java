package mlbb.scene;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.core.Scene;
import mlbb.display.Font;
import mlbb.display.mainmenu.FriendList;

public class MainMenu extends Scene {
	
	private Image bg;
	private FriendList friendList;
	
	private Image classic;
	private Image startBtn;
	private Image arrow;
	
	private Image[] events;
	private Image event_buffer;
	private boolean event_buffer_redraw = false;
	private int event_buffer_redraw_cursor = 0;
	private int event_state = 0;
	private int event_waitCounter = 0;
	
	private Random r = new Random();
	private int chat_counter = 0;
	private int chat_max = 2000;
	private int chat_state = 0;
	private String[] chat = {
			"-1 rank",
			"push wr CL smurf",
			"invt rank",
			"rank -1 tank",
			"mabar clasik",
			"troll inv",
			"SPAM CLASSIC WR",
			"classic wr 95+",
			"bantu ranked",
			"yuk main",
			"push wr GM SMURF",
			"-1 rank core",
			"ada yg mw mabar gak",
			"-1",
			"kenak mental a**",
			"JUAL AKUN MYTIC",
			"epic abadi ***",
			"wibu gila",
			"-1 troll",
			"ikut rank capek lose",
			"ayo bg",
			"WOII RANK SERIUS",
			"need core",
			"pe by one zilong",
			"by one ayo",
			"by1 suuu",
			"invit yo",
			"bnyk bcd yah",
			"yank butuh rank mana nih",
			"cari aldous",
			"mabar bos",
			"colbar sini",
			"-1 troll gm",
			"p",
			"main apa *** bingung",
			"PUNYAKU 20 CM CROOT",
			"z",
			"brawl gan",
			"invt brawl",
			"bantu brawl gan",
			"sini brawl",
			"brawl pls"
	};

	public MainMenu() {
		try {
			bg = Image.createImage(240, 320);
			friendList = new FriendList();
			Graphics g = bg.getGraphics();
			
			classic = Image.createImage("/mlbb/mainmenu/classic.png");
			startBtn = Image.createImage("/mlbb/mainmenu/start_btn.png");
			arrow = Image.createImage("/mlbb/mainmenu/arrow.png");
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/bg_final_baked_op.jpg"), 0, 0, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/avatar.png"), 5, 5, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/ticket.png"), 101, 8, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/bp.png"), 100, 25, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/diamond.png"), 167, 25, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/signal.png"), 195, 11, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/battery.png"), 211, 12, Graphics.LEFT | Graphics.TOP);
			g.setColor(0x00FF00);
			g.fillRect(213, 14, 4, 3);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 5, 320-5, Graphics.LEFT | Graphics.BOTTOM);
			g.drawRegion(Image.createImage("/mlbb/mainmenu/menu_frame.png"), 0, 0, 85, 29, Sprite.TRANS_MIRROR, 240-5, 320-5, Graphics.RIGHT | Graphics.BOTTOM);
			g.drawImage(Image.createImage("/mlbb/mainmenu/info.png"), 12, 320-11, Graphics.LEFT | Graphics.BOTTOM);
			g.drawImage(Image.createImage("/mlbb/mainmenu/menu.png"), 240-12, 320-11, Graphics.RIGHT | Graphics.BOTTOM);
			
			Font.font2.render("Scarlet", 40, 8, g);
			Font.font.render("30", 38, 26, g);
			
			Font.font.render("100", 116, 10, g);
			Font.font.render("32000", 116, 27, g);
			Font.font.render("0", 185, 27, g);
			
			Font.font.render("Info", 47, 296, g);
			Font.font.render("Menu", 164, 296, g);
			
			// exp
			g.setColor(0x3067B3);
			g.fillRect(56, 30, 40, 3);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/chat.png"), 7, 266, Graphics.LEFT | Graphics.TOP);
			
			g.drawImage(Image.createImage("/mlbb/mainmenu/event/f2.png"), 10, 47, Graphics.LEFT | Graphics.TOP);
			g.drawImage(Image.createImage("/mlbb/mainmenu/event/f1.png"), 50, 47, Graphics.LEFT | Graphics.TOP);
			g.fillRect(15, 79, 20, 3);
			
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
			event_buffer = Image.createImage(events[0].getWidth(), events[0].getHeight());
			event_buffer.getGraphics().drawImage(events[event_state], 0, 0, Graphics.LEFT | Graphics.TOP);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void initialize() {
		friendList.x = 240 - friendList.width - 10;
		friendList.y = 51;
	}

	public void update(long dt) {
//		int[] touch = get_input().getTouchCoord(TouchPhase.END);
//		if (touch != null) {
//			tempX = touch[0];
//			tempY = touch[1];
//			System.out.println(tempX + " " + tempY);
//		}
		
		requestRender();
		
		event_waitCounter += dt;
		if (event_waitCounter > 4000) {
			event_buffer_redraw = true;
			event_buffer_redraw_cursor = 0;
			event_waitCounter = 0;
			event_state++;
			if (event_state > events.length-1) event_state = 0;
		}
		
		if (event_buffer_redraw && event_buffer_redraw_cursor < event_buffer.getWidth()) {
			int tempWidth = 5;
			if ((tempWidth + event_buffer_redraw_cursor) > event_buffer.getWidth()) 
				tempWidth = event_buffer.getWidth() - event_buffer_redraw_cursor;
			
			event_buffer.getGraphics().drawRegion(
					events[event_state],
					event_buffer_redraw_cursor, 0,
					tempWidth, event_buffer.getHeight(),
					Sprite.TRANS_NONE,
					event_buffer_redraw_cursor, 0,
					Graphics.TOP | Graphics.LEFT);
			
			event_buffer_redraw_cursor += tempWidth;
		} else {
			event_buffer_redraw = false;
			event_buffer_redraw_cursor = 0;
		}
		
		chat_counter += dt;
		if (chat_counter > chat_max) {
			chat_counter = 0;
			chat_state = (int)(r.nextFloat() * (chat.length-1));
			chat_max = (int)(r.nextFloat() * 2000) + 2000;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, Graphics.LEFT | Graphics.TOP);
		
		// exp bar
		g.setColor(0xD0D1FF);
		g.fillRect(56, 30, (int)(40*0.3), 3);
		
		g.drawImage(classic, 240/2, (int)(195 + Math.sin(System.currentTimeMillis() * 0.0005) * 5), Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(startBtn, 240/2, 231, Graphics.HCENTER | Graphics.TOP);
		
		g.drawImage(arrow, (int)(19 + Math.cos(System.currentTimeMillis() * 0.004) * 2), 230, Graphics.LEFT | Graphics.TOP);
		g.drawRegion(arrow, 0, 0, arrow.getWidth(), arrow.getHeight(), Sprite.TRANS_MIRROR, (int)(195 + Math.cos(System.currentTimeMillis() * 0.004) * -2), 230, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(event_buffer, 10, 87, Graphics.LEFT | Graphics.TOP);
		Font.font.render(chat[chat_state], 37, 269, g);
		
		friendList.render(g);
	}

	public void destroy() {
		friendList.destroy();
	}

}
