package com.melody.input;

import com.melody.core.MainEngine;
import com.melody.enums.KeyCodeEnum;
import com.melody.enums.TouchPhase;

public final class Input {
	private MainEngine _e;
	private int[] _keyState;
	private KeyCodeAdapter _adapter; 
	
	private TouchPhase _currentTouchPhase = TouchPhase.NONE;
	private int _touchX = 0;
	private int _touchY = 0;
	
	public boolean enable = true;
	
	public static final int KEY_COUNT = 19;
	
	public Input() {
		_e = MainEngine.get_instance();
		_adapter = KeyCodeAdapter.getInstance(_e.get_gameRoot());
		
		_keyState = new int[KEY_COUNT];
		for (int i=0; i<KEY_COUNT; i++) {
			_keyState[i] = 0;
		}
	}
	
	// updated by Engine
	public final void update(long dt) {
		if (!enable) return;
		for (int i=0; i<KEY_COUNT; i++) {
            if (_keyState[i] != 0) _keyState[i]++;
        }
		
		_currentTouchPhase = TouchPhase.NONE;
	}
	
	// updated by Engine
	public final void onKeyDown(int rawKeyCode) {
		int keyIndex = getKeyIndex(rawKeyCode);
		if (keyIndex == KeyCodeEnum.NOT_DEFINED_KEY.getValue()) {
			System.out.println("PRESS Not Defined Key " + rawKeyCode);
			return;
		}
		
		_keyState[keyIndex] = Math.max(_keyState[keyIndex], 1);
    }
	
	// updated by Engine
	public final void onKeyUp(int rawKeyCode) {
		int keyIndex = getKeyIndex(rawKeyCode);
		if (keyIndex == KeyCodeEnum.NOT_DEFINED_KEY.getValue()) {
			System.out.println("RELESE Not Defined Key " + rawKeyCode);
			return;
		}
		
		_keyState[keyIndex] = -1;
    }
	
	public final int getKeyIndex(int rawKeyCode) {
		int internal = _adapter.adoptKeyCode(rawKeyCode);
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
			
			default: return KeyCodeEnum.NOT_DEFINED_KEY.getValue();
		}
	}
	
	// updated by engine
	public void onTouch(TouchPhase phase, int x, int y) {
		_currentTouchPhase = phase;
		_touchX = x;
		_touchY = y;
	}
	
	// GET STATUS
    public final boolean isHeld(KeyCodeEnum keyCodeEnum) {
        return _keyState[keyCodeEnum.getValue()] > 0;
    }
    
    public final boolean isDown(KeyCodeEnum keyCodeEnum) {
//    	System.out.println(keyState[0] + "," + keyState[1] + "," + keyState[2] + "," + keyState[3]);
        return _keyState[keyCodeEnum.getValue()] == 1;
    }
    
    public final boolean isReleased(KeyCodeEnum keyCodeEnum) {
        return _keyState[keyCodeEnum.getValue()] == -1;
    }
    
    public int getKeyStatus(KeyCodeEnum keyCodeEnum) {
    	return _keyState[keyCodeEnum.getValue()];
    }
    
    public int getKeyStatus(int keyId) {
    	return _keyState[keyId];
    }
    
    // Touch Input
    public final boolean getTouch(TouchPhase phase) {
		return phase == _currentTouchPhase;
    }
    
    public final boolean getTouchRect(int x, int y, int width, int height, TouchPhase phase) {
    	if (getTouch(phase)) {
            int mathx = x + width - 1;
            int mathy = y + height - 1;
            return _touchX >= x && _touchX <= mathx && _touchY >= y && _touchY <= mathy;
        }
    	
        return false;
    }
    
    public final boolean getTouchCircle(int x, int y, int radius, TouchPhase phase) {
    	if (getTouch(phase)) {
    	    return ((_touchX - x) * (_touchX - x)) + ((_touchY - y) * (_touchY - y)) < radius * radius;
        }
        return false;
    }
    
    public final int[] getTouchCoord(TouchPhase phase) {
    	if (phase == _currentTouchPhase) return new int[]{_touchX,  _touchY};
    	return null;
    }
    
}
