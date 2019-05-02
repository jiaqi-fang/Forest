import java.util.Random;

/**
 * The Cat class is used to represent a Cat,
 * and store its information.
 * @author jqfang
 */
public class Cat extends Feline {
	/**
	 * Initialize the Cat.
	 */
	public void init() {
		this.setAbbr('c');
		this.setName("Cat");
		this.initLoc();
		
		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Cat at (%d, %d): Cat is Feline, ", x, y);
		System.out.print("Feline moves in all eight directions, one step a time.\n");
	}
	
	/**
	 * Implement the Cat's attacking behavior.
	 * @param a another Animal attacked by the Cat
	 * @return whether the Cat wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Cat) {
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
