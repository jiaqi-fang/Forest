import java.util.Random;

/**
 * The Lion class is used to represent a Lion,
 * and store its information.
 * @author jqfang
 */
public class Lion extends Feline {
	/**
	 * Initialize the Lion.
	 */
	public void init() {
		this.setAbbr('l');
		this.setName("Lion");
		this.initLoc();

		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Lion at (%d, %d): Lion is Feline, ", x, y);
		System.out.print("Feline moves in all eight directions, one step a time.\n");
	}
	
	/**
	 * Implement the Lion's attacking behavior.
	 * @param a another Animal attacked by the Lion
	 * @return whether the Lion wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Lion) {
			if(rand.nextInt(2) == 1)
				return true;
			else
				return false;
		}
		else if(a instanceof Hippo) {
			//System.out.println("rule 6");
			return true;
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
