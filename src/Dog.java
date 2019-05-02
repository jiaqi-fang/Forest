import java.util.Random;

/**
 * The Dog class is used to represent a Dog,
 * and store its information.
 * @author jqfang
 */
public class Dog extends Canine {
	/**
	 * Initialize the Dog.
	 */
	public void init() {
		this.setAbbr('d');
		this.setName("Dog");
		this.initLoc();

		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Dog at (%d, %d): Dog is Canine, ", x, y);
		System.out.print("Canine moves in four directions, one or two steps a time.\n");
	}
	
	/**
	 * Implement the Dog's attacking behavior.
	 * @param a another Animal attacked by the Dog
	 * @return whether the Dog wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Dog) {
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
		else if(a instanceof Feline) {
			//System.out.println("rule 3");
			if(rand.nextInt(2) == 1)
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
