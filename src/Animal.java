import java.util.Random;

/**
 * The Animal class is used to represent an arbitrary animal,
 * and store its information.
 * @author jqfang
 */
public abstract class Animal {
	private char abbr;
	private String name;
	private int[] loc;
	private boolean active;
	
	/**
	 * A 2D array to represent the Forest
	 */
	public static Animal[][] forest;
	/**
	 * Size of the Forest
	 */
	public static int lim;
	/**
	 * Number of living animals
	 */
	public static int animNum;
	/**
	 * Number of dead animals
	 */
	public static int deadNum;
	/**
	 * An array to store information about dead animals
	 */
	public static String[] deadList;
	/**
	 * An array to store information about the locations where animals died
	 */
	public static int[][] deadLocList;
	
	/**
	 * Initialize the Forest and cells in it.
	 * @param n Size of the Forest
	 */
	public static void initForest(int n) {
		lim = n;
		forest = new Animal[lim][lim];
		animNum = 0;
		deadNum = 0;
	}
	
	/**
	 * Initialize the list of dead animals and their locations.
	 */
	public static void initDeadList() {
		deadList = new String[animNum + 5];
		deadLocList = new int[animNum + 5][2];
	}
	
	/**
	 * Initialize the Animal.
	 */
	public abstract void init();
	
	/**
	 * Reset the cell where the Animal used to stay.
	 */
	public void reset() {
		forest[this.getx()][this.gety()] = null;
	}
	
	/**
	 * Initialize the Animal's location randomly.
	 */
	public void initLoc() {
		this.loc = new int[2];
		Random rand = new Random();
		while(true) {
			int tx = rand.nextInt(lim);
			int ty = rand.nextInt(lim);
			if(forest[tx][ty] == null || forest[tx][ty].getAbbr() == '.') {
				this.setLoc(tx, ty);
				forest[tx][ty] = this;
				++animNum;
				return;
			}
			else
				continue;
		}
	}
	
	/**
	 * Set the Animal's location.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void setLoc(int x, int y) {
		this.loc[0] = x;
		this.loc[1] = y;
	}
	
	/**
	 * Get the Animal's current location.
	 * @return the Animal's current coordinate location
	 */
	public int[] getLoc() {
		return this.loc;
	}
	
	/**
	 * Get the Animal's current x-coordinate.
	 * @return the Animal's current x-coordinate
	 */
	public int getx() {
		return this.loc[0];
	}
	
	/**
	 * Get the Animal's current y-coordinate.
	 * @return the Animal's current y-coordinate
	 */
	public int gety() {
		return this.loc[1];
	}
	
	/**
	 * Set the Animal's abbreviation.
	 * @param abbr the abbreviation to be set
	 */
	public void setAbbr(char abbr) {
		this.abbr = abbr;
	}
	
	/**
	 * Get the Animal's abbreviation.
	 * @return the Animal's abbreviation
	 */
	public char getAbbr() {
		return this.abbr;
	}
	
	/**
	 * Set the Animal's full name.
	 * @param name the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the Animal's full name.
	 * @return the Animal's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Activate the Animal, making it able to move.
	 */
	public void activate() {
		active = true;
	}
	
	/**
	 * Check whether the Animal is able to move.
	 * @return whether the Animal is able to move
	 */
	public boolean isActive() {
		return this.active;
	}
	
	/**
	 * Implement the Animal's move to a destination cell.
	 * @param x x-coordinate of the destination
	 * @param y y-coordinate of the destination
	 */
	public void moveto(int x, int y) {
		forest[x][y] = this;
		this.reset();
		this.setLoc(x, y);
	}
	
	/**
	 * Implement the Animal's death.
	 * @param x x-coordinate of the location where the Animal dies
	 * @param y y-coordinate of the location where the Animal dies
	 * @return the Animal's current location
	 */
	public void die(int x, int y) {
		--animNum;
		deadList[deadNum] = this.name;
		deadLocList[deadNum] = this.loc;
		++deadNum;
		printDieInfo(x, y);
		this.reset();
	}
	
	/**
	 * Implement the Animal's moving behavior.
	 * @return the destination coordinate
	 */
	public abstract int[] move();
	
