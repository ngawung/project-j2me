package com.melody.enums;

public class SoundRequest extends Enums {
	
	public final static SoundRequest NONE = new SoundRequest("NONE", 0);
	
	public final static SoundRequest PLAY = new SoundRequest("PLAY", 1);
	public final static SoundRequest STOP = new SoundRequest("STOP", 2);
	public final static SoundRequest REPLAY = new SoundRequest("REPLAY", 3);

	private SoundRequest(String Name, int Value) {
		super(Name, Value);
		// TODO Auto-generated constructor stub
	}

}
