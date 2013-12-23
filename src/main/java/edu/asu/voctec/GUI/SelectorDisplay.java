package edu.asu.voctec.GUI;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.utilities.UtilFunctions;

public class SelectorDisplay<T extends SelectorIcon> extends Component
{
	protected static Image defaultBorder;
	protected static Image highlightedBorder;
	protected static Image correctBorder;
	protected static Image incorrectBorder;
	
	protected BasicComponent[] choiceBorders;
	protected Selector<T> associatedSelector;
	protected ArrayList<T> elements;
	protected int capacity;
	protected int x;
	protected int y;
	
	public class ChoiceListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			System.out.println("Choice Border Clicked.");
			
			int index = Arrays.asList(choiceBorders).indexOf((BasicComponent)this.associatedComponent);
			T icon = elements.get(index);
			//sendToDisplay(element);
		}
	}
	
	static
	{
		try
		{
			defaultBorder = new Image(ImagePaths.SelectorDisplayBorders.DEFAULT);
			highlightedBorder = new Image(
					ImagePaths.SelectorDisplayBorders.HIGHLIGHTED);
			correctBorder = new Image(ImagePaths.SelectorDisplayBorders.CORRECT);
			incorrectBorder = new Image(
					ImagePaths.SelectorDisplayBorders.INCORRECT);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public SelectorDisplay(int x, int y, int capacity)
	{
		super();
		this.x = x;
		this.y = y;
		elements = new ArrayList<>(capacity);
	}
	
	public SelectorDisplay(int x, int y, boolean useDefaults)
	{
		this(x, y, 5);
		if (useDefaults)
		{
			int spacing = 15; // Minimum space between choiceBorders
			
			/*
			 * Create the choice borders for this component, using the default
			 * image and format; relative to this component
			 */
			ArrayList<BasicComponent> borders = generateDefaultFormation(spacing);
			
			// Instantiate choice borders with the values defined above.
			choiceBorders = borders.toArray(new BasicComponent[borders.size()]);
			
			// Setup each choiceBorder
			setupComponents(); // Set screen-relative positions, associate with
								// GUI, and listen for mouse clicks
		}
	}
	
	protected void setupComponents()
	{
		if (choiceBorders != null)
		{
			for (Component component : choiceBorders)
			{
				/*
				 * Associate this component's GUI with each component Note:
				 * because the component has not been associated with the GUI,
				 * the GUI will not render or scale the component, it will only
				 * track the listeners assigned to these components
				 */
				component.associate(associatedGUI);
				
				// Replace relative positioning with absolute positioning
				component.translate(x, y);
				
				// Listen for clicks on all components
			}
		}
	}
	
	protected ArrayList<BasicComponent> generateDefaultFormation(int spacing)
	{
		ArrayList<BasicComponent> borders = new ArrayList<>(5);
		
		// Populate default borders - Make a '5-domino' formation
		// Top left border
		Point relativeLocation = new Point(0, 0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Top right border
		relativeLocation.translate(2 * spacing + 2 * defaultBorder.getWidth(),
				0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Middle Border
		relativeLocation.setLocation(spacing + defaultBorder.getWidth(),
				spacing + defaultBorder.getHeight());
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Bottom right border
		relativeLocation.setLocation(0,
				2 * spacing + 2 * defaultBorder.getHeight());
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Bottom left border
		relativeLocation.translate(2 * spacing + 2 * defaultBorder.getWidth(),
				0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		return borders;
	}
	
	public boolean accept(T selection)
	{
		// TODO handle cases in which the item should not be accepted
		return elements.add(selection);
	}
	
	public void associate(Selector<T> associatedSelector)
	{
		this.associatedSelector = associatedSelector;
	}
	
	public void link(Selector<T> associatedSelector)
	{
		associate(associatedSelector);
		associatedSelector.associate(this);
	}
	
	public boolean sendToSelector(T data)
	{
		if (data != null)
			return associatedSelector.accept(data);
		else
			return false;
	}
	
	@Override
	public void draw(Graphics graphics)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getX()
	{
		return x;
	}
	
	@Override
	public int getY()
	{
		return y;
	}
	
	@Override
	public Rectangle getBounds()
	{
		// TODO calculate width and height based on components
		return new Rectangle(x, y, choiceBorders[1].getX()
				+ choiceBorders[1].getBounds().width - x,
				choiceBorders[1].getY() + choiceBorders[1].getBounds().height
						- y);
	}
	
	@Override
	public void setX(int x)
	{
		// Make choiceBorder positions relative to this component
		UtilFunctions.translateAll(-this.x, -y, choiceBorders);
		
		this.x = x;
		
		// Reset screen-relative positions for all choiceBorders
		UtilFunctions.translateAll(this.x, y, choiceBorders);
	}
	
	@Override
	public void setY(int y)
	{
		// Make choiceBorder positions relative to this component
		UtilFunctions.translateAll(-x, -this.y, choiceBorders);
		
		this.y = y;
		
		// Reset screen-relative positions for all choiceBorders
		UtilFunctions.translateAll(x, this.y, choiceBorders);
	}
	
	@Override
	public boolean resize(int width, int height)
	{
		boolean success = true;
		
		for (Component component : choiceBorders)
		{
			// Make position relative to this component
			component.translate(-x, -y);
			
			// Rescale the component
			success = success && component.rescale(width, height);
			
			// Reset screen-relative position
			component.translate(x, y);
		}
		
		return success;
	}
	
}
