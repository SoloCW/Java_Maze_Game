package falstad;

import static org.junit.Assert.*;

import org.junit.Test;

import falstad.Robot.Turn;
import generation.CardinalDirection;
import generation.MazeConfiguration;
import generation.MazeFactory;
import generation.StubMazeController;
import generation.StubOrder;
import generation.Order.Builder;

public class WizardTest{

	private MazeController controller;
	private StubMazeController sController;
	private Wizard robot;
	private StubOrder sOrder;
	private MazeConfiguration mazeConfig;
	private MazeApplication maze;
	private MazeFactory mazefactory;
	private CardinalDirection curDir;
	private BasicRobot basic;
	
	private void mySetup() {
		robot = new Wizard(sController);
		sController = new StubMazeController();
		sOrder = new StubOrder(1, true, Builder.DFS);
		basic = new BasicRobot();
		mazeConfig = sOrder.getConfiguration();
		mazefactory = new MazeFactory(true);
		mazefactory.order(sOrder);
		mazefactory.waitTillDelivered();
		mazeConfig = sOrder.getConfiguration();
		sController.deliver(mazeConfig);
		basic.setBatteryLevel(3000);
		basic.resetOdometer();
		basic.setMaze(sController);
		curDir = CardinalDirection.East;
		robot = new Wizard(sController);
		robot.setRobot(basic);
		robot.setDimensions(mazeConfig.getWidth(),mazeConfig.getHeight());
		robot.setDistance(mazeConfig.getMazedists());
		
	}
	@Test
	public void testObjectIsNotNull() {
		mySetup();
		assertTrue(robot!=null);
	}
	
	@Test
	/**
	 * Checks if set robot is working
	 */
	public void testSetRobot() {
		mySetup();
		assertTrue(robot.getRealRobot() !=null);
		assertTrue(robot.getBattery()!=0);
	}
	
	@Test
	/**
	 * test to check if null they are never set in here.
	 */
	public void testSetDimention() {
		mySetup();
		assertTrue(robot.getHeight() != 0);
		assertTrue(robot.getWidth() != 0);
		assertTrue(robot.getMazeBoard() != null);
	}
	
	@Test
	public void testSetDistance(){
		mySetup();
		assertTrue(robot.getDist() !=null);
		
	}
	
	@Test 
	/**
	 * This function should not move robot or use drive 2 exit
	 * @throws Exception
	 */
	public void checkForExit() throws Exception {
		mySetup();
		robot.checkForExit();
		assertTrue(robot.getBattery() != 0);
		
	}
	/**
	 * Tests Battery being depleted.
	 * @throws Exception
	 */
	@Test
	public void testgGetEnergyConsumption() throws Exception {
		mySetup();
		assertTrue(robot.getEnergyConsumption()==0);
		robot.turnRightMove();
		robot.turnLeftMove();
		assertTrue(robot.getEnergyConsumption()!=0);
	}
/**
 * Runs Drive2Exit and makes sure it returns true.
 * @throws Exception
 */
	@Test
	public void testDrive2Exit() throws Exception {
		mySetup();
		robot.drive2Exit();
		assertTrue(robot.drive2Exit() == true);
	}

	
}