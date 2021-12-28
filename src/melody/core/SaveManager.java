package melody.core;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import melody.interfaces.SaveData;

public class SaveManager implements RecordFilter {
	
	private RecordStore _res;
	private String _recordName;
	private int currId;
	
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
			currId = id;
			RecordEnumeration e = _res.enumerateRecords(this, null, false);
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
			currId = id;
			RecordEnumeration e = _res.enumerateRecords(this, null, false);
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

	public boolean matches(byte[] data) {
		if (getId(data) == currId) return true;
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
