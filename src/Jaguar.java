import java.util.Random;

/**
 * The Jaguar class is used to represent a Jaguar,
 * and store its information.
 * @author jqfang
 */
public class Jaguar extends Feline {
	/**
	 * Initialize the Jaguar.
	 */
	public void init() {
		this.setAbbr('j');
		this.setName("Jaguar");
		this.initLoc();
		
		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Jaguar at (%d, %d): Jaguar is Feline, ", x, y);
		System.out.print("Feline moves in all eight directions, one step a time.\n");
	}
	
	/**
	 * Implement the Jaguar's attacking behavior.
	 * @param a another Animal attacked by the Jaguar
	 * @return whether the Jaguar wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Jaguar) {
			if(rand.nextInt(2) == 1)
				return true;
			else
				return false;
		}
		else if(a instanceof Turtle) {
			//System.out.println("rule 9");
			return true;
		}
		else if(a instanceof Canine) {
			//System.out.println("rule 2");
			return true;
		}
		else {
			//System.out.println("rule 1");
			return false;
		}
	}

}
