package com.melody.enums;

public class KeyCodeEnum extends Enums {
	
	public static final KeyCodeEnum UP = new KeyCodeEnum("UP", 0);
	public static final KeyCodeEnum DOWN = new KeyCodeEnum("DOWN", 1);
	public static final KeyCodeEnum LEFT = new KeyCodeEnum("LEFT", 2);
	public static final KeyCodeEnum RIGHT = new KeyCodeEnum("RIGHT", 3);
	
	public static final KeyCodeEnum KEY_1 = new KeyCodeEnum("KEY_1", 4);
	public static final KeyCodeEnum KEY_2 = new KeyCodeEnum("KEY_2", 5);
	public static final KeyCodeEnum KEY_3 = new KeyCodeEnum("KEY_3", 6);
	public static final KeyCodeEnum KEY_4 = new KeyCodeEnum("KEY_4", 7);
	public static final KeyCodeEnum KEY_5 = new KeyCodeEnum("KEY_5", 8);
	public static final KeyCodeEnum KEY_6 = new KeyCodeEnum("KEY_6", 9);
	public static final KeyCodeEnum KEY_7 = new KeyCodeEnum("KEY_7", 10);
	public static final KeyCodeEnum KEY_8 = new KeyCodeEnum("KEY_8", 11);
	public static final KeyCodeEnum KEY_9 = new KeyCodeEnum("KEY_9", 12);
	public static final KeyCodeEnum KEY_0 = new KeyCodeEnum("KEY_0", 13);
	
	public static final KeyCodeEnum KEY_STAR = new KeyCodeEnum("KEY_STAR", 14);
	public static final KeyCodeEnum KEY_POUND = new KeyCodeEnum("KEY_POUND", 15);
	
	public static final KeyCodeEnum SOFTKEY_LEFT = new KeyCodeEnum("SOFTKEY_LEFT", 16);
	public static final KeyCodeEnum SOFTKEY_RIGHT = new KeyCodeEnum("SOFTKEY_RIGHT", 17);
	public static final KeyCodeEnum CENTER = new KeyCodeEnum("CENTER", 18);
	
	public static final KeyCodeEnum NOT_DEFINED_KEY = new KeyCodeEnum("NOT_DEFINED_KEY", 19);

	public KeyCodeEnum(String Name, int Value) {
		super(Name, Value);
		
	}

}
