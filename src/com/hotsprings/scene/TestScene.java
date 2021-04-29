package com.hotsprings.scene;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStore;
import com.hotsprings.object.SaveTest;
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
	private MText text;
	
	private RecordStore res;
	private Runtime runtime;

	public TestScene() {

	}
	
	public void initialize() {
		System.out.println("Test Scene Initialized!");
		
//		MyObject obj = new MyObject("My Object");
//		addChild(obj);
		
		runtime = Runtime.getRuntime();
		
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
		
		text = new MText("text", "My Text", 0x0);
		text.x = 15;
		text.y = 140;
		addChild(text);
		
//		_e.saveManager.removeAll();
		
//		SaveTest mySave1 = new SaveTest("Ferdian1", 10);
//		SaveTest mySave2 = new SaveTest("Ferdian2", 20);
//		SaveTest mySave3 = new SaveTest("Ferdian3", 30);
//		SaveTest mySave4 = new SaveTest("Ferdian4", 40);
//		SaveTest mySave5 = new SaveTest("Ferdian5", 50);
//		
//		_e.saveManager.save(0, mySave1);
//		_e.saveManager.save(1, mySave2);
//		_e.saveManager.save(2, mySave3);
//		_e.saveManager.save(3, mySave4);
//		_e.saveManager.save(4, mySave5);
		
		
//		RecordStore res;
//		try {
//			res = RecordStore.openRecordStore(_e.projectName, true);
//			System.out.println(res.getNumRecords());
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		SaveTest myLoad1 = new SaveTest("", 0);
		SaveTest myLoad2 = new SaveTest("", 0);
		SaveTest myLoad3 = new SaveTest("", 0);
		SaveTest myLoad4 = new SaveTest("", 0);
		SaveTest myLoad5 = new SaveTest("", 0);
//		
		_e.saveManager.load(0, myLoad1);
		_e.saveManager.load(1, myLoad2);
		_e.saveManager.load(2, myLoad3);
		_e.saveManager.load(3, myLoad4);
		_e.saveManager.load(4, myLoad5);
//		
		System.out.println(myLoad1.name + ", " + myLoad1.random);
		System.out.println(myLoad2.name + ", " + myLoad2.random);
		System.out.println(myLoad3.name + ", " + myLoad3.random);
		System.out.println(myLoad4.name + ", " + myLoad4.random);
		System.out.println(myLoad5.name + ", " + myLoad5.random);
		
//		try {
//			res = RecordStore.openRecordStore("hotsprings", true);
//			
//			byte[] temp = res.getRecord(1);
//			ByteArrayInputStream out = new ByteArrayInputStream(temp);
//			DataInputStream dis = new DataInputStream(out);
//			
//			img.x = dis.readInt();
//			img.y = dis.readInt();
//			
//			dis.close();
//			out.close();
//			temp = null;
//			
//		} catch (Exception e) {
//			System.out.println("Error, " + e.getMessage());
//			e.printStackTrace();
//		}
		
	}
	
	public long lastDt = 0;
	public long lastDt2 = 0;
	public void update(long dt) {
		lastDt += dt;
		lastDt2 += dt;
		if (lastDt > 2000) {
			text.text = ((runtime.totalMemory() - runtime.freeMemory()) / 1000) + "kb / " + (runtime.totalMemory() / 1000) +"kb";
			lastDt = 0;
		}
		
		if (lastDt2 > 10000) {
			text.text = "Garbage Collector!";
			System.gc();
			lastDt2 = 0;
		}
		
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
