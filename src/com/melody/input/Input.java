package com.melody.input;

import com.melody.core.MainEngine;
import com.melody.enums.KeyCodeEnum;

public final class Input {
	public MainEngine _e;
	public boolean enable = true;
	public int[] keyState;
	
	public KeyCodeAdapter adapter; 
	
	public static final int KEY_COUNT = 19;
	
	public Input() {
		_e = MainEngine.getInstance();
		adapter = KeyCodeAdapter.getInstance(_e.get_gameRoot());
		
		keyState = new int[KEY_COUNT];
		for (int i=0; i<KEY_COUNT; i++) {
			keyState[i] = 0;
		}
	}
	
	// updated by Engine
	public final void update() {
		if (!enable) return;
		for (int i=0; i<KEY_COUNT; i++) {
            if (keyState[i] != 0) keyState[i]++;
        }
	}
	
	// updated by Engine
	public final void onKeyDown(int rawKeyCode) {
		int keyIndex = getKeyIndex(rawKeyCode);
		if (keyIndex == KeyCodeEnum.NOT_DEFINED_KEY.getValue()) {
			System.out.println("PRESS Not Defined Key " + rawKeyCode);
			return;
		}
		
		keyState[keyIndex] = Math.max(keyState[keyIndex], 1);
    }
	
	// updated by Engine
	public final void onKeyUp(int rawKeyCode) {
		int keyIndex = getKeyIndex(rawKeyCode);
		if (keyIndex == KeyCodeEnum.NOT_DEFINED_KEY.getValue()) {
			System.out.println("RELESE Not Defined Key " + rawKeyCode);
			return;
		}
		
		keyState[keyIndex] = -1;
    }
	
	public final int getKeyIndex(int rawKeyCode) {
		int internal = adapter.adoptKeyCode(rawKeyCode);
		switch(internal) {
			case KeyCodeAdapter.UP_KEY: return KeyCodeEnum.UP.getValue();
			case KeyCodeAdapter.DOWN_KEY: return KeyCodeEnum.DOWN.getValue();
			case KeyCodeAdapter.LEFT_KEY: return KeyCodeEnum.LEFT.getValue();
			case KeyCodeAdapter.RIGHT_KEY: return KeyCodeEnum.RIGHT.getValue();
			
			case KeyCodeAdapter.KEY_1: return KeyCodeEnum.KEY_1.getValue();
			case KeyCodeAdapter.KEY_2: return KeyCodeEnum.KEY_2.getValue();
			case KeyCodeAdapter.KEY_3: return KeyCodeEnum.KEY_3.getValue();
			case KeyCodeAdapter.KEY_4: return KeyCodeEnum.KEY_4.getValue();
			case KeyCodeAdapter.KEY_5: return KeyCodeEnum.KEY_5.getValue();
			case KeyCodeAdapter.KEY_6: return KeyCodeEnum.KEY_6.getValue();
			case KeyCodeAdapter.KEY_7: return KeyCodeEnum.KEY_7.getValue();
			case KeyCodeAdapter.KEY_8: return KeyCodeEnum.KEY_8.getValue();
			case KeyCodeAdapter.KEY_9: return KeyCodeEnum.KEY_9.getValue();
			case KeyCodeAdapter.KEY_0: return KeyCodeEnum.KEY_0.getValue();
			
			case KeyCodeAdapter.KEY__STAR: return KeyCodeEnum.KEY_STAR.getValue();
			case KeyCodeAdapter.KEY__POUND: return KeyCodeEnum.KEY_POUND.getValue();
			
			case KeyCodeAdapter.SOFT_KEY_LEFT: return KeyCodeEnum.SOFTKEY_LEFT.getValue();
			case KeyCodeAdapter.SOFT_KEY_RIGHT: return KeyCodeEnum.SOFTKEY_RIGHT.getValue();
			case KeyCodeAdapter.CENTER_KEY: return KeyCodeEnum.CENTER.getValue();
		}
		
		// use else if because case need to be constant
//		if (rawKeyCode == adapter.SOFTKEY_LEFT || internal == adapter.SOFTKEY_LEFT) return KeyCodeEnum.SOFTKEY_LEFT.getValue();
//		else if (rawKeyCode == adapter.SOFTKEY_RIGHT || internal == adapter.SOFTKEY_RIGHT) return KeyCodeEnum.SOFTKEY_RIGHT.getValue();
//		else if (rawKeyCode == adapter.SOFTKEY_MIDDLE_INTERNET || internal == adapter.SOFTKEY_MIDDLE_INTERNET) return KeyCodeEnum.SOFTKEY_MIDDLE.getValue();
		
		System.out.println(internal +", " + rawKeyCode);
		
		return KeyCodeEnum.NOT_DEFINED_KEY.getValue();
	}
	
	// GET STATUS
    public final boolean isHeld(KeyCodeEnum keyCodeEnum) {
        return keyState[keyCodeEnum.getValue()] > 0;
    }
    
    public final boolean isDown(KeyCodeEnum keyCodeEnum) {
//    	System.out.println(keyState[0] + "," + keyState[1] + "," + keyState[2] + "," + keyState[3]);
        return keyState[keyCodeEnum.getValue()] == 1;
    }
    
    public final boolean isReleased(KeyCodeEnum keyCodeEnum) {
        return keyState[keyCodeEnum.getValue()] == -1;
    }

}
