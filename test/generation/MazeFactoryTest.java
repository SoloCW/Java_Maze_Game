package generation;


import static org.junit.Assert.*;

import org.junit.Test;

import falstad.Constants;
import generation.Order.Builder;

import generation.Cells;


public class MazeFactoryTest {

	
	private MazeFactory mazefactory; 
	private MazeConfiguration mazeconfig;
	private StubOrder stuborder;
	private int level;

	
	/**
	 * all required call prior to testing maze
	 * Change value of level to test different types of mazes
	 * determinism to test same mazes
	 * send stuborder
	 * wait for thread
	 * @param determinism
	 * 
	 */
	private void mySetup(boolean determinism) {
		level = 1;
		mazefactory = new MazeFactory(determinism);
		stuborder = new StubOrder(level,determinism,Builder.Eller);
		mazefactory.order(stuborder); //send stuborder
		mazefactory.waitTillDelivered(); //instruction said to wait for it.
		mazeconfig = stuborder.getConfiguration();  // stuff here will probably go to the set up
		
	}

	
	/**
	 * checks if values are not null
	 * checks set up method
	 */
	@Test
	public void doesSetupWork(){
		boolean determinism = true;
		mySetup(determinism);	
		assertNotNull(mazeconfig);  // check to see if value is null 
		assertNotNull(stuborder);
		assertNotNull(mazefactory);
	}
	
	/**
	 * check to see that there are no loops and no isolated areas, 
	 * which also implies that there are no rooms as rooms can imply loops
	 */
	@Test
	public void checkMazePerfection(){     
		boolean determinism = true;		
		mySetup(determinism);
		boolean perfect = stuborder.isPerfect();
		assertTrue(perfect);
		
	}
	
	/**
	 * Check dimension of a specific skill level maze
	 * get values from supposed(correct width,height for specific level) skill level values
	 * and compares with actual generated skill values
	 */
	@Test
	public void dimensionCheck() {

		boolean determinism = true; // set determinism to true to test out the constructor code
		mySetup(determinism);

		int level =  stuborder.getSkillLevel();		
		int ReaLwidth = Constants.SKILL_X[level]; //dimension for specific level
		int ReaLheight = Constants.SKILL_Y[level];		
		int testWidth = mazeconfig.getWidth();   //generated dimension
		int testHeight = mazeconfig.getHeight();				
		assertEquals(testWidth, ReaLwidth);		
		assertEquals(testHeight, ReaLheight);
	
	
	
	
	}

	/**
	 * Checks if exit is in maze
	 * gets width from generated maze
	 * getDistanceToExit use loop to loop through maze location to find distance = 1 (see if exit exists)
	 * isExitPoisition to check if a position with distance = 1  is an exit position (check if it's a valid exit)
	 * assertTrue checks if exit is a valid exit
	 * assertEquals checks if exit is 1, exit is less than 1 then exit does not exist
	 * if value is greater than 1 then too many exit in maze
	 */
	@Test
	public void oneExitExist() {
				
		boolean determinism = true; // set determinism to true
		mySetup(determinism);
		int wid = mazeconfig.getWidth();   //generated dimension
		int hei = mazeconfig.getHeight();
		Cells celly = new Cells(wid,hei);
		boolean ayeItworks;
		int exit =0;  //set to zero, if one means theres 1 exit, if two means theres more than 1 exit.
		for(int xaxis = 0; xaxis<mazeconfig.getHeight();xaxis++) { //use height from maze
			for(int yaxis =0; yaxis < mazeconfig.getWidth();yaxis++) { //use width from maze
				if(mazeconfig.getDistanceToExit(xaxis, yaxis) == 1) {  //function to get distance away from exit, if distance = 1 then exit exists
					//System.out.println(mazeconfig.getDistanceToExit(xaxis, yaxis) );
					ayeItworks=celly.isExitPosition(xaxis, yaxis);
					assertTrue(ayeItworks);
					exit++; //also checks in exit is accessible from all location
				}
			}
		}

		
		assertEquals(exit,1); //only one exit should exit
		
}
	
	
	
}