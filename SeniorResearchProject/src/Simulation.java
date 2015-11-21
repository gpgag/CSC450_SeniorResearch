
public class Simulation {

	public static void main(String[] args) {	
		Population testPop = new Population(1, true);
		Maze maze = new Maze();
		testPop.size();
		
		int i = 0;
		int j = 1;
	/*	while (i < testPop.size()) {
			
			System.out.println("Child Mouse #" + j);
			j++;
			
			testPop.breedMice(i, i + 1);
			
			i += 2;
		}*/
		Mouse testMouse = new Mouse();
		testMouse.setProbUp(50);
		testMouse.setProbRight(50);
		maze.runMaze(testPop.getMouse(0));
		maze.runMaze(testMouse);
	}
}
