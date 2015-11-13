import java.util.Random;

public class Maze {

	//***************************************************
	//Variables											*				
	//***************************************************
	private static int SIZE = 5;
	private static int START_X = 0;
	private static int START_Y = 0;
	private static int MAXIMUM_TOTAL_MOVES = 2147483647;
	
	private int positionX;
	private int positionY;
	private int nextMove;
	private int totalMoves = 0;
	
	private int rangeUp;
	private int rangeDown;
	private int rangeLeft;
	private int rangeRight;
	
	private boolean mazeComplete = false;

	//***************************************************
	//Constructors										*
	//***************************************************
	public Maze(){}
	
	//***************************************************
	//Functions											*
	//***************************************************
	public int runMaze(Mouse testMouse) {
		Random rand = new Random();
		
		int positionX = 0;
		int positionY = 0;
		int nextMove = 0;
		int totalMoves = 0;
		
		int rangeUp = 0;
		int rangeDown = 0;
		int rangeLeft = 0;
		int rangeRight = 0;
		
		boolean mazeComplete = false;
		
		rangeUp = testMouse.getProbUp();
		rangeDown = rangeUp + testMouse.getProbDown();
		rangeLeft = rangeDown + testMouse.getProbLeft();
		rangeRight = rangeLeft + testMouse.getProbRight();
		
		while (!mazeComplete && totalMoves < MAXIMUM_TOTAL_MOVES) {
			nextMove = rand.nextInt((100 - 1) + 1) + 1;
			
			//check if its up
			if (nextMove <= rangeUp) {
				if (positionY < SIZE)
					positionY++;
				
				totalMoves++;
				
			} //else check down
			else if (nextMove > rangeUp && nextMove <= rangeDown) {
				if (positionY > 0)
					positionY--;
				
				totalMoves++;
				
			} //else check left
			else if (nextMove > rangeDown && nextMove <= rangeLeft) {
				if (positionX > 0)
					positionX--;
				
				totalMoves++;
				
			} //else check right
			else if (nextMove > rangeLeft && nextMove <= rangeRight) {
				if (positionX < SIZE)
					positionX++;
				
				totalMoves++;
			}	
			
			if (positionX == SIZE && positionY == SIZE){
				mazeComplete = true;
			}
		}
		
		//test if the maze is finished to output a message
		if (mazeComplete) {
			System.out.println("Maze Completed in " + totalMoves + " Moves!!!");
		} else if (totalMoves == MAXIMUM_TOTAL_MOVES) {
			System.out.println("Maze Incomplete at " + totalMoves + " Moves...");
		}
			
		System.out.println("PositionX: " + positionX);
		System.out.println("PositionY: " + positionY);
		
		return totalMoves;
	}
}
