
public class Population {

	Mouse[] mice;

	public Population(int numMice, boolean initialize) {
		mice = new Mouse[numMice];

		if(initialize) {
			
			for (int i = 0; i < size(); i++) {
                Mouse newMouse = new Mouse();
                System.out.println("Mouse #" + i);
                newMouse.generateMouse();
                newMouse.showTotalProb();
                saveMouse(i, newMouse);
			}
		}
	}
	// Getters 
    public Mouse getMice(int index) {
        return mice[index];
    }

    public Mouse getFittest() {
    	Mouse fittest = mice[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getMice(i).getFitness()) {
                fittest = getMice(i);
            }
        }
        return fittest;
    }

    // Save individual
    public void saveMouse(int index, Mouse mouse) {
        mice[index] = mouse;
    }

    //returns the size of the population
	public int size() {
		return mice.length;
	}
}
