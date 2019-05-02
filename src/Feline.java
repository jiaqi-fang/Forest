import java.util.Random;

/**
 * The Feline class is used to represent a Feline,
 * and store its information.
 * @author jqfang
 */
public abstract class Feline extends Animal {
	/**
	 * Implement the Feline's moving behavior.
	 * @return the destination coordinate
	 * @see Animal#move()
	 */
	public int[] move() {
		int x = this.getx();
		int y = this.gety();
		Random rand = new Random();
		while(true) {
			int r = rand.nextInt(8);
			int[] res = this.tryMove(x, y, r);
			if(res != null)
				return res;
			else
				continue;
		}
	}

	/**
	 * Implement the Feline's attacking behavior.
	 * @param a another Animal attacked by the Feline
	 * @return whether the Feline wins
	 * @see Animal#attack(Animal)
	 */
	public abstract boolean attack(Animal a);
	
}
