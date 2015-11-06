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
		return probUp;
	}
	public void setProbDown(int prob) {
		probUp = prob;
	}
	
	//probLeft
	public int getProbLeft() {
		return probUp;
	}
	public void setProbLeft(int prob) {
		probUp = prob;
	}

	//probRight
	public int getProbRight() {
		return probUp;
	}
	public void setProbRight(int prob) {
		probUp = prob;
	}	
	
	//fitness
	public int getFitness() {
		if (fitness == 0) {
			//calculate fitness here
		}
		
		return fitness;
	}
}
