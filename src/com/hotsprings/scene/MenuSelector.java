package com.hotsprings.scene;

import javax.microedition.lcdui.Graphics;

import com.hotsprings.scene.demo.AffineTransformDemo;
import com.hotsprings.scene.demo.BitmapFontDemo;
import com.hotsprings.scene.demo.Capabilities;
import com.hotsprings.scene.demo.ImageDemo;
import com.hotsprings.scene.demo.ImageStress;
import com.hotsprings.scene.demo.KeypadDemo;
import com.hotsprings.scene.demo.MovieclipDemo;
import com.hotsprings.scene.demo.MovieclipStress;
import com.hotsprings.scene.demo.Player1Demo;
import com.hotsprings.scene.demo.TextDemo;
import com.hotsprings.scene.demo.TouchDemo;
import com.hotsprings.scene.demo.game.GameMechanicTest;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;

public class MenuSelector extends Scene {
	private final int ITEM_PER_PAGE = 6;
	
	private int selected = 0;
	private int currentPage = 0;
	
	private MText left = new MText("prev", 0x0);
	private MText right = new MText("next", 0x0);
	private MText center = new MText("ok", 0x0);
	
	private MText page = new MText("", 0x0);
	
	private MText[] menuList;
	public String[] menuName = new String[]{
			"Device Capabilites",
			"Image Demo",
			"Image Stress test",
			"Moviclip Demo",
			"Moviclip Stress test",
			"Text Demo",
			"BitmapFont Demo",
			"Keypad Input Demo",
			"Touch Input Demo",
			"Sound test (1 player)",
//			"Change framerate",
			"Game Mechanic test",
			"Affine Transform",
	};

	public MenuSelector() {
		
	}

	public void initialize() {
		left.y = get_height() - left.get_height() - 5;
		left.x = 5;
		right.y = get_height() - right.get_height() - 5;
		right.x = get_width() - right.get_width() - 5;
		center.y = get_height() - center.get_height() - 5;
		center.x = get_width() / 2 - center.get_width() / 2;
		page.y = 40 + (30 * ITEM_PER_PAGE) + 20;
		
		menuList = new MText[ITEM_PER_PAGE];
		for (int i=0; i<menuList.length; i++) {
			menuList[i] = new MText("", 0x0);
			menuList[i].x = 20;
			menuList[i].y = 40 + (30 * i);
		}
		
		updateSelected(selected);
		updatePage(currentPage);
		
		requestRender();
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			for (int i=0; i<menuList.length; i++) {
				if (!menuList[i].visible) continue;
				if (CoordUtils.pointInRect(coord[0], coord[1], (int)menuList[i].x, (int)menuList[i].y, menuList[i].get_width(), menuList[i].get_height())) {
					updateSelected(i);
					break;
				}
			}
			
			if (CoordUtils.pointInRect(coord[0], coord[1], 0, get_height() - 40, 80, 40)) updatePage(currentPage - 1);
			else if (CoordUtils.pointInRect(coord[0], coord[1], 80, get_height() - 40, 80, 40)) selectMenu();
			else if (CoordUtils.pointInRect(coord[0], coord[1], 160, get_height() - 40, 80, 40)) updatePage(currentPage + 1);
		}
		
		if (get_input().isDown(KeyCodeEnum.SOFTKEY_LEFT)) updatePage(currentPage - 1);
		else if (get_input().isDown(KeyCodeEnum.CENTER)) selectMenu();
		else if (get_input().isDown(KeyCodeEnum.SOFTKEY_RIGHT)) updatePage(currentPage + 1);
		
		else if (get_input().isDown(KeyCodeEnum.UP) || get_input().isDown(KeyCodeEnum.KEY_2)) updateSelected(selected - 1);
		else if (get_input().isDown(KeyCodeEnum.DOWN) || get_input().isDown(KeyCodeEnum.KEY_8)) updateSelected(selected + 1);
	}
	
	public void updateSelected(int newSelected) {
		if (newSelected < 0) newSelected = 0;
		else if (newSelected > menuList.length - 1) newSelected = menuList.length - 1;
		else if (currentPage == ((int)(Math.ceil((float)menuName.length / (float)menuList.length)) - 1)) {
			int lastPageItemList = menuName.length - menuList.length * ((int)(Math.ceil((float)menuName.length / (float)menuList.length)) - 1);
			if (newSelected > lastPageItemList - 1) newSelected = lastPageItemList - 1;
		}
		
		menuList[selected].color = 0x0;
		menuList[newSelected].color = 0xFF0000;
		selected = newSelected;
		
		requestRender();
	}
	
	public void render(Graphics g) {
		for (int i=0; i<menuList.length; i++) {
			menuList[i].render(g);
		}
		
		left.render(g);
		right.render(g);
		center.render(g);
		page.render(g);
	}
	
	public void updatePage(int newPage) {
		int maxPage = (int)(Math.ceil((float)menuName.length / (float)menuList.length)) - 1;
		if (newPage < 0) currentPage = 0;
		else if (newPage > maxPage) currentPage = maxPage;
		else currentPage = newPage;
		
		for (int i=0; i<menuList.length; i++) {
			menuList[i].visible = true;
			
			int menuNum = currentPage * menuList.length + i;
			if (menuNum < menuName.length) menuList[i].set_text(i+(currentPage*ITEM_PER_PAGE) + ") " + menuName[currentPage * menuList.length + i]);
			else menuList[i].visible = false;
		}
		
		updateSelected(0);
		page.set_text("Page " + currentPage + " of " + maxPage);
		page.x = get_width() / 2 - page.get_width() / 2;
		
		requestRender();
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	public void selectMenu() {
		int currSelect = currentPage * ITEM_PER_PAGE + selected;
		switch(currSelect) {
			case 0: _e.get_gameRoot().set_scene(new Capabilities()); break;
			case 1: _e.get_gameRoot().set_scene(new ImageDemo()); break;
			case 2: _e.get_gameRoot().set_scene(new ImageStress()); break;
			case 3: _e.get_gameRoot().set_scene(new MovieclipDemo()); break;
			case 4: _e.get_gameRoot().set_scene(new MovieclipStress()); break;
			case 5: _e.get_gameRoot().set_scene(new TextDemo()); break;
			case 6: _e.get_gameRoot().set_scene(new BitmapFontDemo()); break;
			case 7: _e.get_gameRoot().set_scene(new KeypadDemo()); break;
			case 8: _e.get_gameRoot().set_scene(new TouchDemo()); break;
			case 9: _e.get_gameRoot().set_scene(new Player1Demo()); break;
//			case 9: _e.get_gameRoot().set_scene(new ChangeFramerate()); break;
			case 10: _e.get_gameRoot().set_scene(new GameMechanicTest()); break;
			case 11: _e.get_gameRoot().set_scene(new AffineTransformDemo()); break;
		}
	}

}
