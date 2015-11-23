import java.util.Random; //for random range: random.nextInt((max - min) + 1) + min

public class Mouse {

	//***************************************************
	//Variables											*				
	//***************************************************
	private static int max = 100;
	
	private int[] moveProbs = new int[4];
	
	private int fitness = 0;
	private int totalMoves;
	
	//***************************************************
	//Constructors										*
	//***************************************************
	public Mouse() {}
	
	public Mouse(int probUp, int probDown, int probLeft, int probRight) {
		moveProbs[0] = probUp;
		moveProbs[1] = probDown;
		moveProbs[2] = probLeft;
		moveProbs[3] = probRight;
	}
	
	//***************************************************
	//Functions											*
	//***************************************************
	
	//This function randomly generates the movement probabilities of the mouse
	public void generateMouse() {
		Random rand = new Random();
		
		//calculate 4 probabilities that total 100%
		int[] probabilities = new int[4];
		
		probabilities[0] = rand.nextInt((max -3) -1) + 1;
		probabilities[1] = rand.nextInt(((max - probabilities[0]) - 2) -1) + 1;
		probabilities[2] = rand.nextInt((((max - probabilities[0]) - probabilities[1]) - 1)-1) + 1;
		probabilities[3] = max - probabilities[0] - probabilities[1] - probabilities[2];
	
		int nextProb = rand.nextInt((3-0) + 1) + 0;
		
		//Assign the probabilities to the movements in a random order
		for (int i = 0; i < moveProbs.length; i++) {
			moveProbs[nextProb % moveProbs.length] = probabilities[i];
			nextProb++;
		}
		
		/*//outpute the four probabilities for testing
		for (int i = 0; i < moveProbs.length; i++)
			System.out.println(moveProbs[i]);*/
		
	}
	
	//This function checks if the total probability of movement is 100%,
	//if not it will call the function to adjust
	public boolean correctPercent() {
		Random rand = new Random();
		int totalProb = getTotalProb();
		
		if (totalProb < 100) {
			
			int slightMutate = rand.nextInt((3-0) + 1) + 0;
			
			if (slightMutate == 0)
				moveProbs[slightMutate]++;
			else if (slightMutate == 1)
				moveProbs[slightMutate]++;
			else if (slightMutate == 2)
				moveProbs[slightMutate]++;
			else if (slightMutate == 3)
				moveProbs[slightMutate]++;
			
			return false;
		} else
		return true;
	}
	
	//this function redistributes probabilities back to 100 percent
	public void redistributeProbs() {
		int totalProb = getTotalProb();
		
		setProbUp(getProbUp() / totalProb);
		setProbDown(getProbDown() / totalProb);
		setProbLeft(getProbLeft() / totalProb);
		setProbRight(getProbRight() / totalProb);
		
		//probability may not total 100, repeat this loop till it does
		boolean done = false;
		while (done == false) {
			done = correctPercent();
		}
	}
	
	//this function prints the 4 probabilities
	public void printProbs() {
		for (int i = 0; i < moveProbs.length; i++)
			System.out.println(moveProbs[i]);
	}
	
	//***************************************************
	//Getters & Setters									*
	//***************************************************
	
	//movementProbs
	public int getProb(int index) {
		return moveProbs[index];
	}
	public void setProb(int index,int prob) {
		moveProbs[index] = prob;
	}
	
	//probUp
	public int getProbUp() {
		return moveProbs[0];
	}
	public void setProbUp(int prob) {
		moveProbs[0] = prob;
	}
	
	//probDown
	public int getProbDown() {
		return moveProbs[1];
	}
	public void setProbDown(int prob) {
		moveProbs[1] = prob;
	}
	
	//probLeft
	public int getProbLeft() {
		return moveProbs[2];
	}
	public void setProbLeft(int prob) {
		moveProbs[2] = prob;
	}

	//probRight
	public int getProbRight() {
		return moveProbs[3];
	}
	public void setProbRight(int prob) {
		moveProbs[3] = prob;
	}	
	
	//totalMoves
	public int getTotalMoves() {
		return totalMoves;
	}
	public void setTotalMoves(int moves) {
		totalMoves = moves;
	}	
	
	//This function displays the total, used to test and ensure the total is 100
	public int getTotalProb() {
		int totalProb;
		totalProb = moveProbs[0] + moveProbs[1] + moveProbs[2] + moveProbs[3];
		return totalProb;
	}
	
	//fitness
	public int getFitness() {
		if (fitness == 0) {
			fitness = FitTest.getFitness(this);
		}
		
		return fitness;
	}
}
