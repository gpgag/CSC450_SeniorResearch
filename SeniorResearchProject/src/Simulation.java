
public class Simulation {

	//***************************************************
	//					MAIN FUNCTION					*				
	//***************************************************
	public static void main(String[] args) {	
		
		//***************************************************
		//Variables											*				
		//***************************************************
		final int POPULATION_SIZE = 100;
		final int GENERATION_AMOUNT = 100;
		
		Maze maze = new Maze();
		
		Population testPop1 = new Population (POPULATION_SIZE, true);
		Population testPop2 = new Population (POPULATION_SIZE, true);
		
		int genCount;
		
		//results for ordinal selection
		int bestGenFit1 = -1;
		int bestFit1 = 0;
		int bestGenMove1 = -1;
		int bestMove1 = 2147483647;
		Mouse bestMouse1 = null;

		
		//results for proportional selection
		int bestGenFit2 = -1;
		int bestFit2 = 0;
		int bestGenMove2 = -1;
		int bestMove2 = 2147483647;
		Mouse bestMouse2 = null;

		
		
		//***************************************************
		//Ordinal Selection Test							*				
		//***************************************************
		System.out.println("ORDINAL SELECTION");
		System.out.println("---------------------------------------");
		for (genCount = 1; genCount <= GENERATION_AMOUNT; genCount++) {
			
			for (int i = 0; i < testPop1.size(); i++) {
				maze.runMaze(testPop1.getMouse(i));
			}
			
			System.out.println("Generation: " + genCount + " Average Moves: "
					+ testPop1.getAverageTotalMoves() + " Fittest: " + 
					testPop1.getFittest().getFitness());
			
			//test if this generation has a better fitness
			if (testPop1.getFittest().getFitness() > bestFit1) {
				bestGenFit1 = genCount;
				bestFit1 = testPop1.getFittest().getFitness();
				bestMouse1 = testPop1.getFittest();
			}
			
			//test if this generation has the best number of moves
			if (testPop1.getAverageTotalMoves() < bestMove1) {
				bestGenMove1 = genCount;
				bestMove1 = testPop1.getAverageTotalMoves();
			}
			
			testPop1 = GenAlg.evolveNextGen(testPop1, false);
		}
		
		//***************************************************
		//Proportional Selection Test						*				
		//***************************************************
		System.out.println();
		System.out.println("PROPORTIONAL SELECTION");
		System.out.println("---------------------------------------");
		for (genCount = 1; genCount <= GENERATION_AMOUNT; genCount++) {
			
			for (int i = 0; i < testPop2.size(); i++) {
				maze.runMaze(testPop2.getMouse(i));
			}
			
			System.out.println("Generation: " + genCount + " Average Moves: "
					+ testPop2.getAverageTotalMoves() + " Fittest: " + 
					testPop2.getFittest().getFitness());
			
			//test if this generation has a better fitness
			if (testPop2.getFittest().getFitness() > bestFit2) {
				bestGenFit2 = genCount;
				bestFit2 = testPop2.getFittest().getFitness();
				bestMouse2 = testPop2.getFittest();
			}
			
			//test if this generation has the best number of moves
			if (testPop2.getAverageTotalMoves() < bestMove2) {
				bestGenMove2 = genCount;
				bestMove2 = testPop2.getAverageTotalMoves();
			}
			
			//the boolean = true is for tournament selection
			testPop2 = GenAlg.evolveNextGen(testPop2, true);
		}
		
		//***************************************************
		//Top Results for Comparison						*				
		//***************************************************
		System.out.println();
		System.out.println("ORDINAL RESULTS");
		System.out.println("---------------------------------------");
		System.out.println("Best Fitness");
		System.out.println("Generation: " + bestGenFit1 + " Fitness: " + bestFit1);
		System.out.println("Best Average Number of Moves");
		System.out.println("Generation: " + bestGenMove1 + " Moves: " + bestMove1);
		System.out.println("Best Mouse");
		System.out.println("Fitness: " + bestMouse1.getFitness() + " Moves: " + bestMouse1.getTotalMoves());
		bestMouse1.printProbs();
		
		System.out.println();
		System.out.println("PROPORTIONAL RESULTS");
		System.out.println("---------------------------------------");
		System.out.println("Best Fitness");
		System.out.println("Generation: " + bestGenFit2 + " Fitness: " + bestFit2);
		System.out.println("Best Average Number of Moves");
		System.out.println("Generation: " + bestGenMove2 + " Moves: " + bestMove2);
		System.out.println("Best Mouse");
		System.out.println("Fitness: " + bestMouse2.getFitness() + " Moves: " + bestMouse2.getTotalMoves());
		bestMouse2.printProbs();
		System.out.println();
	}
}
