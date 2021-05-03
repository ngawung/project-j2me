package com.melody.utils;

public class CoordUtils {
	
	public final static boolean pointInRect(int coordX, int coordY, int x, int y, int width, int height) {
		int mathx = x + width - 1;
        int mathy = y + height - 1;
        return coordX >= x && coordX <= mathx && coordY >= y && coordY <= mathy;
	}
	
	public final static boolean rectInRect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		
		return (x1 < x4 && x2 > x3 &&
			    y1 < y4 && y2 > y3);
	}

}
