package mlbb.display;

import melody.display.BMFont;

public class Font {
	public static BMFont font;
	public static BMFont font2;
	
	public static int[][] bmf_font = new int[][]{
		{10, 10, 8},
		{32, 100, 31, 3, 1, -1, 9, 7},
		{33, 28, 25, 2, 7, 1, 1, 4},
		{34, 68, 31, 6, 2, 0, 1, 7},
		{35, 24, 33, 5, 5, 0, 2, 6},
		{36, 12, 26, 5, 7, 0, 1, 6},
		{37, 84, 0, 7, 7, 0, 1, 8},
		{38, 113, 16, 6, 7, 0, 1, 7},
		{39, 83, 31, 2, 2, 0, 1, 3},
		{40, 24, 25, 3, 7, 0, 1, 4},
		{41, 124, 0, 3, 7, 0, 1, 4},
		{42, 6, 27, 5, 7, 0, 1, 6},
		{43, 4, 35, 7, 5, -1, 2, 6},
		{44, 56, 32, 3, 3, 0, 6, 4},
		{45, 94, 31, 5, 1, 0, 4, 6},
		{46, 80, 31, 2, 2, 0, 6, 3},
		{47, 12, 34, 5, 5, 1, 2, 7},
		{48, 8, 10, 7, 7, 0, 1, 8},
		{49, 106, 16, 6, 7, 0, 1, 7},
		{50, 16, 9, 7, 7, 0, 1, 8},
		{51, 24, 9, 7, 7, 0, 1, 8},
		{52, 32, 9, 7, 7, 0, 1, 8},
		{53, 40, 9, 7, 7, 0, 1, 8},
		{54, 52, 0, 7, 7, 0, 1, 8},
		{55, 56, 8, 7, 7, 0, 1, 8},
		{56, 64, 8, 7, 7, 0, 1, 8},
		{57, 72, 8, 7, 7, 0, 1, 8},
		{58, 46, 32, 2, 5, 0, 3, 3},
		{59, 0, 35, 3, 6, 0, 3, 4},
		{60, 41, 32, 4, 5, 0, 2, 5},
		{61, 30, 33, 5, 5, 0, 2, 6},
		{62, 36, 32, 4, 5, 0, 2, 5},
		{63, 5, 0, 7, 9, 0, 1, 8},
		{64, 13, 0, 7, 8, 1, 1, 9},
		{65, 112, 8, 7, 7, 0, 1, 8},
		{66, 120, 8, 7, 7, 0, 1, 8},
		{67, 0, 19, 7, 7, 0, 1, 8},
		{68, 8, 18, 7, 7, 0, 1, 8},
		{69, 16, 17, 7, 7, 0, 1, 8},
		{70, 24, 17, 7, 7, 0, 1, 8},
		{71, 32, 17, 7, 7, 0, 1, 8},
		{72, 40, 17, 7, 7, 0, 1, 8},
		{73, 78, 16, 6, 7, 0, 1, 7},
		{74, 48, 17, 7, 7, 0, 1, 8},
		{75, 56, 16, 7, 7, 0, 1, 8},
		{76, 60, 0, 7, 7, 0, 1, 8},
		{77, 76, 0, 7, 7, 0, 1, 8},
		{78, 68, 0, 7, 7, 0, 1, 8},
		{79, 100, 0, 7, 7, 0, 1, 8},
		{80, 104, 8, 7, 7, 0, 1, 8},
		{81, 96, 8, 7, 7, 0, 1, 8},
		{82, 88, 8, 7, 7, 0, 1, 8},
		{83, 80, 8, 7, 7, 0, 1, 8},
		{84, 92, 16, 6, 7, 0, 1, 7},
		{85, 48, 9, 7, 7, 0, 1, 8},
		{86, 0, 11, 7, 7, 0, 1, 8},
		{87, 116, 0, 7, 7, 0, 1, 8},
		{88, 108, 0, 7, 7, 0, 1, 8},
		{89, 85, 16, 6, 7, 0, 1, 7},
		{90, 92, 0, 7, 7, 0, 1, 8},
		{91, 104, 31, 1, 1, 0, 0, 0},
		{92, 18, 33, 5, 5, 1, 2, 7},
		{93, 106, 31, 1, 1, 0, 0, 0},
		{94, 49, 32, 6, 3, 0, 1, 7},
		{95, 86, 31, 7, 1, 0, 8, 7},
		{96, 75, 31, 4, 2, 0, 1, 5},
		{97, 64, 24, 6, 6, 0, 2, 7},
		{98, 71, 16, 6, 7, 0, 1, 7},
		{99, 78, 24, 6, 6, 0, 2, 7},
		{100, 64, 16, 6, 7, 0, 1, 7},
		{101, 99, 24, 6, 6, 0, 2, 7},
		{102, 0, 27, 5, 7, 0, 1, 6},
		{103, 21, 0, 6, 8, 0, 2, 7},
		{104, 99, 16, 6, 7, 0, 1, 7},
		{105, 49, 0, 2, 8, 1, 0, 4},
		{106, 0, 0, 4, 10, 0, 0, 5},
		{107, 120, 16, 6, 7, 0, 1, 7},
		{108, 31, 25, 2, 7, 1, 1, 4},
		{109, 34, 25, 7, 6, 0, 2, 8},
		{110, 113, 24, 6, 6, 0, 2, 7},
		{111, 50, 25, 6, 6, 0, 2, 7},
		{112, 42, 0, 6, 8, 0, 2, 7},
		{113, 28, 0, 6, 8, 0, 2, 7},
		{114, 71, 24, 6, 6, 0, 2, 7},
		{115, 57, 24, 6, 6, 0, 2, 7},
		{116, 18, 25, 5, 7, 0, 1, 6},
		{117, 85, 24, 6, 6, 0, 2, 7},
		{118, 92, 24, 6, 6, 0, 2, 7},
		{119, 42, 25, 7, 6, 0, 2, 8},
		{120, 106, 24, 6, 6, 0, 2, 7},
		{121, 35, 0, 6, 8, 0, 2, 7},
		{122, 120, 24, 6, 6, 0, 2, 7},
		{123, 108, 31, 1, 1, 0, 0, 0},
		{124, 110, 31, 1, 1, 0, 0, 0},
		{125, 112, 31, 1, 1, 0, 0, 0},
		{126, 60, 31, 7, 2, 0, 3, 8},
	};
	
