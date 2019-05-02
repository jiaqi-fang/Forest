import java.util.Random;

/**
 * The Hippo class is used to represent a Hippo,
 * and store its information.
 * @author jqfang
 */
public class Hippo extends Animal {
	/**
	 * Initialize a Hippo.
	 */
	public void init() {
		this.setAbbr('h');
		this.setName("Hippo");
		this.initLoc();

		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Hippo at (%d, %d): Hippo is Animal, ", x, y);
		System.out.print("Hippo moves in four directions, one step a time.\n");
	}
	
	/**
	 * Implement the Hippo's moving behavior.
	 * @return the destination coordinate
	 * @see Animal#move()
	 */
	public int[] move() {
		int x = this.getx();
		int y = this.gety();
		Random rand = new Random();
		while(true) {
			int r = rand.nextInt(4);
			int[] res = this.tryMove(x, y, r * 2);
			if(res != null)
				return res;
			else
				continue;
		}
	}
	
	/**
	 * Implement the Hippo's attacking behavior.
	 * @param a another Animal attacked by the Hippo
	 * @return whether the Hippo wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Hippo) {
			if(rand.nextInt(2) == 1)
				return true;
			else
				return false;		
		}
		else if(a instanceof Turtle) {
			//System.out.println("rule 4");
			if(rand.nextInt(5) == 4)
				return true;
			else
				return false;
		}
		else {
			//System.out.println("rule 1");
			return false;
		}	
	}
}
