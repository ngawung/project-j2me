package melody.utils;

import java.util.Random;

public final class RandomUtils {
	
	/**
	 * Random 0-range
	 * @param range
	 * @param rand - for seed...currentTime + rand
	 */
	public final static int range(int range, long rand) {
		Random random = new Random(System.currentTimeMillis() + rand);
		int ran = Math.abs(random.nextInt());
		int number = 1 + ran % range;
		return number;
	}
	
	public final static int rand(int min,int max) { 
		Random random = new Random(System.currentTimeMillis());
		return (int)Math.floor(random.nextFloat() * (max - min + 1) + min);
	}

}
