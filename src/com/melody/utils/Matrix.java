package com.melody.utils;

public class Matrix {
	
	public float a;
	public float b;
	public float c;
	public float d;
	public float tx;
	public float ty;

	public Matrix() {
		identity();
	}
	
	public void identity() {
		a = 1;
		b = 0;
		c = 0;
		d = 1;
		tx = 0;
		ty = 0;
	}
	
	public void rotate(float angle) {
		float cos = (float)Math.cos(angle * (Math.PI/180));
		float sin = (float)Math.sin(angle * (Math.PI/180));

		float a1 = a * cos - b * sin;
		b = a * sin + b * cos;
		a = a1;

		float c1 = c * cos - d * sin;
		d = c * sin + d * cos;
		c = c1;

		float tx1 = tx * cos - ty * sin;
		ty = tx * sin + ty * cos;
		tx = tx1;
	}
	
	public void scale(float sx, float sy) {
		a *= sx;
		b *= sy;
		c *= sx;
		d *= sy;
		tx *= sx;
		ty *= sy;
	}
	
	public void translate(float dx, float dy) {
		tx += dx;
		ty += dy;
	}
	
	public void invert() {
		float norm = a * d - b * c;

		if (norm == 0) {
			a = b = c = d = 0;
			tx = -tx;
			ty = -ty;
		} else {
			norm = 1.0f / norm;
			float a1 = d * norm;
			d = a * norm;
			a = a1;
			b *= -norm;
			c *= -norm;

			float tx1 = -a * tx - c * ty;
			ty = -b * tx - d * ty;
			tx = tx1;
		}
	}
	
	public void transformPoint(float x, float y, Point dest) {
		dest.x = _transformX(x,y);
		dest.y = _transformY(x,y);
	}
	
	private float _transformX(float px, float py) {
		return px * a + py * c + tx;
	}

	private float _transformY(float px, float py) {
		return px * b + py * d + ty;
	}

	// SET & GET
	
	public void setTo(float a, float b, float c, float d, float tx, float ty) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.tx = tx;
		this.ty = ty;
	}

}
