package falstad;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import falstad.Constants.StateGUI;
import generation.Order;
import generation.Order.Builder;

/**
 * Implements the screens that are displayed whenever the game is not in 
 * the playing state. The screens shown are the title screen, 
 * the generating screen with the progress bar during maze generation,
 * and the final screen when the game finishes.
 * @author pk
 *
 */
public class MazeView extends DefaultViewer {

	// need to know the maze model to check the state 
	// and to provide progress information in the generating state
	private MazeController controller ;
	private JButton wallfollower;
	private JButton wizard;
	private JButton pledge;
	
	
	public MazeView(MazeController c) {
		super() ;
		controller = c ;
	}
	
	public void robotChoice(Graphics gc) {
		// produce white background
		gc.setColor(Color.white);
		gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title 
		gc.setFont(largeBannerFont);
		FontMetrics fm = gc.getFontMetrics();
		gc.setColor(Color.red);
		centerString(gc, fm, "MAZE", 100);
		// write the reference to falstad
		gc.setColor(Color.blue);
		gc.setFont(smallBannerFont);
		fm = gc.getFontMetrics();
		centerString(gc, fm, "by Paul Falstad", 160);
		centerString(gc, fm, "www.falstad.com", 190);
		// write the instructions
		gc.setColor(Color.black);
		centerString(gc, fm, "To start, select a driver type.", 250);
		centerString(gc, fm, "1:Manual, 2:Wizard, 3:WallFollower, 4:Pledge", 300);
		centerString(gc, fm, "Version 2.0", 350);
		
	}		

		
	/*gc.setColor(Color.white);
	gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
	// write the title 
	gc.setFont(largeBannerFont);
	FontMetrics fm = gc.getFontMetrics();
	gc.setColor(Color.red);
	centerString(gc, fm, "Select Driver", 100);
	WallFollower = new JButton("WallFollower Driver");
	WallFollower.setBounds(100, 100, 60, 30);
	
	//Wizard = new JButton("Wizard Driver");
	//Pledge = new JButton("Pledge Driver");
	*/


	void redrawTittle(Graphics gc) {
		// produce white background
		//gc.setColor(Color.white);
		//gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title 
		//gc.setFont(largeBannerFont);
		//FontMetrics fm = gc.getFontMetrics();
		//gc.setColor(Color.red);
		//centerString(gc, fm, "MAZE", 100);
		// write the reference to falstad
	//	gc.setColor(Color.blue);
	//	gc.setFont(smallBannerFont);

		//label.setText("THIS SUCKS");
	//	jp.add(label);
	//	jp.setBackground(Color.RED);
	//	jp.setVisible(true);
	//	jp.repaint();
		/*JFrame jf = new JFrame();
		Panel jp = controller.getPanel();
		JComboBox box = new JComboBox();
		box.addItem("Wizard");
		box.addItem("Pledge");
		box.addItem("WallFollower");
		box.addItem("Explorer");
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new FlowLayout());
		jf.setSize(300, 200);
		jf.add(box);
		jf.setVisible(true);
		jp.add(jf);
		jp.setVisible(true);
		jp.repaint();
	*/
		

			
	}

	@Override
	public void redraw(MazePanel panel, StateGUI state, int px, int py, int view_dx,
			int view_dy, int walk_step, int view_offset, RangeSet rset, int ang) {



		switch (state) {
		//case ROBOT_TITLE:
		//	robotChoice(gc);
		//	break;
		case STATE_TITLE:
			redrawTitle(panel.getBufferGraphics());
			break;
		case STATE_GENERATING:
			redrawGenerating(panel.getBufferGraphics());
			break;
		case STATE_PLAY:
			// skip this one
			break;
		case STATE_FINISH:
			redrawFinish(panel.getBufferGraphics());
			break;
		}
	}
	
	private void dbg(String str) {
		System.out.println("MazeView:" + str);
	}
	
	// 
	
	
	/**
	 * Helper method for redraw to draw the title screen, screen is hard coded
	 * @param  gc graphics is the off screen image
	 */
	
