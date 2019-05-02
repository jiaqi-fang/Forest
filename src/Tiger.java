import java.util.Random;

/**
 * The Tiger class is used to represent a Tiger,
 * and store its information.
 * @author jqfang
 */
public class Tiger extends Feline {
	/**
	 * Initialize the Tiger.
	 */
	public void init() {
		this.setAbbr('t');
		this.setName("Tiger");
		this.initLoc();

		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Tiger at (%d, %d): Tiger is Feline, ", x, y);
		System.out.print("Feline moves in all eight directions, one step a time.\n");
	}
	
	/**
	 * Implement the Tiger's attacking behavior.
	 * @param a another Animal attacked by the Tiger
	 * @return whether the Tiger wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Tiger) {
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
