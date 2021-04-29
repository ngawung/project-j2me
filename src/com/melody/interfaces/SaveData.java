package com.melody.interfaces;

public interface SaveData {
	
	public byte[] serialize();
	public void deserialize(byte[] data);

}
