import java.util.Random;

public class Maze {

	//***************************************************
	//Variables											*				
	//***************************************************
	private static int SIZE = 10;
	private static int START_X = 0;
	private static int START_Y = 0;
	//private static int MAXIMUM_TOTAL_MOVES = 2147483647;
	private static int MAXIMUM_TOTAL_MOVES = 1000000000;
	
	
	private int positionX;
	private int positionY;
	private int nextMove;
	private int totalMoves = 0;
	private int negativeMoves = 0;
	private int positiveMoves = 0;
	
	private int rangeUp;
	private int rangeDown;
	private int rangeLeft;
	private int rangeRight;
	
	private boolean mazeComplete;

	//***************************************************
	//Constructors										*
	//***************************************************
	public Maze(){}
	
	//***************************************************
	//Functions											*
	//***************************************************
	public void runMaze(Mouse testMouse) {
		Random rand = new Random();
		
		//at the start initialize all variables
		positionX = START_X;
		positionY = START_Y;
		nextMove = 0;
		totalMoves = 0;
		
		rangeUp = 0;
		rangeDown = 0;
		rangeLeft = 0;
		rangeRight = 0;
		
		mazeComplete = false;
		
		rangeUp = testMouse.getProbUp();
		rangeDown = rangeUp + testMouse.getProbDown();
		rangeLeft = rangeDown + testMouse.getProbLeft();
		rangeRight = rangeLeft + testMouse.getProbRight();
		
		while (!mazeComplete && totalMoves < MAXIMUM_TOTAL_MOVES) {
			nextMove = rand.nextInt((100 - 1) + 1) + 1;
			
			int oldX = positionX;
			int oldY = positionY;
			
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
			
			//test if the mouse moved away from the exit or towards it
			if (oldX < positionX || oldY < positionY) {
				negativeMoves++;
			} else if (oldX > positionX || oldY > positionY) {
				positiveMoves++;
			}
			
			//test if the maze has been completed
			if (positionX == SIZE && positionY == SIZE){
				mazeComplete = true;
			}
			
			
		}
		
		//set the total moves to the mouse
		testMouse.setTotalMoves(totalMoves);
		
		//test if the maze is finished to output a message
		if (mazeComplete) {
			System.out.println("Maze Completed in " + totalMoves + " Moves!!!");
		} else if (totalMoves == MAXIMUM_TOTAL_MOVES) {
			System.out.println("Maze Incomplete at " + totalMoves + " Moves...");
		}
			
		System.out.println("PositionX: " + positionX);
		System.out.println("PositionY: " + positionY);
		
		System.out.println("Fitness: " + testMouse.getFitness());
		
		
		return;
	}
}
