package com.melody.utils;

public class CoordUtils {
	
	public final static boolean pointInRect(float coordX, float coordY, float x, float y, float width, float height) {
		float mathx = x + width - 1;
		float mathy = y + height - 1;
        return coordX >= x && coordX <= mathx && coordY >= y && coordY <= mathy;
	}
	
	public final static boolean rectInRect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		
		return (x1 < x4 && x2 > x3 &&
			    y1 < y4 && y2 > y3);
	}
	
	public final static double aTan2(double y, double x) {
		double coeff_1 = Math.PI / 4d;
		double coeff_2 = 3d * coeff_1;
		double abs_y = Math.abs(y);
		double angle;
		if (x >= 0d) {
			double r = (x - abs_y) / (x + abs_y);
			angle = coeff_1 - coeff_1 * r;
		} else {
			double r = (x + abs_y) / (abs_y - x);
			angle = coeff_2 - coeff_1 * r;
		}
		
		return y < 0d ? -angle : angle;
	}
}