	public static int[][] bmf_font2 = new int[][]{
		{10, 10, 8},
		{32, 23, 61, 5, 3, -2, 8, 7},
		{33, 12, 42, 4, 9, 0, 0, 4},
		{34, 105, 49, 8, 4, -1, 0, 7},
		{35, 50, 50, 7, 7, -1, 1, 6},
		{36, 111, 30, 7, 9, -1, 0, 6},
		{37, 108, 0, 9, 9, -1, 0, 8},
		{38, 93, 30, 8, 9, -1, 0, 7},
		{39, 0, 62, 4, 4, -1, 0, 3},
		{40, 6, 43, 5, 9, -1, 0, 4},
		{41, 0, 43, 5, 9, -1, 0, 4},
		{42, 120, 20, 7, 9, -1, 0, 6},
		{43, 24, 50, 9, 7, -2, 1, 6},
		{44, 89, 49, 5, 5, -1, 5, 4},
		{45, 15, 62, 7, 3, -1, 3, 6},
		{46, 121, 49, 4, 4, -1, 5, 3},
		{47, 34, 50, 7, 7, 0, 1, 7},
		{48, 40, 11, 9, 9, -1, 0, 8},
		{49, 84, 30, 8, 9, -1, 0, 7},
		{50, 50, 11, 9, 9, -1, 0, 8},
		{51, 60, 11, 9, 9, -1, 0, 8},
		{52, 70, 10, 9, 9, -1, 0, 8},
		{53, 80, 10, 9, 9, -1, 0, 8},
		{54, 68, 0, 9, 9, -1, 0, 8},
		{55, 100, 10, 9, 9, -1, 0, 8},
		{56, 110, 10, 9, 9, -1, 0, 8},
		{57, 0, 23, 9, 9, -1, 0, 8},
		{58, 123, 40, 4, 7, -1, 2, 3},
		{59, 18, 52, 5, 8, -1, 2, 4},
		{60, 73, 50, 6, 7, -1, 1, 5},
		{61, 58, 50, 7, 7, -1, 1, 6},
		{62, 66, 50, 6, 7, -1, 1, 5},
		{63, 7, 0, 9, 11, -1, 0, 8},
		{64, 17, 0, 9, 10, 0, 0, 9},
		{65, 50, 21, 9, 9, -1, 0, 8},
		{66, 60, 21, 9, 9, -1, 0, 8},
		{67, 70, 20, 9, 9, -1, 0, 8},
		{68, 80, 20, 9, 9, -1, 0, 8},
		{69, 90, 20, 9, 9, -1, 0, 8},
		{70, 100, 20, 9, 9, -1, 0, 8},
		{71, 110, 20, 9, 9, -1, 0, 8},
		{72, 0, 33, 9, 9, -1, 0, 8},
		{73, 48, 31, 8, 9, -1, 0, 7},
		{74, 10, 32, 9, 9, -1, 0, 8},
		{75, 20, 31, 9, 9, -1, 0, 8},
		{76, 78, 0, 9, 9, -1, 0, 8},
		{77, 98, 0, 9, 9, -1, 0, 8},
		{78, 88, 0, 9, 9, -1, 0, 8},
		{79, 0, 13, 9, 9, -1, 0, 8},
		{80, 40, 21, 9, 9, -1, 0, 8},
		{81, 30, 21, 9, 9, -1, 0, 8},
		{82, 20, 21, 9, 9, -1, 0, 8},
		{83, 10, 22, 9, 9, -1, 0, 8},
		{84, 66, 31, 8, 9, -1, 0, 7},
		{85, 90, 10, 9, 9, -1, 0, 8},
		{86, 30, 11, 9, 9, -1, 0, 8},
		{87, 20, 11, 9, 9, -1, 0, 8},
		{88, 10, 12, 9, 9, -1, 0, 8},
		{89, 57, 31, 8, 9, -1, 0, 7},
		{90, 118, 0, 9, 9, -1, 0, 8},
		{91, 29, 58, 3, 3, -1, -1, 0},
		{92, 42, 50, 7, 7, 0, 1, 7},
		{93, 33, 58, 3, 3, -1, -1, 0},
		{94, 80, 49, 8, 5, -1, 0, 7},
		{95, 5, 62, 9, 3, -1, 7, 7},
		{96, 114, 49, 6, 4, -1, 0, 5},
		{97, 60, 41, 8, 8, -1, 1, 7},
		{98, 39, 31, 8, 9, -1, 0, 7},
		{99, 78, 40, 8, 8, -1, 1, 7},
		{100, 30, 31, 8, 9, -1, 0, 7},
		{101, 105, 40, 8, 8, -1, 1, 7},
		{102, 120, 10, 7, 9, -1, 0, 6},
		{103, 27, 0, 8, 10, -1, 1, 7},
		{104, 75, 30, 8, 9, -1, 0, 7},
		{105, 63, 0, 4, 10, 0, -1, 4},
		{106, 0, 0, 6, 12, -1, -1, 5},
		{107, 102, 30, 8, 9, -1, 0, 7},
		{108, 17, 42, 4, 9, 0, 0, 4},
		{109, 22, 41, 9, 8, -1, 1, 8},
		{110, 0, 53, 8, 8, -1, 1, 7},
		{111, 42, 41, 8, 8, -1, 1, 7},
		{112, 54, 0, 8, 10, -1, 1, 7},
		{113, 36, 0, 8, 10, -1, 1, 7},
		{114, 69, 41, 8, 8, -1, 1, 7},
		{115, 51, 41, 8, 8, -1, 1, 7},
		{116, 119, 30, 7, 9, -1, 0, 6},
		{117, 87, 40, 8, 8, -1, 1, 7},
		{118, 96, 40, 8, 8, -1, 1, 7},
		{119, 32, 41, 9, 8, -1, 1, 8},
		{120, 114, 40, 8, 8, -1, 1, 7},
		{121, 45, 0, 8, 10, -1, 1, 7},
		{122, 9, 53, 8, 8, -1, 1, 7},
		{123, 37, 58, 3, 3, -1, -1, 0},
		{124, 41, 58, 3, 3, -1, -1, 0},
		{125, 45, 58, 3, 3, -1, -1, 0},
		{126, 95, 49, 9, 4, -1, 2, 8}
	};
}