	void redrawTitle(Graphics gc) {
		// produce white background
		gc.setColor(Color.white);
		gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title 
		gc.setFont(largeBannerFont);
		FontMetrics fm = gc.getFontMetrics();
		gc.setColor(Color.red);
		centerString(gc, fm, "MAZE", 50);
		// write the reference to falstad
		gc.setColor(Color.blue);
		gc.setFont(smallBannerFont);
		fm = gc.getFontMetrics();
		centerString(gc, fm, "by Paul Falstad", 70);
		centerString(gc, fm, "www.falstad.com", 90);
		// write the instructions
		gc.setColor(Color.black);
		//centerString(gc, fm, "To start, select a skill level.", 130);
		//centerString(gc, fm, "(Press a number from 0 to 9,", 150);
		//centerString(gc, fm, "or a letter from A to F)", 170);
		//centerString(gc, fm, "Version 2.0", 190);
		Panel pan = controller.getPanel();
		//Driver option
		JPanel jp = new JPanel();
		gc.setColor(Color.RED);
		centerString(gc, fm, "Chose a driver", 190);
		jp.setBackground(Color.WHITE);
		jp.setLocation(145, 195);
		jp.setLayout(new FlowLayout());
		jp.setSize(130, 30);
		String[] items = {"Wizard","Pledge","WallFollower","Explorer","Manual"};
		JComboBox<String> comb = new JComboBox<>(items);
		jp.add(comb);
		jp.setVisible(true);
		pan.add(jp);
		
		//Algorithm Option
		JPanel jp1 = new JPanel();
		
		gc.setColor(Color.RED);
		centerString(gc, fm, "Chose a algorithm", 260);
		jp1.setBackground(Color.WHITE);
		jp1.setLocation(145, 265);
		jp1.setLayout(new FlowLayout());
		jp1.setSize(130, 30);
		String[] items1 = {"DFS","Eller","Prim"};
		JComboBox<String> comb1 = new JComboBox<>(items1);
		jp1.add(comb1);
		jp1.setVisible(true);
		pan.add(jp1);
		
		//Skill option
		
		JPanel jp3 = new JPanel();
		gc.setColor(Color.RED);
		centerString(gc, fm, "Select skill Level", 130);
		jp3.setBackground(Color.WHITE);
		jp3.setLocation(145, 130);
		jp3.setLayout(new FlowLayout());
		jp3.setSize(130, 30);
		String[] items2 = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		JComboBox<String> comb2 = new JComboBox<>(items2);
		jp3.add(comb2);
		jp3.setVisible(true);
		pan.add(jp3);
		
		
		
		
		
		//selected
		comb.setLightWeightPopupEnabled (false);
		comb1.setLightWeightPopupEnabled (false);
		comb2.setLightWeightPopupEnabled (false);
		
		//Start Button
		JPanel jp2 = new JPanel();
		jp2.setBackground(Color.WHITE);
		jp2.setLocation(145, 295);
		jp2.setSize(130, 30);
		JButton start = new JButton();
		start.setText("Start");
		
		//Button functionality
		start.addActionListener(e-> {
	        if(comb1.getSelectedIndex() != -1 && comb.getSelectedIndex() != -1) {
	        		int driverPicked =  comb.getSelectedIndex();
	        		String algoPicked = (String) comb2.getSelectedItem();
	            //controller.setMazeCont(driverPicked,algoPicked);

	            pan.removeAll();
	            //jp2.removeComponentListener((ComponentListener) start);
	   
	            pan.repaint();
	            Builder itemPicked;

	            switch(algoPicked) {
	            case "DFS":
	            		itemPicked = Builder.DFS;
	            		break;
	            case "Eller":
	            		itemPicked = Builder.Eller;
	            		break;
	            case "Prim":
	            		itemPicked = Builder.Prim;
	            		break;
	            	default:
	            		itemPicked = Builder.DFS;
	            		break;
	            
	            }

	            controller.picked=driverPicked;

	            
	            
	            
	            controller.setSkillLevel(comb2.getSelectedIndex());
	            controller.setState(StateGUI.STATE_GENERATING);
	            controller.setBuilder(itemPicked);
	            controller.switchToGeneratingScreen();
	           // controller.setMazeCont(driverPicked,algoPicked);
	      
	            
	        }
	          else {
	            System.out.println("Not selected");
	          }
		});
		
		
		
		
		jp2.add(start);
		pan.add(jp2);
		
		
		
		
		
		
		
		
		
		pan.paintComponents(gc);
		
		
		
	}
	/**
	 * Helper method for redraw to draw final screen, screen is hard coded
	 * @param gc graphics is the off screen image
	 */
	void redrawFinish(Graphics gc) {
		// produce blue background
		gc.setColor(Color.blue);
		gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title 
		gc.setFont(largeBannerFont);
		FontMetrics fm = gc.getFontMetrics();
		gc.setColor(Color.yellow);
		centerString(gc, fm, "You won!", 100);
		// write some extra blufb
		gc.setColor(Color.orange);
		gc.setFont(smallBannerFont);
		fm = gc.getFontMetrics();
		centerString(gc, fm, "Congratulations!", 160);
		centerString(gc, fm, "Path length :" +
		Integer.toString(BasicRobot.getPathLength()), 180);
		centerString(gc, fm, "Battery left :" +
		Float.toString(BasicRobot.getBatteryLevelGame()), 200);
		// write the instructions
		gc.setColor(Color.white);
		centerString(gc, fm, "Hit any key to restart", 300);
	}

	/**
	 * Helper method for redraw to draw screen during phase of maze generation, screen is hard coded
	 * only attribute percentdone is dynamic
	 * @param gc graphics is the off screen image
	 */
	void redrawGenerating(Graphics gc) {
		// produce yellow background
		gc.setColor(Color.yellow);
		gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title 
		gc.setFont(largeBannerFont);
		FontMetrics fm = gc.getFontMetrics();
		gc.setColor(Color.red);
		centerString(gc, fm, "Building maze", 150);
		gc.setFont(smallBannerFont);
		fm = gc.getFontMetrics();
		// show progress
		gc.setColor(Color.black);
		if (null != controller)
			centerString(gc, fm, controller.getPercentDone()+"% completed", 200);
		else
			centerString(gc, fm, "Error: no controller, no progress", 200);
		// write the instructions
		centerString(gc, fm, "Hit escape to stop", 300);
	}
	
	private void centerString(Graphics g, FontMetrics fm, String str, int ypos) {
		g.drawString(str, (Constants.VIEW_WIDTH-fm.stringWidth(str))/2, ypos);
	}

	final Font largeBannerFont = new Font("TimesRoman", Font.BOLD, 48);
	final Font smallBannerFont = new Font("TimesRoman", Font.BOLD, 16);

}
