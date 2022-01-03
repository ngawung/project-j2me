#include <bits/stdint-uintn.h>
#include <iostream>
#include <fstream>
#include "BMPlib.h"


int main() {
	BMPlib::BMP bmp;
	bmp.Read("temp.bmp");

	unsigned char* pixel = bmp.GetPixelBuffer();
	uint16_t totalSize = bmp.GetWidth()*bmp.GetHeight();

	std::ofstream out("output", std::ios::binary);
		
	for (int i=0; i<totalSize*3; i+=3) {

		uint8_t r = pixel[i];
		uint8_t g = pixel[i+1];
		uint8_t b = pixel[i+2];

		uint8_t a = 0xFF;

		out.write((char*)&a, sizeof(a));
		out.write((char*)&r, sizeof(a));
		out.write((char*)&g, sizeof(a));
		out.write((char*)&b, sizeof(a));

	}

	out.close();

	return 0;
}