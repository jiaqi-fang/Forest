import java.util.Scanner;

/**
 * The Forest class is used to simulate a Forest,
 * where animals move and attack each other.
 * @author jqfang
 */
public class Forest {

	private Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Forest myForest = new Forest();
		int n = 15;
		myForest.init(n);
		myForest.exec();
	}
	
	/**
	 * Initialize the Forest simulation program.
	 * @param n size of the Forest
	 */
	public void init(int n) {
		Animal.initForest(n);
		printForest();
		while(true) {
			printInitInfo();
			String arg = readInitArg();
			if(arg.length() < 1 || arg.charAt(0) < '0' || arg.charAt(0) > '9')
				continue;
			int argn = Integer.parseInt(arg);
			if(argn == 0) {
				printForest();
				break;
			}
			Animal a;
			switch(argn) {
			case 1:
				a = new Dog(); break;
			case 2:
				a = new Fox(); break;
			case 3:
				a = new Wolf(); break;
			case 4:
				a = new Cat(); break;
			case 5:
				a = new Lion(); break;
			case 6:
				a = new Tiger(); break;
			case 7:
				a = new Hippo(); break;
			case 8:
				a = new Turtle(); break;
			default:
				continue;
			}
			a.init();
			Animal.forest[a.getx()][a.gety()] = a;
			printForest();
		}
		Animal.initDeadList();
	}
	
	/**
	 * Execute the Forest simulation program.
	 * Users can enter to run a cycle of animal behavior simulation,
	 * type 'print' to print the Forest, or type 'exit' to quit.
	 * @param n size of the Forest
	 */
	public void exec() {
		while(true) {
			String arg = readExecArg();
			if(arg.equals("")) {
				doMove();
				printForest();
			}
			else if(arg.equals("print")) {
				printForest();
				//printAnimList();
				printDeadList();
			}
			else if(arg.equals("exit")) {
				printForest();
				//printAnimList();
				printDeadList();
				keyboard.close();
				break;
			}
			else
				continue;
		}	
	}
	
	/**
	 * Print the Forest.
	 * @param n size of the Forest
	 */
	public void printForest() {
		for(int i = 0; i < Animal.lim; ++i) {
			for(int j = 0; j < Animal.lim; ++j) {
				if(Animal.forest[i][j] == null)
					System.out.print('.');
				else
					System.out.print(Animal.forest[i][j].getAbbr());
			}
			System.out.print('\n');
		}
		System.out.print('\n');
	}
	
	/**
	 * Print information about initialization of the Forest,
	 * reminding users to add animals.
	 */
	public void printInitInfo() {
		System.out.println("1.\tDog (d)");
		System.out.println("2.\tFox (f)");
		System.out.println("3.\tWolf (w)");
		System.out.println("4.\tCat (c)");
		System.out.println("5.\tLion (l)");
		System.out.println("6.\tTiger (t)");
		System.out.println("7.\tHippo (h)");
		System.out.println("8.\tTurtle (u)");
		System.out.println("What would you like to add to the Forest?");
	}
	
	/**
	 * Read an input argument to determine which kind of animals should be added.
	 * @return input argument
	 */
	public String readInitArg() {
		System.out.print("Please enter your choice (1-8, or 0 to finish the animal input): ");
		String arg = keyboard.nextLine();
		return arg;
	}
	
	/**
	 * Implement animal moving and attacking behaviors in alphabetic order.
	 * @param n
	 */
	public void doMove() {
		char[] animList = {'c', 'd', 'f', 'h', 'l', 't', 'u', 'w'};
		for(int i = 0; i < Animal.lim; ++i)
			for(int j = 0; j < Animal.lim; ++j) {
				Animal a = Animal.forest[i][j];
				if(a != null) {
					a.activate();
				}
			}
		for(int k = 0; k < 8; ++k)
			for(int i = 0; i < Animal.lim; ++i)
				for(int j = 0; j < Animal.lim; ++j) {
					Animal a = Animal.forest[i][j];
					if(a != null && a.getAbbr() == animList[k] && a.isActive()) {
						a.move();
					}
				}
	}
	
	/**
	 * Read an input argument to determine what to be executed in the iteration.
	 * @return input argument
	 */
	public String readExecArg() {
		System.out.print("Press enter to iterate, type 'print' to print the Forest or 'exit' to quit: ");
		String arg = keyboard.nextLine();
		return arg;
	}
	
	/**
	 * Print the list of living animals and their locations.
	 */
	public void printAnimList() {
		for(int i = 0; i < Animal.lim; ++i)
			for(int j = 0; j < Animal.lim; ++j) {
				Animal a = Animal.forest[i][j];
				if(a != null) {
					System.out.printf("%s is at location (%d, %d)\n", a.getName(), i, j);
				}
			}
		System.out.print('\n');
	}
	
	/**
	 * Print the list of dead animals and their locations.
	 */
	public void printDeadList() {
		for(int i = 0; i < Animal.deadNum; ++i) {
			String name = Animal.deadList[i];
			int x = Animal.deadLocList[i][0];
			int y = Animal.deadLocList[i][1];
			System.out.printf("%s died at location (%d, %d)\n", name, x, y);
		}
		System.out.print('\n');
	}

}
