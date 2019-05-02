import java.util.Random;

/**
 * The Turtle class is used to represent a Turtle,
 * and store its information.
 * @author jqfang
 */
public class Turtle extends Animal {
	/**
	 * initialize the Turtle.
	 */
	public void init() {
		this.setAbbr('u');
		this.setName("Turtle");
		this.initLoc();

		int x = this.getx();
		int y = this.gety();
		System.out.printf("Added Turtle at (%d, %d): Turtle is Animal, ", x, y);
		System.out.print("Turtle has 50% chance stay in the same position, ");
		System.out.print("and 50% chance move in four directions, one step a time.\n");
	}
	
	/**
	 * Implement the Turtle's moving behavior.
	 * @return the destination coordinate
	 * @see Animal#move()
	 */
	public int[] move() {
		int x = this.getx();
		int y = this.gety();
		Random rand = new Random();
		if(rand.nextInt(2) == 1) {
			while(true) {
				int r = rand.nextInt(4);
				int[] res = this.tryMove(x, y, r * 2);
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
	 * Implement the Turtle's attacking behavior.
	 * @param a another Animal attacked by the Turtle
	 * @return whether the Turtle wins
	 * @see Animal#attack(Animal)
	 */
	public boolean attack(Animal a) {
		//System.out.println("rule 5");
		Random rand = new Random();
		if(rand.nextInt(2) == 1) 
			return true;
		else
			return false;
	}

}
