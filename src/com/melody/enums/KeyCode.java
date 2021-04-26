package com.melody.enums;

public class KeyCode extends Enums {
	
	public static final KeyCode UP = new KeyCode("UP", 0);
	public static final KeyCode DOWN = new KeyCode("DOWN", 1);
	public static final KeyCode LEFT = new KeyCode("LEFT", 2);
	public static final KeyCode RIGHT = new KeyCode("RIGHT", 3);

	public KeyCode(String Name, int Value) {
		super(Name, Value);
		
	}

}
