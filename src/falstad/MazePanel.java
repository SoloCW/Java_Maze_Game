package falstad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.RenderingHints;

import generation.CardinalDirection;
import generation.Cells;
import generation.Seg;

/**
 * Add functionality for double buffering to an AWT Panel class.
 * Used for drawing a maze.
 * 
 * @author pk
 *
 */
public class MazePanel extends Panel  {
	/* Panel operates a double buffer see
	 * http://www.codeproject.com/Articles/2136/Double-buffer-in-standard-Java-AWT
	 * for details
	 */
	// bufferImage can only be initialized if the container is displayable,
	// uses a delayed initialization and relies on client class to call initBufferImage()
	// before first use
	private Image bufferImage;  
	private Graphics2D graphics; // obtained from bufferImage, 
	// graphics is stored to allow clients to draw on same graphics object repeatedly
	// has benefits if color settings should be remembered for subsequent drawing operations
	
	Graphics gc;
	MazeController controller;
	Seg seg;

	
	/**
	 * Constructor. Object is not focusable.
	 */
	public MazePanel(MazeController controller) {
		
		//this.gc = getBufferGraphics();
		setFocusable(false);
		bufferImage = null; // bufferImage initialized separately and later
		graphics = null;
		this.controller = controller;
		//System.out.println(gc);
		
		
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	/**
	 * Method to draw the buffer image on a graphics object that is
	 * obtained from the superclass. The method is used in the MazeController.
	 * Warning: do not override getGraphics() or drawing might fail. 
	 */
	public void update() {
		paint(getGraphics());
	}
	

	/**
	 * Draws the buffer image to the given graphics object.
	 * This method is called when this panel should redraw itself.
	 */
	@Override
	public void paint(Graphics g) {
		if (null == g) {
			System.out.println("MazePanel.paint: no graphics object, skipping drawImage operation");
		}
		else {
			
			g.drawImage(bufferImage,0,0,null);	
		}
	}

	public void initBufferImage() {
		bufferImage = createImage(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		if (null == bufferImage)
		{
			System.out.println("Error: creation of buffered image failed, presumedly container not displayable");
		}
	}
	/**
	 * Obtains a graphics object that can be used for drawing.
	 * The object internally stores the graphics object and will return the
	 * same graphics object over multiple method calls. 
	 * To make the drawing visible on screen, one needs to trigger 
	 * a call of the paint method, which happens 
	 * when calling the update method. 
	 * @return graphics object to draw on, null if impossible to obtain image
	 */
	public Graphics getBufferGraphics() {
		if (null == graphics) {
			// instantiate and store a graphics object for later use
			if (null == bufferImage)
				initBufferImage();
			if (null == bufferImage)
				return null;
			graphics = (Graphics2D) bufferImage.getGraphics();
			if (null == graphics) {
				System.out.println("Error: creation of graphics for buffered image failed, presumedly container not displayable");
			}
			// success case
			
			//System.out.println("MazePanel: Using Rendering Hint");
			// For drawing in FirstPersonDrawer, setting rendering hint
			// became necessary when lines of polygons 
			// that were not horizontal or vertical looked ragged
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}
		
		return graphics;
	}
	
	public void setGraphics() {
		gc = getBufferGraphics();
	}
	
	public void setColorToBlack() {
		// gc = getBufferGraphics();
		//System.out.println(gc);
		gc.setColor(Color.black);
	}

	public void setColorToDarkGray() {
		// gc = getBufferGraphics();
		gc.setColor(Color.darkGray);
		
		
	}

	public void setColorToWhite() {
		 gc = getBufferGraphics();
		gc.setColor(Color.white);
		
	}
	public void setColorToRed() {
		 gc = getBufferGraphics();
		gc.setColor(Color.red);
		
	}
	
	
	public void setColorToYellow() {
		 gc = getBufferGraphics();
		gc.setColor(Color.yellow);
	}
	public void setToColorWhiteOrGray(Cells seencells, int x, int y, CardinalDirection curDir) {
		 gc = getBufferGraphics();
		gc.setColor(seencells.hasWall(x,y, curDir) ? Color.white : Color.gray);
	}
	
	public void MazeDrawLine(int a,int b, int c,int d) {
		gc = getBufferGraphics();
		gc.drawLine(a, b, c, d);
		
	}

	public void MazeFillOval(int i, int j, int cirsiz) {
		//gc.fillOval(ctrx-cirsiz/2, ctry-cirsiz/2, cirsiz, cirsiz);
		gc = getBufferGraphics();
		gc.fillOval(i,j,cirsiz,cirsiz);
	}

	public void MazeFillRect(int i, int j, int w, int h) {
		gc = getBufferGraphics();
		gc.fillRect(i, j, w, h);
		
	}

	public void setSegColor(Seg seg) {
		// TODO Auto-generated method stub
		this.seg = seg;
		Color segCol = new Color(seg.col[0],seg.col[1],seg.col[2]);
		gc.setColor(segCol);
		
	}

	public void MazeFillPolygon(int[] xps, int[] yps, int i) {
		gc.fillPolygon(xps, yps, i);
		
	}

	public static int mazeGetRGB(int[] color) {
		Color col =new Color(color[0], color[1], color[2]);
		return col.getRGB();
		//return 0;
	}

	public static int[] newColor(int col) {
		Color newcolor = new Color(col);
		int[] vals = new int[3];
		vals[0] = newcolor.getRed();
		vals[1] = newcolor.getGreen();
		vals[2] = newcolor.getBlue();
		return vals;
	}





}
