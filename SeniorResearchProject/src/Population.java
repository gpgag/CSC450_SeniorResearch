
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
    public Mouse getMouse(int index) {
        return mice[index];
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

    // Save individual
    public void saveMouse(int index, Mouse mouse) {
        mice[index] = mouse;
    }

    //returns the size of the population
	public int size() {
		return mice.length;
	}
	
	//This function breeds two mice together to create a single child
	public Mouse breedMice(int momIndex, int dadIndex) {
		
		int momUp = mice[momIndex].getProbUp();
		int momDown = mice[momIndex].getProbDown();
		int momLeft = mice[momIndex].getProbLeft();
		int momRight = mice[momIndex].getProbRight();
		
		int dadUp = mice[dadIndex].getProbUp();
		int dadDown = mice[dadIndex].getProbDown();
		int dadLeft = mice[dadIndex].getProbLeft();
		int dadRight = mice[dadIndex].getProbRight();
		
		
		Mouse child = new Mouse(((momUp + dadUp)/2), ((momDown + dadDown)/2), ((momLeft + dadLeft)/2), ((momRight + dadRight)/2));
		
		boolean done = false;
		
		while (done == false) {
			done = child.correctPercent();
		}
		
		System.out.println(child.getProbUp());
		System.out.println(child.getProbDown());
		System.out.println(child.getProbLeft());
		System.out.println(child.getProbRight());
		child.showTotalProb();
		
		return child;
	}
}
