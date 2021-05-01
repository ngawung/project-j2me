package com.hotsprings.scene;

import com.hotsprings.scene.demo.ImageDemo;
import com.hotsprings.scene.demo.ImageStress;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;
import com.melody.utils.CoordUtils;

public class MenuSelector extends Scene {
	private final int ITEM_PER_PAGE = 6;
	
	private int selected = 0;
	private int currentPage = 0;
	
	private MText left = new MText("prev", "prev", 0x0);
	private MText right = new MText("next", "next", 0x0);
	private MText center = new MText("ok", "ok", 0x0);
	
	private MText page = new MText("page", "", 0x0);
	
	public String[] menuName = new String[]{
			"0) Image Demo",
			"1) Image Stress test",
			"2) Image add/remove test",
			"3) Moviclip Demo",
			"4) Moviclip Stress test",
			"5) Moviclip add/remove test",
			"6) Text Demo",
			"7) Keypad Input Demo",
			"8) Touch Input Demo",
			"9) Sound test (1 player)",
			"10) Sound test (2 player)",
			"11) Sound test (mp3)",
			"12) Transform",
			"13) Random",
			"14) Change framerate",
	};

	public MenuSelector() {
		// TODO Auto-generated constructor stub
	}

	public void initialize() {
		System.out.println("init");
		left.y = get_height() - left.get_height() - 5;
		left.x = 5;
		right.y = get_height() - right.get_height() - 5;
		right.x = get_width() - right.get_width() - 5;
		center.y = get_height() - center.get_height() - 5;
		center.x = get_width() / 2 - center.get_width() / 2;
		page.y = 40 + (30 * ITEM_PER_PAGE) + 20;
		
		for (int i=0; i<ITEM_PER_PAGE; i++) {
			MText menu = new MText("menu_" + i, menuName[currentPage * ITEM_PER_PAGE + i], 0x0);
			menu.x = 20;
			menu.y = 40 + (30 * i);
			addChild(menu);
		}
		
		addChild(left);
		addChild(right);
		addChild(center);
		addChild(page);
		
		updateSelected(selected);
		updatePage(currentPage);
	}

	public void update(long dt) {
		int[] coord = get_input().getTouchCoord(TouchPhase.BEGIN);
		
		if (coord != null) {
			for (int i=0; i<ITEM_PER_PAGE; i++) {
				MText temp = (MText)getChildByName("menu_" + i);
				if (!temp.visible) continue;
				if (CoordUtils.pointInRect(coord[0], coord[1], temp.x, temp.y, temp.get_width(), temp.get_height())) {
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
		else if (newSelected > ITEM_PER_PAGE - 1) newSelected = ITEM_PER_PAGE - 1;
		else if (currentPage == ((int)(Math.ceil((float)menuName.length / (float)ITEM_PER_PAGE)) - 1)) {
			int lastPageItemList = menuName.length - ITEM_PER_PAGE * ((int)(Math.ceil((float)menuName.length / (float)ITEM_PER_PAGE)) - 1);
			if (newSelected > lastPageItemList - 1) newSelected = lastPageItemList - 1;
		}
		
		((MText)getChildByName("menu_" + selected)).color = 0x0;
		((MText)getChildByName("menu_" + newSelected)).color = 0xFF0000;
		selected = newSelected;
	}
	
	public void updatePage(int newPage) {
		int maxPage = (int)(Math.ceil((float)menuName.length / (float)ITEM_PER_PAGE)) - 1;
		if (newPage < 0) currentPage = 0;
		else if (newPage > maxPage) currentPage = maxPage;
		else currentPage = newPage;
		
		for (int i=0; i<ITEM_PER_PAGE; i++) {
			MText temp = (MText)getChildByName("menu_" + i);
			temp.visible = true;
			
			int menuNum = currentPage * ITEM_PER_PAGE + i;
			if (menuNum < menuName.length) temp.text = menuName[currentPage * ITEM_PER_PAGE + i];
			else temp.visible = false;
		}
		
		updateSelected(0);
		page.text = "Page " + currentPage + " of " + maxPage;
		page.x = get_width() / 2 - page.get_width() / 2;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	public void selectMenu() {
		int currSelect = currentPage * ITEM_PER_PAGE + selected;
		switch(currSelect) {
			case 0: _e.get_gameRoot().set_scene(new ImageDemo());
			case 1: _e.get_gameRoot().set_scene(new ImageStress());
		}
	}

}
