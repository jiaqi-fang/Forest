import java.util.Random;

/**
 * The Kitten class is used to represent a Kitten,
 * and store its information.
 * @author jqfang
 */
public class Kitten extends Cat {
	/**
	 * Initialize the Kitten.
	 */
	public void init() {
		this.setAbbr('k');
		this.setName("Kitten");
		this.initLoc();
		
		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Kitten at (%d, %d): Kitten is Feline, ", x, y);
		System.out.print("Kitten has 30% chance stay in the same position, ");
		System.out.print("and 70% chance move in all eight directions, one step a time.\n");
	}
	
	/**
	 * Implement the Kitten's moving behavior.
	 * @return the destination coordinate
	 * @see Animal#move()
	 * @see Feline#move()
	 */
	public int[] move() {
		int x = this.getx();
		int y = this.gety();
		Random rand = new Random();
		if(rand.nextInt(10) < 7) {
			while(true) {
				int r = rand.nextInt(8);
				int[] res = this.tryMove(x, y, r);
				if(res != null)
					return res;
				else
					continue;
			}
		}
		else {
			this.printStayInfo(x, y);
			return this.getLoc();
		}
	}
	
	/**
	 * Implement the Kitten's attacking behavior.
	 * @param a another Animal attacked by the Kitten
	 * @return whether the Kitten wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		Random rand = new Random();
		if(a instanceof Kitten) {
			if(rand.nextInt(2) == 1)
				return true;
			else
				return false;
		}
		else if(a instanceof Cat) {
			//System.out.println("rule 10");
			if(rand.nextInt(10) >= 7)
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
