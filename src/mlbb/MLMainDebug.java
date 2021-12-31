package mlbb;

import java.io.IOException;

import javax.microedition.lcdui.Image;

import melody.core.MainEngine;
import melody.display.BMFont;
import mlbb.display.Font;
import mlbb.scene.MainMenu;

public class MLMainDebug extends MainEngine {

	public MLMainDebug() {
		super("MLBB Simulator");
		
		try {
			Font.font = new BMFont(Image.createImage("/mlbb/font/font.png"), Font.bmf_font);
			Font.font2 = new BMFont(Image.createImage("/mlbb/font/font2.png"), Font.bmf_font2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		setupGame(20, true);
		get_gameRoot().get_stat().color = 0xFFFF00;
	}

	public void update() {

	}

	public void handleGameReady() {
		get_gameRoot().set_scene(new MainMenu());
	}

}
