package com.hotsprings.scene;

import com.melody.core.Scene;
import com.melody.display.Mimage;
import com.melody.enums.KeyCodeEnum;
import com.melody.utils.RandomUtils;

public class TestScene extends Scene {
	
	private Mimage img;

	public TestScene() {

	}
	
	public void initialize() {
		System.out.println("Test Scene Initialized!");
		
//		MyObject obj = new MyObject("My Object");
//		addChild(obj);
		
		img = new Mimage("example");
		img.set_buffer("/image/example.png");
		addChild(img);
	}
	
	public void update(long dt) {
		if (get_input().isReleased(KeyCodeEnum.CENTER)) {
			img.x = RandomUtils.range(get_width() - img.get_buffer().getWidth(), 2384786);
			img.y = RandomUtils.range(get_height() - img.get_buffer().getHeight(), 9437272);
		}
	}

}
