import java.util.Random;

public class GenAlg {

	//***************************************************
	//Variables											*				
	//***************************************************
	
	private static final int tournamentSize = 10;
	private static final double mutateRate = 0.015;
	private static final int maxMutate = 50;
	private static final int minMutate = 10;
	private static final boolean saveBest = true;
	
	//***************************************************
	//Functions											*
	//***************************************************
	
	//evolve population to next generation
	public static Population evolveNextGen(Population pop, boolean tournySelection) {
		Population nextGen = new Population(pop.size(), false);
		
		//this always saves the best mouse for the next population
		if (saveBest) {
			nextGen.saveMouse(0, pop.getFittest());
		}
		
		int saveBestOffset;
		if (saveBest) {
			saveBestOffset = 1;
		} else {
			saveBestOffset = 0;
		}
		
		
		//create new mice until the new population is same size as old
		//i = 1 because index 0 is best mouse from old generation
		for (int i = saveBestOffset; i < pop.size(); i++) {
			
			if (tournySelection) {
				Mouse mom = tournamentSelection(pop);
				Mouse dad = tournamentSelection(pop);
				Mouse kid = breedMice(mom, dad);
				nextGen.saveMouse(i,  kid);
			} else {
				Mouse mom = ordinalSelection(pop);
				Mouse dad = ordinalSelection(pop);
				Mouse kid = breedMice(mom, dad);
				nextGen.saveMouse(i,  kid);
			}
		}
		
		for (int i = saveBestOffset; i < nextGen.size(); i++) {
			mutate(nextGen.getMouse(i));
		}
		
		return nextGen;
	}
	
	//This function breeds two mice together to create a single child
	private static Mouse breedMice(Mouse momMouse, Mouse dadMouse) {
		
		//get moms genetics
		int momUp = momMouse.getProbUp();
		int momDown = momMouse.getProbDown();
		int momLeft = momMouse.getProbLeft();
		int momRight = momMouse.getProbRight();
		
		//get dads genetics
		int dadUp = dadMouse.getProbUp();
		int dadDown = dadMouse.getProbDown();
		int dadLeft = dadMouse.getProbLeft();
		int dadRight = dadMouse.getProbRight();
		
		//create the child
		Mouse child = new Mouse(((momUp + dadUp)/2), ((momDown + dadDown)/2), 
				((momLeft + dadLeft)/2), ((momRight + dadRight)/2));
		
		//probability may not total 100, repeat this loop till it does
		boolean done = false;
		while (done == false) {
			done = child.correctPercent();
		}
		
		/*//output for testing
		System.out.println(child.getProbUp());
		System.out.println(child.getProbDown());
		System.out.println(child.getProbLeft());
		System.out.println(child.getProbRight());
		System.out.println(child.getTotalProb());*/
		
		//return the finished child
		return child;
	}
	
	//this function mutates the mice to get more variation
	private static void mutate(Mouse mouse) {
		Random rand = new Random();
		
		//chance to mutate each direction
		for (int i = 0; i < 4; i++) {
			
			//mutate rate is the probability of mutation
			if (Math.random() <= mutateRate) {
				mouse.setProb(i, mouse.getProb(i) + 
						rand.nextInt(((maxMutate - minMutate) + 1) + minMutate));
			}
		}
		
		//now values must be readjusted back to 100% total
		
	}
	
	//ordinal selection, just take the best
	private static Mouse ordinalSelection(Population pop) {
		Random rand = new Random();
		pop.sortPopByMoves(pop);
		
		//randomly pick one mouse out of top group based on tournament size
		return pop.getMouse(rand.nextInt((((tournamentSize/2) - 1) + 0) + 1) + 0);
	}
	
	//proportional selection: tournament selection
	private static Mouse tournamentSelection(Population pop) {
		Random rand = new Random();
		Population tournament = new Population(tournamentSize, false);
		
		//randomly get 10 mice from the population for the tournament
		for (int i = 0; i < tournamentSize; i++) {
			int randIndex = rand.nextInt((((pop.size() - 1) + 0) + 1) + 0);
			tournament.saveMouse(i, pop.getMouse(randIndex));
		}
		
		//find and return the most fit of the mice;
		Mouse fittest = tournament.getFittest();
		return fittest;
	}
}
