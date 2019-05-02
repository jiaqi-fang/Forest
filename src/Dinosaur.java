import java.util.Random;

/**
 * The Dinosaur class is used to represent a Dinosaur,
 * and store its information.
 * @author jqfang
 */
public class Dinosaur extends Animal {
	/**
	 * Initialize the Dinosaur.
	 */
	public void init() {
		this.setAbbr('i');
		this.setName("Dinosaur");
		this.initLoc();

		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Dinosaur at (%d, %d): Hippo is Animal, ", x, y);
		System.out.print("Dinosaur moves in four directions, three steps a time.\n");
	}
	
	/**
	 * Implement the Dinosaur's attacking behavior.
	 * @param a another Animal attacked by the Dinosaur
	 * @return whether the Dinosaur wins
	 * @see Animal#attack(Animal)
	 */
	public int[] move() {
		int x = this.getx();
		int y = this.gety();
		Random rand = new Random();
		while(true) {
			int r = rand.nextInt(4);
			int[] res = this.tryMove(x, y, r * 2, 3);
			if(res != null)
				return res;
			else
				continue;
		}
	}
	
	/**
	 * Implement the Dinosaur's attacking behavior.
	 * @param a another Animal attacked by the Dinosaur
	 * @return whether the Dinosaur wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Dinosaur) {
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
			//System.out.println("rule 8");
			return true;
		}	
	}
}
