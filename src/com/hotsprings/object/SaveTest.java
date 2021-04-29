package com.hotsprings.object;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import com.melody.interfaces.SaveData;

public class SaveTest implements SaveData {
	
	public String name;
	public int random;

	public SaveTest(String name, int random) {
		this.name = name;
		this.random = random;
	}

	public byte[] serialize() {
		byte[] temp = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			dos.writeUTF(name);
			dos.writeInt(random);
			
			temp = out.toByteArray();
			
			dos.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	public void deserialize(byte[] data) {
		// TODO Auto-generated method stub

	}

}
