package melody.utils;

import java.util.Random;

public final class RandomUtils {
	
	// random 0-range
	public final static int range(int range, long rand) {
		Random random = new Random(System.currentTimeMillis() + rand);
		int ran = Math.abs(random.nextInt());
		int number = 1 + ran % range;
		return number;
	}

}
