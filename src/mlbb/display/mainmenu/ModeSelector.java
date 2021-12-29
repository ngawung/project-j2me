package mlbb.display.mainmenu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import melody.display.Mobject;
import melody.utils.RandomUtils;
import mlbb.display.Font;

public class ModeSelector extends Mobject {
	public int x = 0;
	public int y = 0;
	
	private Image classic;
	private Image startBtn;
	private Image arrow;
	private Image chatBg;
	
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

	public ModeSelector() {
		try {
			classic = Image.createImage("/mlbb/mainmenu/classic.png");
			startBtn = Image.createImage("/mlbb/mainmenu/start_btn.png");
			arrow = Image.createImage("/mlbb/mainmenu/arrow.png");
			chatBg = Image.createImage("/mlbb/mainmenu/chat.png");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {

	}

	public void update(long dt) {
		chat_counter += dt;
		if (chat_counter > chat_max) {
			chat_counter = 0;
			chat_state = RandomUtils.rand(0, chat.length);
			chat_max = RandomUtils.rand(2000, 4000);
		}
	}

	public void render(Graphics g) {
		g.drawImage(classic, 240/2+x, (int)(y + Math.sin(System.currentTimeMillis() * 0.0005) * 5), Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(startBtn, 240/2+x, 36+y, Graphics.HCENTER | Graphics.TOP);
		
		g.drawImage(arrow, (int)(x + 19 + Math.cos(System.currentTimeMillis() * 0.004) * 2), 35+y, Graphics.LEFT | Graphics.TOP);
		g.drawRegion(arrow, 0, 0, arrow.getWidth(), arrow.getHeight(), Sprite.TRANS_MIRROR, (int)(x+ 195 + Math.cos(System.currentTimeMillis() * 0.004) * -2), 35+y, Graphics.LEFT | Graphics.TOP);
		
		g.drawImage(chatBg, 7+x, 71+y, Graphics.LEFT | Graphics.TOP);
		Font.font.render(chat[chat_state], 37, 269, g);
	}

	public void destroy() {

	}

}
