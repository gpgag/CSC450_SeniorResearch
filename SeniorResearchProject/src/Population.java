
public class Population {

	//***************************************************
	//Variables											*				
	//***************************************************
	Mouse[] mice;

	//***************************************************
	//Constructors										*
	//***************************************************
	public Population(int numMice, boolean initialize) {
		mice = new Mouse[numMice];

		if(initialize) {
			
			for (int i = 0; i < size(); i++) {
                Mouse newMouse = new Mouse();
                //System.out.println("Mouse #" + i);
                newMouse.generateMouse();
                //System.out.println(newMouse.getTotalProb());
                saveMouse(i, newMouse);
			}
		}
	}

	//***************************************************
	//Getters & Setters									*
	//***************************************************
    public Mouse getMouse(int index) {
        return mice[index];
    }
    public void setMouse(int index, Mouse mouse) {
    	mice[index] = mouse;
    }

    public Mouse getFittest() {
    	Mouse fittest = mice[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getMouse(i).getFitness()) {
                fittest = getMouse(i);
            }
        }
        return fittest;
    }

    //returns the size of the population
	public int size() {
		return mice.length;
	}
	
	//This function returns an average for total moves across the population
    public int getAverageTotalMoves() {
    	int aveTotMove = 0;
    	
    	for (int i = 0; i < size(); i++) {
    		aveTotMove += Math.abs(getMouse(i).getTotalMoves());
    	}
    	
    	aveTotMove = aveTotMove / size();
    	
    	return aveTotMove;
    }
	
	//***************************************************
	//Functions											*
	//***************************************************
	
	 // Save individual
    public void saveMouse(int index, Mouse mouse) {
        mice[index] = mouse;
    }
	
	//this is a function to sort the population based on total moves
	public void sortPopByMoves(Population pop) {
		int i;
		boolean flag = true;
		Mouse temp;
		
		//perform a bubble sort
		while(flag) {
			flag = false;
			for (i = 0; i < pop.size() - 1; i++) {
				
				if(pop.getMouse(i).getTotalMoves() > pop.getMouse(i+1).getTotalMoves()) {
					temp = pop.getMouse(i);
					pop.setMouse(i, pop.getMouse(i+1));;
					pop.setMouse(i+1, temp);
					flag = true;
				}
			}
		}
	}
}