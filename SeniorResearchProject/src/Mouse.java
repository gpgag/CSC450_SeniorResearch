import java.util.Random;

public class Mouse {

	//***************************************************
	//Variables											*				
	//***************************************************
	private static int max = 100;
	
	private int probUp;
	private int probDown;
	private int probLeft;
	private int probRight;	
	
	private int fitness = 0;
	
	//***************************************************
	//Constructors										*
	//***************************************************
	public Mouse() {}
	
	public Mouse(int probUp, int probDown, int probLeft, int probRight) {
		this.probUp = probUp;
		this.probDown = probDown;
		this.probLeft = probLeft;
		this.probRight = probRight;
	}
	
	//***************************************************
	//Functions											*
	//***************************************************
	
	//This function randomly generates the movement probabilities of the mouse
	public void generateMouse() {
		Random rand = new Random();
		
		probUp = rand.nextInt((max -3) -1) + 1;
		System.out.println(probUp);
		probDown = rand.nextInt(((max - probUp) - 2) -1) + 1;
		System.out.println(probDown);
		probLeft = rand.nextInt((((max - probUp) - probDown) - 1)-1) + 1;
		System.out.println(probLeft);
		probRight = max - probUp - probDown - probLeft;
		System.out.println(probRight);
	}
	
	//This function displays the total, used to test and ensure the total is 100
	public void showTotalProb() {
		int totalProb;
		totalProb = probUp + probDown + probLeft + probRight;
		System.out.println(totalProb);
	}
	
	//This function checks if the total probability of movement is 100%,
	//if not it will call the function to adjust
	public boolean correctPercent() {
		Random rand = new Random();
		int totalProb = probUp + probDown + probLeft + probRight;
		
		if (totalProb < 100) {
			
			int mutate = rand.nextInt((4-1) + 1) + 1;
			
			if (mutate == 1)
				probUp++;
			else if (mutate == 2)
				probDown++;
			else if (mutate == 3)
				probLeft++;
			else if (mutate == 4)
				probRight++;
			
			return false;
		} else
		return true;
	}
	
	//***************************************************
	//Getters & Setters									*
	//***************************************************
	
	//probUp
	public int getProbUp() {
		return probUp;
	}
	public void setProbUp(int prob) {
		probUp = prob;
	}
	
	//probDown
	public int getProbDown() {
		return probDown;
	}
	public void setProbDown(int prob) {
		probDown = prob;
	}
	
	//probLeft
	public int getProbLeft() {
		return probLeft;
	}
	public void setProbLeft(int prob) {
		probLeft = prob;
	}

	//probRight
	public int getProbRight() {
		return probRight;
	}
	public void setProbRight(int prob) {
		probRight = prob;
	}	
	
	//fitness
	public int getFitness() {
		if (fitness == 0) {
			//calculate fitness here
		}
		
		return fitness;
	}
}
