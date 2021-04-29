package com.melody.utils;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import com.melody.core.MainEngine;
import com.melody.interfaces.SaveData;

public class SaveManager {
	
	public MainEngine _e;
	public RecordStore res;
	
	public SaveManager() {
		_e = MainEngine.getInstance();
		
	}
	
	public boolean save(int id, SaveData data) {
		try {
			res = RecordStore.openRecordStore(_e.projectName, true);
			
			// prepare data
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeInt(id);
			dos.write(data.serialize());
			
			// find data
			RecordEnumeration e = res.enumerateRecords(new SaveFilter(id), null, false);
			if (e.numRecords() <= 0) {
				res.addRecord(out.toByteArray(), 0, out.size());
			} else {
				res.setRecord(e.nextRecordId(), out.toByteArray(), 0, out.size());
			}
			
			// close all
			e.destroy();
			dos.close();
			out.close();
			res.closeRecordStore();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean load(int id, SaveData data) {
		try {
			res = RecordStore.openRecordStore(_e.projectName, true);
			RecordEnumeration e = res.enumerateRecords(new SaveFilter(id), null, false);
			if (e.numRecords() <= 0) return false;
			
			data.deserialize(e.nextRecord());
			
			e.destroy();
			res.closeRecordStore();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void removeAll() {
		try {
			RecordStore.deleteRecordStore(_e.projectName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
