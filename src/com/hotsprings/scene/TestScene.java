package com.hotsprings.scene;

import com.melody.core.Scene;
import com.melody.display.Mimage;
import com.melody.display.Movieclip;
import com.melody.display.Quad;
import com.melody.enums.KeyCodeEnum;
import com.melody.utils.RandomUtils;

public class TestScene extends Scene {
	
	private Mimage img;
	private Quad q;

	public TestScene() {

	}
	
	public void initialize() {
		System.out.println("Test Scene Initialized!");
		
//		MyObject obj = new MyObject("My Object");
//		addChild(obj);
		
		img = new Mimage("example");
		img.set_buffer("/image/example.png");
		addChild(img);
		
		q = new Quad("quad", 0, 0, 30, 30, 0xFF0000);
		addChild(q);
		
		Movieclip mov = new Movieclip("test");
		mov.set_buffer("/image/example.png", 50, 50);
		mov.frameData = new int[2];
		mov.frameData[0] = 0;
		mov.frameData[1] = 0;
		mov.x = 120;
		mov.y = 120;
		addChild(mov);
	}
	
	public void update(long dt) {
		if (get_input().isReleased(KeyCodeEnum.CENTER)) {
			img.x = RandomUtils.range(get_width() - img.get_buffer().getWidth(), 2384786);
			img.y = RandomUtils.range(get_height() - img.get_buffer().getHeight(), 9437272);
			
			q.fill = !q.fill;
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
