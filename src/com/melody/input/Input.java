package com.melody.input;

import com.melody.core.MainEngine;
import com.melody.enums.KeyCode;

public class Input {
	public MainEngine _e;
	public boolean enable = true;
	public int[] keyState;
	
	public KeyCodeAdapter adapter; 
	
	public static final int KEY_COUNT = 4;
	
	public Input() {
		_e = MainEngine.getInstance();
		adapter = KeyCodeAdapter.getInstance(_e.get_gameRoot());
		
		keyState = new int[KEY_COUNT];
		for (int i=0; i<KEY_COUNT; i++) {
			keyState[i] = 0;
		}
	}
	
	// updated by Engine
	public void update() {
		if (!enable) return;
		for (int i=0; i<KEY_COUNT; i++) {
            if (keyState[i] != 0) keyState[i]++;
        }
	}
	
	// updated by Engine
	public void onKeyDown(KeyCode code) {
		if (code.getValue() == -1) return;
		
		System.out.println("onKeyDown " + code.getValue() + code.getKey());
		
		keyState[code.getValue()] = Math.max(keyState[code.getValue()], 1);
    }
	
	// updated by Engine
	public void onKeyUp(KeyCode code) {
		if (code.getValue() == -1) return;
		
		keyState[code.getValue()] = -1;
    }
	
	// GET STATUS
    public boolean isHeld(KeyCode code) {
        return keyState[code.getValue()] > 0;
    }
    
    public boolean isDown(KeyCode code) {
//    	System.out.println(keyState[0] + "," + keyState[1] + "," + keyState[2] + "," + keyState[3]);
        return keyState[code.getValue()] == 1;
    }
    
    public boolean isReleased(KeyCode code) {
        return keyState[code.getValue()] == -1;
    }

}
