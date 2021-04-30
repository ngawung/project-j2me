package com.melody.core.helper;

import javax.microedition.rms.RecordFilter;

public class SaveFilter implements RecordFilter {
	private int id;

	public SaveFilter(int id) {
		this.id = id;
	}

	public boolean matches(byte[] data) {
		if (this.getId(data) == id) return true;
		else return false;
	}
	
	public int getId(byte[] data) {
		// get first 4 byte from data
		return ((0xFF & data[0]) << 24) | 
	            ((0xFF & data[1]) << 16) | 
	            ((0xFF & data[2]) << 8) | 
	            (0xFF & data[3]);
	}

}
