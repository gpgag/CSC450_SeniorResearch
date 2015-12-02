import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Simulation {

	//***************************************************
	//					MAIN FUNCTION					*				
	//***************************************************
	public static void main(String[] args) {	
		
		//***************************************************
		//Variables											*				
		//***************************************************
		final int POPULATION_SIZE = 100;
		final int GENERATION_AMOUNT = 400;
		final int NUMBER_OF_TRIALS = 1000;
		
		double[] results1 = new double[NUMBER_OF_TRIALS];
		double[] results2 = new double[NUMBER_OF_TRIALS];
		
		double[] fitResults1 = new double[NUMBER_OF_TRIALS];
		double[] fitResults2 = new double[NUMBER_OF_TRIALS];
		
		Maze maze = new Maze();
		
		//total results ordinal
		double aveBestGen1 = -1;
		double aveBestFit1 = 0;
		double std1 = 0;
		
		//total results proportional
		double aveBestGen2 = -1;
		double aveBestFit2 = 0;
		double std2 = 0;
		
		//REPEAT THE TRIALS TO GATHER DATA
		for (int i = 0; i < NUMBER_OF_TRIALS; i++) {
			Population testPop1 = new Population (POPULATION_SIZE, true);
			Population testPop2 = new Population (POPULATION_SIZE, true);
			
			int genCount;
			
			//results for ordinal selection
			int bestGenFit1 = -1;
			int bestFit1 = 0;	
			Mouse bestMouse1 = null;

			
			//results for proportional selection
			int bestGenFit2 = -1;
			int bestFit2 = 0;
			Mouse bestMouse2 = null;

			
			
			//***************************************************
			//Ordinal Selection Test							*				
			//***************************************************
			/*System.out.println("ORDINAL SELECTION");
			System.out.println("---------------------------------------");*/
			for (genCount = 1; genCount <= GENERATION_AMOUNT; genCount++) {
				
				for (int j = 0; j < testPop1.size(); j++) {
					maze.runMaze(testPop1.getMouse(j));
				}
				
				/*System.out.println("Generation: " + genCount + " Fittest: " + 
						testPop1.getFittest().getFitness());*/
				
				//test if this generation has a better fitness
				if (testPop1.getFittest().getFitness() > bestFit1) {
					bestGenFit1 = genCount;
					bestFit1 = testPop1.getFittest().getFitness();
					bestMouse1 = testPop1.getFittest();
				}
				
				testPop1 = GenAlg.evolveNextGen(testPop1, false);
			}
			
			//***************************************************
			//Proportional Selection Test						*				
			//***************************************************
			/*System.out.println();
			System.out.println("PROPORTIONAL SELECTION");
			System.out.println("---------------------------------------");*/
			for (genCount = 1; genCount <= GENERATION_AMOUNT; genCount++) {
				
				for (int j = 0; j < testPop2.size(); j++) {
					maze.runMaze(testPop2.getMouse(j));
				}
				
				/*System.out.println("Generation: " + genCount + " Fittest: " + 
						testPop2.getFittest().getFitness());*/
				
				//test if this generation has a better fitness
				if (testPop2.getFittest().getFitness() > bestFit2) {
					bestGenFit2 = genCount;
					bestFit2 = testPop2.getFittest().getFitness();
					bestMouse2 = testPop2.getFittest();
				}
				
				//the boolean = true is for tournament selection
				testPop2 = GenAlg.evolveNextGen(testPop2, true);
			}
			
			//***************************************************
			//Top Trial Results for Comparison						*				
			//***************************************************
			System.out.println();
			System.out.println(i + ". ORDINAL RESULTS");
			System.out.println("---------------------------------------");
			System.out.println("Best Fitness");
			System.out.println("Generation: " + bestGenFit1 + " Fitness: " + bestFit1);
			
			System.out.println();
			System.out.println(i + ". PROPORTIONAL RESULTS");
			System.out.println("---------------------------------------");
			System.out.println("Best Fitness");
			System.out.println("Generation: " + bestGenFit2 + " Fitness: " + bestFit2);
			
			//***************************************************
			//Adding trial results to total results				*				
			//***************************************************
			results1[i] = bestGenFit1;
			fitResults1[i] = bestFit1;
			aveBestGen1 += bestGenFit1;
			aveBestFit1 += bestFit1;
			
			results2[i] = bestGenFit2;
			fitResults2[i] = bestFit2;
			aveBestGen2 += bestGenFit2;
			aveBestFit2 += bestFit2;
			
		}//end "number of trials" for loop
		
		//***************************************************
		//Displaying final results							*				
		//***************************************************
		aveBestGen1 = aveBestGen1 / NUMBER_OF_TRIALS;
		aveBestFit1 = aveBestFit1 / NUMBER_OF_TRIALS;
		aveBestGen2 = aveBestGen2 / NUMBER_OF_TRIALS;
		aveBestFit2 = aveBestFit2 / NUMBER_OF_TRIALS;
		
		//calculate standard deviation
		std1 = calcStandardDev(results1, aveBestGen1);
		std2 = calcStandardDev(results2, aveBestGen2);
		
		System.out.println("          ***FINAL RESULTS***");
		System.out.println();
		System.out.println("ORDINAL RESULTS");
		System.out.println("---------------------------------------");
		System.out.println("Average Best Fitness");
		System.out.println("Fitness: " + aveBestFit1);
		System.out.println("Average Best Generation");
		System.out.println("Generation: " + aveBestGen1 + " Standard Deviation: " + std1);
		
		System.out.println();
		System.out.println("PROPORTIONAL RESULTS");
		System.out.println("---------------------------------------");
		System.out.println("Average Best Fitness");
		System.out.println("Fitness: " + aveBestFit2);
		System.out.println("Average Best Generation");
		System.out.println("Generation: " + aveBestGen2 + " Standard Deviation: " + std2);
		System.out.println();
		
		try(Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("GenAlgOutput.txt"), "utf-8"))) {
			System.out.println("Writing Ordinal Data");
			writer.write("Ordinal Gen Data\r\n");
			for(int j = 0; j < results1.length; j++) {
				writer.write(Double.toString(results1[j]));
				if (j < results1.length - 1) {
					writer.write(", ");
				}
			}
			
			writer.write("\r\n\r\nOrdinal Fit Data\r\n");
			for(int j = 0; j < fitResults1.length; j++) {
				writer.write(Double.toString(fitResults1[j]));
				if (j < fitResults1.length - 1) {
					writer.write(", ");
				}
			}
			
			System.out.println("Completed Writing Ordinal Data");
			System.out.println("Writing Proportional Data");
			writer.write("\r\n\r\nProportional Gen Data\r\n");
			for(int j = 0; j < results2.length; j++) {
				writer.write(Double.toString(results2[j]));
				if (j < results2.length - 1) {
					writer.write(", ");
				}
			}
			
			writer.write("\r\n\r\nProportional Fit Data\r\n");
			for(int j = 0; j < fitResults2.length; j++) {
				writer.write(Double.toString(fitResults2[j]));
				if (j < fitResults2.length - 1) {
					writer.write(", ");
				}
			}
			System.out.println("Completed Writing Proportional Data");
		} catch (IOException ex) {
			System.out.println("Error writing output");
		} 
	}
	
	//standard deviation calculating function
	private static double calcStandardDev(double[] results, double mean) {
		double std = 0;
		
		for (int i = 0; i < results.length; i++) {
			std += Math.pow((results[i] - mean), 2);
		}
		
		std = std / results.length;
		std = Math.sqrt(std);
		
		return std;
	}
}