	/**
	 * Implement a moving try with one step of the Animal.
	 * @param x the Animal's current x-coordinate
	 * @param y the Animal's current y-coordinate
	 * @param r an integer to represent the moving direction
	 * @return the destination coordination if the try succeeds,
	 * null if the try failed
	 */
	public int[] tryMove(int x, int y, int r) {
		int[][] dir = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
		int tx = x + dir[r][0];
		int ty = y + dir[r][1];
		if(tx >= 0 && tx < lim && ty >= 0 && ty < lim) {
			if(forest[tx][ty] == null || forest[tx][ty].getAbbr() == '.') {
				this.moveto(tx, ty);
				this.printMoveInfo(x, y, tx, ty);
				return this.getLoc();
			}
			else {
				if(this.attack(forest[tx][ty])) {
					this.printWinInfo(forest[tx][ty], x, y, tx, ty);
					forest[tx][ty].die(tx, ty);
					this.printMoveInfo(x, y, tx, ty);
					this.moveto(tx, ty);
					return this.getLoc();
				}
				else {
					this.printLoseInfo(forest[tx][ty], x, y, tx, ty);
					this.die(x, y);
					return this.getLoc();
				}
			}
		}
		else
			return null;
	}
	
	/**
	 * Implement a moving try with multiple steps of the Animal.
	 * @param x the Animal's current x-coordinate
	 * @param y the Animal's current y-coordinate
	 * @param r an integer to represent the moving direction
	 * @param s number of moving steps
	 * @return the destination coordination if the try succeeds,
	 * null if the try failed
	 */
	public int[] tryMove(int x, int y, int r, int s) {
		int[][] dir = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
		int tx = x + dir[r][0] * s;
		int ty = y + dir[r][1] * s;
		if(tx >= 0 && tx < lim && ty >= 0 && ty < lim) {
			boolean flag = true;
			int ttx = x;
			int tty = y;
			for(int i = 1; i <= s; ++i) {
				ttx += dir[r][0];
				tty += dir[r][1];
				if(forest[ttx][tty] == null || forest[ttx][tty].getAbbr() == '.') {
					this.moveto(ttx, tty);
					flag = true;
				}
				else {
					flag = false;
					if(this.attack(forest[ttx][tty])) {
						this.printWinInfo(forest[ttx][tty], x, y, ttx, tty);
						forest[ttx][tty].die(ttx, tty);
						this.printMoveInfo(x, y, ttx, tty);
						this.moveto(ttx, tty);
						x = ttx;
						y = tty;
					}
					else {
						this.printLoseInfo(forest[ttx][tty], x, y, ttx, tty);
						this.die(x, y);
						return this.getLoc();
					}
				}
			}
			if(flag)
				this.printMoveInfo(x, y, tx, ty);
			return this.getLoc();
		}
		else 
			return null;
	}
	
	/**
	 * Implement the Animal's attacking behavior
	 * @param a another Animal attacked by the Animal
	 * @return whether the Animal wins
	 */
	public abstract boolean attack(Animal a);
	
	/**
	 * Print the information about a moving behavior of the Animal.
	 * @param x the Animal's current x-coordinate
	 * @param y the Animal's current y-coordinate
	 * @param tx the destination x-coordinate
	 * @param ty the destination y-coordinate
	 */
	public void printMoveInfo(int x, int y, int tx, int ty) {
		this.active = false;
		System.out.printf("%s moved from (%d, %d) to (%d, %d)\n", this.name, x, y, tx, ty);
	}
	
	/**
	 * Print the information about a staying behavior of the Animal.
	 * @param x the Animal's current x-coordinate
	 * @param y the Animal's current y-coordinate
	 */
	public void printStayInfo(int x, int y) {
		System.out.printf("%s stayed in (%d, %d)\n", this.name, x, y);
	}
	
	/**
	 * Print the information about a winning behavior of the Animal.
	 * @param x the Animal's current x-coordinate
	 * @param y the Animal's current y-coordinate
	 */
	public void printWinInfo(Animal a, int x, int y, int tx, int ty) {
		System.out.printf("%s from (%d, %d) attacks %s at (%d, %d) and wins\n", this.name, x, y, a.getName(), tx, ty);
	}
	
	/**
	 * Print the information about a losing behavior of the Animal.
	 * @param x the Animal's current x-coordinate
	 * @param y the Animal's current y-coordinate
	 */
	public void printLoseInfo(Animal a, int x, int y, int tx, int ty) {
		System.out.printf("%s from (%d, %d) attacks %s at (%d, %d) and loses\n", this.name, x, y, a.getName(), tx, ty);
	}
	
	/**
	 * Print the information about the death of the Animal.
	 * @param x the Animal's current x-coordinate
	 * @param y the Animal's current y-coordinate
	 */
	public void printDieInfo(int x, int y) {
		System.out.printf("%s dies at (%d, %d)\n", this.name, x, y);
	}
}
