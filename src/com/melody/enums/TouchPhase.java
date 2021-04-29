package com.melody.enums;

public class TouchPhase extends Enums {
	
	public final static TouchPhase BEGIN = new TouchPhase("BEGIN", 0);
	public final static TouchPhase END = new TouchPhase("END", 1);
	public final static TouchPhase DRAG = new TouchPhase("DRAG", 2);
	
	public final static TouchPhase NONE = new TouchPhase("NONE", 3);

	private TouchPhase(String Name, int Value) {
		super(Name, Value);
		// TODO Auto-generated constructor stub
	}

}
