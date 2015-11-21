
public class FitTest {

	//***************************************************
	//Variables											*				
	//***************************************************
	static private int[] solution = {50,0,0,50}; 
	
	//***************************************************
	//Functions											*
	//***************************************************
	static int getFitness(Mouse mouse) {
		int fitness = 0;
		
		//for each probability, calculate how far it is from the solution, 
		//for every 10 away, it is less fit
		for (int i = 0; i < solution.length; i++) { 
			fitness += getFitValue(solution[i], mouse.getProb(i));
		}
		return fitness;
	}
	
	//This function determines how much to add to fitness based on 
	//the difference between the desired and actual values
	static int getFitValue(int desired, int actual) {
		
		int value = 0;
		
		//get the distance
		int distance = Math.abs(desired - actual);
		
		//check the distance
		if (distance == 0) 
			value = 5;
		else if (distance > 0 && distance <= 10)
			value = 4;
		else if (distance > 10 && distance <= 20)
			value = 3;
		else if (distance > 20 && distance <= 30)
			value = 2;
		else if (distance > 30 && distance <= 40)
			value = 1;
		else if (distance > 40 && distance <= 50)
			value = 0;
		else  
			value = -1;
		
		/*//for testing
		System.out.println("desired: " + desired);
		System.out.println("actual: " + actual);
		System.out.println("distance: " + distance);
		System.out.println("value: " + value);*/
		
		return value;
	}
	
}
