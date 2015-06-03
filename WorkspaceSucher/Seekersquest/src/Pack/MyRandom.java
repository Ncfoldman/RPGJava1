package Pack;

public class MyRandom {
	
	public static int myRandom(int low, int high) {
		// zB (2/7) random 2,3,4,5,6,7
		high++;
		return (int) (Math.random() * (high - low) + low);
	}
}
