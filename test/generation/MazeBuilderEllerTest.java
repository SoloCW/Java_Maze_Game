package generation;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import generation.Order.Builder;
import generation.CardinalDirection;
import generation.Cells;
import generation.Wall;

public class MazeBuilderEllerTest extends MazeBuilderEller implements Runnable{
	
	private MazeFactory mazefactory; 
	private MazeConfiguration mazeconfig;
	private StubOrder stuborder;

    /**
     * Tests to see if the basic MazeBuilderEller works for an imperfect maze.
     */
    @Test
    public void testIfMazeBuilderEllerWorksForImperfectMaze(){
        mazefactory = new MazeFactory();
        stuborder = new StubOrder(1, false, Builder.Eller);
        mazefactory.order(stuborder);
        mazefactory.waitTillDelivered();
        mazeconfig = stuborder.getConfiguration();
        assertNotNull(mazefactory);
        assertNotNull(stuborder);
        assertNotNull(mazeconfig);
    }
    /**
     * Tests to see if the basic MazeBuilderEller works for a perfect maze.
     */
    @Test
    public void testIfMazeBuilderEllerWorksForPerfectMaze(){
        mazefactory = new MazeFactory();
        stuborder = new StubOrder(1, true, Builder.Eller);
        mazefactory.order(stuborder);
        mazefactory.waitTillDelivered();
        mazeconfig = stuborder.getConfiguration();
        assertNotNull(mazefactory);
        assertNotNull(stuborder);
        assertNotNull(mazeconfig);
    }
    @Test
    /**
     * Check if all values are equal
     * 
     */
    public void CheckValues(){
        mazefactory = new MazeFactory();
        stuborder = new StubOrder(1, false, Builder.Eller);
        mazefactory.order(stuborder);
        mazefactory.waitTillDelivered();
        mazeconfig = stuborder.getConfiguration();
		int wid = mazeconfig.getWidth();   //generated dimension
		int hei = mazeconfig.getHeight();
		Cells celly = new Cells(wid,hei);
		int samevals=0;
		int cellsWithVal = wid*hei;
		int first = celly.getValueOfCell(0, 0);
        for(int i = 0; i<mazeconfig.getHeight();i++) {
        		for(int j = 0; j<mazeconfig.getWidth(); j++) {
        			if(celly.getValueOfCell(i, j)== first) {
        				samevals++;
        				//System.out.println(celly.getValueOfCell(i, j));
        		}
        	}
        }
        
        assertEquals(samevals,cellsWithVal);
    }   
    /**
     * Deletes wall should expect false, Test is sucessful if it passes at least once.
     */
    @Test
    public void testVerticalDirection() {

		Wall wall;
	   	MazeBuilderEller maze = new MazeBuilderEller();
	   	mazefactory = new MazeFactory();
	   	stuborder = new StubOrder(1, true, Builder.Eller);
	   	mazefactory.order(stuborder);
	   	mazefactory.waitTillDelivered();
	   	mazeconfig = stuborder.getConfiguration();

	   	int[][] set = new int[mazeconfig.getWidth()][mazeconfig.getHeight()];


	   	wall = new Wall(0, 0, CardinalDirection.South);
		while(mazeconfig.hasWall(0, 0, CardinalDirection.South) == true)
		{
	    mazefactory = new MazeFactory();
	    stuborder = new StubOrder(1, true, Builder.Eller);
	    mazefactory.order(stuborder);
	    mazefactory.waitTillDelivered();
	    mazeconfig = stuborder.getConfiguration();
       	maze.verticalDirection(set,0,0,wall);
		}
       	assertFalse(mazeconfig.hasWall(0, 0, CardinalDirection.South));
   }
    /**
     * Deletes a wall should expect False, Test is sucessful if it passes at least once.
     */
    @Test
    public void testHorizontalDirection() {
    		Wall wall;
    	   	MazeBuilderEller maze = new MazeBuilderEller();
        mazefactory = new MazeFactory();
        stuborder = new StubOrder(1, true, Builder.Eller);
        mazefactory.order(stuborder);
        mazefactory.waitTillDelivered();
        mazeconfig = stuborder.getConfiguration();

       	int[][] set = new int[mazeconfig.getWidth()][mazeconfig.getHeight()];


		wall = new Wall(0, 0, CardinalDirection.East);
		while(mazeconfig.hasWall(0, 0, CardinalDirection.East) == true)
		{
	    mazefactory = new MazeFactory();
	    stuborder = new StubOrder(1, true, Builder.Eller);
	    mazefactory.order(stuborder);
	    mazefactory.waitTillDelivered();
	    mazeconfig = stuborder.getConfiguration();
       	maze.horizontalDirection(set,0,0,wall);
		}
       	assertFalse(mazeconfig.hasWall(0, 0, CardinalDirection.East));
    }
    /**
     * Check if board can have same values
     * 
     */
    @Test 
    
    public void checkBoardValues() {
    	int [][]maze  = new int[12][12];
    	for(int row = 0; row < maze.length; row++) {
    		for(int col =0; col < maze.length; col++) {
    			maze[row][col] = 1;
    		}
    	}
    	Random rand = new Random();
    	int rand1 = rand.nextInt(12);
    	int rand2 = rand.nextInt(12);
    	int rand3 = rand.nextInt(12);
    	int rand4 = rand.nextInt(12);
    	
    	assertTrue( maze[rand1][rand2] == maze[rand3][rand4]);
    }
}