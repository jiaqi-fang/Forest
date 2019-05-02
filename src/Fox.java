import java.util.Random;

/**
 * The Fox class is used to represent a Fox,
 * and store its information.
 * @author jqfang
 */
public class Fox extends Canine {
	/**
	 * Initialize the Fox.
	 */
	public void init() {
		this.setAbbr('f');
		this.setName("Fox");
		this.initLoc();
		
		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Fox at (%d, %d): Fox is Canine, ", x, y);
		System.out.print("Canine moves in four directions, one or two steps a time.\n");
	}
	
	/**
	 * Implement the Fox's attacking behavior.
	 * @param a another Animal attacked by the Fox
	 * @return whether the Fox wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Fox) {
			if(rand.nextInt(2) == 1)
				return true;
			else
				return false;
		}
		else if(a instanceof Cat) {
			//System.out.println("rule 7");
			return true;
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
