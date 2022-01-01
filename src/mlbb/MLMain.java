package mlbb;

import java.io.IOException;

import javax.microedition.lcdui.Image;

import melody.core.MainEngine;
import melody.display.BMFont;
import mlbb.display.Font;
//#ifdef DEBUG
import mlbb.scene.Shop;
//#else
import mlbb.scene.intro.SplashScreen;
//#endif

public class MLMain extends MainEngine {

	public MLMain() {
		super("MLBB Simulator");
		
		try {
			Font.font = new BMFont(Image.createImage("/mlbb/font/font.png"), Font.bmf_font);
			Font.font2 = new BMFont(Image.createImage("/mlbb/font/font2.png"), Font.bmf_font2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		//#ifdef DEBUG
		setupGame(20, true);
		get_gameRoot().get_stat().color = 0xFFFF00;
		//#else
		setupGame(20, false);
		//#endif
	}

	public void update() {

	}

	public void handleGameReady() {
		//#ifdef DEBUG
//		get_gameRoot().set_scene(new MainMenu(false));
		get_gameRoot().set_scene(new Shop());
		//#else
		get_gameRoot().set_scene(new SplashScreen());
		//#endif
	}

}
