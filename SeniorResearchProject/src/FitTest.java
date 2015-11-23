
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
			value = 25;
		else if (distance > 0 && distance <= 4)
			value = 24;
		else if (distance > 4 && distance <= 8)
			value = 23;
		else if (distance > 8 && distance <= 12)
			value = 22;
		else if (distance > 12 && distance <= 16)
			value = 21;
		else if (distance > 16 && distance <= 20)
			value = 20;
		else if (distance > 20 && distance <= 24)
			value = 19;
		else if (distance > 24 && distance <= 28)
			value = 18;
		else if (distance > 28 && distance <= 32)
			value = 17;
		else if (distance > 32 && distance <= 36)
			value = 16;
		else if (distance > 36 && distance <= 40)
			value = 15;
		else if (distance > 40 && distance <= 44)
			value = 14;
		else if (distance > 44 && distance <= 48)
			value = 13;
		else if (distance > 48 && distance <= 52)
			value = 12;
		else if (distance > 52 && distance <= 56)
			value = 11;
		else if (distance > 56 && distance <= 60)
			value = 10;
		else if (distance > 60 && distance <= 64)
			value = 9;
		else if (distance > 64 && distance <= 68)
			value = 8;
		else if (distance > 68 && distance <= 72)
			value = 7;
		else if (distance > 72 && distance <= 76)
			value = 6;
		else if (distance > 76 && distance <= 80)
			value = 5;
		else if (distance > 80 && distance <= 84)
			value = 4;
		else if (distance > 84 && distance <= 88)
			value = 3;
		else if (distance > 88 && distance <= 92)
			value = 2;
		else if (distance > 92 && distance <= 96)
			value = 1;
		else if (distance > 96 && distance <= 100)
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
