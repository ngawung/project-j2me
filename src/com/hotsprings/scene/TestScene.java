package com.hotsprings.scene;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStore;
import com.melody.core.Scene;
import com.melody.display.MText;
import com.melody.display.Mimage;
import com.melody.display.Movieclip;
import com.melody.display.Quad;
import com.melody.enums.KeyCodeEnum;
import com.melody.utils.RandomUtils;

public class TestScene extends Scene {
	
	private Mimage img;
	private Quad q;
	private Movieclip mov;
	
	private RecordStore res;

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
		
		mov = new Movieclip("test");
		mov.set_buffer("/image/example.png", 50, 50);
		
		mov.frameData = new int[]{0, 0, 50, 50};
		mov.play(10, new int[]{0, 1}, true);
		
		mov.x = 120;
		mov.y = 120;
		addChild(mov);
		
		MText text = new MText("text", "My Text", 0x0);
		text.x = 15;
		text.y = 140;
		addChild(text);
		
//		_e.saveManager.removeAll();
		
		try {
			res = RecordStore.openRecordStore("hotsprings", true);
			
			byte[] temp = res.getRecord(1);
			ByteArrayInputStream out = new ByteArrayInputStream(temp);
			DataInputStream dis = new DataInputStream(out);
			
			img.x = dis.readInt();
			img.y = dis.readInt();
			
			dis.close();
			out.close();
			temp = null;
			
		} catch (Exception e) {
			System.out.println("Error, " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void update(long dt) {
		if (get_input().isReleased(KeyCodeEnum.CENTER)) {
			img.x = RandomUtils.range(get_width() - img.get_buffer().getWidth(), 2384786);
			img.y = RandomUtils.range(get_height() - img.get_buffer().getHeight(), 9437272);
			
			q.fill = !q.fill;
			
			mov.x = RandomUtils.range(get_width() - mov.width, 83264);
			mov.y = RandomUtils.range(get_width() - mov.height, 348972);
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				DataOutputStream dos = new DataOutputStream(out);
				dos.writeInt(img.x);
				dos.writeInt(img.y);
				
				System.out.println(out.size());
				
//				res.addRecord(out.toByteArray(), 0, out.size());
				res.setRecord(1, out.toByteArray(), 0, out.size());
				
				dos.close();
				out.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
//			res.setRecord(5, img.x., offset, numBytes)
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
