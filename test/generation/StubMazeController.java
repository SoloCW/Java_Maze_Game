package generation;

import falstad.BasicRobot;
import falstad.Constants.StateGUI;
import falstad.MazeController.UserInput;
import falstad.ManualDriver;
import falstad.MazeController;

public class StubMazeController extends MazeController {

	public StubMazeController() {
		super( );
		// TODO Auto-generated constructor stub
	}
	 
	 public StubMazeController( Builder builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}
	 
	 
	 public StubMazeController(  String filename) {
		super( filename);
		// TODO Auto-generated constructor stub
	
		}
	 @Override
	 public boolean keyDown(UserInput key, int value) {
				operateGameInPlayingState(key);
			return true;
		} 
	  
	@Override
	protected void setState(StateGUI newState){
		
	}
	private void slowedDownRedraw(){
		
	}
	public void switchToGeneratingScreen() {
		
	}
	public void switchToPlayingScreen() {
		
	}
	
	public void switchToTitleScreen(boolean cancelOrder) {
		
	}
	protected void switchToFinishScreen() {
		
	}
}