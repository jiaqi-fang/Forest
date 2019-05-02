import java.util.Random;

/**
 * The Wolf class is used to represent a Wolf,
 * and store its information.
 * @author jqfang
 */
public class Wolf extends Canine {
	/**
	 * Initialize the Wolf.
	 */
	public void init() {
		this.setAbbr('w');
		this.setName("Wolf");
		this.initLoc();

		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Wolf at (%d, %d): Wolf is Canine, ", x, y);
		System.out.print("Canine moves in four directions, one or two steps a time.\n");
	}
	
	/**
	 * Implement the Wolf's attacking behavior.
	 * @param a another Animal attacked by the Wolf
	 * @return whether the Wolf wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Wolf) {
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
