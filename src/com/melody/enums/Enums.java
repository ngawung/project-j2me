package com.melody.enums;

public class Enums {
	protected int enumValue;
	protected String enumName;
	
	public Enums(String Name, int Value) {
		this.enumName = Name;
		this.enumValue = Value;
	}
	
	public String getKey(){
		return this.enumName;
	}
	
	public int getValue(){
		return this.enumValue;
	}

}
