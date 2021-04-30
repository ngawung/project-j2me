package com.melody.core;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

import com.melody.core.helper.SaveFilter;
import com.melody.interfaces.SaveData;

public class SaveManager {
	
	private RecordStore _res;
	private String _recordName;
	
	public SaveManager(String recordName) {
		_recordName = recordName;
	}
	
	public boolean save(int id, SaveData data) {
		try {
			_res = RecordStore.openRecordStore(_recordName, true);
			
			// prepare data
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeInt(id);
			dos.write(data.serialize());
			
			// find data
			RecordEnumeration e = _res.enumerateRecords(new SaveFilter(id), null, false);
			if (e.numRecords() <= 0) {
				_res.addRecord(out.toByteArray(), 0, out.size());
			} else {
				_res.setRecord(e.nextRecordId(), out.toByteArray(), 0, out.size());
			}
			
			// close all
			e.destroy();
			dos.close();
			out.close();
			_res.closeRecordStore();
			
			return true;
		} catch (Exception e) {
			System.out.println("Save to RecordStore FAILED!");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean load(int id, SaveData data) {
		try {
			_res = RecordStore.openRecordStore(_recordName, true);
			RecordEnumeration e = _res.enumerateRecords(new SaveFilter(id), null, false);
			if (e.numRecords() <= 0) return false;
			
			data.deserialize(e.nextRecord());
			
			e.destroy();
			_res.closeRecordStore();
			
			return true;
		} catch (Exception e) {
			System.out.println("Load from RecordStore FAILED!");
			e.printStackTrace();
			return false;
		}
	}
	
	public void removeAll() {
		try {
			RecordStore.deleteRecordStore(_recordName);
		} catch (Exception e) {
			System.out.println("remove RecordStore FAILED!");
			e.printStackTrace();
		}
	}

}
