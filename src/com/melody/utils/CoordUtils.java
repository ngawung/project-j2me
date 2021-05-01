package com.melody.utils;

public class CoordUtils {
	
	public final static boolean pointInRect(int coordX, int coordY, int x, int y, int width, int height) {
		int mathx = x + width - 1;
        int mathy = y + height - 1;
        return coordX >= x && coordX <= mathx && coordY >= y && coordY <= mathy;
	}

}
