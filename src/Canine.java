import java.util.Random;

/**
 * The Canine class is used to represent a Canine,
 * and store its information.
 * @author jqfang
 */
public abstract class Canine extends Animal {
	/**
	 * Implement the Canine's moving behavior.
	 * @return the destination coordinate
	 * @see Animal#move()
	 */
	public int[] move() {
		int x = this.getx();
		int y = this.gety();
		Random rand = new Random();
		while(true) {
			int r = rand.nextInt(4);
			int s = rand.nextInt(2) + 1;
			int[] res = this.tryMove(x, y, r * 2, s);
			if(res != null)
				return res;
			else
				continue;
		}
	}
	
	/**
	 * Implement the Canine's attacking behavior.
	 * @param a another Animal attacked by the Canine
	 * @return whether the Canine wins
	 * @see Animal#attack(Animal)
	 */
	public abstract boolean attack(Animal a);

}
